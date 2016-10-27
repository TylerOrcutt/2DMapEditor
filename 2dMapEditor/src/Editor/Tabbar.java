package Editor;

import java.util.ArrayList;

import com.jogamp.opengl.GL2;

import Engine.Engine;

public class Tabbar {
	public static ArrayList<Tab> tabs;
	public static Tab activeTab=null;
	public final static float height=32;
	public static float width=0,x,y;
	public static void Draw(GL2 gl){
		//draw tab bar

		
		
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
		      gl.glColor3f(.25f, .25f, .25f);
			  for(Tab tab :tabs){
				  Engine.spriteRenderer.Draw(gl, sx, 0, tabwidth, 30, 0, 0, 0, 0);
				  
				  sx+=tabwidth+10;
			  }
			  
			  
			  
			  Engine.spriteRenderer.toggleUseTexture();
		  }
		
	
	}
	public static void init(){
		tabs= new ArrayList<>();
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
		activeTab=tab;
		
	}
	public static void onScaleChange(float change){
		if(activeTab!=null){
			activeTab.onScaleChange(change);
		}
	}

}
