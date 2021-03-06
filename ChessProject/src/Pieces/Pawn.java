package Pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;

import Board.Board;
import Board.BoardUtils;
import Board.Move;
import Board.Move.AttackMove;
import Board.Move.MajorMove;
import Pieces.Piece.PieceType;

public class Pawn extends Piece {
 private final static int[] Candidate_Move_Coordinate= {8,16,7,9};
private int candidateDestinationCordinate;

	public Pawn(final Alliance alline,final int picePosition) {
		super(PieceType.PAWN, picePosition, alline);
		// TODO Auto-generated constructor stub
	}
	
	
	public Pawn movePiece(Move move) {
		 
		return new Pawn(move.getMovePiece().getPiceAlline(),
				move.getDestinationCoordinate());
	}
	
	
	
	@Override
	public String toString() {
		return  PieceType.PAWN.toString();
	}

	@Override
	public Collection<Move> calculateLegalMove(final Board board) {
               final ArrayList<Move>legalMoves=new ArrayList<Move>(); 
	            for(final int currentCandidateOffset:Candidate_Move_Coordinate) {
                    final int CandidateDestinitionCoordinate=this.picePosition+
             this.piceAllines.getDirection()*currentCandidateOffset;
		
	if(!BoardUtils.isValideTileCoordinate
			(CandidateDestinitionCoordinate)) {
			continue;
		}
		if(currentCandidateOffset==8&&
    !board.getTile(CandidateDestinitionCoordinate).isOccupied()) {
			//##################
       legalMoves.add(new  MajorMove(board,
    		   this,candidateDestinationCordinate));
              }
		else 
	if(currentCandidateOffset==16&&this.isFirstMove()&&
				(BoardUtils.SEVENTH_RANK[this.picePosition]
						&&this.getPiceAlline().isBlack() )||
				(BoardUtils.SECOND_RANK[this.picePosition]&&
						this.piceAllines.isWhite())){
	
	final int behindCandidateDEstinationCoordinate=
		this.picePosition+(this.piceAllines.getDirection()*8); 
  if(!board.getTile(behindCandidateDEstinationCoordinate).isOccupied()
	&& !board.getTile(candidateDestinationCordinate).isOccupied()
				) {
				legalMoves.add(new MajorMove(board, this,
						candidateDestinationCordinate));
				
			}
		 	
		}else if(currentCandidateOffset==7&&
				!((BoardUtils.EIGHT_COLUMN[this.picePosition]
				&&this.piceAllines.isWhite()
			||(BoardUtils.First_Culmn[this.picePosition]
				&&this.piceAllines.isBlack())) )) {
		if(board.getTile(CandidateDestinitionCoordinate).isOccupied()) {
		final Piece piceCandidate=board.
				getTile(CandidateDestinitionCoordinate).getPice() ;
			if(this.piceAllines!=piceCandidate.piceAllines){
				//TO DOO he pot MajorMove#############
				legalMoves.add(new AttackMove(board, this,
						picePosition, piceCandidate));
			}
			}
			
			
		}else if(currentCandidateOffset==9&&
			 !(BoardUtils.First_Culmn[this.picePosition]&&
	 this.piceAllines.isWhite()||(BoardUtils.EIGHT_COLUMN[this.picePosition]&&
					  this.piceAllines.isBlack()))){
			if(board.getTile(CandidateDestinitionCoordinate).isOccupied()) {
				final Piece piceCandidate=board.getTile(CandidateDestinitionCoordinate).getPice() ;
				if(this.piceAllines!=piceCandidate.piceAllines){
					//TO DOO he put MajorMove#############
					legalMoves.add(new AttackMove(board, this, picePosition, piceCandidate));
				}
				
				}
			}
			
		
		
	}
		
		return   ImmutableList.copyOf(legalMoves);
	}

 

}
