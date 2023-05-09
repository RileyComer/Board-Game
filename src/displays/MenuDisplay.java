package displays;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.Gui;

public class MenuDisplay{
	
	private Gui gui;
	private int size,startX,startY;
	private int buttonsW=3, buttonsH=5;
	
	public MenuDisplay(Gui gui1){
		gui=gui1;
	}
	
	public void render(Graphics g) {
		if((gui.getWidth()/(buttonsW))<gui.getHeight()/buttonsH) {
			size=gui.getWidth()/(buttonsW);
		}else {
			size=gui.getHeight()/(buttonsH);
		}
		startX=(int)((gui.getWidth()/2.0)-((size*buttonsW)/2.0));
		startY=(int)((gui.getHeight()/2.0)-((size*buttonsH)/2.0));
		
		//Title
		Font font=new Font("arial", Font.BOLD, 100);
		g.setFont(font);
		
		g.setColor(new Color(0,0,0));
		g.fillRect(startX, startY, size*3, size*2);
		
		g.setColor(Color.WHITE);
		g.drawString("Menu", startX+190, size);
		
		//Buttons
		g.setColor(new Color(0,0,150));
		g.fillRect(startX, startY+(size*2), (size*3), size);
		
		g.setColor(Color.WHITE);
		g.drawString("Mode1", startX+180, (size/2)+size*2);
		
		g.setColor(new Color(0,150,0));
		g.fillRect(startX, startY+(size*3), (size*3), size);
		
		g.setColor(Color.WHITE);
		g.drawString("Mode2", startX+180, (size/2)+size*3);
		
		g.setColor(new Color(150,0,0));
		g.fillRect(startX, startY+(size*4), (size*3), size);
		
		g.setColor(Color.WHITE);
		g.drawString("Quit", startX+200, (size/2)+size*4);
	}
}
