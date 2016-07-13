package Engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL3;
import com.jogamp.opengl.GL4;
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
    String [] lines= new String[(int)vr.lines().count()];
	System.out.println("Loading Vertex Shader");
	int i=0;
	while((line=vr.readLine()) != null){
		lines[i] = line;
		i++;
	}
	vr.close();
	
	gl.glShaderSource(vertexShader, 1,lines, null);
	gl.glCompileShader(vertexShader);

	gl.glAttachShader(shaderProgram, vertexShader);
	gl.glLinkProgram(shaderProgram);
	gl.glUseProgram(shaderProgram);
	gl.glEnable(GL.GL_TEXTURE_2D);
	System.out.println(gl.glGetUniformLocation(shaderProgram,"vposy"));
	}catch(Exception e){
 
		return false;
	}
	
	gl.glEnable(GL.GL_BLEND);
	
    
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
