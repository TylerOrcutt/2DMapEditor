package io;

import java.io.BufferedReader;
 
import java.io.File;
 
import java.io.InputStreamReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Editor.MapRenderer;
import Editor.Tab;
import Editor.Tabbar;
import Engine.Sprite;
import jogamp.opengl.util.av.JavaSoundAudioSink;

public class FileReader {

	public static String ReadMap(String file){
		System.out.println(file);
		String data="";
		try{
		BufferedReader reader = new BufferedReader(new java.io.FileReader(file));
		String line="";
		while((line=reader.readLine())!=null){
		data+=line;
		}
		System.out.println(data);


	    JSONObject jo = (JSONObject) new JSONParser().parse(data);
	   
	    
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

		
		
		}catch(Exception e){
			System.out.println("Threw expection" + e.getMessage());
		}
		return data;
	}
}
