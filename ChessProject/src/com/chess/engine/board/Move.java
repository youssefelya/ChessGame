package com.chess.engine.board;

import com.chess.engine.Piece;
import com.chess.engine.board.Board.Builder;

public abstract class Move {
	final Board board; 
	final Piece movedPiece; 
	final int distinationcordinate;
	public Move(final Board board,final Piece picemove,final int d) {
		this.distinationcordinate=d;
		this.board = board;
		this.movedPiece = picemove;
	}
	public abstract Board excute(); 
	
	
	public Piece getMovePiece() {
		return this.movedPiece;
	}
	
	
	
	public static final class MajorMove extends Move{

		public MajorMove(final Board board,final Piece picemove,final int d) {
			super(board, picemove, d);
			 
		}
		
	
		

		@Override
		public Board excute() {	 
			final  Builder builder=new Builder();
			for(final Piece piec:this.board.currentPlayer().getActivePieces()) {
				//to do hashcode and equals for pieces
			if(!this.movedPiece.equals(piec)) {
				builder.setPiece(piec);		}
			}
		for(final Piece piec:this.board.currentPlayer().getOpponet().getActivePieces()) {
			builder.setPiece(piec);
		}	
		    //move the moved piece;
		builder.setPiece(this.movedPiece.movePiece(this));
			builder.setMoveMaker(this.board.currentPlayer().getOpponet().getAllience());
			return builder.build();
		}
		
		
		
	}
	
	
	public static final class AttackMove extends Move{
		 final Piece AttackPice;   
		public AttackMove(final Board board,final Piece picemove,final int d,
				final Piece attackpice) {
			super(board, picemove, d);
			this.AttackPice=attackpice;
 
		}
		@Override
		public Board excute() {
			// TODO Auto-generated method stub
			return null;
		}
		
		
	}


	public Integer getDestinationCoordinate() {
		 
		return this.getDestinationCoordinate();
	}



	
	
	
	

}
