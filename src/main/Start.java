package main;

public class Start implements Runnable{
	
	private Thread thread;
	private Window window;
	
	private boolean running;
	
	
	public static void main(String[] args) {
		Start game=new Start();
		game.start();
	}
	
	private void start() {
		if(running) {
			return;
		}
		window=new Window();
		running=true;
		thread=new Thread((Runnable) this);
		thread.run();
	}
	
	public void run() {
		//frames
		double target=60.0;
		double nsPerTick=1000000000.0/target;
		long lastTime=System.nanoTime();
		long timer=System.currentTimeMillis();
		double unprocessed=0.0;
		boolean canRender=false;
		
		while(running) {
			long now=System.nanoTime();
			unprocessed+=(now-lastTime)/nsPerTick;
			lastTime=now;
			if(unprocessed>=1.0) {
				unprocessed--;
				canRender=true;
				
			}else {
				canRender=false;
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException ie){
				System.out.println("Scanning...");
			}
			if(canRender) {
				update();
			}
			
			if(System.currentTimeMillis()-1000>timer) {
			timer+=1000;
			}
		}
		System.exit(0);
	}
	
	private void update() {
		window.update();
	}
}
