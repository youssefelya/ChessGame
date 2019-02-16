package Player;

public enum MoveStatus {
	DONE {
		@Override
		public
		boolean isDone() {
			// TODO Auto-generated method stub
			return true;
		}
	}, ILLEGAL_MOVE {
		@Override
		public
		boolean isDone() {
			// TODO Auto-generated method stub
			return false;
		}
	}, 
	LEAVES_PLEAYER_IN_CHECK {
		@Override
		public
		boolean isDone() {
			// TODO Auto-generated method stub
			return false;
		}
	};
  public abstract boolean isDone();
}
