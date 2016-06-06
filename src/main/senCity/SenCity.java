package main.senCity;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;

public class SenCity {
	
	public static void main(String[] args) {
		File file = new File("/src/main/senCity/capture_wifi.csv");
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			
			Scanner scanner = new Scanner(line);
			scanner.useDelimiter(",");
			
			String elm = scanner.next();
			
			System.out.println(line);
			System.out.println(elm);
			
			scanner.close();
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			//reader.close();
		}
		
		
		
		
	}
	

}
