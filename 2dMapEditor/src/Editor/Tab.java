package Editor;

import com.jogamp.opengl.GL2;

import Camera.Camera;

public abstract class Tab {
 private String name;
 
private  Camera cam;

public Tab(){
	cam = new Camera();
}
 public void setName(String name){
	 this.name=name;
 }
 public String getName(){
	 return name;
 }
 public Camera getCamera(){
	 return cam;
 }
 
 
	public abstract void onScaleChange(float scale);
	public abstract void Draw(GL2 gl);
	public abstract boolean onMouseClick(int button, float mouseX,float mouseY);
	 public  void Resize(float width,float height){
		 cam.resize(width, height);
	 }
	
}
