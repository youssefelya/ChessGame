package com.chess.engine.board;

import com.chess.engine.Pice;

public abstract class Move {
	final Board board; 
	final Pice picemove; 
	final int distinationcordinate;
	public Move(final Board board,final Pice picemove,final int d) {
		this.distinationcordinate=d;
		this.board = board;
		this.picemove = picemove;
	}
	
	
	public static final class MajorMove extends Move{

		public MajorMove(Board board, Pice picemove, int d) {
			super(board, picemove, d);
			 
		}
		
		
	}
	
	
	public static final class AttackMove extends Move{
		 final Pice AttackPice;   
		public AttackMove(Board board, Pice picemove, int d,
				final Pice attackpice) {
			super(board, picemove, d);
			this.AttackPice=attackpice;
 
		}
		
		
	}
	
	
	
	

}
