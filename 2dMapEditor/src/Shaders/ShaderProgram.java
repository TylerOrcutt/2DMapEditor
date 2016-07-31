package Shaders;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.IntBuffer;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL3;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.util.glsl.ShaderCode;
public class ShaderProgram {
	enum GL_SHADER_TYPE{vertex,fragment};
	

	private GL2 gl;
	private 	    com.jogamp.opengl.util.glsl.ShaderProgram program ;

	public ShaderProgram(GL2 gl,String shaderFile,String fragmentFile){
		this.gl = gl;

		
	    ShaderCode vertexShader = ShaderCode.create(gl, gl.GL_VERTEX_SHADER, this.getClass(), ".", null, "shaders/vertexshader", "glsl", null, true);
	    
	    ShaderCode fragmentShader = ShaderCode.create(gl, gl.GL_FRAGMENT_SHADER, this.getClass(), ".", null, "shaders/fragmentshader", "glsl", null, true);
	    program = new  com.jogamp.opengl.util.glsl.ShaderProgram();
	    program.add(vertexShader);
	    program.add(fragmentShader);
	    program.link(gl, System.out);
	   
 
	  gl.glUseProgram(program.id());
		

 	
	}

	public int id(){
		return program.id();
	}

}
