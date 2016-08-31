package Engine;

import java.util.ArrayList;

import com.jogamp.opengl.GL2;

public class Grid {
private ArrayList<Sprite>lines;

public Grid(){
 
 lines = new ArrayList<>();
}

public void Draw(GL2 gl){
	gl.glColor4f(.44f, .44f, .44f, .50f);
	SpriteRenderer.setUseTexture(false);
	for (Sprite sprite : lines) {
		sprite.Draw(gl);
	}
	SpriteRenderer.setUseTexture(true);
}
public void generateGrid(){
	generateGrid(Engine.width, Engine.height, Engine.scale);
}
public void generateGrid(float width,float height,float scale){
	lines.clear();
	float offset=0;
	if(Engine.spriteFrame.visiable){
		offset=Engine.spriteFrame.width;
	}
	for(float i=offset+32*scale;i<=width;i+=32*scale){
		Sprite sprite= new Sprite(null);
		sprite.resize(5, height);
		sprite.move(i-2.5f, 0);
		lines.add(sprite);
	}
	for(int i=0;i<=height;i+=32*scale){
		Sprite sprite= new Sprite(null);
		sprite.resize(width-offset, 5);
		sprite.move(offset, i-2.5f);
		lines.add(sprite);
	}
}
public void resize(float width,float height){
 generateGrid();
}
}
