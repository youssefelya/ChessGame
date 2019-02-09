package com.chess.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;

public class Table {
private final static Dimension  OUTER_FRAME_DIMENSION=
new Dimension(600,600);


private final BoardPanel boardPanel;
private final Board chessBoard;

private static String defaultPieceImagePath="chesspic\\";



private static final Dimension BOARD_PANEL_DIMENSION = new Dimension(400,400);
private static final Dimension TILE_PANEL_DIMENSION =
           new Dimension(20,20);

private final Color LightTileColor=Color.decode("#FFFACD");
private final Color darkTileColor=Color.decode("#593E1A");


	private final JFrame gameFrame; 
	
	public Table() {
		this.gameFrame=new JFrame("Chess");
		this.gameFrame.setLayout(new BorderLayout());

		this.chessBoard=Board.creatStanderdBoard();
		
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
		
	}
	
	
	
	
	private class TilePanel extends JPanel{ 
		private final int tileId;
		TilePanel(final BoardPanel boardPanel,final int tileId){
			super(new GridBagLayout() );
			this.tileId=tileId;
			setPreferredSize(TILE_PANEL_DIMENSION);
			assighnTilecolor();
			assigneTilePieceIcon(chessBoard );
			validate();
			
		}
		private void assigneTilePieceIcon(final Board board) {
			this.removeAll();
			if(board.getTile(this.tileId).isOccupied()) {
				
				String s=(defaultPieceImagePath+board.
              getTile(this.tileId).
              getPice().getPiceAlline().toString().substring(0,1)+board.
         getTile(this.tileId).getPice().toString()+".png");
				System.out.println(s);
				
				
				 
				try {
					System.out.println("try "+board.
              getTile(this.tileId).
              getPice().getPiceAlline().toString().substring(0,1)+board.
         getTile(this.tileId).getPice().toString()+".png");
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
	
	
	
	
	
	
	
	
	
}
