package Engine;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.math.Matrix4;
import com.jogamp.opengl.util.PMVMatrix;

import Shaders.ShaderProgram;

public class SpriteRenderer {
public static GL2 gl;
public static ShaderProgram shaderProgram;
public static final int COORDS_PER_VERTEX=3;


public static int cubeVAOID[];
public static int cubeVBOID[];
public static FloatBuffer cubeData;
public static FloatBuffer TextureData;

public static int vPosition;
public static int vPosx;
public static int vPosy;
public static int vScalex;
public static int vScaley;
public static int tPosx;
public static int tPosy;
public static int tScalex;
public static int tScaley;
public static int mTextLoc;
public static int mSamplerLoc;
public static int useTexture;
public static boolean utexture=true;



static float viewMatrix[];
public static void init(GL2 gl,ShaderProgram shaderProgram){
		SpriteRenderer.gl=gl;
		gl.glEnable(gl.GL_TEXTURE_2D);
		   gl.glEnable(gl.GL_BLEND);
		   gl.glBlendFunc(gl.GL_ONE, gl.GL_ONE_MINUS_SRC_ALPHA);
		SpriteRenderer.shaderProgram = shaderProgram;
		gl.glUseProgram(shaderProgram.id());
	  gl.glEnableClientState(gl.GL_VERTEX_ARRAY);
	 	  gl.glEnable(GL2.GL_DEPTH_TEST);
	vPosx=gl.glGetUniformLocation(shaderProgram.id(), "vposx");
	vPosy=	gl.glGetUniformLocation(shaderProgram.id(), "vposy");
	 vScalex=gl.glGetUniformLocation(shaderProgram.id(), "vScaleX");
	 vScaley=gl.glGetUniformLocation(shaderProgram.id(), "vScaleY");
	tPosx=	gl.glGetUniformLocation(shaderProgram.id(), "tposx");
	tPosy=	gl.glGetUniformLocation(shaderProgram.id(), "tposy");
	tScalex=	gl.glGetUniformLocation(shaderProgram.id(), "tScaleX");
	tScaley=	gl.glGetUniformLocation(shaderProgram.id(), "tScaleY");
	mSamplerLoc=	gl.glGetUniformLocation(shaderProgram.id(), "s_texture");
			useTexture=	gl.glGetUniformLocation(shaderProgram.id(), "useTexture");
	vPosition= gl.glGetAttribLocation(shaderProgram.id(), "vPosition");
    mTextLoc= gl.glGetAttribLocation(shaderProgram.id(), "a_texCoord");
cubeData= genVertexBuffer(1, 1);


System.out.println(mSamplerLoc);
gl.glEnableVertexAttribArray(vPosition);
	   gl.glVertexAttribPointer(vPosition, 3, gl.GL_FLOAT, false, 12, cubeData);
	 	gl.glDisableVertexAttribArray(vPosition);
	 	
	 	TextureData= genTextureBuffer(1, 1);
	 	gl.glEnableVertexAttribArray(mTextLoc);
	 		   gl.glVertexAttribPointer(mTextLoc, 2, gl.GL_FLOAT, false,0, TextureData);
	 		 	gl.glDisableVertexAttribArray(mTextLoc);

	 			gl.glUniform1i(mSamplerLoc, 0);
           setUseTexture(true);
}
public static void Resize(GL2 gl, float width,float height){
	gl.glUseProgram(shaderProgram.id());
    gl.glEnable(gl.GL_BLEND);
gl.glBlendFunc(gl.GL_ONE, gl.GL_ONE_MINUS_SRC_ALPHA);
 
 
		

gl.glMatrixMode(GL2.GL_PROJECTION);
gl.glLoadIdentity();


gl.glOrtho(0, width, height,0, 0,50);

gl.glMatrixMode(GL2.GL_MODELVIEW);
gl.glLoadIdentity();


 
}

public static void Draw(GL2 gl,float x,float y,float width,float height,float imgx,float imgy,float imgwidth,float imgheight){
	//System.out.println("sprite draw");
	gl.glUseProgram(shaderProgram.id());
 

	 	 gl.glEnableVertexAttribArray(vPosition);

	 	 gl.glEnableVertexAttribArray(mTextLoc);
 gl.glUniform1i(mSamplerLoc, 0);
	   gl.glUniform1f(vPosx, x);
	gl.glUniform1f(vPosy, y);
	gl.glUniform1f(vScalex, width);
	gl.glUniform1f(vScaley, height);
	   gl.glUniform1f(tPosx, imgx);
	gl.glUniform1f(tPosy, imgy);
	gl.glUniform1f(tScalex, imgwidth);
	gl.glUniform1f(tScaley, imgheight);
gl.glDrawArrays(gl.GL_TRIANGLE_STRIP, 0, cubeData.capacity()/3);
gl.glDisableVertexAttribArray(mTextLoc);

gl.glDisableVertexAttribArray(vPosition);

}

public static FloatBuffer genVertexBuffer(float renderWidth,float renderHeight){
    float recCoords[]=  { 0.f, 0.f, 0.0f,
           renderWidth, 0.f, 0.0f,
            0.f,  renderHeight, 0.0f,
            renderWidth,  renderHeight, 0.0f
    };
  
    FloatBuffer buf;
   ByteBuffer bb = ByteBuffer.allocateDirect(
            recCoords.length * 4);
    bb.order(ByteOrder.nativeOrder());
    buf = bb.asFloatBuffer();
    buf.put(recCoords);
    buf.position(0);

    return buf;

}
public static void toggleUseTexture(){
   setUseTexture(!utexture);
	
}
public static void setUseTexture(boolean use){
	utexture=use;
	if(utexture){
	gl.glUniform1i(useTexture, 1);
	}else{
		gl.glUniform1i(useTexture, 0);
	}
}
public static FloatBuffer genTextureBuffer(float spriteWidth, float spriteHeight){
    float textcords[]={
    		0,0,
    		  spriteWidth,0,
    		  0,spriteHeight,
  	 
 	  spriteWidth,spriteHeight,  
              
    };
      ByteBuffer   buff = ByteBuffer.allocateDirect(textcords.length * 4);
      buff.order(ByteOrder.nativeOrder());
     FloatBuffer textVert  = buff.asFloatBuffer();
      textVert .put(textcords);
 
      textVert .position(0);
 return textVert;
}
}
