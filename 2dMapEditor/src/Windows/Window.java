package Windows;

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
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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

import Editor.MapRenderer;
import Editor.PropRenderer;
import Editor.Tabbar;
import Engine.Engine;
import Engine.SizedMap;
import Engine.SpriteRenderer;
import Props.PropWindow;
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
      JMenuItem mapmi = new JMenuItem("Map");
     // newMenu.add(mapmi);
      mapmi.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Engine.sprites.clear();
			Engine.useSizedMap=false;
			Engine.sizedMap=null;
		
			
		}
	});
      mapmi = new JMenuItem("Map");
      newMenu.add(mapmi);
      mapmi.addActionListener(new ActionListener() {
  		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Engine.sprites.clear();
			Engine.useSizedMap=true;
			JTextField mwidth = new JTextField();
			mwidth.setText("16");
			JTextField mheight = new JTextField();
			mheight.setText("16");
			final JComponent[] inputs = new JComponent[]{
				new JLabel("Map Width"), mwidth,
				new JLabel("Map Height"),mheight
			};
			int result = JOptionPane.showConfirmDialog(null, inputs, "New Map", JOptionPane.PLAIN_MESSAGE);
			
			
			Tabbar.addTab(new MapRenderer(Integer.parseInt(mwidth.getText()),Integer.parseInt(mheight.getText())));
			//Engine.sizedMap = new SizedMap(Integer.parseInt(mwidth.getText()),Integer.parseInt(mheight.getText()));
			//Engine.grid.generateGrid();
		}
	});
      
      JMenuItem pemi = new JMenuItem("Prop");
       newMenu.add(pemi);
      pemi.addActionListener(new ActionListener() {
  		
		@Override
		public void actionPerformed(ActionEvent e) {
			JTextField mwidth = new JTextField();
			mwidth.setText("2");
			JTextField mheight = new JTextField();
			mheight.setText("2");
			final JComponent[] inputs = new JComponent[]{
				new JLabel("Prop Width"), mwidth,
				new JLabel("prop Height"),mheight
			};
			int result = JOptionPane.showConfirmDialog(null, inputs, "New Prop", JOptionPane.PLAIN_MESSAGE);
			
			
			Tabbar.addTab(new PropRenderer());
		}
	});
      fileMenu.addSeparator();
 
      JMenuItem importbtn = new JMenuItem("Import");
      fileMenu.add(importbtn);
      importbtn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		 
			// TODO Auto-generated method stub
			JFileChooser fc = new JFileChooser();
			int rp= fc.showOpenDialog(null);
			 if(rp == JFileChooser.APPROVE_OPTION){
				 System.out.println("approved");
				 String data=io.FileReader.ReadFile(fc.getSelectedFile().getPath());
			 }else{
				 System.out.println("Filed chooser canceled");
			 }
		}
	});
      
      fileMenu.addSeparator();
      newMenu = new JMenu("Export");
      fileMenu.add(newMenu);
      
      JMenuItem jsonxbtn = new JMenuItem("JSON");
      newMenu.add(jsonxbtn);
      jsonxbtn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int rp= fc.showOpenDialog(null);
			 if(rp == JFileChooser.APPROVE_OPTION){
				 System.out.println("approved");
   System.out.println(fc.getSelectedFile().getAbsolutePath());
				 FileWriter.writeFile(fc.getSelectedFile().getAbsolutePath()+"/test.map");
			 }else{
				 System.out.println("File chooser canceled");
			 }
			
		}
	});
      newMenu.add(new JMenuItem("Block"));
      fileMenu.addSeparator();
      
 
 //     fileMenu.addSeparator();
      fileMenu.add(new JMenuItem("Exit"));
      fileMenu = new JMenu("Edit");
      JCheckBoxMenuItem snapCheck =new JCheckBoxMenuItem("Snap to Grid",true);
      fileMenu.add(snapCheck);
      menubar.add(fileMenu);
      
      fileMenu = new JMenu("View");
      JCheckBoxMenuItem gridcheck =new JCheckBoxMenuItem("Show Grid",true);
      fileMenu.add(gridcheck);
      fileMenu.addSeparator();
      
      fileMenu.add(new JMenuItem("Props"));
      fileMenu.add(new JMenuItem("TileSheet"));
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
    
    btn = new JButton("");
    btn.setIcon(new ImageIcon("images/layer1.png"));
    toolbar.add(btn);
    
    btn = new JButton("");
    btn.setIcon(new ImageIcon("images/layer2.png"));
    toolbar.add(btn);
    
    btn = new JButton("");
    btn.setIcon(new ImageIcon("images/layer3.png"));
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
			FileWriter.writeFile(".test.map");
			
		Runtime.getRuntime().exec("./PETest -dev -m "+ "./.test.map");
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
