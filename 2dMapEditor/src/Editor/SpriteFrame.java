package Editor;

import com.jogamp.newt.event.MouseEvent;
import com.jogamp.opengl.GL2;

import Engine.SpritePalet;
import Engine.SpriteRenderer;
import Engine.SpriteSheet;
import Engine.Engine;
public class SpriteFrame {
	public float  width=512;
	public SpritePalet sp;
	 public boolean visiable=true;
	 public boolean sliderDragged=false;
	 public int selectedProp=0;
	 private long lastClick=0;
	public SpriteFrame(SpriteSheet sp){
		this.sp = new SpritePalet(sp);
		
	}
	public void Draw(GL2 gl){
		if(!Engine.drawProps){
		sp.Draw(gl);
		
		}else{
		float startx=10;
		float posy=10;
		float posx = 10; 
		float tilesize=64;
			for(int i=0;i<Engine.props.size();i++){
			
			   Engine.props.get(i).Draw(gl,posx,posy,tilesize,tilesize);
	
				if(i==selectedProp){
					Engine.spriteRenderer.DrawRect(gl, posx, posy, 64, 64);
				}
				   posx+=74;
				   if(posx>=width-64){
					   posx=startx;
					   posy+=74;
				   }
			}
		}
		Engine.spriteRenderer.setUseTexture(false);
		   gl.glColor4f(.75f,.75f,.75f,10.f);
		      Engine.spriteRenderer.Draw(gl, width-(5/2.0f), 0,5,  Engine.height,0,0,0,0);//top
		Engine.spriteRenderer.setUseTexture(true);
		 
	}
	
	public void sliderDrag(float x,float y){
		if(!sliderDragged){
			return;
		}
		if(x<=5){
			return;
		}
		width=x;
		// Engine.grid.generateGrid();
	}
 public boolean mouseClick(java.awt.event.MouseEvent e, boolean shiftDown){
	 if(Engine.drawProps){
		 
		 
		 int mx = e.getX(),my = e.getY();
		 //System.out.println("Mouse X: " +mx+ "    Mousey"+my);
		 int id = (int)((mx/74)+((my/74) * (int)(width/74)));
		// System.out.println("Mouse X: " +mx+ "    Mousey"+my + "    id" + id);
		 selectedProp= id;
		 Engine.selectedProp=id;
		 long curtime= System.currentTimeMillis();
		 if(curtime-lastClick<=300){
			//double click;
			 String name = Engine.props.get(id).name;
			 PropRenderer tab= new PropRenderer(Engine.props.get(id),false);
			 Tabbar.addTab(tab);
			 tab.setName(name);
			 tab.setZBar(Engine.props.get(id).getZLine());
			lastClick=0; 
		 }else{
		 lastClick =curtime;
		 }
	 }else{
	  sp.onClick(e, shiftDown); 
	 }	 
	 
	 return true;
 }
	 
	 
}
