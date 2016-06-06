package senCity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedWriter;



public abstract class AbstractTraces {
	
	Object elmts;
	
	BufferedWriter writer;
	
	public abstract void ajouter(Trace trace);
	
	public abstract AbstractTraces extract(String ssid);
	
	public void load(String nomFichierWifi, String nomFichierGps, int pourcentage) throws IOException {
		File fileWifi, fileGps;
		BufferedReader readerWifi, readerGps;
		Scanner scannerWifi, scannerGps;
		String lineWifi, lineGps, ssid, time, tsWifi, tsGps;;
		double lat, lon, wrote = 0, read = 0;
		int signal;
		
		try {	
			fileWifi = new File(nomFichierWifi);
			readerWifi = new BufferedReader(new FileReader(fileWifi));
			
			fileGps = new File(nomFichierGps);
			readerGps = new BufferedReader(new FileReader(fileGps));
			
			readerWifi.readLine();
			lineWifi = readerWifi.readLine();
			
			readerGps.readLine();
			lineGps = readerGps.readLine();
			
			scannerGps = new Scanner(lineGps);
			scannerGps.useDelimiter(",");
			scannerGps.useLocale(java.util.Locale.US);
			
			time = scannerGps.next();
			tsGps = time.substring(0, 10);
						
			lat = scannerGps.nextDouble();
			lon = scannerGps.nextDouble();
			
			
			while (lineWifi != null && lineGps != null) {

				scannerWifi = new Scanner(lineWifi);
				scannerWifi.useDelimiter(",");
				time = scannerWifi.next();
				tsWifi = time.substring(0, 10);

				while (!tsWifi.equals(tsGps)) {

					if (tsWifi.compareTo(tsGps) < 0) {
						lineWifi = readerWifi.readLine();

						if (lineWifi == null)
							break;
						scannerWifi.close();
						
						scannerWifi = new Scanner(lineWifi);
						scannerWifi.useDelimiter(",");
						
						//println(tsWifi + "");
						time = scannerWifi.next();
						tsWifi = time.substring(0, 10);

					} else if (tsWifi.compareTo(tsGps) >  0) {
						lineGps = readerGps.readLine();

						if (lineGps == null)
							break;

						scannerGps.close();
						scannerGps = new Scanner(lineGps);
						scannerGps.useDelimiter(",");
						scannerGps.useLocale(java.util.Locale.US);
						
						time = scannerGps.next();
						tsGps = time.substring(0, 10);

						lat = scannerGps.nextDouble();
						lon = scannerGps.nextDouble();
					}
					
					//println(tsWifi + " | " + tsGps);

				}
				
				// We only get the info in the columns that interest us 
				scannerWifi.next();
				ssid = scannerWifi.next();
				scannerWifi.next(); scannerWifi.next();
				signal = scannerWifi.nextInt();
				
				read += 1;
				if (! ssid.equals("<hidden>")) {
					wrote += 1;
					this.ajouter(new Trace(tsWifi, ssid, signal, new GPS(lat, lon)));
				}
				
				scannerWifi.close();
				//scannerGps.close();
				
				lineWifi = readerWifi.readLine();
				//lineGps = readerGps.readLine();
				
				if (read > 100 && wrote/read < pourcentage/100.0) {
					readerWifi.close();
					readerGps.close();
					throw new IOException("Trop de donnee wifi ont ete perdues");
				}
			}
			readerWifi.close();
			readerGps.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			//throw e;
		}
	}
	
	public static void print(String s) {
		System.out.print(s);
	}
	
	public static void print(int i) {
		System.out.print("" + i);
	}

	public static void println(String s) {
		System.out.println(s);
	}
	
	public static void println(int i) {
		System.out.println("" + i);
	}
	
}
