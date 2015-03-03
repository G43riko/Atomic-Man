package Atomic.object;

import java.awt.Color;
import java.awt.Graphics2D;

import Atomic.util.GColor;
import Atomic.util.Vector;

public class Bullet {
	private Vector pos;
	private Vector dir;
	private float speed;
	private GColor color;
	private Level level;
	private int size;
	private boolean dead;
	
	public Bullet(Level level, Vector dir){
		this.level = level;
		pos = level.getPlayer().getPosition();
		color = GColor.randomize(100, Color.YELLOW);
		this.dir = dir;
		speed = 4;
		size = 5;
	}
	
	public void update(float delta){
		pos = pos.add(dir.mul(speed));
		
		if(pos.getX() + size <= 0 || pos.getX() -size >= Map.NUM_X * Block.WIDTH || pos.getY() + size <= 0 || pos.getY() -size >= Map.NUM_Y * Block.HEIGHT){
			dead = true;
		}
	}
	
	public void render(Graphics2D g2){
		g2.setColor(color);
		g2.fillRect(pos.getXi()-level.getOffset().getXi(), pos.getYi()-level.getOffset().getYi(), 5, 5);
	}

	public boolean isDead() {
		return dead;
	}
}
