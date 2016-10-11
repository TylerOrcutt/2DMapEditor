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
import Editor.SpriteFrame;
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
  public static float brushSize=32.f;
  public static boolean running =true;
  public static boolean didInit=false;
  public static SpriteFrame spriteFrame;
  public static boolean shiftDown=false;
  public static boolean useSizedMap=false;
  public static SizedMap sizedMap;
  public static boolean initEngine() {

  sprites  = new ArrayList<Sprite>();
  grid= new Grid();   
		
  camera = new Camera();
  
	 
	 
	return true;
}
  public static void initAssets(GLAutoDrawable drawable){
	  GL2 gl = drawable.getGL().getGL2();

	    sp= new SpriteSheet(gl,"images/sp2.png", 20, 15); 
	    spriteFrame = new SpriteFrame(sp);  
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
			     gl.glClearColor(0, 0, 0, 1);
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
  spriteFrame.Draw(gl);
  if(!useSizedMap){
    for (Sprite sprite : sprites) {
    	if( sprite.x-camera.getX() >=0){
		sprite.Draw(gl, camera,scale,spriteFrame.width);
    	}
    	}
  }else{
	  if(sizedMap!=null){
		  sizedMap.Draw(gl,camera,spriteFrame.width);
	  }
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
	if(e.isShiftDown()){
	    Engine.shiftDown=true;
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
	if(!e.isShiftDown()){
		shiftDown=false;
	}
	System.out.println("key release");
}

public static void MousePress(MouseEvent e){
	System.out.println("Button" +e.getButton()+ "    mouseX: "+ ( e.getX()-spriteFrame.width+camera.getX()) + "   MouseY: " + (e.getY()+camera.getY()));

	if(e.getButton()==1){
		if(e.getX()>=spriteFrame.width-5  && e.getX()<=spriteFrame.width+5 ){
			spriteFrame.sliderDragged=true;
			return;
		}
		
		if(e.getX()<=spriteFrame.width){
			spriteFrame.sp.onClick(e,shiftDown);
			return;
		}
		
		
		 if(useSizedMap){
			 sizedMap.MousePress(( e.getX()-spriteFrame.width+camera.getX()), e.getY()+camera.getY(), e.getButton());
			 return;
		 }
    float mouseX = (e.getX()-spriteFrame.width/scale)+camera.getX();
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

	ArrayList<selection> selected = spriteFrame.sp.getSelected();
	if(selected.size()<=0){return;}
     float baseX = selected.get(0).selectionX;
	float baseY=selected.get(0).selectionY;
	for(int i=0;i<selected.size();i++){
		 selection sel = selected.get(i);

		Sprite s = new Sprite(sp);
	
			float tposx=posx+(sel.selectionX-baseX)*(brushSize/32);
			
			float tposy=posy+(sel.selectionY-baseY)*(brushSize/32);
		s.move(tposx, tposy);
		
		 s.setImgLoc((int)sel.selectionX/32,(int)sel.selectionY/32);
		s.resize(brushSize, brushSize);
		sprites.add(s);
	}
//	s.setImgLoc((int)spriteFrame.sp.selectionX/32,(int)spriteFrame.sp.selectionY/32);
//	spriteFrame.sp.printList();
 
	}
	if(e.getButton()==3){
	    float mouseX = (e.getX()-spriteFrame.width/scale)+camera.getX();
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
public static void MouseRelease(MouseEvent e){
	if(spriteFrame.sliderDragged){
		ArrayList<selection> selected = spriteFrame.sp.getSelected();
		for(int i=0;i<selected.size();i++){
			 selection sel = selected.get(i);
			 if(sel.selectionX>=spriteFrame.width-5){
				 selected.remove(i);
			 }
		
		}
	}
	spriteFrame.sliderDragged=false;
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
public static void mouseDragged(MouseEvent e) {
	spriteFrame.sliderDrag((float)e.getX(),(float)e.getY());
}
public static boolean mouseMoved(MouseEvent e) {
	if(e.getX()>=spriteFrame.width-5  && e.getX()<=spriteFrame.width+5 ){
	 return true;
	}
	return false;
}
}
