package Engine;

import java.util.ArrayList;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL3;

import Camera.Camera;

public class SizedMap extends Map{
  public Sprite[][] mapData;
  float width,height;
  
	
	public SizedMap(int width,int height){
	this.width=width;
	this.height=height;
		mapData = new Sprite[width][];
		for(int i=0;i<width;i++){
			mapData[i] = new Sprite[height];
			for(int j=0;j<height;j++){
				mapData[i][j] = new Sprite(null);
				mapData[i][j].move(i*32, j*32);
			}
		}
		
		
	}
	public void Draw(GL2 gl,Camera cam,float xoffset){
		gl.glColor3f(1,1,1);
		float x = cam.getX();
		float y = cam.getY();
		if(x<=0){
			x=0;
		}else{
			x/=32;
					
		}
		if(y<=0){
			y=0;
		}else{
			y/=32;
					
		}
		for(int i=(int)(x);i<mapData.length && i*32< cam.getWidth();i++){
			for(int j=(int)(y);j<mapData[i].length && j*32 <cam.getHeight();j++){
				mapData[i][j].x = ((i*32) + xoffset)-cam.getX(); 
				mapData[i][j].y = (j*32) -  cam.getY();
				mapData[i][j].Draw(gl, 1);
				
			}
			
		}
		
	}
	public void MousePress(float x, float y,int button){
		if(button==1){
			if(Engine.sp==null){
				return;
			}
			x/=32;
			y/=32;
			if(x<0 || x>width){
				return;
			}
			if(y<0 || y>height){
				return;
			}
			ArrayList<selection> selected = Engine.spriteFrame.sp.getSelected();
			 if(selected.size()==0){return;}
			mapData[(int)x][(int)y].spriteSheet=Engine.sp;
			selection sel =  selected.get(0);
			mapData[(int)x][(int)y].setImgLoc((int)sel.selectionX/32,(int)sel.selectionY/32);
			
		}
		
	}
}
