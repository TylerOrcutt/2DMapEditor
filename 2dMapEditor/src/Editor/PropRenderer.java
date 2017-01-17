package Editor;

import java.util.ArrayList;

import com.jogamp.opengl.GL2;

import Engine.SpriteRenderer;
import Engine.SpriteSheet;
import Props.Prop;
import Engine.Engine;
import Engine.Sprite;
public class PropRenderer extends Tab{
float depthy=0;
float startDragY=0;
boolean dragDepthBar=false;
Prop prop;
 
public  PropRenderer(int width,int height) {
		// TODO Auto-generated constructor stub

	    super(width,height);
	    prop = new Prop(width,height);
	    Engine.props.add(prop);
	    
}
public  PropRenderer(Prop p) {
super(p.getWidth(),p.getHeight());
	this.prop=p;
	Engine.props.add(p);
	
}
public  PropRenderer(Prop p,boolean addToEngine) {
super(p.getWidth(),p.getHeight());
	this.prop=p;
	if(addToEngine){
	Engine.props.add(p);
	}
}
	@Override
	public void Draw(GL2 gl) {
		// TODO Auto-generated method stub
		//super.Draw(gl);
		float barHeight =5;
		super.Draw(gl, prop.getPropData());
	 Engine.spriteRenderer.toggleUseTexture();
	    gl.glColor3f(0, 0, 1);
	    if(Tabbar.tabs.size()>1){
		Engine.spriteRenderer.Draw(gl, 0+Engine.spriteFrame.width, (depthy-super.getCamera().getY())+Tabbar.height-(barHeight/2),super.getCamera().getWidth(), barHeight,0	,0,0, 0);
	    }else{
	    Engine.spriteRenderer.Draw(gl, 0+Engine.spriteFrame.width, (depthy)-super.getCamera().getY()-(barHeight/2),super.getCamera().getWidth(), barHeight,0	,0,0, 0);
		    	
	    }
		Engine.spriteRenderer.toggleUseTexture();
	}

	@Override
	public boolean onMouseClick(int button, float x, float y) {
		// TODO Auto-generated method stub
	/*	y+=super.getCamera().getY();
		if(Tabbar.tabs.size()>1){
			y+=Tabbar.height;
		}*/
		// System.out.println("Got mouse click");
	//	System.out.println("MouseX: " + x + "   MouseY:"+y);
		float tempy=y;
		 if(button==1){
			 y+=super.getCamera().getY();
			 if(Tabbar.tabs.size()>1){
				 y-=Tabbar.height;
			 }
			
				System.out.println("MouseX: " + x + "   MouseY:"+y);	 
		//	 x+=super.getCamera().getX();
			 
		if(y>=depthy-15 && y<= depthy+15){
			startDragY=y;
			dragDepthBar=true;
			System.out.println("dragging bar");
			return true;
		}}
		 
		//dragDepthBar=false;
		return onMouseClick(button, x, tempy,prop.getWidth(),prop.getHeight(),prop.getPropData());
	}

	@Override
	public void Resize(float width, float height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScaleChange(float scale) {
		// TODO Auto-generated method stub
		
	}
 
		public void onCameraDragStart(float mouseX, float mouseY) {
			// TODO Auto-generated method stub
			super.onCameraDragStart(mouseX, mouseY);
			
	
		}

	@Override
	public boolean onMouseDragged(int button, float x, float y) {
		// TODO Auto-generated method stub
	//	 System.out.println("Got mouse drag");
		
		
		if(dragDepthBar){
			y+=super.getCamera().getY();
			if(Tabbar.tabs.size()>1){
				y-=Tabbar.height;
			}
			 System.out.println("DepthY:" + y);
			depthy=y;
			//System.out.println("mouse Dragged");
          return true;
		}else{
			
		}
		return false;
	}

	@Override
	public boolean onMouseRelease(int button, float x, float y) {
		// TODO Auto-generated method stub
		if(dragDepthBar){
			y+=super.getCamera().getY();
			if(Tabbar.tabs.size()>1){
				y-=Tabbar.height;
			}
			depthy=y;
			dragDepthBar=false;
		}
		return false;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "prop";
	}

	@Override
	public float getWidth() {
		// TODO Auto-generated method stub
		return prop.getWidth();
	}

	@Override
	public float getHeight() {
		// TODO Auto-generated method stub
		return prop.getHeight();
	}

	@Override
	public String generateJSON() {
		// TODO Auto-generated method stub
		float width = prop.getWidth();
		float height = prop.getHeight();
		Sprite[][]data = prop.getPropData();
		String json="{";
		 json+="\"Prop\":{";
		 json+="\"width\":\""+prop.getWidth()+"\",";
		 json+="\"height\":\""+prop.getHeight()+"\",";
		 json+="\"Zbar\":\""+depthy+"\",";
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
			 json+="\"imgx\":\""+data[i][j].imgx+"\",";
			 json+="\"imgy\":\""+data[i][j].imgy+"\"";
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
	 
     
     
		json +="}";
		
		return json;
	}

public void setZBar(float z){
	depthy=z;
}
public void setName(String name){
	super.setName(name);
	prop.name = name;
}

}
