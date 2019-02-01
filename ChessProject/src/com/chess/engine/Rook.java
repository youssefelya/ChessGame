package com.chess.engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;

public class Rook extends Pice{
	private final static int[] Canididat_Move_vector_Cordinate= {-8,-1,1,8};

	

	public Rook( Alline alline,int picePosition) {
		super(picePosition, alline);
		 
	}

	public Collection<Move> calculateLegalMove(final Board board) {
		ArrayList<Move> legalMoves=new ArrayList<>();
		for(final int CandidateCordinateOffset:Canididat_Move_vector_Cordinate) {
			int candidateDestinationCordinate=this.picePosition;
			while(BoardUtils.isValideTileCoordinate(candidateDestinationCordinate)) {
			  if(isFirstColumExclusion(candidateDestinationCordinate, CandidateCordinateOffset)
				||isEightColumExclusion(candidateDestinationCordinate, CandidateCordinateOffset)) {
				  break;
			  }
				
				candidateDestinationCordinate+=CandidateCordinateOffset; 
			   if(BoardUtils.isValideTileCoordinate(candidateDestinationCordinate)) {
					final Tile candidate=board.getTile(candidateDestinationCordinate);
					if(!candidate.isOccupied()) {
						legalMoves.add(new Move.MajorMove(board,this,candidateDestinationCordinate));
						
					}else {
						final Pice AtDestination= candidate.getPice();
						final Alline piceAlline= AtDestination.getPiceAlline();
						if(this.piceAllines!=piceAlline) {
							legalMoves.add(new Move.AttackMove(board,this,candidateDestinationCordinate,AtDestination));
						}
						break;	}
						       } 		}    	}
		
		 
		return  (ArrayList<Move>) Collections.unmodifiableList(legalMoves);
	}
	private static boolean isFirstColumExclusion(final int currentPosition,final int CandidateOffset) {
		return BoardUtils.First_Culmn[currentPosition]&&(CandidateOffset==-1
				                           ); }
	
	private static boolean isEightColumExclusion(final int currentPosition,final int CandidateOffset) {
		return BoardUtils.EIGHT_COLUMN[currentPosition]&&(CandidateOffset==1); }
	
	
	
	
	
}
