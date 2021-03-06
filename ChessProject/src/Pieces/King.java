package Pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.google.common.collect.ImmutableList;

import Board.*;
import Pieces.Piece.PieceType;


public class King extends Piece{
private final int[] Candidat_Move_Coordinate= {-9,-8,-7,-1,1,7,8,9};
	public King(Alliance alline, int picePosition) {
		super(PieceType.KING, picePosition, alline);
	 
	}
	
	@Override
	public String toString() {
		return  PieceType.KING.toString();
	}
	
	

	@Override
	public Collection<Move> calculateLegalMove(Board board) {
		final ArrayList<Move> legalsMoves=new ArrayList<>();
		
		for(final int CurrentCandidateOffset:Candidat_Move_Coordinate ) {
		final int CandidateDestinationCorrdinate=this.picePosition+CurrentCandidateOffset;
		if(isFirstColumExclusion(this.picePosition, CurrentCandidateOffset)||
				isEghitColumExclusion(this.picePosition, CurrentCandidateOffset) ) {
			continue;
		}	
		
		
		
		
		if(BoardUtils.isValideTileCoordinate(CandidateDestinationCorrdinate)) {
				final Tile CandidateDestinationTile=board.getTile(CandidateDestinationCorrdinate);
			
				if(!CandidateDestinationTile.isOccupied()) {
					legalsMoves.add(new Move.MajorMove(board,this,CandidateDestinationCorrdinate));
					
				}else {
					final Piece AtDestination= CandidateDestinationTile.getPice();
					final Alliance piceAlline= AtDestination.getPiceAlline();
					if(this.piceAllines!=piceAlline) {
						legalsMoves.add(new Move.AttackMove(board,this,CandidateDestinationCorrdinate,AtDestination));
					}
							}
				
			}
		}
		
		
		
		
		
		
		
		return   ImmutableList.copyOf(legalsMoves);
	}

	
	
	
	private static boolean isFirstColumExclusion(final int currentposition,final int candidateOffset) {
		return  BoardUtils.First_Culmn[currentposition]&&((candidateOffset==-9)||(candidateOffset==-1)
				||(candidateOffset==7)	
			);
		
		
	}
	
	
	private static boolean isEghitColumExclusion(final int currentposition,final int candidateOffset) {
		return  BoardUtils.SECONDE_Culmn[currentposition]&&
				((candidateOffset==7)||(candidateOffset==1)	 
						||(candidateOffset==9)	 );
	}
	
	
	public King movePiece(final Move move) {
		 
		return new King(move.getMovePiece().getPiceAlline(),
				move.getDestinationCoordinate());
	}
	
	
	
	
	
}
