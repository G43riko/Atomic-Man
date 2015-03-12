package Atomic.object;

import java.awt.Color;
import java.awt.Graphics2D;

import Atomic.component.Explosion;
import Atomic.util.GColor;
import Atomic.util.Vector;

public class Block extends GameObject{
	public final static int WIDTH = 40;
	public final static int HEIGHT = 40;
	private Color color;
	private Level level;
	private int healt;
	private int type;
	
	public Block(Vector position, int type, Level level){
		super(position);
		this.level = level;
		this.type = type;
		this.healt = 20;
		color = GColor.randomize(40, Color.GREEN);
		
		if(type==0)
			color = new GColor(0,0,0,0);
		else if(type == 1)
			color = GColor.randomize(50, Color.GREEN);
		else if(type == 2)
			color = GColor.randomize(50, Color.PINK);
		else if(type == 3)
			color = GColor.randomize(50, Color.CYAN);
		else if(type == 4)
			color = GColor.randomize(50, Color.DARK_GRAY);
	}
	
	public void hit(int val){
		healt-=val;
		color = color.darker();
		if(healt <= 0 || (color.getRed()==0 && color.getGreen()==0 && color.getBlue()==0)){
			level.addExplosion(new Explosion(getPosition(),(int)(Math.random()*3),level,10));
			type = 0;
		}
	}
	
	public void render(Graphics2D g2){
		if(type==0)
			return;
		Vector pos = getPosition().sub(level.getOffset());
		
		g2.setColor(color);
		g2.fillRect(pos.getXi(), pos.getYi(), WIDTH, HEIGHT);
	}

	public int getType() {
		return type;
	}

}
