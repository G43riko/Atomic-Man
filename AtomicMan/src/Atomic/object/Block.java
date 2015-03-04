package Atomic.object;

import java.awt.Color;
import java.awt.Graphics2D;

import Atomic.util.GColor;
import Atomic.util.Vector;

public class Block extends GameObject{
	public final static int WIDTH = 40;
	public final static int HEIGHT = 40;
	private Color color;
	private Level level;
	private int type;
	private Vector position;
	
	public Block(Vector position, int type, Level level){
		this.level = level;
		this.position = position;
		this.type = type;
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
	
	public void render(Graphics2D g2){
		if(type==0)
			return;
		g2.setColor(color);
		g2.fillRect(position.getXi()-level.getOffset().getXi(), position.getYi()-level.getOffset().getYi(), WIDTH, HEIGHT);
	}

	public int getType() {
		return type;
	}

	public Vector getPosition() {
		return position;
	}
}
