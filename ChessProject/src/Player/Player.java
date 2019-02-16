package Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.management.RuntimeErrorException;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

import Board.Board;
import Board.Move;
import Pieces.Alliance;
import Pieces.King;
import Pieces.Piece;
import Pieces.Piece.PieceType;

public abstract class Player {

 protected final Board board; 
 protected final King playerKing; 
 protected final Collection<Move> legalMove;
 private final boolean isInCheck;
 
 
 
 protected abstract Collection<Move> calculateKingCateles(Collection<Move> playerLegals,
		 Collection<Move> opponentLegals);
 
 
public Player(Board board, 
		Collection<Move> legalMove,
		Collection<Move> oppementMoves ) {
	this.board = board; 
	this.playerKing = establishKing();
	this.legalMove =ImmutableList.copyOf(Iterables.concat(legalMove,
			calculateKingCateles(legalMove, oppementMoves)));
	this.isInCheck=!Player.calculateAttackOnTile(
			this.playerKing.getPicePosition(),oppementMoves).isEmpty();
	
             }
protected static Collection<Move> calculateAttackOnTile(int picePosition,
		Collection<Move>  Moves) {
	final List<Move> attackeMoves=new ArrayList<>();
	for(final Move move:Moves ) {
		if(picePosition==move.getDestinationCoordinate()) {
			attackeMoves.add(move);
		}
	}
	 
	return ImmutableList.copyOf(attackeMoves);
}
         private King establishKing() {
	 
	for(final Piece piece:getActivePieces()) {
		if(piece.getPieceType().isKing()){
			return (King) piece;
		}
	}
	
	
	throw new RuntimeException("Should not reach here! Not a valid board ");
}
    public boolean isMovelegal(final Move move) {
    	return this.legalMove.contains(move);
    }
           public boolean isInCheck() {
        	   
	        return this.isInCheck;
          }
           
            public boolean isInCheckMate() {
	      return this.isInCheck&& !hasEscapeMoves();
              }

            protected boolean hasEscapeMoves() {
				for(final Move move:this.legalMove) {
					final MoveTransition transition=makeMove(move);
					if(transition.getMoveStatus().isDone()) {
						return true;
						
					}
				}
				return false;
			}
			public boolean isInStaleMate() {
            	return!this.isInCheck&&!hasEscapeMoves();
            }
            public boolean isCastled() {
            	return false;
            }
            public MoveTransition makeMove(final Move move) {
              if(!isMovelegal(move)) {
            	  return new MoveTransition(this.board, 
            			  MoveStatus.ILLEGAL_MOVE,move);
              }
              final Board transitionBoard =move.execute();
       final Collection<Move> KingAttacks=Player.calculateAttackOnTile(
            		 transitionBoard.currentPlayer().
            		 getOpponet().getPlayerKing().getPicePosition(),
              transitionBoard.currentPlayer().getlegalMoves());
              if(!KingAttacks.isEmpty()) {
           return new MoveTransition(this.board, 
        		   MoveStatus.LEAVES_PLEAYER_IN_CHECK,move);
              }
              
          return new  MoveTransition(transitionBoard,
        		  MoveStatus.DONE, move);
            	
            }
 
  public Collection<Move> getlegalMoves() {
			 
				return this.legalMove;
			}
       public King getPlayerKing() {
				// TODO Auto-generated method stub
				return  this.playerKing;
			}
public abstract Collection<Piece> getActivePieces();
  public abstract Alliance getAllience();
  public abstract Player getOpponet();
 
	
	
	
	
	
}
