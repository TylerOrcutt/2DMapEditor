package Engine;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL4;

import Shaders.ShaderProgram;

public class SpriteRenderer {
public static GL2 gl;
public static ShaderProgram shaderProgram;
public static final int COORDS_PER_VERTEX=3;


public static int cubeVAOID[];
public static int cubeVBOID[];
public static FloatBuffer cubeData;
public static final short drawOrder[] = { 0, 1, 2, 0, 2, 3 }; 

public static int vPosition;
public static int vPosx;
public static int vPosy;
public static int vScalex;
public static int vScaley;
public static int tPosx;
public static int tPosy;
public static int tScalex;
public static int tScaley;
public static int uMVPmatrix;

static float viewMatrix[];
public static void init(GL2 gl,ShaderProgram shaderProgram){
		SpriteRenderer.gl=gl;
		SpriteRenderer.shaderProgram = shaderProgram;
		gl.glUseProgram(shaderProgram.id());
		
	
		gl.glGetUniformLocation(vPosx, "vposx");
		gl.glGetUniformLocation(vPosy, "vposy");
		gl.glGetUniformLocation(vScalex, "vScaleX");
		gl.glGetUniformLocation(vScaley, "vScaleY");
		
		gl.glGetUniformLocation(tPosx, "tposx");
		gl.glGetUniformLocation(tPosy, "tposy");
		gl.glGetUniformLocation(tScalex, "tScaleX");
		gl.glGetUniformLocation(tScaley, "tScaleY");
		gl.glGetUniformLocation(uMVPmatrix, "uMVPMatrix");
		vPosition = gl.glGetAttribLocation(shaderProgram.id(), "vPosition");
		
		
        cubeVAOID = new int[1];
	//	gl.glGenVertexArrays(1, cubeVAOID, 0);
		//gl.glBindVertexArray(cubeVAOID[0]);
	
	
		cubeVBOID = new int[1];
		gl.glGenBuffers(1, cubeVBOID,0);
		gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, cubeVBOID[0]);
		
		gl.glVertexAttribPointer(vPosition, COORDS_PER_VERTEX,gl.GL_FLOAT, false,  0,0);
		gl.glEnableVertexAttribArray(vPosition);
		
		
		cubeData= genVertexBuffer(1, 1);
		gl.glBufferData(gl.GL_ARRAY_BUFFER, cubeData.capacity()*Float.BYTES, cubeData, GL4.GL_STATIC_DRAW);
		
		
		System.out.println(cubeVAOID[0]);
		//gl.glVertexAttribPointer(arg0, arg1, arg2, arg3, arg4, arg5);
		
		
		
		 viewMatrix = new float[16];
		for(int i=0;i<16;i++){
			viewMatrix[i]=0.f;
		}
		
	
		gl.glProgramUniformMatrix4fv(uMVPmatrix, 1, 0, false, viewMatrix, 0);
	
	
	
}
public static void Draw(GL2 gl){
	gl.glEnableVertexAttribArray(vPosition); 
	gl.glUniform1f(vPosx, 0);
	gl.glUniform1f(vPosy, 0);
	gl.glUniform1f(vScalex, 100);
	gl.glUniform1f(vScaley, 100);
	gl.glDrawArrays(GL.GL_TRIANGLE_STRIP, 0, cubeData.capacity()/COORDS_PER_VERTEX);
}

public static FloatBuffer genVertexBuffer(float renderWidth,float renderHeight){
    float recCoords[]=  { 0.f, 0.f, 0.0f,
            renderWidth, 0.f, 0.0f,
            0.f,  renderHeight, 0.0f,
            renderWidth,  renderHeight, 0.0f
    };
    FloatBuffer buf;
    ByteBuffer bb = ByteBuffer.allocateDirect(
            // (# of coordinate values * 4 bytes per float)
            recCoords.length * 4);
    bb.order(ByteOrder.nativeOrder());
    buf = bb.asFloatBuffer();
    buf.put(recCoords);
    buf.position(0);
    return buf;

}
}
