package Editor;

import java.util.ArrayList;

import com.jogamp.opengl.GL2;

import Camera.Camera;
import Engine.Engine;

import Engine.Sprite;
import Tools.selection;
import Engine.*;

public class MapRenderer extends Tab {
	  public Sprite[][] mapData;
	  int width,height;
 

public MapRenderer(int width,int height) {
	// TODO Auto-generated constructor stub
	super();
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
	@Override
	public void Draw(GL2 gl) {
	 
		gl.glColor3f(1,1,1);
		float x = super.getCamera().getX();
		float y = super.getCamera().getY();
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
		for(int i=(int)(x);i<mapData.length && (i*32)<= super.getCamera().getWidth();i++){
			for(int j=(int)(y);j<mapData[i].length && (j*32) <=super.getCamera().getHeight();j++){
				mapData[i][j].x = ((i*(32)) );
				mapData[i][j].y = (j*(32)) ;
				if(Tabbar.tabs.size()>1){
				mapData[i][j]. Draw( gl, Engine.spriteRenderer,super.getCamera(),super.getScale(),Engine.spriteFrame.width,Tabbar.height);
				}else{
					mapData[i][j]. Draw( gl, Engine.spriteRenderer,super.getCamera(),super.getScale(),Engine.spriteFrame.width);
						
				}
			}
			
		}
		
	}

	@Override
	public boolean onMouseClick(int button, float x, float y) {
		// TODO Auto-generated method stub
		if(button==1){
			if(Engine.sp==null){
				return false;
			}
			 
			
			x-=Engine.spriteFrame.width;
			x+=super.getCamera().getX()*super.getScale();
			x/=(32*super.getScale());
			if(Tabbar.tabs.size()>1){
			y-=Tabbar.height;
			}
			y+=super.getCamera().getY()*super.getScale();
			y/=32*super.getScale();
		 
			
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

	@Override
	public void Resize(float width, float height) {
		// TODO Auto-generated method stub
		super.Resize(width, height);
	}
	@Override
	public void onScaleChange(float change) {
		// TODO Auto-generated method stub
		if(change<0 && super.getScale()>.25){
			super.setScale(super.getScale()+change);
			
		}
		if(change>0 && super.getScale()<10){
			super.setScale(super.getScale()+change);
		}
		
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

public float getWidth(){
	return width;
}
public float getHeight(){
	return height;
}
}
