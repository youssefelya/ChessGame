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
import Pieces.Piece.PieceType;

public class Knight extends Piece{
	
	private final static int[] CANDIDAT_MOVES_COORDINET= {-17,-15,-16,
			-6,6,16,15,17};
	

	public Knight(Alliance alline, int picePosition) {
		super(PieceType.KNIGHT, picePosition, alline);
	 
	}
	
	
	
	
	
	@Override
	public String toString() {
		return  PieceType.KNIGHT.toString();
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
				final Piece AtDestination= candidate.getPice();
				final Alliance piceAlline= AtDestination.getPiceAlline();
				if(this.piceAllines!=piceAlline) {
					legalMove.add(new Move.AttackMove(board,this,candidatDistinationCoordinate,AtDestination));
				}
						}
			}
		}
		 
		return  ImmutableList.copyOf(legalMove);
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
	
	
	
	public Knight movePiece(Move move) {
		 
		return new Knight(move.getMovePiece().getPiceAlline(),
				move.getDestinationCoordinate());
	}
	
	
	
	
	
	
	
	
	
	

}
