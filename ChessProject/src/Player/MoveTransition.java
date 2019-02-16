package Player;

import Board.Board;
import Board.Move;

public class MoveTransition {
	
	private final Board transitionBoard;
	private final Move move;
	private final MoveStatus moveStatus;
	
	public MoveTransition(final Board transitionBoard, 
			final MoveStatus moveStatus,final Move move) {
		this.transitionBoard = transitionBoard;
		this.move = move;
		this.moveStatus = moveStatus;
	}

	public MoveStatus getMoveStatus() {
		return this.moveStatus;
	}

	public Board getTransitionBoard() {

		return this.transitionBoard;
	}
	
	

}
