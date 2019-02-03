package com.chess.engine.board;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.chess.engine.*;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

public class Board {
	
private final  List<Tile> gamBoard;
	private final Collection<Pice> whitePices;
	private final Collection<Pice>  blackPices;
	
	
	
	private Board(Builder builder){
		this.gamBoard=CreatGameBoard(builder);
		this.whitePices=calcumlateActivePices(this.gamBoard,Alline.White);
		this.blackPices=calcumlateActivePices(this.gamBoard,Alline.Black);
		final Collection<Move> whiteStanderLegalMoves=calculateLegalMoves(this.whitePices) ;
		final Collection<Move> blackStanderLegalMoves=calculateLegalMoves(this.blackPices);
		
		
		
	}

	private Collection<Move> calculateLegalMoves(final Collection<Pice> pieces) {
		final  List<Move> legalMoves=new ArrayList<>();
		for(final Pice piece:pieces) {
		legalMoves.addAll(piece.calculateLegalMove(this));
		}
		
		
		return ImmutableList.copyOf(legalMoves);
	}

	private static Collection<Pice> calcumlateActivePices(final  List<Tile> gamBoard2,final Alline alline) {
		final ArrayList<Pice> activepices=new ArrayList<>();
		for(final Tile tile:gamBoard2) {
			if(tile.isOccupied()) { 
				final Pice piece=tile.getPice();
				if(piece.getPiceAlline()==alline) { 
					activepices.add(piece);
				}
			}
		}
		
		return activepices;
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
		builder.setPiece(new Rook(Alline.Black,0));
		builder.setPiece(new Knight(Alline.Black,1));
		builder.setPiece(new Bishop(Alline.Black,2));
		builder.setPiece(new Queen(Alline.Black,3));
		builder.setPiece(new King(Alline.Black,4));
		builder.setPiece(new Bishop(Alline.Black,5));
		builder.setPiece(new Knight(Alline.Black,6));
		builder.setPiece(new Rook(Alline.Black,7));
		builder.setPiece(new Pawn(Alline.Black,8));
		builder.setPiece(new Pawn(Alline.Black,9));
		builder.setPiece(new Pawn(Alline.Black,10));
		builder.setPiece(new Pawn(Alline.Black,11));
		builder.setPiece(new Pawn(Alline.Black,12));
		builder.setPiece(new Pawn(Alline.Black,13));
		builder.setPiece(new Pawn(Alline.Black,14));
		builder.setPiece(new Pawn(Alline.Black,15));
		//White Pices
		builder.setPiece(new Pawn(Alline.White,48));
		builder.setPiece(new Pawn(Alline.White,49));
		builder.setPiece(new Pawn(Alline.White,50));
		builder.setPiece(new Pawn(Alline.White,51));
		builder.setPiece(new Pawn(Alline.White,51));
		builder.setPiece(new Pawn(Alline.White,53));
		builder.setPiece(new Pawn(Alline.White,54));
		builder.setPiece(new Pawn(Alline.White,55));
		builder.setPiece(new Rook(Alline.White,56));
		builder.setPiece(new Knight(Alline.White,57));
		builder.setPiece(new Bishop(Alline.White,58));
		builder.setPiece(new Queen(Alline.White,59));
		builder.setPiece(new King(Alline.White,60));
		builder.setPiece(new Bishop(Alline.White,61));
		builder.setPiece(new Knight(Alline.White,62));
	    builder.setPiece(new Rook(Alline.White,63));
	    //White first Move
	    builder.setMoveMaker(Alline.White);
	    
	    return builder.build();
	    
	}
	
	
	
	
	
	
	
	
	
	public Tile getTile(final int tileCoordinate) {
		 return gamBoard.get(tileCoordinate);
	}
	
	public static class Builder{
		Map<Integer,Pice> boardConfig;
		 Alline nextMove;
		 public Builder() {
			 
		 }
		 
		 public Builder setPiece(final Pice pice) {
			 this.boardConfig.put(pice.getPicePosition(),pice);
			 return this;
		 }
		
		public Builder setMoveMaker(final Alline nextMoveMaker) {
			this.nextMove=nextMoveMaker;
			return this;
		} 
		 
		public Board build() {
			return new Board(this);
		}
	}
	
	
	
	
}
