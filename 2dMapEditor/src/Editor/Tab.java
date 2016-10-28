package Editor;

import com.jogamp.opengl.GL2;

import Camera.Camera;

public abstract class Tab {
 private String name;
 private float scale=1.f;
private  Camera cam;
private float camDragx,camDragy;


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
 public float getScale(){
	 return scale;
 }
 public void setScale(float scale){
	 this.scale = scale;
 }
 
 
	public abstract void onScaleChange(float scale);
	public abstract void Draw(GL2 gl);
	public abstract boolean onMouseClick(int button, float mouseX,float mouseY);
	 public  void Resize(float width,float height){
		 cam.resize(width, height);
	 }
	 public  void onCameraDragStart(float mouseX,float mouseY){
			camDragx=mouseX;
			camDragy=mouseY;
	 }
	public  void cameraDragged(float mouseX,float mouseY){
		float cx = (mouseX-camDragx)*(1.5f/scale);
		camDragx=mouseX;
		float cy = (mouseY - camDragy)*(1.5f/scale);
		camDragy=mouseY;
		cam.move(cam.getX()-cx, cam.getY()-cy);
	}
}
