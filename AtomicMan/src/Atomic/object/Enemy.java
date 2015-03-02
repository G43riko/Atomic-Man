package Atomic.object;

import java.awt.Color;
import java.awt.Graphics;

import Atomic.util.Vector;

public class Enemy  extends GameObject{
	private Vector pos;
	private Vector dir;
	private Vector size;
	private float speed;
	private Color color;
	
	public Enemy(){
		this(new Vector(40,40));
	}
	
	public Enemy(Vector pos){
		this(pos, new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
	}
	
	public Enemy(Vector pos,Color color){
		this.pos = pos;
		this.color = color;
		dir = new Vector(1,0);
		size = new Vector(30,10);
		speed = 2;
	}
	
	public void render(Graphics g){
		g.setColor(color);
		g.fillRect(pos.getXi(), pos.getYi(), size.getXi(), size.getYi());
	}
	
	public boolean move(){
		return false;
	}
}
