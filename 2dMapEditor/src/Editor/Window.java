package Editor;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
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
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
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
import Engine.SpriteRenderer;
import Shaders.ShaderProgram;
import io.FileWriter;

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
      JCheckBoxMenuItem gridcheck =new JCheckBoxMenuItem("Show Grid",true);
      fileMenu.add(gridcheck);
      
      
      JMenu statusm = new JMenu();
      statusm.add(new JMenuItem("Exit"));
      this.add(statusm,BorderLayout.SOUTH);
 
    
      
      gridcheck.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Engine.drawGrid=!Engine.drawGrid;
		}
	});
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
    this.add(toolbar,BorderLayout.NORTH);
    JTextField bsizeTxt = new JTextField(3);
    toolbar.add(bsizeTxt);
     bsizeTxt.setText("32");
    bsizeTxt.addKeyListener(new KeyListener() {
		
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			   try{
				   Engine.brushSize = Float.parseFloat(bsizeTxt.getText());
				   System.out.println("Brush size: " + Engine.brushSize);
			   }catch (Exception ez) {
				Engine.brushSize=32;
			}
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			   try{
				   Engine.brushSize = Float.parseFloat(bsizeTxt.getText());
				   System.out.println("Brush size: " + Engine.brushSize);
			   }catch (Exception ez) {
				Engine.brushSize=32;
			}
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			   try{
				   Engine.brushSize = Float.parseFloat(bsizeTxt.getText());
				   System.out.println("Brush size: " + Engine.brushSize);
			   }catch (Exception ez) {
				Engine.brushSize=32;
			}
			 
		}
	});
   
    bsizeTxt.setMinimumSize(new Dimension( 50, 50));
    toolbar.addSeparator();
  
    JButton testbtn = new JButton("Run");
    toolbar.add(testbtn);	
    final Window win = this;
   	Engine.initEngine( );
testbtn.addActionListener(new ActionListener() {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try{
			FileWriter.writeFile("test.map");
			
		Runtime.getRuntime().exec("./PETest -dev -m "+ "./test.map");
		}catch(Exception e1){
			
		}
	}
});
    bsizeTxt.setMinimumSize(new Dimension(250, 50));

  GLProfile profle= GLProfile.get(GLProfile.GL2);
   GLCapabilities cap = new GLCapabilities(profle);
   final GLCanvas canvas = new GLCanvas(cap);
 
  final Animator ani = new Animator();
   ani.setUpdateFPSFrames(60, null);
    
     canvas.addGLEventListener(new GLEventListener() {
		
		@Override
		public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
			// TODO Auto-generated method stub
			Engine.initShaders(drawable);
			Engine.resize(drawable, x, y, w, h);
		}
		
		@Override
		public void init(GLAutoDrawable drawable) {
			// TODO Auto-generated method stub
			Engine.initShaders(drawable);
			Engine.initAssets(drawable);
		}
		
		@Override
		public void dispose(GLAutoDrawable arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void display(GLAutoDrawable drawable) {
			// TODO Auto-generated method stub
		Engine.Render(drawable);
			
		}
	});
     
 
     JPanel leftp = new JPanel(new BorderLayout());
    

     //JSplitPane splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftp,canvas);
    // this.getContentPane().add(splitpane,BorderLayout.CENTER);
      this.getContentPane().add(canvas,BorderLayout.CENTER);
    ani.add(canvas);
     ani.start();
   //splitpane.setOneTouchExpandable(true);
   //splitpane.setDividerLocation(50);

 
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
		// TODO Auto-generated method stub
		Engine.KeyPress(e);
	}
});
    
    canvas.addMouseListener(new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			Engine.MouseRelease(e);
			
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
			canvas.requestFocus();
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	});
   canvas.addMouseWheelListener(new MouseWheelListener() {
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		Engine.mouseWheelMove(e);
		// TODO Auto-generated method stub
		
	}
});
 canvas.addMouseMotionListener(new MouseMotionListener() {
	
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if(Engine.mouseMoved(e)){
		   	canvas.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		}else{
		  	canvas.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		Engine.mouseDragged(e);
		
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
		System.out.println("Window close");
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
