package Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.math.Quantiles;

import Board.Board;
import Board.Move;
import Board.Tile;
import Board.Move.KingSideCastlMove;
import Pieces.Alliance;
import Pieces.King;
import Pieces.Piece;
import Pieces.Rook;

public class BlackPlayer extends Player {

	public BlackPlayer(final Board board,
	            		final Collection<Move> whiteStanderLegalMoves, 
			           final Collection<Move> blackStanderLegalMoves) {
	super(board, blackStanderLegalMoves,whiteStanderLegalMoves);
	}

	
	
	protected Collection<Move> calculateKingCateles(
			final Collection<Move> playerlegals,
			final Collection<Move> opponentsLegals)
	{
		final List<Move> kingCastles=new ArrayList<>();
		if(!this.playerKing.isFirstMove()&&
				!this.isInCheck()) {
			if(!this.board.getTile(5).isOccupied()&&
					this.board.getTile(6).isOccupied() ) {
				final Tile rookTile=this.board.getTile(7);
				if(rookTile.isOccupied()&&rookTile.getPice().isFirstMove()) {
			if(Player.calculateAttackOnTile(5,opponentsLegals).isEmpty()&&
			Player.calculateAttackOnTile(6,opponentsLegals).isEmpty()&&
				rookTile.getPice().getPieceType().isRook()) {
				
				kingCastles.add(new Move.KingSideCastlMove(this.board,
	                   	this.playerKing,
	                   	6,
	                   	(Rook) rookTile.getPice(),
		               rookTile.getTileCoordinate(),
		               5));
					}
				}
			}
			
			if(!this.board.getTile(1).isOccupied()&& 
				!this.board.getTile(2).isOccupied()&&
				!this.board.getTile(3).isOccupied() ) {
				final Tile rookTile=this.board.getTile(0);
			if(rookTile.isOccupied()&&rookTile.getPice().isFirstMove()&&
			Player.calculateAttackOnTile(2,opponentsLegals).isEmpty()&&
				Player.calculateAttackOnTile(3,opponentsLegals).isEmpty()&&
						rookTile.getPice().getPieceType().isRook()) {
				
			kingCastles.add(new Move.QueenSideCastlMove(this.board
					,this.playerKing,2,(Rook) rookTile.getPice(),
					rookTile.getTileCoordinate(),3));
				}
			}
		}
		return ImmutableList.copyOf(kingCastles);
	}
	
	
	
	
	
	
	
	
	@Override
	public Collection<Piece> getActivePieces() {
		// TODO Auto-generated method stub
		return this.board.getBlackPieces();
	}

	@Override
	public Alliance getAllience() {
		 return Alliance.Black;
	}

	@Override
	public Player getOpponet() {
		return this.board.whitePlayer();
	}

}
