package Editor;

import com.jogamp.opengl.GL2;

import Camera.Camera;
import Engine.SizedMap;

public class MapRenderer extends Tab {
public SizedMap map;

public MapRenderer(int width,int height) {
	// TODO Auto-generated constructor stub
	super();
   map = new SizedMap(width, height);
 
}
	@Override
	public void Draw(GL2 gl) {
		// TODO Auto-generated method stub
		map.Draw(gl,super.getCamera(), Engine.Engine.spriteFrame.width);
		
	}

	@Override
	public boolean onMouseClick(int button, float mouseX, float mouseY) {
		// TODO Auto-generated method stub
		
		map.MousePress(mouseX, mouseY, button);
		return false;
	}

	@Override
	public void Resize(float width, float height) {
		// TODO Auto-generated method stub
		super.Resize(width, height);
	}
	@Override
	public void onScaleChange(float scale) {
		// TODO Auto-generated method stub
		map.onScaleChange(scale);
		
	}

}
