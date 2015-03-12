package ghc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class GoogleHashCode {

	public static int R, S, U, P, M;
	
	public static void readFromFile(String fileName) {

		try {
			
			BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
			
			String line = br.readLine();
			
			StringTokenizer st = new StringTokenizer(line);
			
			R = Integer.parseInt(st.nextToken());
			
			System.out.println("R = " + R);
			
			S = Integer.parseInt(st.nextToken());
			
			System.out.println("S = " + S);
			
			U = Integer.parseInt(st.nextToken());
			
			System.out.println("U = " + U);
			
			P = Integer.parseInt(st.nextToken());
			
			System.out.println("P = " + P);
			
			M = Integer.parseInt(st.nextToken());
			
			System.out.println("M = " + M);
			
			
			
//			while (line != null) {
//				
//				System.out.println("String: " + line);
//				
//			}

			br.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public static void writeToFile(String fileName) {
		
		try {
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fileName)));
			
			bw.write("# GoogleHashCode");
			
			bw.write("" + 2015);

			bw.newLine();
			
			bw.write("Google Hash Code ");
			
			bw.write("" + 2015);

			bw.newLine();			
			
			bw.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		long startTime = System.nanoTime();		
		
		System.out.println("Hello World!");
		
		readFromFile("dc.in");
		
		writeToFile("WRITEME.txt");
		
		long endTime = System.nanoTime();
		
		System.out.println("Running time 2: " + ((endTime - startTime) / 1000000.0) + " seconds");

	}

}
