package com.chess.engine;

import java.awt.List;
import java.util.ArrayList;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;

public class Knight extends Pice{
	
	private final static int[] CANDIDAT_MOVES_COORDINET= {-17,-15,-16,
			-6,6,16,15,17};
	

	Knight(int picePosition, Alline alline) {
		super(picePosition, alline);
	 
	}

 
	public ArrayList<Move> calculateLegalMove(Board board) {
		int candidatDistination;
		final ArrayList<Move> legalMove=new ArrayList<Move>();
		for(final int co : CANDIDAT_MOVES_COORDINET) {
			candidatDistination=this.picePosition+co;
			if(true){ final Tile candidate=board.getTile(candidatDistination);
			if(!candidate.isOccupied()) {
				legalMove.add(new Move());
				
			}
			}
		}
		 
		return null;
	}

}
