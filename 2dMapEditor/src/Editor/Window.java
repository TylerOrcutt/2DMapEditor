package Editor;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;

import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.awt.GLJPanel;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL3;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.Animator;

import Engine.Engine;

public class Window extends JFrame{
	//private static final Engine engine;
	
	  private  boolean closeRequested = false;
	  private final AtomicReference<Dimension> newCanvasSize = new AtomicReference<Dimension>();
	public Window(String title){
 
		this.setSize(800,600);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setTitle(title);
    JMenuBar menubar = new JMenuBar( );
      JMenu fileMenu = new JMenu("File");
      this.setIconImage(new ImageIcon("images/logo.png").getImage());
      
      menubar.add(fileMenu);
      
      JMenu newMenu = new JMenu("New");
      fileMenu.add(newMenu);
      newMenu.add(new JMenuItem("Map"));
      
      fileMenu.addSeparator();
      fileMenu.add(new JMenuItem("Import"));
      fileMenu.addSeparator();
      newMenu = new JMenu("Export");
      fileMenu.add(newMenu);
      newMenu.add(new JMenuItem("JSON"));
      newMenu.add(new JMenuItem("Block"));
      fileMenu.addSeparator();
      
 
      fileMenu.addSeparator();
      fileMenu.add(new JMenuItem("Exit"));
      
      fileMenu = new JMenu("View");
      fileMenu.add(new JCheckBoxMenuItem("Show Grid",true));
      menubar.add(fileMenu);
 
      
    this.setJMenuBar(menubar);

    JToolBar toolbar = new JToolBar();
    JButton btn = new JButton("");
    btn.setIcon(new ImageIcon("images/cursor.png"));
    toolbar.add(btn);
    
    btn = new JButton("");
    btn.setIcon(new ImageIcon("images/brush.png"));
    toolbar.add(btn);
    btn = new JButton("");
    btn.setIcon(new ImageIcon("images/blocked.png"));
    toolbar.add(btn);
    btn = new JButton("");
    btn.setIcon(new ImageIcon("images/redspawn.png"));
    toolbar.add(btn);
    btn = new JButton("");
    btn.setIcon(new ImageIcon("images/bluespawn.png"));
    toolbar.add(btn);
    toolbar.add(new JButton("Test"));
    
    this.add(toolbar,BorderLayout.NORTH);

  GLProfile profle= GLProfile.get(GLProfile.GL2);
   GLCapabilities cap = new GLCapabilities(profle);
   final GLCanvas canvas = new GLCanvas(cap);
 final float x=100, y=100;
  //  JSplitPane 

     canvas.addGLEventListener(new GLEventListener(){
	@Override
	public void display(GLAutoDrawable drawable) {
		GL3 gl= drawable.getGL().getGL3();
 
         
		gl.glClear( GL.GL_COLOR_BUFFER_BIT );
	//    gl.glLoadIdentity();
		Engine.Render(drawable);

       // draw a triangle filling the window
 
   
     //   gl.glBegin( GL.GL_TRIANGLE_STRIP );
       
       /* gl.glColor3f( 1, 0, 0 );
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

 
	Engine.resize(drawable, x, y, w, h);
	
	}
	   
   });
     Animator ani = new Animator();
     ani.setUpdateFPSFrames(60, null);
      
 
     JPanel leftp = new JPanel(new BorderLayout());
    

     JSplitPane splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftp,canvas);
     this.add(splitpane,BorderLayout.CENTER);
   
     ani.add(canvas);
     ani.start();
   // splitpane.setOneTouchExpandable(true);
    splitpane.setDividerLocation(250);
    splitpane.setResizeWeight(.5);
    canvas.addKeyListener(new KeyListener() {
		
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
			Engine.KeyRelease(e);
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
	 
		}
	});
    
    canvas.addMouseListener(new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			Engine.MousePress(e);
			
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	});
   
 

  this.addWindowListener(new WindowListener(){

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
	 canvas.destroy();
		
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
