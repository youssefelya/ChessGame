package Gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.google.common.collect.Lists;

import Board.Board;
import Board.BoardUtils;
import Board.Move;
import Board.Tile;
import Pieces.Piece;
import Player.MoveTransition;

public class Table {
private final static Dimension  OUTER_FRAME_DIMENSION=
new Dimension(600,600);

private Tile sourceTile;
private Tile destinationTile;
private Piece humanMovePiece; 
private BoardDirection boardDirection;




private final BoardPanel boardPanel;
private Board chessBoard;

private static String defaultPieceImagePath="chesspic\\";


private boolean highlightLegalMove;



private static final Dimension BOARD_PANEL_DIMENSION = 
                    new Dimension(400,400);
private static final Dimension TILE_PANEL_DIMENSION =
           new Dimension(20,20);

private final Color LightTileColor=Color.decode("#FFFACD");
private final Color darkTileColor=Color.decode("#593E1A");


	private final JFrame gameFrame; 
	
	public Table() {
		this.gameFrame=new JFrame("Chess");
		this.gameFrame.setLayout(new BorderLayout());

		this.chessBoard=Board.creatStanderdBoard();
		this.highlightLegalMove= false;
            
		 this.boardDirection=BoardDirection.NORMAL;
		
		final JMenuBar tableMenuBar=createTableMenuBar();
		this.gameFrame.setJMenuBar(tableMenuBar);
		this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
		this.boardPanel=new BoardPanel();
		this.gameFrame.add(this.boardPanel,
				BorderLayout.CENTER); 
		
		
		
		
		this.gameFrame.setVisible(true);
		this.gameFrame.setDefaultCloseOperation(
				this.gameFrame.EXIT_ON_CLOSE);
		
		
	}
	private JMenuBar createTableMenuBar() {
		final JMenuBar tableMenuBar=new JMenuBar();
		 tableMenuBar.add(creatFileMenu());
		 tableMenuBar.add(createPreferencesMenu());
		 return tableMenuBar;	
	}
	
	private JMenu creatFileMenu() {
		final JMenu fileMenu=new JMenu("File");
		final JMenuItem openPGN=new JMenuItem("Load PGN File");
		openPGN.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			System.out.println("Open up that pgn file!");
				
			}
		});
		fileMenu.add(openPGN);
		final JMenuItem exitMenuItem=new JMenuItem("Exit");
		

		
		exitMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				System.exit(0);
				
			}
		});
		fileMenu.add(exitMenuItem);
		
		return fileMenu;
	}

	private class BoardPanel extends JPanel{
		final List<TilePanel> boardTiles;
		
		BoardPanel(){
			super(new GridLayout(8,8));
			this.boardTiles=new ArrayList<>();
			for(int i=0;i<BoardUtils.num_Tile;i++) {
				final TilePanel tilePanel=new TilePanel(
						this, i);
				this.boardTiles.add(tilePanel);
				add(tilePanel);
			}
			setPreferredSize(BOARD_PANEL_DIMENSION);
			validate();
		}

		public void drawBoard(final Board board) {
			 removeAll();
			 System.out.println("DrawBoard methode  ");
	for(final TilePanel tilePanel :
		boardDirection.traverse(boardTiles)) {
	tilePanel.drawTile(board);
				 add(tilePanel);
			 }
			 validate();
			 repaint();
			
		}
		
		
		
		
	}
	
	
	
	
	
	private JMenu createPreferencesMenu() {
		
		final JMenu preferencesMenu=new JMenu("Preferences");
		final JMenuItem flipBoardMenuItem=new JMenuItem("Flip Board");
		flipBoardMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
			 boardDirection=boardDirection.oppisite();
			 boardPanel.drawBoard(chessBoard);
				
			}
		});
		preferencesMenu.add(flipBoardMenuItem);
		preferencesMenu.addSeparator();
		final JCheckBoxMenuItem legalMoveHighlighterCheckbox=
				new JCheckBoxMenuItem ("HighLight Legal Moves",false);
		
		legalMoveHighlighterCheckbox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			highlightLegalMove=legalMoveHighlighterCheckbox.isSelected();
              
				
			}
		});
		
		preferencesMenu.add(legalMoveHighlighterCheckbox);
		return preferencesMenu;
	}
	
	private class TilePanel extends JPanel{ 
		private final int tileId;
		TilePanel(final BoardPanel boardPanel,final int tileId){
			super(new GridBagLayout() );
			this.tileId=tileId;
			setPreferredSize(TILE_PANEL_DIMENSION);
			assighnTilecolor();
			
			highLightLegals(chessBoard);

			assigneTilePieceIcon(chessBoard );	
			addMouseListener(new MouseListener() {	
				@Override
		public void mouseClicked(final MouseEvent e) {
					System.out.println("Mouse Clique "+e);
		 if(javax.swing.SwingUtilities.isRightMouseButton(e)) {
			 System.out.println("Right Mause b" );
			 sourceTile=null;
			 destinationTile=null;
			 humanMovePiece=null;		 
			 }
		 
		 else if(javax.swing.SwingUtilities.isLeftMouseButton(e)) {
			 System.out.println("Left mause click ");
	if(sourceTile==null) {
		sourceTile=chessBoard.getTile(tileId);
		humanMovePiece=sourceTile.getPice();
		if(humanMovePiece==null) { 
			System.out.print("Human mouve ==  null ");
			 sourceTile=null;
		}
		}
	else {
			destinationTile=chessBoard.getTile(tileId);
			final Move move= Move.MoveFactray.createMove(chessBoard, 
					sourceTile.getTileCoordinate(),
					destinationTile.getTileCoordinate());
			final MoveTransition transition=
					chessBoard.currentPlayer().makeMove(move);
		if(transition.getMoveStatus().isDone()) {
			chessBoard=transition.getTransitionBoard();
			//add the move that was made to the move log
		}
		sourceTile=null;
		destinationTile=null;
		humanMovePiece=null;
		
	         	}
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				System.out.println("Runnable Methode!!!!!!");
			boardPanel.drawBoard(chessBoard);	
			}
			});
		
			   }
		  	  
		
					
				}

				
				@Override
				public void mouseReleased(final MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(final MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(final MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
		public void mouseEntered(final MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				

				
				
     
			});
			
			validate();
			
			
			
		}
		public void drawTile(final Board board) {
			assighnTilecolor();
			assigneTilePieceIcon(board);
			validate();
			repaint();
			
		}
		private void assigneTilePieceIcon(final Board board) {
			this.removeAll();
			if(board.getTile(this.tileId).isOccupied()) {	
				String s=(defaultPieceImagePath+board.
              getTile(this.tileId).
              getPice().getPiceAlline().toString().substring(0,1)+board.
         getTile(this.tileId).getPice().toString()+".png");
				//System.out.println(s);
				 
				try {
					
			final BufferedImage image=ImageIO.read(
					new 
             File( defaultPieceImagePath+board.
                     getTile(this.tileId).
                     getPice().getPiceAlline().toString().substring(0,1)+board.
                getTile(this.tileId).getPice().toString()+".png"));
				add(new JLabel(new ImageIcon(image)));
				}catch(IOException e ) {
							 System.out.println(e);
						 }
				
			}
		}
		
		private void highLightLegals(final Board board) {
			//if(highlightLegalMove) 
			if(true){
				System.out.println("HighLight Legals Moves !!!!");

		for(final Move move:pieceLegalMoves(board)) {
			if(move.getDestinationCoordinate()==this.tileId) {
					try {
						System.out.println("Inside tryyy ");

	add(new JLabel(new ImageIcon(
			ImageIO.read(new File("chesspic\\1.jpg") ) )));			
					 		
						}
						catch(Exception e) {System.out.println(e);}
					}
				}
		
			}
		}
		
		
		
		
		
		private Collection<Move> pieceLegalMoves(final Board board) {
			if(humanMovePiece!=null&&humanMovePiece.getPiceAlline()==
					board.currentPlayer().getAllience() ) {
				return humanMovePiece.calculateLegalMove(board);
			}
			return Collections.emptyList();
		}
		
		
		private void assighnTilecolor() {
			if(BoardUtils.EIGHT_RANK[this.tileId]||
				BoardUtils.SIXTH_RANK[this.tileId]||
				BoardUtils.FOURTH_RANK[this.tileId]||
				 BoardUtils.SECOND_RANK[this.tileId] ) {
				setBackground(this.tileId%2==0?
						LightTileColor:darkTileColor);
			}
			else if(BoardUtils.SEVENTH_RANK[this.tileId]||
					BoardUtils.FIFTH_RANK[this.tileId] ||
					 BoardUtils.THIRD_RANK[this.tileId] || 
					 BoardUtils.THIRD_RANK[this.tileId] ||
					  BoardUtils.FIRST_RANK[this.tileId]) {
				setBackground(this.tileId%2!=0? 
						LightTileColor:darkTileColor );
				
			}
			
		}
		
		
		
	}
	

	
	
	public enum BoardDirection{
		NORMAL{
			@Override
			List<TilePanel> traverse(final List<TilePanel> boardTiles){
				return boardTiles;
			}
			@Override
			  BoardDirection oppisite() {
				return FLIPPED;
			}
		},
		
		FLIPPED{
			@Override
			List<TilePanel> traverse(final List<TilePanel> boardTiles){
				return Lists.reverse(boardTiles);
			}
			@Override
			  BoardDirection oppisite() {
				return NORMAL;
			}
			
		};
		abstract List<TilePanel>  traverse(final List<TilePanel> boardTiles);
		abstract BoardDirection oppisite(); 
	}
	
	
	
	
	
	
	
	
	
	
}
