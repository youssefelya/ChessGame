package com.chess.engine.board;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.chess.engine.Pice;

public abstract class Tile {
	//This class is abstract becease we would like to know
	// if it's occupied or not 
	protected final int  tileCoordinate;
	private static final Map<Integer,EmptyTile> empty_tile_cache=creatAllpossibleEmptyTile();
	

	
	public static Tile creatTil(final int coordinate,final Pice pice) {
		return pice!=null?new OccupiedTile(coordinate, pice):empty_tile_cache.get(coordinate);
	}
	
	
	 private Tile(int tileCoordinate) {
		this.tileCoordinate = tileCoordinate;
	}
	
	private static Map<Integer, EmptyTile> creatAllpossibleEmptyTile() {
		final Map<Integer,EmptyTile> emptytile=new HashMap<>();
		
		for(int i=0;i<BoardUtils.num_Tile;i++) {
			emptytile.put(i, new EmptyTile(i));
		}
		
		return Collections.unmodifiableMap(emptytile);
	}

	public abstract boolean isOccupied();
	 public abstract Pice getPice();
	 
	 
	 
	 
	 
	 
	 //
	public static final class EmptyTile extends Tile {
	private	EmptyTile(final int coordinate){
			super(coordinate);
		}
		
		public  boolean isOccupied() {return false;
		//Place is with coordinate XX is NOT OCCUPIED
		}
		public  Pice getPice() {return null; };
	}

	
	
	
	
	
	
 public static final class OccupiedTile extends Tile{
	  private final Pice picetile; 
	 private OccupiedTile(int coordinate,Pice pice) {
		 super(coordinate); 
		 this.picetile=pice;
	 }
	@Override
	public boolean isOccupied() {
		return true;
		//this time it's occupied !! 
	}
	@Override
	public Pice getPice() { 
		return this.picetile;
		//return pice, so we can know what pice in that place.
	}
	 
 }
	

}
