package com.chess.engine.player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.King;
import com.chess.engine.Piece;
import com.chess.engine.Rook;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.KingSideCastlMove;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;
import com.google.common.math.Quantiles;

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
