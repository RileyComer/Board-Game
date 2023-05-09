package displays;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.Gameframe;
import main.Gui;

public class Mode1Display{
	
	private Gui gui;
	private Gameframe gameframe;
	private int size,startX,startY,frameW,frameH,boarderSize;
	private Color p1Color, p2Color, borderColor, backgroundColor, ballColor;
	//game stuff
	
	public Mode1Display(Gui gui, Gameframe gameframe){
		this.gui=gui;
		this.gameframe=gameframe;
		frameW=gameframe.getWidth();
		frameH=gameframe.getHeight();
		boarderSize=1;
		
		//colors
		p2Color=new Color(30, 30, 20);
		p1Color=new Color(200, 200, 180);
		borderColor=new Color(80, 80, 80);
		backgroundColor=new Color(0, 50, 0);
		ballColor=new Color(0, 130, 130);
	}
	
	public void render(Graphics g) {
		if((gui.getWidth()/frameW)<gui.getHeight()/frameH) {
			size=gui.getWidth()/frameW;
		}else {
			size=gui.getHeight()/frameH;
		}
		startX=(int)((gui.getWidth()/2.0)-((size*frameW)/2.0));
		startY=(int)((gui.getHeight()/2.0)-((size*frameH)/2.0));
		
		//BackGround
		g.setColor(backgroundColor);
		g.fillRect(0, 0, gui.getWidth(), gui.getHeight());
		
		//Border
		g.setColor(borderColor);
		g.fillRect(startX, startY, size*frameW, size*frameH);
		
		//Board
		int[][] area=gameframe.getBoard().getArea();
		for(int y=0; y< frameH; y++) {
			for(int x=0; x<frameW;x++) {
				if(area[x][y]==1) {
					g.setColor(p1Color);
				}else {
					g.setColor(p2Color);
				}
				g.fillRect(startX+size*x+boarderSize, startY+size*((frameH-1)-y)+boarderSize, size-boarderSize*2, size-boarderSize*2);
			}
		}
		
		//Pieces
		int[][] pieces=gameframe.getBoard().getPieces();
		for(int y=0; y< frameH; y++) {
			for(int x=0; x<frameW;x++) {
				if(pieces[x][y]!=0) {
					drawPiece(g, area[x][y], pieces[x][y], x, y);
				}
			}
		}
		
		//highlight
		if(gameframe.getHx()!=-1 && gameframe.getHy()!=-1) {
			if(gameframe.getTurnCount()<2) {
				drawPiece(g, 3, 5, gameframe.getHx(), gameframe.getHy());
			}else {
				drawPiece(g, 3, gameframe.getRotation(), gameframe.getHx(), gameframe.getHy());
			}
		}
		drawBall(g, 4, gameframe.getBoard().getBallX(), gameframe.getBoard().getBallY());
	}
	
	private void drawPiece(Graphics g, int player, int value, int x, int y) {
		int pWidth=9;
		
		if(player==1) {
			g.setColor(p2Color);
		}else if(player==2){
			g.setColor(p1Color);
		}else {
			g.setColor(new Color(200, 230, 0));
		}
		int px, py;
		int pSize=size/pWidth;
		if(value==5) {
			g.fillOval(startX+(int)(size*x)+boarderSize, startY+(int)(size*((frameH-1)-y))+boarderSize, size-boarderSize*2, size-boarderSize*2);
			int iconSize=size/3;
			if(gameframe.getBoard().getArea()[x][y]==1) {
				g.setColor(p1Color);
			}else {
				g.setColor(p2Color);
			}
			g.fillOval(startX+(int)(size*x)+boarderSize+iconSize*1, startY+(int)(size*((frameH-1)-y))+boarderSize+iconSize*1, iconSize, iconSize);
		}else if(value==4) {
			px=4;
			py=2;
			g.fillRect(startX+size*x+boarderSize+px*pSize, startY+size*((frameH-1)-y)+boarderSize+pSize*((pWidth-1)-py), pSize, pSize);
			
			px=4;
			py=3;
			g.fillRect(startX+size*x+boarderSize+px*pSize, startY+size*((frameH-1)-y)+boarderSize+pSize*((pWidth-1)-py), pSize, pSize);
			
			px=4;
			py=4;
			g.fillRect(startX+size*x+boarderSize+px*pSize, startY+size*((frameH-1)-y)+boarderSize+pSize*((pWidth-1)-py), pSize, pSize);
			
			px=4;
			py=5;
			g.fillRect(startX+size*x+boarderSize+px*pSize, startY+size*((frameH-1)-y)+boarderSize+pSize*((pWidth-1)-py), pSize, pSize);
			
			px=4;
			py=6;
			g.fillRect(startX+size*x+boarderSize+px*pSize, startY+size*((frameH-1)-y)+boarderSize+pSize*((pWidth-1)-py), pSize, pSize);
			
			px=3;
			py=5;
			g.fillRect(startX+size*x+boarderSize+px*pSize, startY+size*((frameH-1)-y)+boarderSize+pSize*((pWidth-1)-py), pSize, pSize);
			
			px=2;
			py=4;
			g.fillRect(startX+size*x+boarderSize+px*pSize, startY+size*((frameH-1)-y)+boarderSize+pSize*((pWidth-1)-py), pSize, pSize);
			
			px=5;
			py=5;
			g.fillRect(startX+size*x+boarderSize+px*pSize, startY+size*((frameH-1)-y)+boarderSize+pSize*((pWidth-1)-py), pSize, pSize);
			
			px=6;
			py=4;
			g.fillRect(startX+size*x+boarderSize+px*pSize, startY+size*((frameH-1)-y)+boarderSize+pSize*((pWidth-1)-py), pSize, pSize);
			
		}else if(value==2) {
			px=4;
			py=6;
			g.fillRect(startX+size*x+boarderSize+px*pSize, startY+size*((frameH-1)-y)+boarderSize+pSize*((pWidth-1)-py), pSize, pSize);
			
			px=4;
			py=5;
			g.fillRect(startX+size*x+boarderSize+px*pSize, startY+size*((frameH-1)-y)+boarderSize+pSize*((pWidth-1)-py), pSize, pSize);
			
			px=4;
			py=4;
			g.fillRect(startX+size*x+boarderSize+px*pSize, startY+size*((frameH-1)-y)+boarderSize+pSize*((pWidth-1)-py), pSize, pSize);
			
			px=4;
			py=3;
			g.fillRect(startX+size*x+boarderSize+px*pSize, startY+size*((frameH-1)-y)+boarderSize+pSize*((pWidth-1)-py), pSize, pSize);
			
			px=4;
			py=2;
			g.fillRect(startX+size*x+boarderSize+px*pSize, startY+size*((frameH-1)-y)+boarderSize+pSize*((pWidth-1)-py), pSize, pSize);
			
			px=3;
			py=3;
			g.fillRect(startX+size*x+boarderSize+px*pSize, startY+size*((frameH-1)-y)+boarderSize+pSize*((pWidth-1)-py), pSize, pSize);
			
			px=2;
			py=4;
			g.fillRect(startX+size*x+boarderSize+px*pSize, startY+size*((frameH-1)-y)+boarderSize+pSize*((pWidth-1)-py), pSize, pSize);
			
			px=5;
			py=3;
			g.fillRect(startX+size*x+boarderSize+px*pSize, startY+size*((frameH-1)-y)+boarderSize+pSize*((pWidth-1)-py), pSize, pSize);
			
			px=6;
			py=4;
			g.fillRect(startX+size*x+boarderSize+px*pSize, startY+size*((frameH-1)-y)+boarderSize+pSize*((pWidth-1)-py), pSize, pSize);
			
		}else if(value==1) {
			px=2;
			py=4;
			g.fillRect(startX+size*x+boarderSize+px*pSize, startY+size*((frameH-1)-y)+boarderSize+pSize*((pWidth-1)-py), pSize, pSize);
			
			px=3;
			py=4;
			g.fillRect(startX+size*x+boarderSize+px*pSize, startY+size*((frameH-1)-y)+boarderSize+pSize*((pWidth-1)-py), pSize, pSize);
			
			px=4;
			py=4;
			g.fillRect(startX+size*x+boarderSize+px*pSize, startY+size*((frameH-1)-y)+boarderSize+pSize*((pWidth-1)-py), pSize, pSize);
			
			px=5;
			py=4;
			g.fillRect(startX+size*x+boarderSize+px*pSize, startY+size*((frameH-1)-y)+boarderSize+pSize*((pWidth-1)-py), pSize, pSize);
			
			px=6;
			py=4;
			g.fillRect(startX+size*x+boarderSize+px*pSize, startY+size*((frameH-1)-y)+boarderSize+pSize*((pWidth-1)-py), pSize, pSize);
			
			px=5;
			py=3;
			g.fillRect(startX+size*x+boarderSize+px*pSize, startY+size*((frameH-1)-y)+boarderSize+pSize*((pWidth-1)-py), pSize, pSize);
			
			px=4;
			py=2;
			g.fillRect(startX+size*x+boarderSize+px*pSize, startY+size*((frameH-1)-y)+boarderSize+pSize*((pWidth-1)-py), pSize, pSize);
			
			px=5;
			py=5;
			g.fillRect(startX+size*x+boarderSize+px*pSize, startY+size*((frameH-1)-y)+boarderSize+pSize*((pWidth-1)-py), pSize, pSize);
			
			px=4;
			py=6;
			g.fillRect(startX+size*x+boarderSize+px*pSize, startY+size*((frameH-1)-y)+boarderSize+pSize*((pWidth-1)-py), pSize, pSize);
			
		}else if(value==3) {
			px=6;
			py=4;
			g.fillRect(startX+size*x+boarderSize+px*pSize, startY+size*((frameH-1)-y)+boarderSize+pSize*((pWidth-1)-py), pSize, pSize);
			
			px=5;
			py=4;
			g.fillRect(startX+size*x+boarderSize+px*pSize, startY+size*((frameH-1)-y)+boarderSize+pSize*((pWidth-1)-py), pSize, pSize);
			
			px=4;
			py=4;
			g.fillRect(startX+size*x+boarderSize+px*pSize, startY+size*((frameH-1)-y)+boarderSize+pSize*((pWidth-1)-py), pSize, pSize);
			
			px=3;
			py=4;
			g.fillRect(startX+size*x+boarderSize+px*pSize, startY+size*((frameH-1)-y)+boarderSize+pSize*((pWidth-1)-py), pSize, pSize);
			
			px=2;
			py=4;
			g.fillRect(startX+size*x+boarderSize+px*pSize, startY+size*((frameH-1)-y)+boarderSize+pSize*((pWidth-1)-py), pSize, pSize);
			
			px=3;
			py=3;
			g.fillRect(startX+size*x+boarderSize+px*pSize, startY+size*((frameH-1)-y)+boarderSize+pSize*((pWidth-1)-py), pSize, pSize);
			
			px=4;
			py=2;
			g.fillRect(startX+size*x+boarderSize+px*pSize, startY+size*((frameH-1)-y)+boarderSize+pSize*((pWidth-1)-py), pSize, pSize);
			
			px=3;
			py=5;
			g.fillRect(startX+size*x+boarderSize+px*pSize, startY+size*((frameH-1)-y)+boarderSize+pSize*((pWidth-1)-py), pSize, pSize);
			
			px=4;
			py=6;
			g.fillRect(startX+size*x+boarderSize+px*pSize, startY+size*((frameH-1)-y)+boarderSize+pSize*((pWidth-1)-py), pSize, pSize);
			
		}
		
	}
	
	private void drawBall(Graphics g, int value, double x, double y) {
		g.setColor(ballColor);
		g.fillOval(startX+(int)(size*x)+boarderSize, startY+(int)(size*((frameH-1)-y))+boarderSize, size-boarderSize*2, size-boarderSize*2);
		
		int iconSize=size/3;
		Font font=new Font("arial", Font.BOLD, iconSize);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString(""+((gameframe.getTurnMax()+1-gameframe.getTurnCount())/2), startX+(int)(size*x)+boarderSize+iconSize*1, startY+(int)(size*((frameH-1)-y))+boarderSize+iconSize*2);
	}
}