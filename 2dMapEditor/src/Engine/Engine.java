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
public static boolean initEngine(GLAutoDrawable drawable) {
	GL2 gl = drawable.getGL().getGL2();

	  gl.glShadeModel(GL2.GL_FLAT);
	
	  gl.glBindFramebuffer(gl.GL_FRAMEBUFFER, 0);
	  gl.glDrawBuffer(gl.GL_BACK);
  // shaders = new ShaderProgram(gl,"vertexshader.glsl","fragmentshader.glsl");
   //SpriteRenderer.init(gl, shaders);
    gl.glEnable(gl.GL_BLEND);
    gl.glEnable(gl.GL_TEXTURE_2D);
 
   
    
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
	gl.glViewport(0,0,width,height);
	System.out.println("Resize");
    final float h = (float) width / (float) height;
    
    gl.glViewport(0, 0, width, height);
    gl.glMatrixMode(GL2.GL_PROJECTION);
    gl.glLoadIdentity();
    //GLU glu = new GLU();
   // glu.gluPerspective(45.0f, h, 0.1f, 20.0);
    gl.glMatrixMode(GL2.GL_MODELVIEW);
    gl.glLoadIdentity();
	gl.glOrtho(0,  width, height, 0, 0,1);
	//Matrix.orie
}
public static void Render(GLAutoDrawable drawable){
	GL2 gl = drawable.getGL().getGL2();
	//System.out.println("Draw");
    gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
 ;
  gl.glColor3f(1f, 0f, 0f);
 // SpriteRenderer.Draw(gl);
    gl.glBegin(gl.GL_TRIANGLE_STRIP);               
    gl.glVertex3f(0.0f,0.0f,0.0f);
    gl.glVertex3f(64.0f,1.0f,0.0f);
    gl.glVertex3f(0.0f,64.0f,0.0f);
    gl.glVertex3f(64.0f,64.0f,0.0f);
    gl.glEnd();   
}
}
