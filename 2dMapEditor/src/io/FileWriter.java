package io;

import java.io.PrintWriter;

import Editor.Tabbar;
import Engine.Engine;
import Engine.Sprite;
import Engine.SpriteSheet;
public class FileWriter {

	public static void writeFile(String file){
		try{
			PrintWriter pr = new PrintWriter(file);
			if(Tabbar.activeTab !=null){
				pr.print(Tabbar.activeTab.generateJSON());
			}
			pr.close();
		}catch(Exception e){
			System.out.println("Failed to write map file");
			return;
		}
	}
}
