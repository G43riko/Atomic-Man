package Atomic.object;

import java.awt.Color;
import java.awt.Graphics2D;

import Atomic.core.Input;
import Atomic.util.Vector;

public class Player extends GameObject{
	private Vector position;
	private Vector size;
	private Color color;
	private float speed; 
	
	public Player(){
		position = new Vector (40,40);
		size = new Vector (40,40);
		color = Color.BLUE;
		speed = 5;
	}
	
	public void input(float delta,Input input){
		if(input.isKeyDown(87)){ //w
			position = position.add(new Vector(0,-speed*delta));
		}
		if(input.isKeyDown(83)){ //s
			position = position.add(new Vector(0,speed*delta));
		}
		if(input.isKeyDown(65)){ //a
			position = position.add(new Vector(-speed*delta,0));
		}
		if(input.isKeyDown(68)){ //d
			position = position.add(new Vector(speed*delta,0));
		}
	}
	
	public void render(Graphics2D g2){
		g2.setColor(color);
		g2.fillRect(position.getXi(), position.getYi(), size.getXi(), size.getYi());
	}
}
