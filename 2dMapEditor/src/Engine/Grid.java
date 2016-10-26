package Engine;

import java.util.ArrayList;

import com.jogamp.opengl.GL2;

import Camera.Camera;

public class Grid {
private ArrayList<Sprite>lines;

public Grid(){
 
 lines = new ArrayList<>();
}

public void Draw(GL2 gl){
	//gl.glColor4f(.44f, .44f, .44f, .50f);
	gl.glColor4f(1.f, 0.f, 0.f, 1f);
	//SpriteRenderer.setUseTexture(false);
	   Engine.spriteRenderer.toggleUseTexture();
	for (Sprite sprite : lines) {
		   float cy = (sprite.y-Engine.camera.getY())*Engine.scale;
		   float cx = (Engine.spriteFrame.width+(sprite.x+-Engine.camera.getX())*Engine.scale);
		//sprite.Draw(gl,Engine.camera,Engine.scale,Engine.spriteFrame.width);
		float twidth=sprite.width;
		   if(sprite.width==Engine.sizedMap.width*32*Engine.scale && cx<Engine.spriteFrame.width){
			   cx=Engine.spriteFrame.width;
			   twidth=((((Engine.sizedMap.width)*32)-Engine.camera.getX())*Engine.scale) ;
			  
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
public void generateGrid(){
	generateGrid(Engine.width, Engine.height, Engine.scale);
}
public void generateGrid(float width,float height,float scale){
	lines.clear();
	float offset=0;
	if(Engine.spriteFrame.visiable){
	//	offset=Engine.spriteFrame.width;
	}
/*	if(Engine.sizedMap==null){
		return;
	}*/
	width=width*32*scale;
	height=height*32*scale;
	for(float i=(32);i<=width/scale;i+=32){
		Sprite sprite= new Sprite(null);
		sprite.resize(1, height);
		sprite.move(i-0.5f, 0);
		lines.add(sprite);
	}
	for(float i=0;i<=height/scale;i+=32){
		Sprite sprite= new Sprite(null);
		sprite.resize(width-offset, 1);
		sprite.move(offset, i-0.5f);
		lines.add(sprite);
	}
	
/*	if(Engine.sizedMap!=null){
		for(float i=0-Engine.camera.getX();i<=0-Engine.camera.getX()+Engine.sizedMap.width;i+=32*scale){
			Sprite sprite= new Sprite(null);
			sprite.resize(1, height);
			sprite.move(offset+(i-Engine.camera.getX())-0.5f, 0);
			lines.add(sprite);
		}
		for(int i=(int) (0-Engine.camera.getY());i<=0-Engine.camera.getY()+Engine.sizedMap.height;i+=32*scale){
			Sprite sprite= new Sprite(null);
			sprite.resize(width-offset, 1);
			sprite.move(offset, i-0.5f);
			lines.add(sprite);
		}
	}*/
	
}
public void resize(float width,float height){
 generateGrid();
}
}
