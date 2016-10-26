package Engine;

import com.jogamp.opengl.GL2;

import Camera.Camera;

public class Sprite  {
 public SpriteSheet spriteSheet;
	public float x,y;
	public float width=32,height=32;
	public int imgx,imgy;
 
   public Sprite(SpriteSheet sp){
		this.spriteSheet=sp;
		x=0;
		y=0;
		imgx=0;
		imgy=0;
	}
   
   
   public void move(float x, float y){
	   this.x=x;
	   this.y=y;
   }
   public float getX(){
	   return x;
   }
   public float getY(){
	   return y;
   }
   public void setImgLoc(int x,int y){
	   imgx=x;
	   imgy=y;
   }
   public void Draw(GL2 gl,SpriteRenderer sr, Camera camera,float scale,float offset){
	   if(spriteSheet!=null){
	   spriteSheet.draw(gl,sr, (offset+(x+-camera.getX())*scale), (y-camera.getY())*scale, width*scale, height*scale, imgx, imgy);
	   }else{
		   float cy = (y-camera.getY())*scale;
		   float cx = (offset+(x+-camera.getX())*scale);
 
		   
		   sr.toggleUseTexture();
		   sr.Draw(gl, cx, cy, width*scale, height*scale, 0, 0,0,0);
		   sr.toggleUseTexture();
	   }
   }
   
   public void Draw(GL2 gl,SpriteRenderer sr,float scale){
	   if(spriteSheet!=null){
		   spriteSheet.draw(gl,sr, x*scale , y*scale , width*scale, height*scale, imgx, imgy);
		   }else{
			   sr.toggleUseTexture();
			   sr.Draw(gl, x *scale, y*scale , width*scale, height*scale, imgx, imgy,0,0);
			   sr.toggleUseTexture();
		   }
   }
   
   public void Draw(GL2 gl,SpriteRenderer sr,float scale,float offsetx){
	   if(spriteSheet!=null){
		   spriteSheet.draw(gl,sr, (x*scale)+offsetx , y*scale , width*scale, height*scale, imgx, imgy);
		   }else{
			  sr.toggleUseTexture();
			   sr.Draw(gl, x *scale, y*scale , width*scale, height*scale, imgx, imgy,0,0);
			   sr.toggleUseTexture();
		   }
   }
   public void Draw(GL2 gl,SpriteRenderer sr){
	  sr.toggleUseTexture();
	   sr.Draw(gl, x , y , width, height, imgx, imgy,0,0);
	   sr.toggleUseTexture();
   }
   public void Update(){
	   
   }
   public void resize(float width,float height){
	   this.width=width;
	   this.height=height;
   }
	
	
	
	
}
