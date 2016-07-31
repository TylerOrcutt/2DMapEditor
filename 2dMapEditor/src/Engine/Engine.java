package Engine;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
public static boolean initEngine(GLAutoDrawable drawable) {
	GL2 gl = drawable.getGL().getGL2();

	//  gl.glShadeModel(GL2.GL_FLAT);
	
	 // gl.glBindFramebuffer(gl.GL_FRAMEBUFFER, 0);
	 // gl.glDrawBuffer(gl.GL_BACK);
     shaders = new ShaderProgram(gl,"vertexshader.glsl","fragmentshader.glsl");
     SpriteRenderer.init(gl, shaders);
  //  gl.glEnable(gl.GL_BLEND);
   // gl.glEnable(gl.GL_TEXTURE_2D);
     gl.glEnable(GL2.GL_DEPTH_TEST); // enables depth testing
     gl.glDepthFunc(GL2.GL_LEQUAL);  // the type of depth test to do
     gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST); // best perspective correction
     gl.glShadeModel(GL2.GL_SMOOTH); 
     gl.glClearDepth(1.0f);  
	//gl.glOrtho(0,  width, height, 0, 0,1);

    sp= new SpriteSheet(gl,"images/sp2.png", 10, 10); 
    //sprite = new Sprite(sp);
    //sprite.setImgLoc(0, 1);
    sprites  = new ArrayList<Sprite>();
    camera = new Camera();
	return true;
}
private static void getAllFiles(File curDir) {

    File[] filesList = curDir.listFiles();
    for(File f : filesList){
        //if(f.isDirectory())
          //  getAllFiles(f);
        if(f.isFile()){
            System.out.println(f.getName());
        }
    }

}
public static void resize(GLAutoDrawable drawable,int x,int y, int width,int height){
	GL2 gl = drawable.getGL().getGL2();

	System.out.println("Resize");

   Engine.width=width;
    Engine.height=height;
    camera.resize(width, height);

    SpriteRenderer.Resize(gl, width, height);

}
public static void Render(GLAutoDrawable drawable){
	GL2 gl = drawable.getGL().getGL2();
	//System.out.println("Draw");
	 gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); 
 
    for (Sprite sprite : sprites) {
		sprite.Draw(gl, camera);
	}
 
}

public static void KeyRelease(KeyEvent e){
 
	if(e.getKeyChar()=='w'){
     camera.move(camera.getX(), camera.getY()-32);
	}
	
	if(e.getKeyChar()=='s'){
	     camera.move(camera.getX(), camera.getY()+32);
		}
	if(e.getKeyChar()=='a'){
	     camera.move(camera.getX()-32, camera.getY());
		}
	if(e.getKeyChar()=='d'){
	     camera.move(camera.getX()+32, camera.getY());
		}
}

public static void MousePress(MouseEvent e){
	System.out.println("Button" +e.getButton()+ "    mouseX: "+ ( e.getX()+camera.getX()) + "   MouseY: " + (e.getY()+camera.getY()));
	
	if(e.getButton()==1){
	float mouseX = e.getX()+camera.getX();
    float mouseY = e.getY()+camera.getY();
	float posx=32.0f*(float)Math.floor(mouseX/32);
	float posy=32.0f*(float)Math.floor(mouseY/32);
	Sprite s = new Sprite(sp);
	s.setImgLoc(0, 1);
	s.move(posx, posy);
	sprites.add(s);
	}
	if(e.getButton()==3){
		float mouseX = e.getX()+camera.getX();
	    float mouseY = e.getY()+camera.getY();
		float posx=32.0f*(float)Math.floor(mouseX/32);
		float posy=32.0f*(float)Math.floor(mouseY/32);
	    for (int i=0;i<sprites.size();i++) {
	    	
			if(sprites.get(i).getX()==posx && sprites.get(i).getY()==posy){
			  sprites.remove(i);
			  break;
			}
		}
		
		
	}
}

}
