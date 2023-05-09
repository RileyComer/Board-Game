package main;

import Game.Board;
import Game.Ai;

public class Gameframe {
	
	private String mode;
	private Board board;
	private int width, height;
	private int rotation;
	private int hx, hy;
	private int playerTurn;
	private int turnCount;
	private int turnMax;
	private Ai ai;
	private boolean computer;
	
	
	
	public Gameframe() {
		mode="Menu";
		width=10;
		height=10;
		board=new Board(width, height);
		rotation=4;
		hx=-1;
		hy=-1;
		playerTurn=1;
		turnCount=0;
		turnMax=24;
		
		//ai
		ai=new Ai(3);
		computer=false;
	}
	
	public void update() {
		if(playerTurn==-1) {
			board.play();
		}
	}
	
	public String getMode() {
		return mode;
	}
	
	public void setMode(String mode) {
		this.mode=mode;
	}
	
	public Board getBoard() {
		return board;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
	public void rotate() {
		rotation++;
		if(rotation>4) {
			rotation=1;
		}
	}
	
	public int getRotation() {
		return rotation;
	}
	
	public void highlight(int x, int y) {
		hx=x;
		hy=y;
	}
	
	public int getHx() {
		return hx;
	}
	
	public int getHy() {
		return hy;
	}
	
	public void addTurn() {
		playerTurn++;
		turnCount++;
		if(playerTurn>2) {
			playerTurn=1;
		}
		if(computer && playerTurn==2){
			board=ai.Move(board, 2, turnCount, turnMax);
			playerTurn=1;
			turnCount++;
		}
		if(turnCount==turnMax) {
			playerTurn=-1;
		}
	}
	
	public int getPlayerTurn() {
		return playerTurn;
	}
	
	public int getTurnCount() {
		return turnCount;
	}
	
	public int getTurnMax() {
		return turnMax;
	}
	
	/*
	private static void playSound(File Sound) {// just put name of file
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound));
			clip.start();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	*/
}
