package Board;
import Board.Board.Builder;
import Pieces.Pawn;
import Pieces.Piece;
import Pieces.Rook;

public abstract class Move {
	

	final Board board; 
	final Piece movedPiece; 
	final int distinationcordinate;
	
      public static final Move NULL_Move=new NullMove();
      public abstract Board execute();	
	
	public Move(final Board board,
			final Piece picemove,
			final int d			) {
		this.distinationcordinate=d;
		this.board = board;
		this.movedPiece = picemove;
	
	}
	 
	
	
	
 
	
	
	@Override
	public int hashCode() {
		final int prime=31;
		int result=1;
		result=prime*result+this.distinationcordinate;
		result=prime*result+this.movedPiece.hashCode();
		return result; 
		
		
	}
	@Override
	public boolean equals(final Object other) {
	   if(this==other) {return true;}
	   if(!(other instanceof Move)) {
		   return false;
	   }
	   final Move othermove=(Move) other;
	   return getDestinationCoordinate()==othermove.getDestinationCoordinate()&&
			   getMovePiece()==othermove.getMovePiece();
	 }
	
	
	
	
	
	public Piece getMovePiece() {
		return this.movedPiece;
	}
	
	public boolean isAttack() {
		return false;	
	}
	public boolean isCastlingMove() {
		return false;
	}
	public Piece getAttackPiece() {
		return null;
	}
	
	
	
	
	
	
	
	
	
	public int getCurrentCoordinate() {
		// TODO Auto-generated method stub
		return this.getMovePiece().getPicePosition();
	}
 	
	
	
	
	
	
	
	
	public static final class MajorMove extends Move{

		public MajorMove(final Board board,final Piece picemove,
				final int d) {
			super(board, picemove, d);
			 
		}	
		
		@Override
		public Board execute() {
			final Builder builder =new Builder();
			for(final Piece piece:this.board.currentPlayer().getActivePieces()) {
				if(!this.movedPiece.equals(piece)) {
					builder.setPiece(piece);
				}
			}
			for(final Piece piece:this.board.currentPlayer().getOpponet().getActivePieces() ) {
				builder.setPiece(piece);
				
			}
			 builder.setPiece(this.movedPiece.movePiece(this));
			builder.setMoveMaker(this.board.currentPlayer().getOpponet().getAllience());
			return builder.build();
					
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static   class AttackMove extends Move{
		 final Piece AttackPice;   
		public AttackMove(final Board board,final Piece picemove,final int d,
				final Piece attackpice) {
			super(board, picemove, d);
			this.AttackPice=attackpice;
 
		}
		@Override
		public Board execute() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public int hashCode(){
			return this.AttackPice.hashCode()+super.hashCode();
		}
		@Override
		public boolean equals(Object other) {
			if(this==other) {return true;}
			if(!(other instanceof Move)) {return false; }
			final AttackMove otherAttackMove=(AttackMove)other;
			return super.equals(otherAttackMove)&&
					getAttackPiece().equals(otherAttackMove.getAttackPiece());
				}
		
		@Override
		public boolean isAttack() {
			return true;
		}
		public Piece getAttackPiece() {
			return this.AttackPice;
		}
		
		
		
	}


	public int  getDestinationCoordinate() {
		 
		return this.distinationcordinate;
	}
	
	
	
	
	public static final class PawnMove extends Move{

		public PawnMove(final Board board,
				final Piece picemove,
				final int d) {
			super(board, picemove, d);
			 
		}

		@Override
		public
		Board execute() {
			// TODO Auto-generated method stub
			return null;
		}	
	}
	public static class PawnAttackMove extends AttackMove{

		public PawnAttackMove(final Board board,
				final Piece picemove,
				final int d,final Piece attackpice) {
			super(board, picemove, d,attackpice);
			 
		}	
	}
	
	
	public static final class PawnEnPassantAttackMove extends PawnAttackMove{

		public PawnEnPassantAttackMove(final Board board,
				final Piece picemove,
				final int d,final Piece attackpice) {
			super(board, picemove, d,attackpice);
			 
		}	
	}
	
	public static final class PawnJump  extends Move{

		public PawnJump(final Board board,
				final Piece picemove,
				final int d) {
			super(board, picemove, d);
			 
		}	
		
		
		public Board execute() {
			final Builder builder =new Builder();
			for(final Piece piece:this.board.currentPlayer().getActivePieces()) {
				if(!this.movedPiece.equals(piece)) {
					builder.setPiece(piece);
				}
			}
			for(final Piece piece:this.board.currentPlayer().getOpponet().getActivePieces() ) {
				builder.setPiece(piece);
				
			}
			final Pawn movedPawn=(Pawn) this.movedPiece.movePiece(this);
			builder.setPiece(movedPawn);
			builder.setEnPassantPawn(movedPawn);
			builder.setMoveMaker(this.board.currentPlayer().getOpponet().getAllience());
			return builder.build();
					
		}
		
		
		
	}
	
	

     static abstract class CastleMove extends Move{
    		protected final Rook catelRook; 
    		protected final int castelRookStart;
    		protected final int castelRookDestination;
    	 

		public CastleMove(final Board board,final Piece picemove,final int d,
				final Rook catelRook,final int castelRookStart,
				final int castelRookDestination) {
			super(board, picemove, d);
			this.castelRookDestination=castelRookDestination;
			this.castelRookStart=castelRookStart;
			this.catelRook=catelRook;
			 
		}	
		
		public Rook getCastelRook() {
			 return this.catelRook;
		 }
		 public boolean isCastelingMove() {
			 return true;
		 }
		 
		 
		 
		 public Board execute() {
				final  Builder builder=new Builder();
				for(final Piece piec:this.board.currentPlayer().getActivePieces()) {
					//to do hashcode and equals for pieces
				if(!this.movedPiece.equals(piec)
					&&!this.catelRook.equals(piec)	 ) {
					builder.setPiece(piec);		}
				}
			for(final Piece piec:this.board.currentPlayer().getOpponet().getActivePieces()) {
				builder.setPiece(piec);
			}	
			 builder.setPiece(this.movedPiece.movePiece(this));
		  builder.setPiece(new Rook(this.catelRook.getPiceAlline() 
					 , this.castelRookDestination));
			 builder.setMoveMaker(this.board.currentPlayer()
					.getAllience() );
			 return builder.build();
			 
		 }
		 
		
		
	}
	
     public static final class KingSideCastlMove  extends CastleMove{

 		public KingSideCastlMove(final Board board,
 				                 final Piece picemove,
 				                 final int d,
 				                final Rook catelRook,final int castelRookStart,
 								final int castelRookDestination) {
 			super(board, picemove, d,catelRook,castelRookStart,castelRookDestination);
 			 
 		}	
 	}
     public static final class QueenSideCastlMove  extends CastleMove{

  		public  QueenSideCastlMove(final Board board,
  				final Piece picemove,
  				final int d,
	                final Rook catelRook,final int castelRookStart,
					final int castelRookDestination) {
 			super(board, picemove, d,catelRook,castelRookStart,castelRookDestination);
  			 
  		}	
  	}
     
     
     
     public static final class NullMove extends Move{

 		public NullMove() {
 			super(null, null,-1);
 			 
 		}
 		public Board excute() {
 			throw new RuntimeException("cannot excute the null move");
 		}
		@Override
		public
		Board execute() {
			// TODO Auto-generated method stub
			return null;
		}
 	}
     
     
     
     
     public static class MoveFactray{
    	private  MoveFactray(){
    		 throw new RuntimeException("Not instantiable");
    	 }
    	public static Move createMove(final Board board,
    			                      final int currentCoordinate,
    			                      final int destinationCoordinate) {
    		for(final Move move:board.getAlllegalMoves()) {
    			if(move.getCurrentCoordinate()==currentCoordinate&&
    					move.getDestinationCoordinate()==destinationCoordinate ) {
    				return move;
    			}
    		}
    		System.out.print("We return NULLLLLL MOVE###########");
    		return NULL_Move;
    	}
    	 
    	 
     }


	
 	
     
     



	
	
	
	

}
