package main;


import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class Gui extends JFrame{ 
	
	private static final long serialVersionUID = 1L;
	private Display display;
	private Gameframe gameframe;
	
	public Gui(Window window, Gameframe gameframe) {
		super("Title");
		this.gameframe=gameframe;
		
		display =new Display(this, gameframe);
		add(display,BorderLayout.CENTER);
		
		//Mouse Handler
		HandlerClass handler=new HandlerClass();
		display.addMouseListener(handler);
		display.addMouseMotionListener(handler);
		
		//Exit key
		addKeyListener(
				new KeyAdapter() {
					public void keyPressed(KeyEvent ke) {  // handler
						if(ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
							if(gameframe.getMode().equals("Menu")) {
								System.exit(0);
							}else {
								gameframe.setMode("Menu");
							}
							
						}else if(ke.getKeyCode() == KeyEvent.VK_R) {
							gameframe.rotate();
						}
					}
				}
		);
	}
	
	public void redraw() {
		display.redraw();
	}
	
	private class HandlerClass implements MouseListener,MouseMotionListener{

		public void mouseClicked(MouseEvent e) {
			
		}

		public void mouseEntered(MouseEvent e) {
			
		}

		public void mouseExited(MouseEvent e) {
			
		}

		public void mousePressed(MouseEvent e) {
			
		}

		public void mouseReleased(MouseEvent e) {
			double x=e.getX();
			double y=e.getY(); 
			int size;
			int startX, startY;
			if(gameframe.getMode().equals("Menu")) {
				if((getWidth()/(3))<getHeight()/5) {
					size=getWidth()/(3);
				}else {
					size=getHeight()/(5);
				}
				startX=(int)((getWidth()/2.0)-((size*3)/2.0));
				startY=(int)((getHeight()/2.0)-((size*5)/2.0));
				
				if((x>startX&&x<(startX+(size*3)))&&(y>startY+(size*2)&&y<(startY+(size*5)))) {
					if(y<startY+(size*3)) {
						gameframe.setMode("Mode1");
					}else if(y<startY+(size*4)) {
						gameframe.setMode("Mode2");
					}else if(y<startY+(size*5)) {
						System.exit(0);
					}
				}
			}else if(gameframe.getMode().equals("Mode1")) {
				int frameW=gameframe.getWidth();
				int frameH=gameframe.getHeight();
				if((getWidth()/frameW)<getHeight()/frameH) {
					size=getWidth()/frameW;
				}else {
					size=getHeight()/frameH;
				}
				startX=(int)((getWidth()/2.0)-((size*frameW)/2.0));
				startY=(int)((getHeight()/2.0)-((size*frameH)/2.0));
				
				if((x>startX && x<startX+size*frameW) && (y>startY && y<startY+size*frameH)) {
					int px=(int) ((x-startX)/size);
					int py=frameH-1-(int) ((y-startY)/size);
					if(gameframe.getBoard().getArea()[px][py]==gameframe.getPlayerTurn() && gameframe.getBoard().getPieces()[px][py]==0 && !(gameframe.getBoard().getBallX()==px && gameframe.getBoard().getBallY()==py)) {
						if(gameframe.getTurnCount()<2) {
							if((px>0 && px<frameW-1) && (py>0 && py<frameH-1)) {
								gameframe.getBoard().addPiece(px, py, 5);
								gameframe.addTurn();
								gameframe.highlight(-1, -1);
							}
						}else {
							gameframe.getBoard().addPiece(px, py, gameframe.getRotation());
							gameframe.addTurn();
							gameframe.highlight(-1, -1);
						}
					}
				}
			}
		}
		
		public void mouseMoved(MouseEvent e) {
			double x=e.getX();
			double y=e.getY(); 
			int size;
			int startX, startY;
			if(gameframe.getMode().equals("Mode1")) {
				int frameW=gameframe.getWidth();
				int frameH=gameframe.getHeight();
				if((getWidth()/frameW)<getHeight()/frameH) {
					size=getWidth()/frameW;
				}else {
					size=getHeight()/frameH;
				}
				startX=(int)((getWidth()/2.0)-((size*frameW)/2.0));
				startY=(int)((getHeight()/2.0)-((size*frameH)/2.0));
				
				if((x>startX && x<startX+size*frameW) && (y>startY && y<startY+size*frameH)) {
					int px=(int) ((x-startX)/size);
					int py=frameH-1-(int) ((y-startY)/size);
					if(gameframe.getBoard().getArea()[px][py]==gameframe.getPlayerTurn() && gameframe.getBoard().getPieces()[px][py]==0) {
						if(gameframe.getTurnCount()<2) {
							if((px>0 && px<frameW-1) && (py>0 && py<frameH-1)) {
								gameframe.highlight(px, py);
							}else {
								gameframe.highlight(-1, -1);
							}
						}else {
							gameframe.highlight(px, py);
						}
					}else {
						gameframe.highlight(-1, -1);
					}
				}else {
					gameframe.highlight(-1, -1);
				}
			}
		}
		
		public void mouseDragged(MouseEvent e) {
			
		}
	}
}
