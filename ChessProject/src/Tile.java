
public abstract class Tile {
	int  tileCoordinate;

	public Tile(int tileCoordinate) {
		this.tileCoordinate = tileCoordinate;
	}
	
	public abstract boolean isOccupied();
	//public abstract Pice getPice();
	public static final class EmptyTile extends Tile {
		EmptyTile(int coordinate){
			super(coordinate);
		}
		
		public  boolean isOccupied() {return false;};
	//	public  Pice getPice() {return null; };
	}

 
	

}
