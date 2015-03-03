package Atomic.component;

import java.awt.Color;
import java.awt.Graphics2D;

import Atomic.object.GameObject;
import Atomic.util.Vector;

public class Block extends GameObject{
	private Color color;
	private Level level;
	private Vector position;
	
	public Block(Vector position, int type, Level level){
		this.level = level;
		this.position = position;
		if(type==0)
			color = Color.BLACK;
		else if(type == 1)
			color = Color.GREEN;
		else if(type == 2)
			color = Color.PINK;
		else if(type == 3)
			color = Color.CYAN;
		else if(type == 4)
			color = Color.DARK_GRAY;
	}
	
	public void render(Graphics2D g2){
		g2.setColor(color);
		int x = position.getXi()-level.getOffset().getXi();
		int y = position.getYi()-level.getOffset().getYi();
		g2.fillRect(x, y, level.getMap().getBlockSize().getXi(), level.getMap().getBlockSize().getYi());
	}
}
