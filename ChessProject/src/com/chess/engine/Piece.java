package com.chess.engine;
import com.chess.engine.board.Move;

import java.util.Collection;

import com.chess.engine.board.Board;

public abstract class Piece {
	protected final int picePosition; 
	protected final Alliance piceAllines;
	protected final boolean isFirstMoves;
	protected final PieceType pieceType;
	private final int cacheHashCode;
	
	
	
	Piece(final PieceType picetype,
			final int picePosition,
			final Alliance alline){
		this.picePosition=picePosition;
		this. piceAllines=alline;
		//Move Work to do here 
		this.isFirstMoves=false;
		this.pieceType=picetype;
		this.cacheHashCode=computeHashCode();
     	}
	
	private int computeHashCode() {
		int result =pieceType.hashCode();
		result=31*result+piceAllines.hashCode();
		result=31*result+picePosition;
		result=31*result+(isFirstMoves?1:0);
		return result;
	}

	@Override
	public boolean equals(final Object other) {
		if(this==other) {return true;}
		if(!(other instanceof Piece )) {
			return false;
		}
		final Piece otherPiece=(Piece) other;
		return picePosition==otherPiece.getPicePosition()&&
				pieceType==otherPiece.getPieceType()&&
				piceAllines==otherPiece.getPiceAlline()&&
				isFirstMoves==otherPiece.isFirstMoves;
	}
	
	@Override
	public int hashCode() {	
		return this.cacheHashCode;
	}
	
	
	
	public Alliance getPiceAlline() {
		return this.piceAllines;
	}
	public boolean isFirstMove() {
		return isFirstMoves; 
	}
	
	public abstract Collection<Move> calculateLegalMove(final Board board);

	
	public Integer getPicePosition() {
	 
		return this.picePosition;
	}
	public PieceType getPieceType() {
		return this.pieceType;
	}
	
	
	
	
	
	
	
	public enum PieceType{
		PAWN("p") {
			@Override
			public boolean isKing() {
				// TODO Auto-generated method stub
				return false;
			}
		},
		KNIGHT("N") {
			@Override
			public boolean isKing() {
				// TODO Auto-generated method stub
				return false;
			}
		},
		BISHOP("B") {
			@Override
			public boolean isKing() {
				// TODO Auto-generated method stub
				return false;
			}
		},
		ROOK("R") {
			@Override
			public boolean isKing() {
				// TODO Auto-generated method stub
				return false;
			}
		}, QUEEN("Q") {
			@Override
			public boolean isKing() {
				// TODO Auto-generated method stub
				return false;
			}
		},KING("K") {
			@Override
			public boolean isKing() {
				// TODO Auto-generated method stub
				return true;
			}
		};
		private String pieceName; 
		PieceType(final String pieceName){
			this.pieceName=pieceName;
		};
		@Override
		public String toString() {
			return this.pieceName;
		}
		
		public abstract boolean isKing();
		
	}
	
	
	public abstract Piece movePiece(Move move);

}
