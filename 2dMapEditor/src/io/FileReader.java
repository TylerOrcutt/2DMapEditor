package io;

import java.io.BufferedReader;
 
import java.io.File;
 
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Editor.MapRenderer;
import Editor.PropRenderer;
import Editor.Tab;
import Editor.Tabbar;
import Engine.Engine;
import Engine.Sprite;
import Props.Prop;
import Props.propData;
import jogamp.opengl.util.av.JavaSoundAudioSink;

public class FileReader {
	public static String ReadFile(String file,boolean openTab){
		System.out.println(file);
		String data="";
		try{
		BufferedReader reader = new BufferedReader(new java.io.FileReader(file));
		String line="";
		while((line=reader.readLine())!=null){
		data+=line;
		}
		reader.close();
		// System.out.println(data);
		  JSONObject jo = (JSONObject) new JSONParser().parse(data);
		  if(jo.get("Map") != null){
			  //its a map file
			  FileReader.ReadMap(jo, file);
		  }else if(jo.get("Prop")!=null){
			  //its a prop
			  FileReader.ReadPop(jo, file,openTab);
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
	    	tab.mapData[x][y].spriteSheet = Engine.sp.get(0);
	    	//System.out.println("X: " + x + "    Y:"+y + "    imgx:" + imgx + "    imgy:" + imgy);
	       }
	    
	    
	     
		     ja = (JSONArray) jo.get("Props");
		
		     for(int i=0;i<ja.size();i++){
		    	//System.out.println(ja.get(i));
		    //	JSONObject ob = (JSONObject)ja.get(i);
		   //    System.out.println(ob.get("y"));
		    	JSONObject ob = (JSONObject)ja.get(i);
		    	if(ob==null){
		    		continue;
		    	}
		           
		  
		    	String name = ob.get("name").toString();
		    	System.out.println("searching for "+ name);
		    	Prop pp = null;
		    	boolean propfound=false;
		    	for(int p=0;p<Engine.props.size();p++){
		    	 	//System.out.println("found "+ Engine.props.get(p).name);
		    		if(Engine.props.get(p).name.equals(name)){
		    			propfound=true;
		    			pp=Engine.props.get(p);
		    		}
		    	}
		    	if(!propfound){
		    		//load prop
		    		String path = file.split("Maps/")[0];
		    	   FileReader.ReadFile(path+"/Props/"+name+".prop",false);
		    	   pp=Engine.props.get(Engine.props.size()-1);
		    	   
		    		
		    		
		    	    
		    	}
		    	
		    	if(pp!=null){
		    	float x = Float.parseFloat(ob.get("x").toString());
		    	float y = Float.parseFloat(ob.get("y").toString());
		    	propData pd = new propData(pp, x, y);
		    	tab.props.add(pd);
		    	
		    	//System.out.println("X: " + x + "    Y:"+y + "    imgx:" + imgx + "    imgy:" + imgy);
		       }
		     }
	     
	     System.out.println("Adding map tab  " );
	     
	    Tabbar.addTab(tab);
	    
		tab.setName(file.split("/")[file.split("/").length-1].split(".map")[0]);
 
	//	return data;
	
}
public static Prop ReadPop(JSONObject jo,String file,boolean openTab) throws Exception{
	 


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
	    	data[x][y].spriteSheet =Engine.sp.get(0);
	    	//System.out.println("X: " + x + "    Y:"+y + "    imgx:" + imgx + "    imgy:" + imgy);
	       }
	    
	     Prop p = new Prop(width,height,data);
	     p.setZLine(zbar);
	     p.name=file.split("/")[file.split("/").length-1].split(".prop")[0];
	     
	     System.out.println("adding prop:  '" +p.name+"'");
	  //   Engine.props.add(p);
	     
	    if(openTab){
	     PropRenderer tab = new PropRenderer(p);
	    tab.setZBar(zbar);
	    Tabbar.addTab(tab);
	    tab.setName(file.split("/")[file.split("/").length-1].split(".prop")[0]);
	    }else{
	    	Engine.props.add(p);
	    }
	    return p;
	//	return data;
	}
}