package main;
import javax.swing.JFrame;

public class Window {
	
	private Gui gui;
	private Gameframe gameframe;
	
	public Window() {
		// Creates Window
		gameframe=new Gameframe();
		gui= new Gui (this, gameframe);
		gui.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		gui.setUndecorated(true);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setVisible(true);
		
	}
	
	public void update() {
		if(gameframe.getMode().equals("Mode1")) {
			gameframe.update();
		}
		gui.redraw();
	}
}
