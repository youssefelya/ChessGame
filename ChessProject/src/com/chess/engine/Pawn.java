package com.chess.engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;

public class Pawn extends Pice {
 private final static int[] Candidate_Move_Coordinate= {8,16,7,9 };
private int candidateDestinationCordinate;

	public Pawn(final Alline alline,final int picePosition) {
		super(picePosition, alline);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collection<Move> calculateLegalMove(final Board board) {
 final ArrayList<Move>legalMoves=new ArrayList<Move>(); 
	for(final int currentCandidateOffset:Candidate_Move_Coordinate) {
		final int CandidateDestinitionCoordinate=this.picePosition+this.piceAllines.getDirection()*currentCandidateOffset;
		
		if(!BoardUtils.isValideTileCoordinate(CandidateDestinitionCoordinate)) {
			continue;
		}
		if(currentCandidateOffset==8&&!board.getTile(CandidateDestinitionCoordinate).isOccupied()) {//##################
			legalMoves.add(new Move.MajorMove(board,this,candidateDestinationCordinate));
		}else if(currentCandidateOffset==16&&this.isFirstMove()&&
				(BoardUtils.SECONDE_ROW[this.picePosition]
						&&this.getPiceAlline().isBlack() )||
				(BoardUtils.SEVENTH_ROW[this.picePosition]&&this.piceAllines.isWhite())){
			final int behindCandidateDEstinationCoordinate=this.picePosition+(this.piceAllines.getDirection()*8); 
			if(!board.getTile(behindCandidateDEstinationCoordinate).isOccupied(
	)&& !board.getTile(candidateDestinationCordinate).isOccupied()
				) {
				legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCordinate));
				
			}
		 	
		}else if(currentCandidateOffset==7&&!(
			(BoardUtils.EIGHT_COLUMN[this.picePosition]
				&&this.piceAllines.isWhite())
			||(BoardUtils.First_Culmn[this.picePosition]
				&&this.piceAllines.isBlack()))) {
			if(board.getTile(CandidateDestinitionCoordinate).isOccupied()) {
			final Pice piceCandidate=board.getTile(CandidateDestinitionCoordinate).getPice() ;
			if(this.piceAllines!=piceCandidate.piceAllines){
				//TO DOO he pot MajorMove#############
				legalMoves.add(new Move.AttackMove(board, this, picePosition, piceCandidate));
			}
			}
			
			
		}else if(currentCandidateOffset==9&&
			 !(BoardUtils.First_Culmn[this.picePosition])&&
			  this.piceAllines.isWhite()||(BoardUtils.EIGHT_COLUMN[this.picePosition]&&
					  this.piceAllines.isBlack())){
			if(board.getTile(CandidateDestinitionCoordinate).isOccupied()) {
				final Pice piceCandidate=board.getTile(CandidateDestinitionCoordinate).getPice() ;
				if(this.piceAllines!=piceCandidate.piceAllines){
					//TO DOO he pot MajorMove#############
					legalMoves.add(new Move.AttackMove(board, this, picePosition, piceCandidate));
				}
				
				}
			}
			
		
		
	}
		
		return  (ArrayList<Move>) Collections.unmodifiableList(legalMoves);
	}

 

}
