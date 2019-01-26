package com.chess.engine;
import com.chess.engine.board.Move;
import com.chess.engine.board.Board;
import java.util.ArrayList;

public abstract class Pice {
	protected final int picePosition; 
	protected final Alline piceAllines;
	
	Pice(final int picePosition,final Alline alline){
		this.picePosition=picePosition;
		this. piceAllines=alline;
     	}
	
	public abstract ArrayList<Move> calculateLegalMove(final Board board);
	
	
	
	

}
