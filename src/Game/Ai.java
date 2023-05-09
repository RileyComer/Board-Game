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
			out=getMove(out, turn, turnMax, -5, 5, 0);
		}
		return out;
	}
	
	private Board getMove(Board inputBoard, int turn, int turnMax, int alpha, int beta, int step) {
		Board out=inputBoard.copy();
		if(turn%2==0) {
			out.setScore(alpha);
		}else {
			out.setScore(beta);
		}
		if(step==level || turn==turnMax) {
			out.setScore(evaluate(out));
			return out;
		}
		int[][] area=out.getArea();
		int[][] pieces=out.getPieces();
		for(int x=0; x<pieces.length; x++) {
			for(int y=0; y<pieces[0].length; y++) {
				if(area[x][y]==turn%2+1 && pieces[x][y]==0 && !(out.getBallX()==x && out.getBallY()==y)) {
					for(int i=1; i<5; i++) {
						Board altered=inputBoard.copy();
						altered.addPiece(x, y, i);
						if(turn%2==0) {
							Board check=getMove(altered, turn+1, turnMax, out.getScore(), beta, step+1);
							if(check.getScore()>out.getScore()) {
								out=altered;
								out.setScore(check.getScore());
								if(out.getScore()>beta) {
									return out;
								}
							}else if(check.getScore()==out.getScore() && Math.random()>0.95) {
								out=altered;
								out.setScore(check.getScore());
								if(out.getScore()>beta) {
									return out;
								}
							}
						}else {
							Board check=getMove(altered, turn+1, turnMax, alpha, out.getScore(), step+1);
							if(check.getScore()<out.getScore()) {
								out=altered;
								out.setScore(check.getScore());
								if(out.getScore()<alpha) {
									return out;
								}
							}else if(check.getScore()==out.getScore() && Math.random()>0.95) {
								out=altered;
								out.setScore(check.getScore());
								if(out.getScore()<alpha) {
									return out;
								}
							}
						}
					}
				}
			}
		}
		return out;
	}
	
	public int evaluate(Board inputBoard) {
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
