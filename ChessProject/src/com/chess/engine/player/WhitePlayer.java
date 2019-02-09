package com.chess.engine.player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.Piece;
import com.chess.engine.Rook;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

public class WhitePlayer extends Player{

	 
	public WhitePlayer(final Board board, final Collection<Move> whiteStanderLegalMoves,
			final Collection<Move> blackStanderLegalMoves) {
		super(board, whiteStanderLegalMoves,blackStanderLegalMoves);
	}

	
	
	protected Collection<Move> calculateKingCateles(final Collection<Move> playerlegals, 
			final Collection<Move> opponentsLegals){
		final List<Move> kingCastles=new ArrayList<>();
		if(!this.playerKing.isFirstMove()&&
				!this.isInCheck()) {
			
			if(!this.board.getTile(61).isOccupied()&&
					!this.board.getTile(62).isOccupied() ) {
				
				final Tile rookTile=this.board.getTile(63);
				
				if(rookTile.isOccupied()&&rookTile.getPice().isFirstMove()) {
					
		if(Player.calculateAttackOnTile(61,opponentsLegals).isEmpty()&&
			Player.calculateAttackOnTile(62,opponentsLegals).isEmpty()&&
			rookTile.getPice().getPieceType().isRook()) {
						
		kingCastles.add(new Move.KingSideCastlMove(this.board,
							                   	this.playerKing,62,
							                   	(Rook) rookTile.getPice(),
								               rookTile.getTileCoordinate(),59));
					}
				}
			}
			
			if(!this.board.getTile(59).isOccupied()&& 
					!this.board.getTile(58).isOccupied()&&
					!this.board.getTile(57).isOccupied() ) {
				
				final Tile rookTile=this.board.getTile(56);
				
		if(rookTile.isOccupied()&&rookTile.getPice().isFirstMove()&&
				Player.calculateAttackOnTile(58,opponentsLegals).isEmpty()&&
		Player.calculateAttackOnTile(59,opponentsLegals).isEmpty()&&
				rookTile.getPice().getPieceType().isRook()) {
			
			kingCastles.add(new Move.QueenSideCastlMove(this.board
					,this.playerKing,58,(Rook) rookTile.getPice(),
							rookTile.getTileCoordinate(),59));
				}
			}
		}
		return ImmutableList.copyOf(kingCastles);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public Collection<Piece> getActivePieces() {
		// TODO Auto-generated method stub
		return this.board.getwhitePieces();
	}

	public Alliance getAllience(){
		return Alliance.White;
	}

	@Override
	public Player getOpponet() {
		return this.board.BlackPlayer();
	}
 

}
