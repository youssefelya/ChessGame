package Pieces;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.google.common.collect.ImmutableList;

import Board.Board;
import Board.BoardUtils;
import Board.Move;
import Board.Tile;

public class Bishop extends Piece{
	private final static int[] Canididat_Move_vector_Cordinate= {-9,-7,7,9};
	

	public Bishop(Alliance alline, int picePosition) {
		super(PieceType.BISHOP, picePosition, alline);
	
	}

	@Override
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
						final Piece AtDestination= candidate.getPice();
						final Alliance piceAlline= AtDestination.getPiceAlline();
						if(this.piceAllines!=piceAlline) {
							legalMoves.add(new Move.AttackMove(board,this,candidateDestinationCordinate,AtDestination));
						}
						break;	}
						       } 		}    	}
		
		 
		return   ImmutableList.copyOf(legalMoves);
	}
	
	@Override
	public String toString() {
		return  PieceType.BISHOP.toString();
	}
	
	
	private static boolean isFirstColumExclusion(final int currentPosition,final int CandidateOffset) {
		return BoardUtils.First_Culmn[currentPosition]&&(CandidateOffset==-9
				                           ||CandidateOffset==7); }
	
	private static boolean isEightColumExclusion(final int currentPosition,final int CandidateOffset) {
		return BoardUtils.EIGHT_COLUMN[currentPosition]&&(CandidateOffset==-7
				                           ||CandidateOffset==9); }

	@Override
	public Piece movePiece(final Move move) {
		 
		return new Bishop(move.getMovePiece().getPiceAlline(),
				move.getDestinationCoordinate());
	}
	
	
	
	
	
	
	

}
