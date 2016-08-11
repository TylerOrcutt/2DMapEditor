package Engine;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.annotation.Documented;
import java.util.ArrayList;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL3;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.glsl.ShaderCode;

import Camera.Camera;
import Shaders.ShaderProgram;


public class Engine {

	static ShaderProgram shaders;
  public static int width,height;

  public static SpriteSheet sp;
  public static Sprite sprite;
 
  public static Camera camera;
  public static ArrayList<Sprite> sprites;
  public static Grid grid;
  public static boolean drawGrid=true;
  public static boolean ctrlDown=false;
  public static float scale=1.f;
  public static float centerX=0.f;
  public static float centerY=0.f;
  public static float brushSize=16.f;
  public static boolean running =true;
  public static boolean didInit=false;
  public static boolean initEngine() {


 

 
  sprites  = new ArrayList<Sprite>();
  grid= new Grid();   
		
  camera = new Camera();
    
	 
	 
	return true;
}
  public static void initAssets(GLAutoDrawable drawable){
	  GL2 gl = drawable.getGL().getGL2();

	    sp= new SpriteSheet(gl,"images/sp2.png", 10, 10); 
  }
  public static void initShaders(GLAutoDrawable drawable){
	  GL2 gl = drawable.getGL().getGL2();
	   while(SpriteRenderer.vPosition<0){
		 	 shaders = new ShaderProgram(gl,"vertexshader.glsl","fragmentshader.glsl");
			     SpriteRenderer.init(gl, shaders);
			     
			     gl.glEnable(GL2.GL_DEPTH_TEST); 
			     gl.glDepthFunc(GL2.GL_LEQUAL);   
			     gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);  
			     gl.glShadeModel(GL2.GL_SMOOTH); 
			     gl.glClearDepth(1.0f);  
	 }
  }
 
public static void resize(GLAutoDrawable drawable,int x,int y, int width,int height){
	if(height<=0 || width<=0){
		return ;
	}
	 GL2 gl = drawable.getGL().getGL2();
	 if(gl==null){
		 return;
	 }
	

	System.out.println("Resize");

   Engine.width=width;
    Engine.height=height;
    
    camera.resize(width, height);
    camera.orient(centerX, centerY);

  SpriteRenderer.Resize(gl, width, height);
    
     grid.resize(width, height);
  System.out.println("Total Sprites: "+sprites.size());
 
}
public static void Render(GLAutoDrawable drawable){
	GL2 gl = drawable.getGL().getGL2();
 
	//System.out.println("Draw");
	 gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); 
 
    for (Sprite sprite : sprites) {
		sprite.Draw(gl, camera,scale);
	}
    if(drawGrid){
    
   grid.Draw(gl);
    }
 
}
public static void KeyPress(KeyEvent e){
	if(e.isControlDown()){
	    Engine.ctrlDown=true;
	   // System.out.println("Ctrl down");
	}
}
public static void KeyRelease(KeyEvent e){
 
	if(e.getKeyChar()=='w'){
     camera.move(camera.getX(), camera.getY()-32);
    centerY-=32;
   
	}
	
	if(e.getKeyChar()=='s'){
	     camera.move(camera.getX(), camera.getY()+32);
	     centerY+=32;
		}
	if(e.getKeyChar()=='a'){
	     camera.move(camera.getX()-32, camera.getY());
	     centerX-=32;
		}
	if(e.getKeyChar()=='d'){
	     camera.move(camera.getX()+32, camera.getY());
	     centerX+=32;	
	}
	if(!e.isControlDown()){
	    Engine.ctrlDown=false;
	   // System.out.println("Ctrl up");
	}
	System.out.println("key release");
}

public static void MousePress(MouseEvent e){
	System.out.println("Button" +e.getButton()+ "    mouseX: "+ ( e.getX()+camera.getX()) + "   MouseY: " + (e.getY()+camera.getY()));
	
	if(e.getButton()==1){
	float mouseX = (e.getX()/scale)+camera.getX();
    float mouseY = (e.getY()/scale)+camera.getY();
    float posx=32.f;
		float posy=0.f;

	    if(brushSize<32.f){
	    	 posx=brushSize*(float)Math.floor(mouseX/brushSize);
			 posy=brushSize*(float)Math.floor(mouseY/brushSize);


	    }else{
		  posx=32.0f*(float)Math.floor(mouseX/32);
		  posy=32.0f*(float)Math.floor(mouseY/32);

	    }
	Sprite s = new Sprite(sp);
	s.setImgLoc(0, 1);
	s.move(posx, posy);
	s.resize(brushSize, brushSize);
	sprites.add(s);
	}
	if(e.getButton()==3){
		float mouseX = (e.getX()/scale)+camera.getX();
	    float mouseY = (e.getY()/scale)+camera.getY();
	    float posx=32.f;
		float posy=0.f;

	    if(brushSize<32.f){
	    	 posx=brushSize*(float)Math.floor(mouseX/brushSize);
			 posy=brushSize*(float)Math.floor(mouseY/brushSize);


	    }else{
		  posx=32.0f*(float)Math.floor(mouseX/32);
		  posy=32.0f*(float)Math.floor(mouseY/32);

	    }
		for (int i=0;i<sprites.size();i++) {
	    	
			if(sprites.get(i).getX()==posx && sprites.get(i).getY()==posy){
			  sprites.remove(i);
			  break;
			}
		}
		
		
	}
}
public static void mouseWheelMove(MouseWheelEvent e){
	if(ctrlDown){
		//System.out.println(e.getWheelRotation());
		
		if(e.getWheelRotation()==1){ //zoomout
			if(scale>.25){
				scale-=.25f;
				grid.generateGrid();
			    camera.orient(centerX, centerY);
			}
		}
	if(e.getWheelRotation()==-1){ //zoomin
		if(scale<10){
			scale+=.25f;
			grid.generateGrid();
		    camera.orient(centerX, centerY);
		}
			
		}
	}
	
}

}
