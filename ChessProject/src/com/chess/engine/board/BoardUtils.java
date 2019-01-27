package com.chess.engine.board;

public class BoardUtils {
	

	public static final boolean[] First_Culmn = initColum(0);
	public static final boolean[] SECONDE_Culmn =  initColum(1);
	public static final boolean[] SEVEN_Colmn =  initColum(6);
	public static final boolean[] EIGHT_COLUMN =  initColum(7);
	
	public static final int num_Tile=64;
	public static final int num_Tile_Row=8;
	
	
	
	
	
	

	private BoardUtils() {
	 throw new RuntimeException("You cannot istantiate me!");
	 
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
