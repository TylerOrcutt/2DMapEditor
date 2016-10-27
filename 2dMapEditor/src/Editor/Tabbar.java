package Editor;

import java.awt.Font;
import java.util.ArrayList;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.awt.TextRenderer;

import Engine.Engine;

public class Tabbar {
	public static ArrayList<Tab> tabs;
	public static Tab activeTab=null;
	public final static float height=32;
	public static float width=0,x,y;
	public static TextRenderer textRenderer;
	public static void Draw(GLAutoDrawable drawable){
		
		GL2 gl = drawable.getGL().getGL2();
		
		
	  if(activeTab!=null){	  
	  //draw active tab 
	   activeTab.Draw(gl);
	  }

		  if(tabs.size()>1){
			  Engine.spriteRenderer.toggleUseTexture();
			  gl.glColor3f(.75f, .75f, .75f);
			  Engine.spriteRenderer.Draw(gl, Engine.spriteFrame.width, 0, width, height, 0, 0, 0, 0);
			
			  float tabwidth = width/tabs.size();
		      float sx = Engine.spriteFrame.width;
		
			  for(Tab tab :tabs){
				  if(tab == activeTab){
					  gl.glColor3f(0.5f, 0.5f, 0.5f);
				  }else{
					  gl.glColor3f(.25f, .25f, .25f);
				  
				  }Engine.spriteRenderer.Draw(gl, sx, 0, tabwidth, 30, 0, 0, 0, 0);
					
				  gl.glUseProgram(0);
				 textRenderer.beginRendering(drawable.getSurfaceWidth(), drawable.getSurfaceHeight());
				 textRenderer.setColor(1,1,1,0.8f);
				 textRenderer.draw(tab.getName(), (int) sx, drawable.getSurfaceHeight()-24);
				 textRenderer.endRendering();
			
			
				  sx+=tabwidth+10;
			  }
			  
			  
			  
			//  Engine.spriteRenderer.toggleUseTexture();
		  }
		  gl.glUseProgram(Engine.spriteRenderer.shaderProgram.id());
		  Engine.spriteRenderer.setUseTexture(true);
		
	
	}
	public static void init(){
		tabs= new ArrayList<>();
		textRenderer = new TextRenderer(new Font("SansSerif",Font.BOLD,24));
	}
	public static void Resize(float width,float height){
		Tabbar.width=width-Engine.spriteFrame.width;
		for(int i=0;i<tabs.size();i++){
			tabs.get(i).Resize(width, height);
		}
	}
	
	public static boolean onMouseClick(int button,float mouseX,float mouseY){
		//check tab bar
		//if(mouseX >)
		if(mouseX>Engine.spriteFrame.width && mouseX<Engine.width && mouseY>0 && mouseY<height){
			  float tabwidth = width/tabs.size();
			int sel =((int) mouseX-(int)Engine.spriteFrame.width)/(int)tabwidth;
	  //   System.out.println("selected tab " + sel);
		
			if(sel>=0 && sel<tabs.size()){
				activeTab= tabs.get(sel);
			}
			return true;
		}
		
		//sent to tab
		if(activeTab!=null){
			activeTab.onMouseClick(button, mouseX, mouseY);
		}
		return false;
	}
	
	public static void addTab(Tab tab){
		tabs.add(tab);
		tab.setName("Untitled-"+tabs.size());
		activeTab=tab;
		
	}
	public static void onScaleChange(float change){
		if(activeTab!=null){
			activeTab.onScaleChange(change);
		}
	}
	
	public static void onCameraDragged(float mouseX,float mouseY){
		if(activeTab!=null){
			activeTab.cameraDragged(mouseX, mouseY);
		}
	}

}
