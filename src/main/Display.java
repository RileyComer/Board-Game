package main;

import java.awt.*;

import javax.swing.*;

import displays.*;

public class Display extends JPanel{
	
	private static final long serialVersionUID = 1L;
	Toolkit t=Toolkit.getDefaultToolkit();
	private Gameframe gameframe;
	
	private MenuDisplay menu;
	private Mode1Display mode1Display;
	private Mode2Display mode2Display;
	
	//game stuff
	
	public Display(Gui gui, Gameframe gameframe){
		this.gameframe=gameframe;
		menu=new MenuDisplay(gui);
		mode1Display=new Mode1Display(gui, gameframe);
		mode2Display=new Mode2Display(gui, gameframe);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(gameframe.getMode().equals("Menu")) {
			this.setBackground(new Color(80,80,80));
			menu.render(g);
			
		}else if(gameframe.getMode().equals("Mode1")) {
			this.setBackground(new Color(100,100,100));
			mode1Display.render(g);
		}else if(gameframe.getMode().equals("Mode2")) {
			this.setBackground(new Color(20,20,20));
			mode2Display.render(g);
		}
	}
	
	public void redraw() {
		this.repaint();
	}
}