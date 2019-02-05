package com.chess.engine;
import com.chess.engine.board.Move;

import java.util.Collection;

import com.chess.engine.board.Board;

public abstract class Pice {
	protected final int picePosition; 
	protected final Alline piceAllines;
	protected final boolean isFirstMoves;
	
	Pice(final int picePosition,final Alline alline){
		this.picePosition=picePosition;
		this. piceAllines=alline;
		//Move Work to do here 
		this.isFirstMoves=false;
     	}
	
	public Alline getPiceAlline() {
		return this.piceAllines;
	}
	public boolean isFirstMove() {
		return isFirstMoves; 
	}
	
	public abstract Collection<Move> calculateLegalMove(final Board board);

	
	public Integer getPicePosition() {
	 
		return this.picePosition;
	}
	
	
	
	public enum PieceType{
		PAWN("p"),
		KNIGHT("N"),
		BISHOP("B"),
		ROOK("R"), QUEEN("Q"),KING("K");
		private String pieceName; 
		PieceType(final String pieceName){
			this.pieceName=pieceName;
		};
		@Override
		public String toString() {
			return this.pieceName;
		}
		
	}

}
