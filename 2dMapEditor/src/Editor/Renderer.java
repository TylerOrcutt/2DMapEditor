package Editor;

import java.awt.Canvas;
import java.awt.Container;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;

import Engine.Engine;

public abstract class Renderer {
	 final GLCanvas canvas ;
	 final Animator ani ;
  public Renderer(Container context){
	 
	  GLProfile profle= GLProfile.get(GLProfile.GL2);
	   GLCapabilities cap = new GLCapabilities(profle);
	   canvas = new GLCanvas(cap);
	 
	      ani = new Animator();
	   ani.setUpdateFPSFrames(60, null);
	    
	     canvas.addGLEventListener(new GLEventListener() {
			
			@Override
			public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
				// TODO Auto-generated method stub
			//	Engine.initShaders(drawable);
				//Engine.resize(drawable, x, y, w, h);
			    GL2 gl = drawable.getGL().getGL2(); 
			    gl.glEnable(gl.GL_BLEND);
			    gl.glBlendFunc(gl.GL_ONE, gl.GL_ONE_MINUS_SRC_ALPHA);
			     
			     
			    		

			    gl.glMatrixMode(GL2.GL_PROJECTION);
			    gl.glLoadIdentity();


			    gl.glOrtho(0, w, h,0, 0,50);

			    gl.glMatrixMode(GL2.GL_MODELVIEW);
			    gl.glLoadIdentity();
				 Reize(drawable.getGL().getGL2(),x,y,w,h);
			}
			
			@Override
			public void init(GLAutoDrawable drawable) {
				// TODO Auto-generated method stub
			    GL2 gl = drawable.getGL().getGL2(); 
			    
				gl.glEnable(GL2.GL_DEPTH_TEST); 
			     gl.glDepthFunc(GL2.GL_LEQUAL);   
			     gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);  
			     gl.glShadeModel(GL2.GL_SMOOTH); 
			     gl.glClearDepth(1.0f); 
			     gl.glClearColor(0, 0, 0, 1);
			 Init(gl);
			}
			
			@Override
			public void dispose(GLAutoDrawable drawable) {
				// TODO Auto-generated method stub
			//	drawable.destroy();
				ani.stop();
			
				canvas.destroy();
				System.out.println("dispose");
			}
			
			@Override
			public void display(GLAutoDrawable drawable) {
				// TODO Auto-generated method stub
				   GL2 gl = drawable.getGL().getGL2(); 
				 gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); 
		     Render(gl);
			}
		});
	     context.add(canvas);
	     ani.add(canvas);
	     ani.start();
  }
  public void WindowClosing(){
	 canvas.destroy();
  }
 public  abstract void Render(GL2 gl);
 public  abstract void Init(GL2 gl);
 public  abstract void Reize(GL2 gl,int x, int y, int w, int h);
}
