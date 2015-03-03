package Atomic.core;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import Atomic.component.Level;
import Atomic.component.Window;
import Atomic.object.GameObject;

public abstract class CoreGame {
	private boolean isRunning;
	private float frameTime;
	private Graphics2D g2;
	private Window window;
	private Canvas canvas;
	private Level level;
	public  Input input;
	private ArrayList<GameObject> scene = new ArrayList<GameObject>();
	
	public abstract void init();
	
	protected void createWindow(int width, int height, String title){
		window = new Window(width, height, title);
		canvas = new Canvas();
		window.add(canvas);
		input = new Input();
		canvas.addKeyListener(input);
	}
	
	protected void createLevel(Level level){
		this.level = new Level();
		scene.add(level);
	}
	
	public void start(float fps){
		frameTime = 1000/fps;
		isRunning = true;
		mainLoop();
	}
	
	private void input(float delta){
		for(GameObject g :scene){
			g.input(delta,input);
		}
	}
	
	private void update(float delta){
		for(GameObject g :scene){
			g.update(delta);
		}
	}
	
	private void prepare(){
		BufferStrategy buffer = canvas.getBufferStrategy();
		if(buffer==null){
			canvas.createBufferStrategy(3);
			return;
		}
		g2 = (Graphics2D)buffer.getDrawGraphics();
		
		render();
		
		g2.dispose();
		buffer.show();
	}
	
	private void render(){
		g2.setColor(level.getBackgroundColor());
		g2.fillRect(0, 0, window.getWidth(), window.getHeight());
		
		for(GameObject g :scene){
			g.render(g2);
		}
		
	}
	
	private void mainLoop(){
		int frames = 0;
		int ticks = 0;
		double lastTime;
		double startTime = System.currentTimeMillis();
		while(isRunning){
			lastTime = System.currentTimeMillis();
			while(System.currentTimeMillis()-lastTime <= frameTime){
				ticks++;
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {e.printStackTrace(); }
			}
			float cicleTime = (float)(System.currentTimeMillis()-lastTime) / frameTime;
			input(cicleTime);
			update(cicleTime);
			prepare();
			frames++;
			if(System.currentTimeMillis()-startTime >= 1000){
				System.out.println("frames: "+frames+" ticks: "+ticks/*+" startTime: "+(System.currentTimeMillis()-startTime)*/);
				ticks = 0;
				frames = 0;
				startTime = System.currentTimeMillis();
			}
		}
	}
}
