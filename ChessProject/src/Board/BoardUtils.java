package Board;

public class BoardUtils {
	

	public static final boolean[] First_Culmn = initColum(0);
	public static final boolean[] SECONDE_Culmn =  initColum(1);
	public static final boolean[] SEVEN_Colmn =  initColum(6);
	public static final boolean[] EIGHT_COLUMN =  initColum(7);
	
	
	public static final boolean[] EIGHT_RANK=initRow(0);
	public static final boolean[] SEVENTH_RANK=initRow(8);
	public static final boolean[] SIXTH_RANK=initRow(16);
	public static final boolean[] FIFTH_RANK=initRow(24);
	public static final boolean[] FOURTH_RANK=initRow(32);
	public static final boolean[] THIRD_RANK=initRow(40);
	public static final boolean[] SECOND_RANK=initRow(48);
	public static final boolean[] FIRST_RANK=initRow(56);
	
	public static final int num_Tile=64;
	public static final int num_Tile_Row=8;
	
	
	
	
	
	

	private BoardUtils() {
	 throw new RuntimeException("You cannot istantiate me!");
	 
	}

	private static boolean[] initRow(int rowNumber) {
		final boolean[] row=new boolean[num_Tile];
		
		do {
			row[rowNumber]=true;
			rowNumber++;
			
		}while(rowNumber%num_Tile_Row!=0);
		return row; 
	}

	private static boolean[] initColum(int i) {
		 final boolean[] column=new boolean[64];
		 do {
			 column[i]=true;
			 i+=8;
			 
		 }while(i<64);
		return column;
	}

	public static boolean isValideTileCoordinate(final int candidat) {
		return candidat>=0&&candidat<num_Tile;
	}

}
