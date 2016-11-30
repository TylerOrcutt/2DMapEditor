package Editor;

import java.util.ArrayList;

import com.jogamp.opengl.GL2;

import Camera.Camera;
import Engine.Engine;

import Engine.Sprite;
import Props.Prop;
import Tools.selection;
import Engine.*;

public class MapRenderer extends Tab {

ArrayList<Prop> props;
int width, height;
public Sprite[][] mapData;
public MapRenderer( ) {
	// TODO Auto-generated constructor stub
	super();
 
 
}

public MapRenderer(int width,int height) {
	// TODO Auto-generated constructor stub
 super();
 this.width=width;
 this.height = height;
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
	 
		super.Draw(gl,mapData);
		
	}

	@Override
	public boolean onMouseClick(int button, float x, float y) {
		// TODO Auto-generated method stub
		return super.onMouseClick(button, x, y,width,height,mapData);
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

@Override
public boolean onMouseDragged(int button, float x, float y) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean onMouseRelease(int button, float x, float y) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public String getType() {
	// TODO Auto-generated method stub
	return "map";
}
}
