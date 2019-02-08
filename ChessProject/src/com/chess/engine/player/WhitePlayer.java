package com.chess.engine.player;

import java.util.Collection;

import com.chess.engine.Alliance;
import com.chess.engine.Piece;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

public class WhitePlayer extends Player{

	 
	public WhitePlayer(final Board board, final Collection<Move> whiteStanderLegalMoves,
			final Collection<Move> blackStanderLegalMoves) {
		super(board, whiteStanderLegalMoves,blackStanderLegalMoves);
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
