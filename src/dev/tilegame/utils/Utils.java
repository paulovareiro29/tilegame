package dev.tilegame.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utils {
public static String loadFileAsString(String path){
	StringBuilder builder = new StringBuilder();
	
	try{
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line;
		while((line = br.readLine()) != null)
			builder.append(line + "\n");
			br.close();
		
	}catch(IOException e){}
	return builder.toString();

}

	public static int parseInt(String number){
		try{
			return Integer.parseInt(number);
		}catch(NumberFormatException e){
			e.printStackTrace();
			return 0;}
	}

	public static long map(long x, long in_min, long in_max, long out_min, long out_max)
	{
		  return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
		}
}
