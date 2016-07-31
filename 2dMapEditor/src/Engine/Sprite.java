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
   public void Draw(GL2 gl, Camera camera){
	   spriteSheet.draw(gl, x-camera.getX(), y-camera.getY(), width, height, imgx, imgy);
	   
   }
   public void Update(){
	   
   }
	
	
	
	
}
