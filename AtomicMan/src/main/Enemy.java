package main;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy {
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
	
	public void draw(Graphics g){
		g.setColor(color);
		g.fillRect(pos.getXi(), pos.getYi(), size.getXi(), size.getYi());
	}
	
	public boolean move(){
//		pos.add(dir.mul(speed));
		
		pos.add(new Vector(dir.getX() * speed, 0));
		if(pos.getX()<0 || pos.getX() + size.getX()-3 > 800){
//			pos.add(new Vector(dir.getX() * speed, 0));
			dir.mul(new Vector(-1,1));
			System.out.println(dir.getX());
		}
		
//		pos.add(new Vector(0, dir.getY() * speed));
//		if(pos.getY()<0 || pos.getY() + size.getY() > 400)
//			pos.mul(new Vector(1,-1));
		
		
		return false;
	}
}
