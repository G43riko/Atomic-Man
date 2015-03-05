package Atomic.object;

import java.awt.Color;
import java.awt.Graphics2D;

import Atomic.core.Input;
import Atomic.util.GColor;
import Atomic.util.Vector;

public class Player extends GameObject{
	public final static int WIDTH = 30;
	public final static int HEIGHT = 30;
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
			Vector newPos = position.add(new Vector(0,-speed*delta));
			if(level.getMap().isCollision(newPos) ||
			  (position.getX()%Block.WIDTH != 0 && (position.getX()+WIDTH)%Block.WIDTH != 0 && level.getMap().isCollision(newPos.add(new Vector(WIDTH,0))))){
				position.setY(level.getMap().get(newPos).getPosition().getY()+Block.HEIGHT);
			}
			else
				position = newPos;
		}
		if(input.isKeyDown(83)){ //s
			Vector newPos = position.add(new Vector(0,speed*delta));
			if(level.getMap().isCollision(newPos.add(new Vector(0,HEIGHT))) ||
			  ((position.getX()+WIDTH)%Block.WIDTH != 0 && level.getMap().isCollision(newPos.add(new Vector(WIDTH,HEIGHT))))){
				position.setY(level.getMap().get(newPos.add(new Vector(0,HEIGHT))).getPosition().getY()-HEIGHT);
			}
			else
				position = newPos;
		}
		if(input.isKeyDown(65)){ //a
			Vector newPos = position.add(new Vector(-speed*delta,0));
			if(level.getMap().isCollision(newPos) ||
			  (position.getY()%Block.HEIGHT != 0 && (position.getY()+HEIGHT)%Block.HEIGHT != 0 && level.getMap().isCollision(newPos.add(new Vector(0,HEIGHT))))){
				position.setX(level.getMap().get(newPos).getPosition().getX()+Block.WIDTH);
			}
			else
				position = newPos;
		}
		if(input.isKeyDown(68)){ //d
			Vector newPos = position.add(new Vector(speed*delta, 0));
			if(level.getMap().isCollision(newPos.add(new Vector(WIDTH,0))) ||
			  ((position.getY()+HEIGHT)%Block.HEIGHT != 0 && level.getMap().isCollision(newPos.add(new Vector(WIDTH,HEIGHT))))){
				position.setX(level.getMap().get(newPos.add(new Vector(WIDTH,0))).getPosition().getX()-WIDTH);
			}
			else
				position = newPos;
		}
		
		if(input.isMouseDown(1)){
			Vector realMousePos = input.getMousePos().add(level.getOffset());
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
		g2.fillRect(position.getXi()-level.getOffset().getXi(), position.getYi()-level.getOffset().getYi(), WIDTH, HEIGHT);
	}

	public Vector getPosition() {
		return position;
	}
}
