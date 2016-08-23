package Engine;

import com.jogamp.opengl.GL2;

public class SpritePalet {
	private SpriteSheet sheet;
	public SpritePalet(SpriteSheet sp){
		 sheet = sp;
	}
	public void Draw(GL2 gl){
		
	  // sheet.draw(gl, 0, 0, sheet.width, sheet.height, 0, 0);
  	 
		SpriteRenderer.Draw(gl, 0, 0,200, sheet.height,0,0,(1.f/sheet.width)*200,1.f);
		
		SpriteRenderer.setUseTexture(false);
  float x=100, y=400;
  float width=5;
      gl.glColor3f(0,0,1);
      SpriteRenderer.Draw(gl, x-(width/2), y,32, width,0,0,0,0);//top
      SpriteRenderer.Draw(gl, x-(width/2), y+32,32, width,0,0,0,0);//bottom
      
     SpriteRenderer.Draw(gl,  x-(width/2), y ,width, 32,0,0,0,0);//top
     SpriteRenderer.Draw(gl,  32+x-(width/2), y ,width, 32,0,0,0,0);//top
     // SpriteRenderer.Draw(gl, x+32, y-(width/2),width, 32,0,0,0,0);//top
		SpriteRenderer.setUseTexture(true);
	  
	}

}
