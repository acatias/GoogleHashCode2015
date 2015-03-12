package ghc;

public class PrinterHC {
	
	public static void clearscreen() {
		char escCode = 0x1B;
		System.out.print(String.format("%c[2J",escCode));
	}
	
	public static void gotoxy(int x, int y) {
		char escCode = 0x1B;
		System.out.print(String.format("%c[%d;%df",escCode,x,y));
	}

	public static void printMatrix(int x, int y) {
		gotoxy(x,y);
		for (int i = 0; i < GoogleHashCode.R; i++) {
			
			for (int j = 0; j < GoogleHashCode.S; j++) {
				
				if (GoogleHashCode.RS[i][j] == -2) {
				
					System.out.print(" .  ");
					
				} else {
					
					System.out.print(String.format("%3d ", GoogleHashCode.RS[i][j]));
				}
			}
			
			System.out.println();
			System.out.println();
		}
		
	}
	
}
