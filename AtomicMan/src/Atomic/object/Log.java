package Atomic.object;

import java.awt.Color;
import java.awt.Graphics2D;

public class Log extends GameObject{
	private Level level;
	private int fps;
	
	public Log(Level level){
		this.level = level;
	}
	
	public void render(Graphics2D g2){
		if(level==null)
			return;
		String s0 = "FPS: "+fps;
		String s1 = "Nepriatelove: "+level.getNumOfEnemies();
		String s2 = "Striel: "+level.getNumOfBullets();
		
		int max = Math.max(s0.length(), Math.max(s1.length(), s2.length())) * 6;
		
		
		g2.setColor(new Color(0,0,0,127));
		g2.fillRect(0, 0, max, 30);
		
		g2.setColor(Color.WHITE);
		g2.drawString(s0, 0, 10);
		g2.drawString(s1, 0, 20);
		g2.drawString(s2, 0, 30);
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public void setFps(int fps) {
		this.fps = fps;
	}
}
