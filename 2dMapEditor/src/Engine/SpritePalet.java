package Engine;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.jogamp.opengl.GL2;
   class selection {
		float selectionX=0,selectionY=0;
		public selection(float x,float y){
			selectionX=x;
		    selectionY=y;
		}
}
public class SpritePalet {
	private SpriteSheet sheet;

 public	ArrayList<selection> selected;
	
	public SpritePalet(SpriteSheet sp){
		 sheet = sp;
		 selected=new ArrayList<>();
	}
	public void DrawSelectioNSquare(GL2 gl){
		
		Engine.spriteRenderer.setUseTexture(false);
 
  float width=5;
   for(selection s : selected){
       float x = s.selectionX,y=s.selectionY;
	   gl.glColor3f(0,0,1);
      Engine.spriteRenderer.Draw(gl, x-(width/2.0f), y,30, width,0,0,0,0);//top
      Engine.spriteRenderer.Draw(gl, x-(width/2.0f), y+30,31, width,0,0,0,0);//bottom
      
     Engine.spriteRenderer.Draw(gl,  x-(width/2.f), y ,width, 31,0,0,0,0);//left
     Engine.spriteRenderer.Draw(gl,  30+x-(width/2.f), y ,width, 32,0,0,0,0);//right
     // SpriteRenderer.Draw(gl, x+32, y-(width/2),width, 32,0,0,0,0);//top
   }
		Engine.spriteRenderer.setUseTexture(true);
	}
	public void Draw(GL2 gl){
		
	  // sheet.draw(gl, 0, 0, sheet.width, sheet.height, 0, 0);
  	 float drawWidth=1.f;
  	 float fwidth= Engine.spriteFrame.width;
  	 if(Engine.spriteFrame.width<sheet.width){
  		drawWidth=(1.f/sheet.width)*Engine.spriteFrame.width;
  	 }else{
  		 fwidth=sheet.width;
  	 }
		Engine.spriteRenderer.Draw(gl, 0, 0,fwidth, sheet.height,0,0,drawWidth,1.f);
		this.DrawSelectioNSquare(gl);

	  
	}
	public void onClick(MouseEvent e,boolean shitdown){
		 float mouseX = (e.getX());
		 float mouseY = (e.getY());
		 
		 
		 if(!shitdown){
			 selected.clear();
		 }
		
		 
			float selectionX=0,selectionY=0;
		  selectionX=32.0f*(float)Math.floor(mouseX/32);
		  selectionY=32.0f*(float)Math.floor(mouseY/32);
		  selection s = new selection(selectionX, selectionY);
		   if(selected.isEmpty()){
		  selected.add(s);
		   }else{
			//sort
			  boolean added=false;
			  for(int i=0;i<selected.size();i++){
				 
				  selection sel = selected.get(i);
	
				  //ignore duplicats
				  if(s.selectionX==sel.selectionX && s.selectionY==sel.selectionY){
					  selected.remove(i);
					  added=true;
					  break;
				  }
		 
				
				  
			  }
			  if(!added){
				  selected.add(s);
			  }
		 }
		  
 }
	public void printList(){
		  for(int i=0;i<selected.size();i++){
			  selection sel = selected.get(i);
			  System.out.println(i+": X:"+sel.selectionX + "  Y:"+sel.selectionY);
			  
		  }
	}
 public ArrayList<selection> getSelected(){
 return selected;
}
}
