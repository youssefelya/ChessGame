package Board;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import Pieces.Piece;

public abstract class Tile {
	//This class is abstract beceause we would like to know
	// if it's occupied or not 
	protected final int  tileCoordinate;
	private static final Map<Integer,EmptyTile> 
	      empty_tile_cache=creatAllpossibleEmptyTile();
	

	
	public static Tile creatTil(final int coordinate,final Piece pice) {
		return pice!=null?new OccupiedTile(coordinate, pice)
				:empty_tile_cache.get(coordinate);
	}
	
	
	 private Tile(int tileCoordinate) {
		this.tileCoordinate = tileCoordinate;
	}
	
	private static Map<Integer, EmptyTile> creatAllpossibleEmptyTile() {
		final Map<Integer,EmptyTile> emptytile=new HashMap<>();
		
		for(int i=0;i<BoardUtils.num_Tile;i++) {
			emptytile.put(i, new EmptyTile(i));
		}
		
		return ImmutableMap.copyOf(emptytile);
	}

	public abstract boolean isOccupied();
	 public abstract Piece getPice();
	 
	 
	 
	 
	 
	 
	 //
	public static final class EmptyTile extends Tile {
	private	EmptyTile(final int coordinate){
			super(coordinate);
		}
		
		public  boolean isOccupied() {
			return false;
		//Place is with coordinate XX is NOT OCCUPIED
		}
		
		@Override
		public String toString() {
			return "-";
		}
		
		public  Piece getPice() {return null; };
	}

	
	
	
	
	
	
 public static final class OccupiedTile extends Tile{
	  private final Piece picetile; 
	 private OccupiedTile(int coordinate,Piece pice) {
		 super(coordinate); 
		 this.picetile=pice;
	 }
	@Override
	public boolean isOccupied() {
		return true;
		//this time it's occupied !! 
	}
	
	@Override
	public String toString() {
		return  getPice().getPiceAlline().isBlack() ? getPice().toString().toLowerCase():
			getPice().toString();
	}
	
	@Override
	public Piece getPice() { 
		return this.picetile;
		//return pice, so we can know what pice in that place.
	}
	 
 }







public int getTileCoordinate() {
	// TODO Auto-generated method stub
	return  this.tileCoordinate;
}





 
	

}
