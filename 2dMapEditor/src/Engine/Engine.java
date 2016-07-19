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
import com.jogamp.opengl.util.glsl.ShaderCode;

import Shaders.ShaderProgram;


public class Engine {

	static ShaderProgram shaders;
public static boolean initEngine(GLAutoDrawable drawable) {
	GL4 gl = drawable.getGL().getGL4();
    shaders = new ShaderProgram(gl,"vertexshader.glsl","fragmentshader.glsl");
    
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
	GL3 gl = drawable.getGL().getGL3();
	gl.glViewport(0,0,width,height);
	//Matrix.orie
}
public static void Render(GLAutoDrawable drawable){
	GL3 gl = drawable.getGL().getGL3();
	
}
}
