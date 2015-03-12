package ghc;

import java.util.Iterator;

public class Alg1 {

	public static int poolID;
	public static int line;
	
	public static int[][] POOLMAT = new int[GoogleHashCode.P][GoogleHashCode.R];
	
	public static int[] CAPPOOL = new int[GoogleHashCode.P];
	
	public static int[] LASTROWMAX = new int[GoogleHashCode.P];
	
	public static int SCORE;
	
	static public void placeServers() {
		
		poolID = 0;
		Iterator<Server> i = GoogleHashCode.servers.iterator();
		line = 0;
		while (i.hasNext()) {
			Server s = i.next();
			placeServer(s);
			PrinterHC.clearscreen();
			PrinterHC.printMatrix(0, 0);
			
			SCORE = 10000000;
			
			for (int p = 0; p < GoogleHashCode.P; p++) {
				
				int score = CAPPOOL[p] - LASTROWMAX[p];
				
				if (score < SCORE) {
					
					SCORE = score;
				}
				
				System.out.print(score + " ");
			}
			
			System.out.println("score - " + SCORE);
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	static public boolean placeServer(Server s) {
		for (int i = 0; i < GoogleHashCode.R; i++) {
			for (int j = 0; j < GoogleHashCode.S; j++) {
				if (canPlace(s, line, j)) {
					for (int k = 0; k < s.noslots; k++) {
						GoogleHashCode.RS[line][j+k] = s.no;
					}
					s.row = line;
					s.slot = j;
					s.pool = poolID % GoogleHashCode.P;
					
					POOLMAT[s.pool][s.row] += s.capacity;
					
					CAPPOOL[s.pool] += s.capacity;
					
					if (LASTROWMAX[s.pool] < POOLMAT[s.pool][s.row]) {
						
						LASTROWMAX[s.pool] = POOLMAT[s.pool][s.row];
					}
					
					poolID++;
					line++;
					line %= GoogleHashCode.R;
					return true;
				}
			}
			line++;
			line %= GoogleHashCode.R;
		}
		return false;
	}
	
	static public boolean canPlace(Server s, int i, int j) {
		if (j+s.noslots >= GoogleHashCode.S)
			return false;
		for (int k = 0; k < s.noslots; k++) {
			if (GoogleHashCode.RS[i][j+k] != -2) {
				return false;
			}
		}
		return true;
	}
	
}
