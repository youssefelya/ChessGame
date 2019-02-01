package com.chess.engine;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;

public class Knight extends Pice{
	
	private final static int[] CANDIDAT_MOVES_COORDINET= {-17,-15,-16,
			-6,6,16,15,17};
	

	public Knight(Alline alline, int picePosition) {
		super(picePosition, alline);
	 
	}

 
	public Collection<Move> calculateLegalMove(final Board board) {
		
		int candidatDistinationCoordinate;
		final ArrayList<Move> legalMove=new ArrayList<Move>();
		for(final int currentCandidatOffset : CANDIDAT_MOVES_COORDINET) {
			candidatDistinationCoordinate=this.picePosition+currentCandidatOffset;
			if(BoardUtils.isValideTileCoordinate(candidatDistinationCoordinate)){
				if(isFirstColumExclusion(this.picePosition, currentCandidatOffset)
						||isSecondetColumExclusion(this.picePosition, currentCandidatOffset)
						||isEightColumExclusion(this.picePosition, currentCandidatOffset)
						||isSeventhColumExclusion(this.picePosition, currentCandidatOffset)
						) {
					continue;
				}
				
				final Tile candidate=board.getTile(candidatDistinationCoordinate);
			if(!candidate.isOccupied()) {
				legalMove.add(new Move.MajorMove(board,this,candidatDistinationCoordinate));
				
			}else {
				final Pice AtDestination= candidate.getPice();
				final Alline piceAlline= AtDestination.getPiceAlline();
				if(this.piceAllines!=piceAlline) {
					legalMove.add(new Move.AttackMove(board,this,candidatDistinationCoordinate,AtDestination));
				}
						}
			}
		}
		 
		return (ArrayList<Move>) Collections.unmodifiableList(legalMove);
	}
	
	
	
	private static boolean isFirstColumExclusion(final int currentposition,final int candidateOffset) {
		return  BoardUtils.First_Culmn[currentposition]&&((candidateOffset==-17)||(candidateOffset==-10)
				||(candidateOffset==6)||(candidateOffset==15)	
			);
		
		
	}
	
	
	private static boolean isSecondetColumExclusion(final int currentposition,final int candidateOffset) {
		return  BoardUtils.SECONDE_Culmn[currentposition]&&
				((candidateOffset==-10)||(candidateOffset==-6)	 	);
		
		
	}
	
	
	
	private static boolean isSeventhColumExclusion(final int currentposition,final int candidateOffset) {
		return  BoardUtils.SEVEN_Colmn[currentposition]&&
				((candidateOffset==-6)||(candidateOffset==10)	 	);
		
		
	}
	
	
	
	private static boolean isEightColumExclusion(final int currentposition,final int candidateOffset) {
		return  BoardUtils.EIGHT_COLUMN[currentposition]&&
				((candidateOffset==-15)||(candidateOffset==-6)	
						||(candidateOffset==10)	
						||(candidateOffset==-17));
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
