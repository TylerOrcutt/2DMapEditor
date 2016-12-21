package io;

import java.io.BufferedReader;
 
import java.io.File;
 
import java.io.InputStreamReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Editor.MapRenderer;
import Editor.PropRenderer;
import Editor.Tab;
import Editor.Tabbar;
import Engine.Sprite;
import Props.Prop;
import jogamp.opengl.util.av.JavaSoundAudioSink;

public class FileReader {
	public static String ReadFile(String file){
		System.out.println(file);
		String data="";
		try{
		BufferedReader reader = new BufferedReader(new java.io.FileReader(file));
		String line="";
		while((line=reader.readLine())!=null){
		data+=line;
		}
		// System.out.println(data);
		  JSONObject jo = (JSONObject) new JSONParser().parse(data);
		  if(jo.get("Map") != null){
			  //its a map file
			  FileReader.ReadMap(jo, file);
		  }else if(jo.get("Prop")!=null){
			  //its a prop
			  FileReader.ReadPop(jo, file);
		  }
		  else{
			  //invalid file
			  throw(new Exception("Invalid file"));
		  }
		
		

		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		
		return null;
		
	}
	
	
	
	public static void ReadMap(JSONObject jo,String file) throws Exception{
		 


	   // JSONObject jo = (JSONObject) new JSONParser().parse(data);
	   
	    
	   // System.out.println(((JSONObject)jo.get("Map")).get("width"));
	    //System.out.println(((JSONObject)jo.get("Map")).get("height"));
	 
	    int width = Integer.parseInt(((JSONObject)jo.get("Map")).get("width").toString());
	    int height = Integer.parseInt(((JSONObject)jo.get("Map")).get("height").toString());
	 
	    MapRenderer tab = new MapRenderer(width,height);
	    JSONArray ja = (JSONArray) jo.get("Blocks");
	   // System.out.println(ja.size());
	   
	    
	    
	    for(int i=0;i<ja.size();i++){
	    	//System.out.println(ja.get(i));
	    //	JSONObject ob = (JSONObject)ja.get(i);
	   //    System.out.println(ob.get("y"));
	    	JSONObject ob = (JSONObject)ja.get(i);
	    	if(ob==null){
	    		continue;
	    	}
	    
	    	
	    	int x = Integer.parseInt(ob.get("x").toString());
	    	int y = Integer.parseInt(ob.get("y").toString());
	    	int imgx= (int)Float.parseFloat(ob.get("imgx").toString()); 
	    	int imgy= (int)Float.parseFloat(ob.get("imgy").toString()); 
	    	tab.mapData[x][y].imgx=imgx;
	    	tab.mapData[x][y].imgy=imgy;
	    	tab.mapData[x][y].spriteSheet = Engine.Engine.sp.get(0);
	    	//System.out.println("X: " + x + "    Y:"+y + "    imgx:" + imgx + "    imgy:" + imgy);
	       }
	    
	    
	    
	    Tabbar.addTab(tab);
	    
		tab.setName(file.split("/")[file.split("/").length-1]);
 
	//	return data;
	
}
public static void ReadPop(JSONObject jo,String file) throws Exception{
	 


	   // JSONObject jo = (JSONObject) new JSONParser().parse(data);
	   
	    
	   // System.out.println(((JSONObject)jo.get("Map")).get("width"));
	    //System.out.println(((JSONObject)jo.get("Map")).get("height"));
	 
	    int width = Integer.parseInt(((JSONObject)jo.get("Prop")).get("width").toString());
	    int height = Integer.parseInt(((JSONObject)jo.get("Prop")).get("height").toString());
	 float zbar = Float.parseFloat(((JSONObject)jo.get("Prop")).get("Zbar").toString());
	  
	    JSONArray ja = (JSONArray) jo.get("Blocks");
	   // System.out.println(ja.size());
	   
		Sprite[][]data = new Sprite[width][];
		for(int i=0;i<width;i++){
			data[i] = new Sprite[height];
			for(int j=0;j<height;j++){
				data[i][j] = new Sprite(null);
				data[i][j].move(i*32, j*32);
			}
		}
	    
	    for(int i=0;i<ja.size();i++){
	    	//System.out.println(ja.get(i));
	    //	JSONObject ob = (JSONObject)ja.get(i);
	   //    System.out.println(ob.get("y"));
	    	JSONObject ob = (JSONObject)ja.get(i);
	    	if(ob==null){
	    		continue;
	    	}
	    
	    	
	    	int x = Integer.parseInt(ob.get("x").toString());
	    	int y = Integer.parseInt(ob.get("y").toString());
	    	int imgx= (int)Float.parseFloat(ob.get("imgx").toString()); 
	    	int imgy= (int)Float.parseFloat(ob.get("imgy").toString()); 
	    	data[x][y].imgx=imgx;
	    	data[x][y].imgy=imgy;
	    	data[x][y].spriteSheet = Engine.Engine.sp.get(0);
	    	//System.out.println("X: " + x + "    Y:"+y + "    imgx:" + imgx + "    imgy:" + imgy);
	       }
	    
	     Prop p = new Prop(width,height,data);
	     p.setZLine(zbar);
	    
	    PropRenderer tab = new PropRenderer(p);
	    tab.setZBar(zbar);
	    Tabbar.addTab(tab);
	    
		tab.setName(file.split("/")[file.split("/").length-1]);

	//	return data;
	}
}