package io;

import java.io.PrintWriter;
import Engine.Engine;
import Engine.Sprite;
import Engine.SpriteSheet;
public class FileWriter {

	public static void writeFile(String file){
		try{
			PrintWriter pr = new PrintWriter(file);
			pr.print("{");
			
			for(int i=0;i<Engine.sp.size();i++){
			pr.print("\"SpriteSheet\":{");
			pr.print("\"loc\":\"images/sp2.png\",");
			pr.print("\"id\":\"\""+i+"\"");
			pr.print("},");
		}
			if(!Engine.useSizedMap){
	
	
			for (Sprite sp: Engine.sprites){
				pr.print("\"Block\":{");
				pr.print("\"x\":\""+sp.x+"\",");
				pr.print("\"y\":\""+sp.y+"\",");
				pr.print("\"width\":\""+sp.width+"\",");
				pr.print("\"height\":\""+sp.height+"\"");
				pr.print("\"imgx\":\""+sp.imgx+"\"");
				pr.print("\"imgy\":\""+sp.imgy+"\"");
				pr.print("},");				
						
						
				
				
				
			
		
			}
			}else{
	//			pr.print(Engine.sizedMap.generateJSON());
			}
			pr.print("}");
			pr.close();
		}catch(Exception e){
			System.out.println("Failed to write map file");
			return;
		}
	}
}
