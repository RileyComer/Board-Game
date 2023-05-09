package Game;

public class Board {
	private int[][] pieces;
	private int[][] area;
	private double ballX, ballY;
	private int ballDirection;
	private int ballTouched;
	private int tik, tikMax;
	private int score;
	
	public Board(int width, int height) {
		pieces=new int[width][height];
		area=new int[width][height];
		ballX=(int)(Math.random()*(width-2))+1;
		ballY=(int)(Math.random()*(height-2))+1;
		ballDirection=(int)(Math.random()*4)+1;
		tik=0;
		tikMax=30;
		for(int x=0; x<width; x++) {
			for(int y=0; y<height; y++) {
				if(area[x][y]==0) {
					if(y<height/2) {
						if(y!=0 && x!=0 && x!=width-1) {
							if(y==1 || y==2) {
								area[x][y]=1;
							}else {
								int temp=(int)(Math.random()*2)+1;
								if(temp==1) {
									area[x][y]=1;
									area[width-1-x][height-1-y]=2;
								}else {
									area[x][y]=2;
									area[width-1-x][height-1-y]=1;
								}
							}
						}else {
							area[x][y]=2;
						}
					}else {
						if(y!=height-1 && x!=0 && x!=width-1) {
							if(y==height-2 || y==height-3) {
								area[x][y]=2;
							}else {
								int temp=(int)(Math.random()*2)+1;
								if(temp==1) {
									area[x][y]=1;
									area[width-1-x][height-1-y]=2;
								}else {
									area[x][y]=2;
									area[width-1-x][height-1-y]=1;
								}
							}
						}else {
							area[x][y]=1;
						}
					}
				}
			}
		}
		if(ballDirection==4) {
			ballTouched=area[((int)ballX)][((int)ballY)+1];
		}else if(ballDirection==3) {
			ballTouched=area[((int)ballX)-1][((int)ballY)];
		}else if(ballDirection==2) {
			ballTouched=area[((int)ballX)][((int)ballY)-1];
		}else if(ballDirection==1) {
			ballTouched=area[((int)ballX)+1][((int)ballY)];
		}
	}
	
	public Board copy() {
		Board out=new Board(pieces.length, pieces[0].length);
		out.setPieces(pieces);
		out.setArea(area);
		out.setBall((int)ballX, (int)ballY);
		return out;
	}
	
	public void addPiece(int x, int y, int value) {
		pieces[x][y]=value;
	}
	
	public int[][] getPieces(){
		return pieces;
	}
	
	public void setPieces(int[][] pieces) {
		for(int x=0; x<pieces.length; x++) {
			for(int y=0; y<pieces[0].length; y++) {
				this.pieces[x][y]=pieces[x][y];
			}
		}
	}
	
	public void setArea(int[][] area) {
		for(int x=0; x<area.length; x++) {
			for(int y=0; y<area[0].length; y++) {
				this.area[x][y]=area[x][y];
			}
		}
	}
	
	public int[][] getArea(){
		return area;
	}
	
	public void setScore(int score) {
		this.score=score;
	}
	
	public int getScore() {
		return score;
	}
	
	public double getBallX() {
		return ballX;
	}
	
	public double getBallY() {
		return ballY;
	}
	
	public void setBall(int x, int y) {
		ballX=x;
		ballY=y;
	}
	
	public int getBallTouched() {
		return ballTouched;
	}
	
	public void play() {
		tik++;
		if(tik==tikMax) {
			tik=0;
			ballX=Math.round(ballX);
			ballY=Math.round(ballY);
			if(pieces[(int) ballX][(int) ballY]!=0) {
				ballDirection=pieces[(int) ballX][(int) ballY];
				pieces[(int) ballX][(int) ballY]=0;
				ballTouched=area[(int) ballX][(int) ballY];
			}
		}else {
			if(ballDirection==4) {
				ballY+=(1.0/tikMax);
			}else if(ballDirection==3) {
				ballX-=(1.0/tikMax);
			}else if(ballDirection==2) {
				ballY-=(1.0/tikMax);
			}else if(ballDirection==1) {
				ballX+=(1.0/tikMax);
			}
		}
	}
}
