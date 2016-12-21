package Props;

import com.jogamp.opengl.GL2;

import Camera.Camera;
import Engine.Sprite;
import Engine.SpriteSheet;
import Engine.Engine;

public class Prop {
	int width,height;
	SpriteSheet sp;
	Sprite [][]propData;
	float zline=0;
	
	public Prop(int width,int height,Sprite[][]data){
		this.width=width;
		this.height = height;
		this.propData=data;
	}
	public Prop(int width,int height){
		this.width=width;
		this.height = height;
		
		propData = new Sprite[width][];
		for(int i=0;i<width;i++){
			propData[i] = new Sprite[height];
			for(int j=0;j<height;j++){
				propData[i][j] = new Sprite(null);
				propData[i][j].move(i*32, j*32);
			}
		}
		
	}
	public void Draw(GL2 gl,float sx,float sy){
		for(int x=0;x<width;x++){
			for(int y=0;y<height;y++){
			propData[x][y].Draw(gl, Engine.spriteRenderer, new Camera(),1, sx, sy);
			}
		}
		
	}
	
	public void Draw(GL2 gl,float sx,float sy,float dwidth,float dheight){
		//this.Draw(gl, sx, sy);
		 
		for(int x=0;x<width;x++){
			
			for(int y=0;y<height;y++){
			//propData[x][y].Draw(gl, Engine.spriteRenderer, new Camera(),1, sx, sy);
			if(propData[x][y].spriteSheet!=null){
				SpriteSheet ss = propData[x][y].spriteSheet;
		//Engine.spriteRenderer.Draw(gl,sx+(x*(dwidth/width)), sy+(y*(dheight/height)), dwidth/width, dheight/height, (1.f/propData[x][y].imgx, propData[x][y].imgy, 
			//	propData[x][y].spriteSheet.getActualSpriteWidth(), propData[x][y].spriteSheet.getActualSpriteHeight());
		
			  ss.draw(gl,Engine.spriteRenderer, sx+(x*(dwidth/width)), sy+(y*(dheight/height)), dwidth/width, dheight/height, propData[x][y].imgx, propData[x][y].imgy);
			}else{
				gl.glColor3f(1, 1, 1);
				Engine.spriteRenderer.Draw(gl, sx+(x*(dwidth/width)), sy+(y*(dheight/height)), dwidth/width, dheight/height, 0, 0, 0, 0);
				
			}
			}
		}
		
	}
	
	public void setSpriteSheet(SpriteSheet s){
		sp=s;
	}
	
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	
	public Sprite[][] getPropData(){
		return propData;
	}
	public void setZLine(float z){
		zline = z;
	}
	public float getZLine(){
		return zline;
	}
	
	

}
