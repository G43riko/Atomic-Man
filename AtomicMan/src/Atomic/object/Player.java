package Atomic.object;

import java.awt.Color;
import java.awt.Graphics2D;

import Atomic.core.Input;
import Atomic.util.GColor;
import Atomic.util.Vector;

public class Player extends GameObject{
	private Vector position;
	//private Vector size;
	private Color color;
	private float speed; 
	private Level level;
	
	public Player(Level level){
		this.level = level;
		position = new Vector (40,40);
		//size = new Vector (40,40);
		color = GColor.BLACK;
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
		
		if(input.isMouseDown(1)){
			Vector realMousePos = input.getMousePos().sub(level.getOffset());
			level.addBullet(new Bullet(level, realMousePos.sub(position).Normalized()));
		}
	}
	
	public void update(float delta){
		level.getOffset().setX(position.getX() - level.getCanvas().getWidth() / 2);
		level.getOffset().setY(position.getY() - level.getCanvas().getHeight() / 2);

		//skontroluje posun
		if(level.getOffset().getX() < 0){
			level.getOffset().setX(0);
        }
        if(level.getOffset().getX() > (Map.NUM_X * Block.WIDTH) - level.getCanvas().getWidth()){
        	level.getOffset().setX((Map.NUM_X * Block.WIDTH) - level.getCanvas().getWidth());
        }
        if(level.getOffset().getY() < 0){
        	level.getOffset().setY(0);
        }	        
        if(level.getOffset().getY() > (Map.NUM_Y * Block.HEIGHT) - level.getCanvas().getHeight()){
        	level.getOffset().setY((Map.NUM_Y * Block.HEIGHT) - level.getCanvas().getHeight()); 
        }
        
      //skontroluje pozíciu
        if(position.getX() < 0){
        	position.setX(0);
        }
        if(position.getY() < 0){
        	position.setY(0);
        }
        if(position.getX() + Block.WIDTH > Map.NUM_X * Block.WIDTH){
        	position.setX(Map.NUM_X * Block.WIDTH - Block.WIDTH);
        }
        if(position.getY() + Block.HEIGHT > Map.NUM_Y * Block.HEIGHT){
        	position.setY(Map.NUM_Y * Block.HEIGHT - Block.HEIGHT);
        }
	}
	
	public void render(Graphics2D g2){
		g2.setColor(color);
		g2.fillRect(position.getXi()-level.getOffset().getXi(), position.getYi()-level.getOffset().getYi(), Block.WIDTH, Block.HEIGHT);
	}

	public Vector getPosition() {
		return position;
	}
}
