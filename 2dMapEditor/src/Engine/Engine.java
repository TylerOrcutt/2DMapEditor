package Engine;

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

import Shaders.ShaderProgram;


public class Engine {

	static ShaderProgram shaders;
  public static int width,height;
  public static SpriteSheet sp;
  public static SpriteSheet sp2;
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
    sp = new SpriteSheet(gl,"images/sp2.png", 1, 1);
    sp2 = new SpriteSheet(gl,"images/sp2.png", 10, 10);
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

    SpriteRenderer.Resize(gl, width, height);

}
public static void Render(GLAutoDrawable drawable){
	GL2 gl = drawable.getGL().getGL2();
	//System.out.println("Draw");
	 gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); 
	 sp.draw(gl,0,0,320,320,0,0);
	 sp2.draw(gl,320,320,320,320, 0,1);

 
}
}
