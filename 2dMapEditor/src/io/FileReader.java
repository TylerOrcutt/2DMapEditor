package io;

import java.io.BufferedReader;
import java.io.File;
 
import java.io.InputStreamReader;

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
		System.out.println(data);
		
		}catch(Exception e){
			
		}
		return data;
	}
}
