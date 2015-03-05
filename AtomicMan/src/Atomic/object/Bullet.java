package Atomic.object;

import java.awt.Color;
import java.awt.Graphics2D;

import Atomic.util.GColor;
import Atomic.util.Vector;

public class Bullet {
	public final static int WIDTH = 4;
	public final static int HEIGHT = 4;
	private Vector position;
	private Vector dir;
	private float speed;
	private GColor color;
	private Level level;
	private int size;
	private int demage;
	private boolean dead;
	
	public Bullet(Level level, Vector dir){
		this.level = level;
		position = level.getPlayer().getPosition();
		color = GColor.randomize(100, Color.YELLOW);
		this.dir = dir;
		speed = 15;
		size = 5;
		demage = 1;
		dead = false;
	}
	
	public void update(float delta){
		position = position.add(dir.mul(speed));
		
		if(position.getX() + size <= 0 || position.getX() - size >= Map.NUM_X * Block.WIDTH || position.getY() + size <= 0 || position.getY() - size >= Map.NUM_Y * Block.HEIGHT){
			dead = true;
		}
		Block b = level.getMap().get(position.add(new Vector(WIDTH/2,HEIGHT/2)));
		if(b.getType()!=0){
			b.hit(demage);
			dead = true;
		}
	}
	
	public void render(Graphics2D g2){
		g2.setColor(color);
		g2.fillRect(position.getXi()-level.getOffset().getXi(), position.getYi()-level.getOffset().getYi(), WIDTH, HEIGHT);
	}

	public boolean isDead() {
		return dead;
	}
}
