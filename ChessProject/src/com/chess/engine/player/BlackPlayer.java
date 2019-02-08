package com.chess.engine.player;

import java.util.Collection;

import com.chess.engine.Alliance;
import com.chess.engine.Piece;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

public class BlackPlayer extends Player {

	public BlackPlayer(final Board board,
			final Collection<Move> whiteStanderLegalMoves, 
			final Collection<Move> blackStanderLegalMoves) {
	super(board, blackStanderLegalMoves,whiteStanderLegalMoves);
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
