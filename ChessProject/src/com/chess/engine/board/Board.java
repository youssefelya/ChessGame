package com.chess.engine.board;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chess.engine.*;
import com.chess.engine.player.BlackPlayer;
import com.chess.engine.player.Player;
import com.chess.engine.player.WhitePlayer;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

public class Board {
	
    private final  List<Tile> gamBoard;
	private final Collection<Piece> whitePices;
	private final Collection<Piece>  blackPices;
	
	private final WhitePlayer whitePlayer;
	private final BlackPlayer blackePlayer;
	
	private final Player currentPlayer;
	
	
	
	private Board(final Builder builder){
		this.gamBoard=CreatGameBoard(builder);
		this.whitePices=calcumlateActivePices(this.gamBoard,Alliance.White);
		this.blackPices=calcumlateActivePices(this.gamBoard,Alliance.Black);
		final Collection<Move> whiteStanderLegalMoves=calculateLegalMoves(this.whitePices) ;
		final Collection<Move> blackStanderLegalMoves=calculateLegalMoves(this.blackPices);
		this.whitePlayer=new WhitePlayer(this, whiteStanderLegalMoves,blackStanderLegalMoves); 
		this.blackePlayer=new BlackPlayer(this,whiteStanderLegalMoves,blackStanderLegalMoves);
		this.currentPlayer=builder.nextMoveMaker.choosePlayer(this.whitePlayer,this.blackePlayer);
		//#####
		
		
	}
	@Override
	public String toString() {
		final StringBuilder builder=new StringBuilder(); 
		for(int i=0;i<BoardUtils.num_Tile;i++) {
			final String tileText=this.gamBoard.get(i).toString();
			builder.append(String.format("%3s", tileText));
			if((i+1)%BoardUtils.num_Tile_Row==0) {
				builder.append("\n");
			}
		}
		return builder.toString();
		
	}
	
	
	public Player currentPlayer() {
		return this.currentPlayer;
	}
	
	

	 
	private  Collection<Move> calculateLegalMoves(final Collection<Piece> pieces) {
		final  List<Move> legalMoves=new ArrayList<>();
		for(final Piece piece:pieces) {
		legalMoves.addAll(piece.calculateLegalMove(this));
		}
		
		
		return ImmutableList.copyOf(legalMoves);
	}

	
	
	
	private static Collection<Piece> calcumlateActivePices(final  List<Tile> gamBoard2,final Alliance alline) {
		final ArrayList<Piece> activepices=new ArrayList<>();
		for(final Tile tile:gamBoard2) {
			if(tile.isOccupied()) { 
				final Piece piece=tile.getPice();
				if(piece.getPiceAlline()==alline) { 
					activepices.add(piece);
				}
			}
		}
		
		return activepices;
	}
	
	
	public Collection<Piece> getBlackPieces(){
		return this.blackPices;
	}
	
	public Collection<Piece> getwhitePieces(){
		 return this.whitePices;
	}
	
	
	
	
	

	private List<Tile> CreatGameBoard(Builder builder) {
		final Tile[] tiles=new Tile[BoardUtils.num_Tile];
		for(int i=0;i<BoardUtils.num_Tile;i++) {
			tiles[i]=Tile.creatTil(i, builder.boardConfig.get(i));
		}
		
		return  ImmutableList.copyOf(tiles); 

	}

	
	public static Board creatStanderdBoard() {
		final Builder builder=new Builder();
		//BlackPices
		builder.setPiece(new Rook(Alliance.Black,0));
		builder.setPiece(new Knight(Alliance.Black,1));
		builder.setPiece(new Bishop(Alliance.Black,2));
		builder.setPiece(new Queen(Alliance.Black,3));
		builder.setPiece(new King(Alliance.Black,4));
		builder.setPiece(new Bishop(Alliance.Black,5));
		builder.setPiece(new Knight(Alliance.Black,6));
		builder.setPiece(new Rook(Alliance.Black,7));
		builder.setPiece(new Pawn(Alliance.Black,8));
		builder.setPiece(new Pawn(Alliance.Black,9));
		builder.setPiece(new Pawn(Alliance.Black,10));
		builder.setPiece(new Pawn(Alliance.Black,11));
		builder.setPiece(new Pawn(Alliance.Black,12));
		builder.setPiece(new Pawn(Alliance.Black,13));
		builder.setPiece(new Pawn(Alliance.Black,14));
		builder.setPiece(new Pawn(Alliance.Black,15));
		//White Pices
		builder.setPiece(new Pawn(Alliance.White,48));
		builder.setPiece(new Pawn(Alliance.White,49));
		builder.setPiece(new Pawn(Alliance.White,50));
		builder.setPiece(new Pawn(Alliance.White,51));
		builder.setPiece(new Pawn(Alliance.White,51));
		builder.setPiece(new Pawn(Alliance.White,53));
		builder.setPiece(new Pawn(Alliance.White,54));
		builder.setPiece(new Pawn(Alliance.White,55));
		builder.setPiece(new Rook(Alliance.White,56));
		builder.setPiece(new Knight(Alliance.White,57));
		builder.setPiece(new Bishop(Alliance.White,58));
		builder.setPiece(new Queen(Alliance.White,59));
		builder.setPiece(new King(Alliance.White,60));
		builder.setPiece(new Bishop(Alliance.White,61));
		builder.setPiece(new Knight(Alliance.White,62));
	    builder.setPiece(new Rook(Alliance.White,63));
	    //White first Move
	    builder.setMoveMaker(Alliance.White);
	    
	    return builder.build();
	    
	}
	
	
	
	
	
	
	
	
	
	public Tile getTile(final int tileCoordinate) {
		 return gamBoard.get(tileCoordinate);
	}
	
	public static class Builder{
		Map<Integer,Piece> boardConfig;
		 Alliance nextMoveMaker;
		 public Builder() {
			 this.boardConfig=new HashMap<>();
			 
		 }
		 
		 public Builder setPiece(final Piece pice) {
			 this.boardConfig.put(pice.getPicePosition(),pice);
			 return this;
		 }
		
		public Builder setMoveMaker(final Alliance nextMoveMaker) {
			this.nextMoveMaker=nextMoveMaker;
			return this;
		} 
		 
		public Board build() {
			return new Board(this);
		}
	}

	public Player whitePlayer() {
		// TODO Auto-generated method stub
		return this.whitePlayer();
	}
	public Player BlackPlayer() {
		// TODO Auto-generated method stub
		return this.BlackPlayer();
	}
	 
	
	
	
	
}
