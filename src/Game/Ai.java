package Game;

public class Ai {
	private int level;
	public Ai(int level) {
		this.level=level;
	}
	
	public Board Move(Board inputBoard, int player, int turn, int turnMax) {
		Board out=inputBoard.copy();
		if(turn<2) {
			int x=(int)(Math.random()*(out.getPieces().length-2))+1;
			int y=(int)(Math.random()*(out.getPieces()[0].length-2))+1;
			while(out.getArea()[x][y]!=player || (out.getBallX()==x && out.getBallY()==y)) {
				x=(int)(Math.random()*(out.getPieces().length-2))+1;
				y=(int)(Math.random()*(out.getPieces()[0].length-2))+1;
			}
			out.addPiece(x, y, 5);
		}else {
			out.setScore(500);
			out=getMove(out, turn, turnMax, 0);
			System.out.println(evaluate(out, 0)+" ----> "+out.getScore());
		}
		return out;
	}
	
	private boolean[][] getActions(Board inputBoard, int turn){
		boolean[][] out=new boolean[inputBoard.getArea().length][inputBoard.getArea()[0].length];
		int player=turn%2+1;
		int trailCount=0;
		for(int i=1; i<5; i++) {
			boolean[][] trail=new boolean[inputBoard.getArea().length][inputBoard.getArea()[0].length];
			Board board=inputBoard.copy();
			int x=(int) board.getBallX();
			int y=(int) board.getBallY();
			int touched=0;
			int dir=i;
			int[][] pieces=board.getPieces();
			int[][] area=board.getArea();
			if(i==1) {
				touched=area[x+1][y];
			}else if(i==2) {
				touched=area[x][y-1];
			}else if(i==3) {
				touched=area[x-1][y];
			}else if(i==4) {
				touched=area[x][y+1];
			}
			while(!((y==-1 && dir==2) || (y==pieces[0].length && dir==4)) && !((x==-1 && dir==3) || (x==pieces.length && dir==1)) && pieces[x][y]!=5) {
				if(pieces[x][y]!=0) {
					dir=pieces[x][y];
					touched=area[x][y];
					pieces[x][y]=0;
				}else if(area[x][y]==player && !(board.getBallX()==x && board.getBallY()==y) && inputBoard.getPieces()[x][y]==0){
					trail[x][y]=true;
				}
				if(dir==1) {
					x++;
				}else if(dir==2) {
					y--;
				}else if(dir==3) {
					x--;
				}else if(dir==4) {
					y++;
				}
			}
			
			if(!((y==-1 && dir==2) || (y==pieces[0].length && dir==4)) && !((x==-1 && dir==3) || (x==pieces.length && dir==1))) {
				if(area[x][y]==player) {
					for(int ix=0; ix<pieces.length; ix++) {
						for(int iy=0; iy<pieces.length; iy++) {
							if(trail[ix][iy]) {
								trailCount++;
								out[ix][iy]=true;
							}
						}
					}
				}
			}else {
				if(touched==player) {
					for(int ix=0; ix<pieces.length; ix++) {
						for(int iy=0; iy<pieces.length; iy++) {
							if(trail[ix][iy]) {
								trailCount++;
								out[ix][iy]=true;
							}
						}
					}
				}
			}
		}
		if(trailCount==0) {
			int options=0;
			for(int ix=0; ix<inputBoard.getArea().length; ix++) {
				for(int iy=0; iy<inputBoard.getArea()[0].length; iy++) {
					if(options>4) {
						break;
					}
					if(inputBoard.getArea()[ix][iy]==player && inputBoard.getPieces()[ix][iy]==0 && !(inputBoard.getBallX()==ix && inputBoard.getBallY()==iy)) {
						trailCount++;
						out[ix][iy]=true;
						options++;
					}
				}
				if(options>4) {
					break;
				}
			}
		}
		return out;
	}
	
	private Board getMove(Board inputBoard, int turn, int turnMax, int step) {
		Board out=inputBoard.copy();
		if(turn%2==0) {
			out.setScore(-100);
		}else {
			out.setScore(100);
		}
		if(step==level || turn==turnMax) {
			out.setScore(evaluate(out, step));
			return out;
		}
		int[][] area=out.getArea();
		int[][] pieces=out.getPieces();
		boolean[][] actions=getActions(inputBoard, turn);
		
		for(int x=0; x<pieces.length; x++) {
			for(int y=0; y<pieces[0].length; y++) {
				if(actions[x][y]) {
					for(int i=1; i<5; i++) {
						if(!((y==0 && i==2) || (y==pieces[0].length-1 && i==4)) && !((x==0 && i==3) || (x==pieces.length-1 && i==1))) {
							Board altered=inputBoard.copy();
							altered.addPiece(x, y, i);
							if(turn%2==0) {
								Board check=getMove(altered, turn+1, turnMax, step+1);
								if(check.getScore()>out.getScore()) {
									out=altered;
									out.setScore(check.getScore());
								}else if(check.getScore()==out.getScore() && Math.random()>0.5) {
									out=altered;
									out.setScore(check.getScore());
								}
							}else {
								Board check=getMove(altered, turn+1, turnMax, step+1);
								if(check.getScore()<out.getScore()) {
									out=altered;
									out.setScore(check.getScore());
								}else if(check.getScore()==out.getScore() && Math.random()>0.5) {
									out=altered;
									out.setScore(check.getScore());
								}
							}
						}
					}
				}
			}
		}
		return out;
	}
	
	public int evaluate(Board inputBoard, int step) {
		int out=0;
		for(int i=1; i<5; i++) {
			Board board=inputBoard.copy();
			int x=(int) board.getBallX();
			int y=(int) board.getBallY();
			int touched=0;
			int dir=i;
			int[][] pieces=board.getPieces();
			int[][] area=board.getArea();
			if(i==1) {
				touched=area[x+1][y];
			}else if(i==2) {
				touched=area[x][y-1];
			}else if(i==3) {
				touched=area[x-1][y];
			}else if(i==4) {
				touched=area[x][y+1];
			}
			while(!((y==-1 && dir==2) || (y==pieces[0].length && dir==4)) && !((x==-1 && dir==3) || (x==pieces.length && dir==1)) && pieces[x][y]!=5) {
				if(pieces[x][y]!=0) {
					dir=pieces[x][y];
					touched=area[x][y];
					pieces[x][y]=0;
				}
				if(dir==1) {
					x++;
				}else if(dir==2) {
					y--;
				}else if(dir==3) {
					x--;
				}else if(dir==4) {
					y++;
				}
			}
			
			if(!((y==-1 && dir==2) || (y==pieces[0].length && dir==4)) && !((x==-1 && dir==3) || (x==pieces.length && dir==1))) {
				if(area[x][y]==1) {
					out--;
				}else {
					out++;
				}
			}else {
				if(touched==1) {
					out--;
				}else {
					out++;
				}
			}
		}
		return out;
	}
}
