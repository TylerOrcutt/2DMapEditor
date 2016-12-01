package Editor;

import com.jogamp.opengl.GL2;

import Engine.SpriteRenderer;
import Props.Prop;
import Engine.Engine;
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
	
	@Override
	public void Draw(GL2 gl) {
		// TODO Auto-generated method stub
		//super.Draw(gl);
		super.Draw(gl, prop.getPropData());
	 Engine.spriteRenderer.toggleUseTexture();
	    gl.glColor3f(0, 0, 1);
	    if(Tabbar.tabs.size()>1){
		Engine.spriteRenderer.Draw(gl, 0+Engine.spriteFrame.width, (depthy-super.getCamera().getY())+Tabbar.height,super.getCamera().getWidth(), 5,0	,0,0, 0);
	    }else{
	    Engine.spriteRenderer.Draw(gl, 0+Engine.spriteFrame.width, (depthy)-super.getCamera().getY(),super.getCamera().getWidth(), 5,0	,0,0, 0);
		    	
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
		 if(button==1){
		if(y>=depthy-10 && y<= depthy+15){
			startDragY=y;
			dragDepthBar=true;
			System.out.println("dragging bar");
			return true;
		}}
		 
		//dragDepthBar=false;
		return  onMouseClick(button, x, y,prop.getWidth(),prop.getHeight(),prop.getPropData());
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
		if(dragDepthBar){
			y+=super.getCamera().getY();
			if(Tabbar.tabs.size()>1){
				y+=Tabbar.height;
			}
			 System.out.println("DepthY:" + y);
			depthy=y;
			System.out.println("mouse Dragged");

		}
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
		return null;
	}



}
