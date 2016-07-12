package Engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import com.jogamp.opengl.GL3;
import com.jogamp.opengl.GLAutoDrawable;

public class Engine {
	static int shaderProgram;
	static int vertexShader;
	static int fragmentShader;
public static boolean initEngine(GLAutoDrawable drawable) {
	GL3 gl = drawable.getGL().getGL3();
	shaderProgram = gl.glCreateProgram();
	vertexShader = gl.glCreateShader(gl.GL_VERTEX_SHADER);
	try{
	BufferedReader vr = new BufferedReader(new FileReader("vertexshader.glsl"));
	String line;
    System.out.println(vr.lines().count());
    String [] lines= new String[(int)vr.lines().count()];
	System.out.println("Loading Vertex Shader");
	int i=0;
	while((line=vr.readLine()) != null){
		lines[i] = line;
		i++;
	}
	vr.close();
	}catch(Exception e){
 
		return false;
	}
    
	return true;
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
