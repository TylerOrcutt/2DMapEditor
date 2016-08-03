 
package Camera;
import Engine.Engine;
public class Camera {
	float x=0,y=0,z=0;
	float width=800,height=600;
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	public float getZ(){
		return z;
	}
	public void move(float x,float y){
		this.x=x;
		this.y=y;
	}
	public void move(float x,float y,float z){
		this.x=x;
		this.y=y;
		this.z=z;
	}
	public float getWidth(){
		return width;
	}
	public float getHeight(){
		return height;
	}
	public void resize(float width,float height){
		this.width=width;
		this.height=height;
		
	}
	public void orient(float centerX,float centerY){
 
 
	 
	}
}
