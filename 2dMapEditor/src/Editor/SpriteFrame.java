package Editor;

import com.jogamp.opengl.GL2;

import Engine.SpritePalet;
import Engine.SpriteSheet;

public class SpriteFrame {
	public float  width=600;
	public SpritePalet sp;
	 public boolean visiable=true;
	public SpriteFrame(SpriteSheet sp){
		this.sp = new SpritePalet(sp);
		
	}
	public void Draw(GL2 gl){
		sp.Draw(gl);
		
	}

	 
	 
}
