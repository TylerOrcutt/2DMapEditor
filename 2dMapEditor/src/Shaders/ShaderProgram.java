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

		
	    ShaderCode vertexShader = ShaderCode.create(gl, gl.GL_VERTEX_SHADER, this.getClass(), ".", null, "vertexshader", "glsl", null, true);
	    
	    ShaderCode fragmentShader = ShaderCode.create(gl, gl.GL_FRAGMENT_SHADER, this.getClass(), ".", null, "fragmentshader", "glsl", null, true);
	    program = new  com.jogamp.opengl.util.glsl.ShaderProgram();
	    program.add(vertexShader);
	    program.add(fragmentShader);
	    program.link(gl, System.out);
	   //program.useProgram(gl, true);
	  gl.glUseProgram(program.id());
		
  //  System.out.println(gl.glGetAttribLocation(program.id(), "vPosition")); 
    //System.out.println(gl.glGetUniformLocation(program.id(),"vposy")); 	
	}
	
	public static String[] getProgramSource(String file){
	String[] src;
	
	 System.out.println("Reading shader File.");
		try{
	    String line;
	    BufferedReader reader = new BufferedReader(new FileReader(new File(file)));
	    //System.out.println("Lines: " + reader.lines().count());
	    int i=0;
	    src= new String[(int)reader.lines().count()];
	    reader.close();
	    reader = new BufferedReader(new FileReader(new File(file)));
	  //line=reader.readLine();
		//System.out.println(line);
	    
	    while((line=reader.readLine())!=null){
	    	src[i]=line;
	 //   	System.out.println(src[i]);
	    	i++;
	  	//  line=reader.readLine();
	    }
	    
	    reader.close();
	    return null;
	    ///loadProgram(type, src);
	}catch(Exception E){

			return null;
	}
	
	//return null;
	}
	
	public int loadProgram(int type, String []source){
		/*System.out.println("Loading shader...");
		int shaderid=gl.glCreateShader(type);
		gl.glShaderSource(shaderid, 0, source,  (int[])null, 0);
		gl.glCompileShader(shaderid);
*/
		return -1;	
	}
	public int id(){
		return program.id();
	}

}
