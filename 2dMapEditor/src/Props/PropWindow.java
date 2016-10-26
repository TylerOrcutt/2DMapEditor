package Props;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;

import Editor.Renderer;
import Engine.Engine;
import Engine.SpriteRenderer;

public class PropWindow extends JFrame {
	Renderer renderer;
	SpriteRenderer spriteRenderer;
	public PropWindow(){
		this.setTitle("Prop Editor");
		this.setSize(800,600);
		this.setVisible(true);
	//	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.out.println("closing");
				//renderer.WindowClosing();
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		 renderer = new Renderer(this.getContentPane()) {
			
			@Override
			public void Render(GL2 gl) {
				// TODO Auto-generated method stub
			//	gl.glClearColor(0, 0, 0, 1);
				spriteRenderer.toggleUseTexture();
				gl.glColor3f(1, 1, 1);
			//  spriteRenderer.Draw(gl, 10, 10, 100,100, 0, 0, 0, 0);
				spriteRenderer.toggleUseTexture();
			}
			
			
			@Override
			public void Init(GL2 gl) {
				// TODO Auto-generated method stub
				if(spriteRenderer==null){
				   spriteRenderer = new SpriteRenderer(gl);
				//   spriteRenderer.shaderProgram=Engine.spriteRenderer.shaderProgram;
				   spriteRenderer.init(gl);
				}
	
			}

			@Override
			public void Reize(GL2 gl, int x, int y, int w, int h) {
				// TODO Auto-generated method stub
				
			}
		};
	}

}
