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
	lines.clear();
	for(int i=0;i<=Engine.width;i+=32){
		Sprite sprite= new Sprite(null);
		sprite.resize(5, Engine.height);
		sprite.move(i-2.5f, 0);
		lines.add(sprite);
	}
	for(int i=0;i<=Engine.height;i+=32){
		Sprite sprite= new Sprite(null);
		sprite.resize(Engine.width, 5);
		sprite.move(0, i-2.5f);
		lines.add(sprite);
	}
}
public void resize(float width,float height){
 generateGrid();
}
}
