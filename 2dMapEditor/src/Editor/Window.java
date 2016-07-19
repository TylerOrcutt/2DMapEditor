package Editor;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.MenuItem;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.awt.GLJPanel;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL3;

import com.jogamp.opengl.glu.GLU;

import Engine.Engine;

public class Window extends JFrame{
	//private static final Engine engine;
	  private  boolean closeRequested = false;
	  private final AtomicReference<Dimension> newCanvasSize = new AtomicReference<Dimension>();
	public Window(String title){
	this.setSize(800,600);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setTitle(title);
    JMenu menu = new JMenu("File");
  	this.add(menu,BorderLayout.NORTH);

  GLProfile profle= GLProfile.get(GLProfile.GL4);
   GLCapabilities cap = new GLCapabilities(profle);
   final GLCanvas canvas = new GLCanvas(cap);
     JPanel leftp = new JPanel();
     float x=100,y=200;
  //  JSplitPane 
     this.add(leftp, BorderLayout.WEST);
    leftp.add(new Button("test"));
   
     canvas.addGLEventListener(new GLEventListener(){
	@Override
	public void display(GLAutoDrawable arg0) {
		GL3 gl= arg0.getGL().getGL3();
		// TODO Auto-generated method stub
	    gl.glClear( GL.GL_COLOR_BUFFER_BIT );

       // draw a triangle filling the window
  /*      gl.glLoadIdentity();
        gl.glBegin( GL.GL_TRIANGLE_STRIP );
       
        gl.glColor3f( 1, 0, 0 );
        gl.glVertex2f( x, y );
        gl.glColor3f( 0, 1, 0 );
        gl.glVertex2f(x, y+100 );
        gl.glColor3f( 0, 0, 1 );
        gl.glVertex2f( x+100, y );
        gl.glColor3f( 1, 1, 1 );
        gl.glVertex2f( x+100, y+100 );
        gl.glEnd();
     */
   	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		Engine.initEngine(drawable);
		
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {

	//	Engine.resize(drawable, x, y, w, h);
		// TODO Auto-generated method stub
		/*  gl.glMatrixMode( GL2.GL_PROJECTION );
	       gl.glLoadIdentity();

	        // coordinate system origin at lower left with width and height same as the window
	//       GLU glu = new GLU();
	   //     glu.gluOrtho2D( 0.0f, w, h, 0.0f );

	     //   gl.glMatrixMode( GL2.GL_MODELVIEW );
	      //  gl.glLoadIdentity();

	      //  gl.glViewport( 0,0,w,h );*/
	}
	   
   });
   this.add(canvas, BorderLayout.CENTER);
  this.addWindowListener(new WindowListener(){

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
	
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	  
  });
  

	this.show();

}
	
	
}
