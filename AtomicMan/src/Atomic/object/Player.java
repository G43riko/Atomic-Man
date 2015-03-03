package Atomic.object;

import java.awt.Color;
import java.awt.Graphics2D;

import Atomic.component.Level;
import Atomic.core.Input;
import Atomic.util.Vector;

public class Player extends GameObject{
	private Vector position;
	private Vector size;
	private Color color;
	private float speed; 
	private Level level;
	
	public Player(Level level){
		this.level = level;
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
	
	public void update(float delta){
		level.getOffset().setX(position.getX() - level.getWindow().getWidth() / 2);
		level.getOffset().setY(position.getY() - level.getWindow().getHeight() / 2);

		//skontroluje posun
		if(level.getOffset().getX() < 0){
			level.getOffset().setX(0);
        }
        if(level.getOffset().getX() > (level.getMap().getNumberOfBlock().getX() * level.getMap().getBlockSize().getX()) - level.getWindow().getWidth()){
        	level.getOffset().setX((level.getMap().getNumberOfBlock().getX() * level.getMap().getBlockSize().getX()) - level.getWindow().getWidth());
        }
        if(level.getOffset().getY() < 0){
        	level.getOffset().setY(0);
        }	        
        if(level.getOffset().getY() > (level.getMap().getNumberOfBlock().getY() * level.getMap().getBlockSize().getY()) - level.getWindow().getHeight()){
        	level.getOffset().setY((level.getMap().getNumberOfBlock().getY() * level.getMap().getBlockSize().getY()) - level.getWindow().getHeight()); 
        }
        
      //skontroluje pozíciu
        if(position.getX() < 0){
        	position.setX(0);
        }
        if(position.getY() < 0){
        	position.setY(0);
        }
        if(position.getX() + level.getMap().getBlockSize().getX() > level.getMap().getNumberOfBlock().getX() * level.getMap().getBlockSize().getX()){
        	position.setX(level.getMap().getNumberOfBlock().getX() * level.getMap().getBlockSize().getX() - level.getMap().getBlockSize().getX());
        }
        if(position.getY() + level.getMap().getBlockSize().getY() > level.getMap().getNumberOfBlock().getY() * level.getMap().getBlockSize().getY()){
        	position.setY(level.getMap().getNumberOfBlock().getY() * level.getMap().getBlockSize().getY() - level.getMap().getBlockSize().getY());
        }
	}
	
	public void render(Graphics2D g2){
		g2.setColor(color);
		g2.fillRect(position.getXi()-level.getOffset().getXi(), position.getYi()-level.getOffset().getYi(), size.getXi(), size.getYi());
	}
}
