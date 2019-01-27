package com.chess.engine;
import com.chess.engine.board.Move;

import java.util.Collection;

import com.chess.engine.board.Board;

public abstract class Pice {
	protected final int picePosition; 
	protected final Alline piceAllines;
	
	Pice(final int picePosition,final Alline alline){
		this.picePosition=picePosition;
		this. piceAllines=alline;
     	}
	
	public Alline getPiceAlline() {
		return this.piceAllines;
	}
	
	public abstract Collection<Move> calculateLegalMove(final Board board);
	
	
	
	

}
