package Atomic.object;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;

import Atomic.util.GColor;
import Atomic.util.Vector;

public class Bullet {
	public final static int WIDTH = 4;
	public final static int HEIGHT = 4;
	
	private Vector position;
	private Vector dir;
	private int speed;
	private GColor color;
	private Level level;
	private float stroke =  3 +(float)Math.random()*4-2;
	private int size;
	private int demage;
	private boolean dead;
	
	public Bullet(Level level, Vector dir, int accuracy){
		this.level = level;
		this.dir = dir.add(new Vector((float)Math.random()/accuracy, (float)Math.random()/accuracy));
		
		position = level.getPlayer().getPosition().add(new Vector(Player.WIDTH/2, Player.HEIGHT/2));
		color = GColor.randomize(100, Color.RED);
		speed = 15+(int)(Math.random()*10-5);
		size = 5;
		demage = 1;
		dead = false;
	}
	
	public void update(float delta){
		position = position.add(dir.mul(speed));
		
		if(position.getX() + size <= 0 || position.getX() - size >= Map.NUM_X * Block.WIDTH || 
		   position.getY() + size <= 0 || position.getY() - size >= Map.NUM_Y * Block.HEIGHT){
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
		Vector pos = position.sub(level.getOffset());
		Vector sur  = pos.sub(dir.mul(speed));
		g2.setStroke(new BasicStroke(stroke));
		
		g2.drawLine(pos.getXi(), pos.getYi(), sur.getXi(), sur.getYi());
	}

	public boolean isDead() {
		return dead;
	}
}
