package Engine;

import java.awt.event.MouseEvent;

import com.jogamp.opengl.GL2;

public class SpritePalet {
	private SpriteSheet sheet;
	float selectionX=0,selectionY=0;
	public SpritePalet(SpriteSheet sp){
		 sheet = sp;
	}
	public void DrawSelectioNSquare(GL2 gl){
		
		SpriteRenderer.setUseTexture(false);
  float x=selectionX, y=selectionY;
  float width=5;
      gl.glColor3f(0,0,1);
      SpriteRenderer.Draw(gl, x-(width/2.0f), y,30, width,0,0,0,0);//top
      SpriteRenderer.Draw(gl, x-(width/2.0f), y+30,31, width,0,0,0,0);//bottom
      
     SpriteRenderer.Draw(gl,  x-(width/2.f), y ,width, 31,0,0,0,0);//left
     SpriteRenderer.Draw(gl,  30+x-(width/2.f), y ,width, 32,0,0,0,0);//right
     // SpriteRenderer.Draw(gl, x+32, y-(width/2),width, 32,0,0,0,0);//top
		SpriteRenderer.setUseTexture(true);
	}
	public void Draw(GL2 gl){
		
	  // sheet.draw(gl, 0, 0, sheet.width, sheet.height, 0, 0);
  	 
		SpriteRenderer.Draw(gl, 0, 0,200, sheet.height,0,0,(1.f/sheet.width)*200,1.f);
		this.DrawSelectioNSquare(gl);

	  
	}
	public void onClick(MouseEvent e){
		 float mouseX = (e.getX());
		 float mouseY = (e.getY());
		 
		  selectionX=32.0f*(float)Math.floor(mouseX/32);
		  selectionY=32.0f*(float)Math.floor(mouseY/32);
	}

}
