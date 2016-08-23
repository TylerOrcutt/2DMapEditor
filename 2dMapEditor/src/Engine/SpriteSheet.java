package Engine;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.xml.soap.Text;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUnurbs;
import com.jogamp.opengl.util.awt.ImageUtil;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import com.jogamp.opengl.util.texture.awt.AWTTextureIO;

public class SpriteSheet {
	  private int sheetRows=1;
	    private int sheetCols=1;
	    public int width,height;
	   // int []textureids=null;
	  public Texture texture;

	    public SpriteSheet (GL2 gl, String file,int rows, int cols){
	        sheetRows=rows;
	        sheetCols=cols;
	        //this.gl=gl;
	      //  this.context=context;
	        texture= loadTexture(file,gl);


	    }
	    public  Texture loadTexture(String file,GL2 gl){
	    	
	    	
	    	try{
	    	BufferedImage image = ImageIO.read(new File(file));
	   
	             width=image.getWidth();
	             height=image.getHeight();
	             System.out.println("Image width "+ width );
	    
	             
	             Texture texture = AWTTextureIO.newTexture(gl.getGLProfile(), image, false);

	        return texture;
	    	}catch(Exception e){
	    		
	    	}
	    	return null;
	    	}


 
	/*
	    public void draw( float x,float y, int  imgx,int imgy){
	        float sx=(1.0f/getTextureWidth())*getSpriteWidth()*imgx;
	        float sy=(1.0f/getTextureHeight())*getSpriteHeight()*imgy;
	        SpriteRenderer.Draw(x,y,sx,sy);
	    }
	*/
	    public void draw(GL2 gl,float x,float y,float width,float height,float imgx,float imgy ){
                //txt.bind(gl);
              //  txt.getTextureObject()
	    	if(texture!=null){
                texture.bind(gl);
	    	}
	       float sx=(1.0f/getTextureWidth())*getSpriteWidth()*imgx;
	        float sy=(1.0f/getTextureHeight())*getSpriteHeight()*imgy;
	     //   SpriteRenderer.Draw(x,y,w,h,sx,sy,getActualSpriteWidth(),getActualSpriteHeight());
               
	    	 SpriteRenderer.Draw(gl, x, y, width, height,sx,sy,getActualSpriteWidth(),getActualSpriteHeight());
	    }


	
	    public int getTextureWidth(){
	        return width;
	    }
	    public int getTextureHeight(){
	        return height;
	    }
	    public int getSheetCols(){
	        return  sheetCols;
	    }
	    public int getSheetRows(){
	        return sheetRows;
	    }
	    public int getSpriteWidth(){
	        return  width/sheetCols;
	    }
	    public int getSpriteHeight(){
	        return  height/sheetRows;
	    }
	    public float getActualSpriteWidth(){
	        return (1.0f/getTextureWidth())*getSpriteWidth();
	    }
	    public float getActualSpriteHeight(){
	        return (1.0f/getTextureHeight())*getSpriteHeight();
	}
}
