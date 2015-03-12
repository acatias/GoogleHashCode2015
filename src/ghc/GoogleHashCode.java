package ghc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

class Server {
	
	public int row;
	public int slot;
	public int pool;
	
	public int capacity;
	public int noslots;
	
	public double power;
	public int no;
	
	Server(int no, int row, int slot, int pool, int capacity, int noslots) {
		
		this.no = no;
		this.row = row;
		this.slot = slot;
		this.pool = pool;
		
		this.capacity = capacity;
		this.noslots = noslots;
		
		this.power = ((double) capacity) / noslots;
	}
	
}

public class GoogleHashCode {

	public static int R;
	
	public static int S;
	
	public static int U;
	
	public static int P;
	
	public static int M;
	
	public static int[][] RS; // row-slot matrix : -2 available, -1 unavailable
	
	public static int[] SS; // slots per server
	
	public static int[] CS; // capacity per server
	
	public static Server[] SERVERS;
	
	public static ArrayList<Server> servers;
	
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
			
			RS = new int[R][S];
			
			for (int i = 0; i < R; i++) {
				
				for (int j = 0; j < S; j++) {
					
					RS[i][j] = -2;
				}
			}
			
			for (int i = 0; i < U; i++) {
				
				line = br.readLine();
				
				st = new StringTokenizer(line);
				
				int r = Integer.parseInt(st.nextToken());
				
				int s = Integer.parseInt(st.nextToken());
				
				RS[r][s] = -1; 
			}
			
			System.out.println("RS[0][0] = " + RS[0][0]);
			
			System.out.println("RS[10][23] = " + RS[10][23]);
			
			SS = new int[M];
			
			CS = new int[M];
			
			SERVERS = new Server[M];
			
			for (int i = 0; i < M; i++) {
				
				line = br.readLine();
				
				st = new StringTokenizer(line);
				
				int ss = Integer.parseInt(st.nextToken());
				
				int cs = Integer.parseInt(st.nextToken());
				
				SS[i] = ss;
				
				CS[i] = cs;
				
				SERVERS[i] = new Server(i, -1, -1, -1, cs, ss);
			}
			
			br.close();
			
			servers = new ArrayList<Server>();
			for (int i =0; i<M; i++) {
				servers.add(SERVERS[i]);
			}

		    Collections.sort(servers, new Comparator<Server>() {

		        public int compare(Server o1, Server o2) {
		            return (int)Math.round(o2.power - o1.power);
		        }
		    });
			
		    System.out.println("Server 625 - Sloturi =  " + SS[624] +  " Capacity = " + CS[624]);
			

			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public static void printMatrix(String code) {
		
		System.out.println("Matrix " + code);
		
		for (int i = 0; i < R; i++) {
			
			for (int j = 0; j < S; j++) {
				
				if (RS[i][j] == -2) {
				
					System.out.print(".");
					
				} else {
					
					System.out.print("x");
				}
			}
			
			System.out.println();
		}
	}
	
	public static void writeToFile(String fileName) {
		
		try {
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fileName)));
			
			for (int i = 0; i < M; i++) {
				
				if (SERVERS[i].row == -1) {
					
					bw.write("x");

					bw.newLine();
					
				} else {
					
					bw.write(SERVERS[i].row + " " + SERVERS[i].slot + " " + SERVERS[i].pool);
					
					bw.newLine();
				}
			}	
			
			bw.close();
						
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		long startTime = System.nanoTime();		
		
		System.out.println("Hello World!");
		
		readFromFile("dc.in");

		Alg1.placeServers();

		writeToFile("dc.out");

		long endTime = System.nanoTime();
		
		System.out.println("Running time 2: " + ((endTime - startTime) / 1000000.0) + " seconds");

	}

}
