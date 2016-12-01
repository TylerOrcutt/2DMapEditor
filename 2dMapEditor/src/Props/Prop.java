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
	
	
	

}
