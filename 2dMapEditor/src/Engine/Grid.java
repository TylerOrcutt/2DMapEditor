package Engine;

import java.util.ArrayList;

import com.jogamp.opengl.GL2;

import Camera.Camera;
import Editor.MapRenderer;
import Editor.Tab;
import Editor.Tabbar;

public class Grid {
private ArrayList<Sprite>lines;

public Grid(){
 
 lines = new ArrayList<>();
}

public void Draw(GL2 gl,MapRenderer tab){
	//gl.glColor4f(.44f, .44f, .44f, .50f);
	gl.glColor4f(1.f, 0.f, 0.f, 1f);
	//SpriteRenderer.setUseTexture(false);
	   Engine.spriteRenderer.toggleUseTexture();
	for (Sprite sprite : lines) {
		   float cy=(sprite.y-tab.getCamera().getY())*tab.getScale();
		   if(Tabbar.tabs.size()>1){
			   cy=Tabbar.height+(sprite.y-tab.getCamera().getY())*tab.getScale();
		   }
		   float cx = (Engine.spriteFrame.width+(sprite.x+-tab.getCamera().getX())*tab.getScale());
		//sprite.Draw(gl,Engine.camera,Engine.scale,Engine.spriteFrame.width);
		   float twidth=sprite.width;
		   if(sprite.width==tab.getWidth()*32*tab.getScale() && cx<Engine.spriteFrame.width){
			   cx=Engine.spriteFrame.width;
			   twidth=((((tab.getWidth()*32)-tab.getCamera().getX())*tab.getScale())) ;
			  
		   }
 
		   if(twidth<0){
			   continue;
		   }
		   
		   if(cx<Engine.spriteFrame.width){
			   continue;
		   }
		   
	
		   Engine.spriteRenderer.Draw(gl, cx, cy, twidth, sprite.height, 0, 0,0,0);
		
	}
	   Engine.spriteRenderer.toggleUseTexture();
 
}

public void generateGrid(MapRenderer tab){
	lines.clear();
	float offset=0;
 
	
	float width=tab.getWidth()*32*tab.getScale();
	float height=tab.getHeight()*32*tab.getScale();
	for(float i=(32);i<=width/tab.getScale();i+=32){
		Sprite sprite= new Sprite(null);
		sprite.resize(1, height);
		sprite.move(i-0.5f, 0);
		lines.add(sprite);
	}
	for(float i=0;i<=height/tab.getScale();i+=32){
		Sprite sprite= new Sprite(null);
		sprite.resize(width-offset, 1);
		sprite.move(offset, i-0.5f);
		lines.add(sprite);
	}
		
}
public void resize(float width,float height){
 
}
}
