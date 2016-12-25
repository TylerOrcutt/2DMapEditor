package Editor;

import java.util.ArrayList;

import com.jogamp.opengl.GL2;

import Camera.Camera;
import Engine.Engine;

import Engine.Sprite;
import Props.Prop;
import Tools.selection;
import Engine.*;

class propData{
float x,y;
Prop prop;
public propData(){}
public propData(Prop p,float x,float y){
	this.prop=p;
	this.x=x;
	this.y=y;
}
}
public class MapRenderer extends Tab {

ArrayList<propData> props;
int width, height;
public Sprite[][] mapData;

public MapRenderer( ) {
	// TODO Auto-generated constructor stub
	//super();
 props = new ArrayList<>();
 
}

public MapRenderer(int width,int height) {
	// TODO Auto-generated constructor stub
	 
	super();
	props = new ArrayList<>();
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
		float sy=0;
		if(Tabbar.tabs.size()>1){
			sy+=Tabbar.height;
		}
		for(int i = 0;i<props.size();i++){
			propData p =props.get(i); 
			//p.prop.Draw(gl,  p.x-super.getCamera().getX(),p.y-super.getCamera().getX());
		
			p.prop.Draw(gl,super.getCamera(),p.x,p.y,Engine.spriteFrame.width , sy);
		
		}
		
	}

	@Override
	public boolean onMouseClick(int button, float x, float y) {
		// TODO Auto-generated method stub
		if(Engine.editLayer==1){
	        if(Engine.selectedProp>Engine.props.size() || Engine.selectedProp<0 || Engine.props.size()==0){
	        	return false;
	        }
	        System.out.println("Adding prop ID: " + Engine.selectedProp );
	        Prop s = Engine.props.get(Engine.selectedProp);
	     //    Prop p = new Prop(s.getPropData(),s.getWidth() ,s.getHeight(), x+super.getCamera().getX(),y+ super.getCamera().getY());
	        if(Tabbar.tabs.size()>1){
	          y-=Tabbar.height;
	        }
	        x-=Engine.spriteFrame.width;
	        System.out.println("Mouse x: "+ x + "    MouseY:" +y);
	         props.add(new propData(s,x+super.getCamera().getX(),y+ super.getCamera().getY()));
		//	props.add(new propData(props.get(Engine.selectedProp),x+super.getCamera().getX(),y+ super.getCamera().getY());
	        
	         return true;
		}else{
		return super.onMouseClick(button, x, y,width,height,mapData);
	}
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
		String json="{";
		 json+="\"Map\":{";
		 json+="\"width\":\""+width+"\",";
		 json+="\"height\":\""+height+"\",";
		 json+="\"BlockScale\":\"32\",";
		 json+="\"SheetCount\":\""+Engine.sp.size()+"\"";
		 json+="},";
		 json+="\"Blocks\":[";
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				json +="{";		
			 json+="\"x\":\""+i+"\",";
			 json+="\"y\":\""+j+"\",";
		//	 json+="\"width\":\""+mapData[i][j].width+"\",";
		//	 json+="\"height\":\""+mapData[i][j].height+"\",";
			 json+="\"imgx\":\""+mapData[i][j].imgx+"\",";
			 json+="\"imgy\":\""+mapData[i][j].imgy+"\"";
				json +="}";
			 if(width+height>2 && j<=height-1 && i<=width-1){
				 json+=",";
			 }
			}
		}
		json+="],";
      ArrayList<SpriteSheet> sheets = Engine.sp;
 	 json+="\"SpriteSheets\":[";
 	 
      for(int i=0;i<sheets.size();i++){
    	  SpriteSheet s = sheets.get(i);
    	 	 json+="{\"id\":\""+i+"\", \"File\":\""+s.file+"\"}";
    	  if(i<sheets.size()-1){
    		  json+=",";
    	  }
      }
      json+="]";
		if(props.size()>0){
			json+=",\"Props\":[";
			json+="]";
		}
      
      
		json +="}";
		
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
