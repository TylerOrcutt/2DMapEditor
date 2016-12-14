package Editor;

import java.util.ArrayList;

import com.jogamp.opengl.GL2;

import Camera.Camera;
import Engine.Engine;
import Engine.Sprite;
import Tools.selection;

public abstract class Tab {
 private String name;
 private float scale=1.f;
private  Camera cam;
private float camDragx,camDragy;
//public int width,height;
//public Sprite[][] mapData;
public Tab(){
	cam = new Camera();
}
 public void setName(String name){
	 this.name=name;
 }
 public String getName(){
	 return name;
 }
 public Camera getCamera(){
	 return cam;
 }
 public float getScale(){
	 return scale;
 }
 public void setScale(float scale){
	 this.scale = scale;
 }
 
 public Tab(int width,int height) {
		// TODO Auto-generated constructor stub
	 this();
 
	
	 
	}
	public abstract void onScaleChange(float scale);
	
	public abstract void Draw(GL2 gl);	
	public void Draw(GL2 gl,Sprite[][]data){
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
		for(int i=(int)(x);i<data.length ;i++){
			for(int j=(int)(y);j<data[i].length;j++){
				data[i][j].x = ((i*(32)) );
				data[i][j].y = (j*(32)) ;
				if(Tabbar.tabs.size()>1){
				data[i][j]. Draw( gl, Engine.spriteRenderer,cam,scale,Engine.spriteFrame.width,Tabbar.height);
				}else{
					data[i][j]. Draw( gl, Engine.spriteRenderer,cam,scale,Engine.spriteFrame.width);
						
				}
			}
			
		}
	}
	
	
	public abstract boolean onMouseRelease(int button, float x,float y);
	public abstract boolean onMouseClick(int button, float x,float y);
	
	public boolean onMouseClick(int button, float x,float y,float width,float height,Sprite[][]mapData){
		if(button==1){
			if(Engine.sp==null){
				return false;
			}
			 
			
			x-=Engine.spriteFrame.width;
			x+=cam.getX()*scale;
			x/=(32*scale);
			if(Tabbar.tabs.size()>1){
			y-=Tabbar.height;
			}
			y+=cam.getY()*scale;
			y/=32*scale;
		 
			
			if(x<0 || x>=width){
				return false ;
			}
			if(y<0 || y>=height){
				return false;
			}
			
			System.out.println("Button" +button+ "    mouseX: "+ ( x) + "   MouseY: " + (y));

			ArrayList<selection> selected = Engine.spriteFrame.sp.getSelected();
			 if(selected.size()==0){return false;}
			mapData[(int)x][(int)y].spriteSheet=Engine.sp.get(0);
			selection sel =  selected.get(0);
			mapData[(int)x][(int)y].setImgLoc((int)sel.selectionX/32,(int)sel.selectionY/32);
			
		}
		return false;
	}
	
	public abstract boolean onMouseDragged(int button, float x,float y);
	
	
	 public  void Resize(float width,float height){
		 cam.resize(width, height);
	 }
	 public  void onCameraDragStart(float mouseX,float mouseY){
			camDragx=mouseX;
			camDragy=mouseY;
	 }
	public  void cameraDragged(float mouseX,float mouseY){
		float cx = (mouseX-camDragx)*(1.5f/scale);
		camDragx=mouseX;
		float cy = (mouseY - camDragy)*(1.5f/scale);
		camDragy=mouseY;
		cam.move(cam.getX()-cx, cam.getY()-cy);
	}
	public abstract float getWidth();
	public abstract float getHeight();
	public abstract String getType();
	public abstract String generateJSON();
	
}

