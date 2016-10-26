package Editor;

import java.util.ArrayList;

import com.jogamp.opengl.GL2;

public class Tabbar {
	public static ArrayList<Tab> tabs;
	public static Tab activeTab=null;
	public final float height=96;
	public static float width=0,x,y;
	public static void Draw(GL2 gl){
	  if(activeTab==null){return;}
	  //draw tab bar
	  
	  //draw active tab 
	   activeTab.Draw(gl);
	}
	public static void init(){
		tabs= new ArrayList<>();
	}
	public static void Resize(float width,float height){
		Tabbar.width=width;
		for(int i=0;i<tabs.size();i++){
			tabs.get(i).Resize(width, height);
		}
	}
	
	public static boolean onMouseClick(int button,float mouseX,float mouseY){
		//check tab bar
		//if(mouseX >)
		
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
