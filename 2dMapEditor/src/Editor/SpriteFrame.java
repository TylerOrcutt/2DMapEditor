package Editor;

import com.jogamp.opengl.GL2;

import Engine.SpritePalet;
import Engine.SpriteRenderer;
import Engine.SpriteSheet;

public class SpriteFrame {
	public float  width=620;
	public SpritePalet sp;
	 public boolean visiable=true;
	 public boolean sliderDragged=false;
	public SpriteFrame(SpriteSheet sp){
		this.sp = new SpritePalet(sp);
		
	}
	public void Draw(GL2 gl){
		sp.Draw(gl);
		SpriteRenderer.setUseTexture(false);
		   gl.glColor4f(.75f,.75f,.75f,10.f);
		      SpriteRenderer.Draw(gl, width-(5/2.0f), 0,5, Engine.Engine.height,0,0,0,0);//top
		SpriteRenderer.setUseTexture(true);
		 
	}
	
	public void sliderDrag(float x,float y){
		if(!sliderDragged){
			return;
		}
		if(x<=5){
			return;
		}
		width=x;
		Engine.Engine.grid.generateGrid();
	}
 
	 
	 
}
