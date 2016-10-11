package Engine;

import java.util.ArrayList;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL3;

public class SizedMap extends Map{
  public Sprite[][] mapData;
  
	
	public SizedMap(int width,int height){
		mapData = new Sprite[width][];
		for(int i=0;i<width;i++){
			mapData[i] = new Sprite[height];
			for(int j=0;j<height;j++){
				mapData[i][j] = new Sprite(null);
				mapData[i][j].move(i*32, j*32);
			}
		}
		
		
	}
	public void Draw(GL2 gl, float x,float y,float xoffset){
		gl.glColor3f(1,1,1);
		for(int i=(int)(x/32);i<mapData.length;i++){
			for(int j=(int)(y/32);j<mapData[i].length;j++){
				mapData[i][j].x = (i*32) + xoffset; 
				mapData[i][j].Draw(gl, 1);
				
			}
			
		}
		
	}
	public void MousePress(float x, float y,int button){
		
	}
}
