package Atomic.object;

import java.awt.Graphics;
import java.awt.Graphics2D;

import Atomic.util.GColor;
import Atomic.util.Vector;

public class Enemy  extends GameObject{
	private Vector pos;
	private Vector dir;
	private float speed;
	private GColor color;
	private Level level;
	
	public Enemy(Level level){
		this.level = level;
		pos = new Vector((float)(Math.random()*Map.NUM_X*Block.WIDTH),(float)(Math.random()*Map.NUM_Y*Block.HEIGHT));
		color = new GColor((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
		double angle = Math.random()*Math.PI*2;
		dir = new Vector((float)Math.sin(angle),(float)Math.cos(angle));
		speed = 4;
	}
	
	public void update(float delta){
		pos = pos.add(dir.mul(speed));
		
		if(pos.getX() <= 0 || pos.getX() + Block.WIDTH >= Map.NUM_X * Block.WIDTH){
			dir = dir.mul(new Vector(-1,1));
		}
		
		if(pos.getY()<= 0 || pos.getY() + Block.HEIGHT >= Map.NUM_Y * Block.HEIGHT){
			dir = dir.mul(new Vector(1,-1));
		}
	}
	
	public void render(Graphics2D g2){
		g2.setColor(color);
		g2.fillRect(pos.getXi()-level.getOffset().getXi(), pos.getYi()-level.getOffset().getYi(), Block.WIDTH, Block.HEIGHT);
	}
}
