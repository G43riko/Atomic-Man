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
		String[] logs = new String[]{"FPS: "+fps,
									 "Nepriatelove: "+level.getNumOfEnemies(),
									 "Striel: "+level.getNumOfBullets(),
									 "Blokov na mape: "+Map.NUM_X*Map.NUM_Y,
									// "Zni�iteln�ch blokov: "+level.getMap().getDestructible(),
									 "Vykreslen�ch blokov: "+level.getMap().getDrawable(),
									 "Pozicia hr��a: "+level.getPlayer().getPosition().toDecimal(5),
									 "offset: "+level.getOffset().toDecimal(5)
								};
		
		int max = logs[0].length();
    	for(int i=1 ; i<logs.length ; i++)
    		if(logs[i].length() > max)
    			max = logs[i].length();
		
		g2.setColor(new Color(0,0,0,127));
		g2.fillRect(0, 0, max*6, logs.length*10+10);
		
		g2.setColor(Color.WHITE);
		for(int i=0 ; i<logs.length ; i++){
			g2.drawString(logs[i], 0, i*10+10);
		}
		
	}

	//SETTERS
	
	public void setLevel(Level level) {
		this.level = level;
	}

	public void setFps(int fps) {
		this.fps = fps;
	}
}
