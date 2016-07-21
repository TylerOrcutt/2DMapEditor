package Engine;

import java.awt.image.BufferedImage;
import java.io.File;

import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUnurbs;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class SpriteSheet {
	  private int sheetRows=1;
	    private int sheetCols=1;
	    public int width,height;
	    int []textureids=null;
	   

	    public SpriteSheet (String file,int rows, int cols){
	        sheetRows=rows;
	        sheetCols=cols;
	        //this.gl=gl;
	      //  this.context=context;
	      


	    }
	    public  Texture loadTexture(String file){
	    	try{
	        Texture text = TextureIO.newTexture(new File(file), false);
	      //  text.text
	        return text;
	    	}catch(Exception e){
	    		
	    	}
	    	return null;
	    	}


	    public int  []loadGLTexture(BufferedImage bitmap){
	        textureids = new int[1];
	    
	        return textureids;
	    }

	/*
	    public void draw( float x,float y, int  imgx,int imgy){
	        float sx=(1.0f/getTextureWidth())*getSpriteWidth()*imgx;
	        float sy=(1.0f/getTextureHeight())*getSpriteHeight()*imgy;
	        SpriteRenderer.Draw(x,y,sx,sy);
	    }
	*/
	    public void draw( float x,float y,float w,float h, int  imgx,int imgy){

	        float sx=(1.0f/getTextureWidth())*getSpriteWidth()*imgx;
	        float sy=(1.0f/getTextureHeight())*getSpriteHeight()*imgy;
	     //   SpriteRenderer.Draw(x,y,w,h,sx,sy,getActualSpriteWidth(),getActualSpriteHeight());

	    }


	    public int getTextureIDs(){
	        return textureids[0];
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
