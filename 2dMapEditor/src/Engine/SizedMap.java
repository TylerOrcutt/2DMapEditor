package Engine;

import java.util.ArrayList;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL3;

import Camera.Camera;

public class SizedMap extends Map{
  public Sprite[][] mapData;
  int width,height;
  float scale=1.f;
  
	
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
		for(int i=(int)(x);i<mapData.length && i*32*scale< cam.getX()+cam.getWidth();i++){
			for(int j=(int)(y);j<mapData[i].length && j*32*scale <=cam.getY()+cam.getHeight();j++){
				mapData[i][j].x = ((i*(32)) );
				mapData[i][j].y = (j*(32)) ;
				mapData[i][j]. Draw( gl, Engine.spriteRenderer,cam,scale,xoffset);
				
			}
			
		}
		
	}
	public void MousePress(float x, float y,int button){
		if(button==1){
			if(Engine.sp==null){
				return;
			}
			 
			
			x-=Engine.spriteFrame.width;
			x+=Engine.camera.getX()*scale;
			x/=(32*scale);
	 
			y+=Engine.camera.getY()*scale;
			y/=32*scale;
		 
			
			if(x<0 || x>=width){
				return;
			}
			if(y<0 || y>=height){
				return;
			}
			
			System.out.println("Button" +button+ "    mouseX: "+ ( x) + "   MouseY: " + (y));

			ArrayList<selection> selected = Engine.spriteFrame.sp.getSelected();
			 if(selected.size()==0){return;}
			mapData[(int)x][(int)y].spriteSheet=Engine.sp.get(0);
			selection sel =  selected.get(0);
			mapData[(int)x][(int)y].setImgLoc((int)sel.selectionX/32,(int)sel.selectionY/32);
			
		}
		
	}
	public void onScaleChange(float change){
		if(change<0 && scale>.25){
			scale+=change;
			
		}
		if(change>0 && scale<10){
			scale+=change;
		}
		//this.scale= scale;
	}
	public String generateJSON(){
		String json="";
		 json+="\"Map\":{";
		 json+="\"width\":\""+width+"\",";
		 json+="\"height\":\""+height+"\",";
		 json+="}";
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
			 json+="\"Block\":{";
			 json+="\"x\":\""+mapData[i][j].x+"\",";
			 json+="\"y\":\""+mapData[i][j].y+"\",";
			 json+="\"width\":\""+mapData[i][j].width+"\",";
			 json+="\"height\":\""+mapData[i][j].height+"\"";
			 json+="\"imgx\":\""+mapData[i][j].imgx+"\"";
			 json+="\"imgy\":\""+mapData[i][j].imgy+"\"";
			 json+="}";
			 if(width+height>2 && j<height-1 && i<width-1){
				 json+=",";
			 }
			}
		}
		
		return json;
	}
}
