package Pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.google.common.collect.ImmutableList;

import Board.Board;
import Board.BoardUtils;
import Board.Move;
import Board.Tile;
import Pieces.Piece.PieceType;

public class Rook extends Piece{
	private final static int[] Canididat_Move_vector_Cordinate= {-8,-1,1,8};

	

	public Rook( Alliance alline,int picePosition) {
		super(PieceType.ROOK, picePosition, alline);
		 
	}
	public Rook movePiece(Move move) { 
		return new Rook(move.getMovePiece().getPiceAlline(),
				move.getDestinationCoordinate());
	}
	
	
	
	@Override
	public String toString() {
		return  PieceType.ROOK.toString();
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
						final Piece AtDestination= candidate.getPice();
						final Alliance piceAlline= AtDestination.getPiceAlline();
						if(this.piceAllines!=piceAlline) {
							legalMoves.add(new Move.AttackMove(board,this,candidateDestinationCordinate,AtDestination));
						}
						break;	}
						       } 		}    	}
		
		 
		return   ImmutableList.copyOf(legalMoves);
	}
	private static boolean isFirstColumExclusion(final int currentPosition,final int CandidateOffset) {
		return BoardUtils.First_Culmn[currentPosition]&&(CandidateOffset==-1
				                           ); }
	
	private static boolean isEightColumExclusion(final int currentPosition,final int CandidateOffset) {
		return BoardUtils.EIGHT_COLUMN[currentPosition]&&(CandidateOffset==1); }
	
	
	
	
	
}
