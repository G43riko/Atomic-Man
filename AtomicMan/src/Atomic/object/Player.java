package Atomic.object;

import java.awt.Color;
import java.awt.Graphics2D;

import Atomic.core.Input;
import Atomic.util.GColor;
import Atomic.util.Vector;

public class Player extends GameObject{
	public final static int WIDTH = 30;
	public final static int HEIGHT = 30;
	//private Vector size;
	private Color color;
	private float speed; 
	private Level level;
	
	public Player(Level level){
		super(new Vector (80,80));
		this.level = level;
		//size = new Vector (40,40);
		color = GColor.BLACK;
		speed = 5;
	}
	
	public void input(float delta,Input input){
		if(input.isKeyDown(87)){ //w
			Vector newPos = getPosition().add(new Vector(0,-speed*delta));
			if(level.getMap().isCollision(newPos) ||
			  (getPosition().getX()%Block.WIDTH != 0 && (getPosition().getX()+WIDTH)%Block.WIDTH != 0 && level.getMap().isCollision(newPos.add(new Vector(WIDTH,0))))){
				getPosition().setY(level.getMap().get(newPos).getPosition().getY()+Block.HEIGHT);
			}
			else
				setPosition(newPos);
		}
		if(input.isKeyDown(83)){ //s
			Vector newPos = getPosition().add(new Vector(0,speed*delta));
			if(level.getMap().isCollision(newPos.add(new Vector(0,HEIGHT))) ||
			  ((getPosition().getX()+WIDTH)%Block.WIDTH != 0 && level.getMap().isCollision(newPos.add(new Vector(WIDTH,HEIGHT))))){
				getPosition().setY(level.getMap().get(newPos.add(new Vector(0,HEIGHT))).getPosition().getY()-HEIGHT);
			}
			else
				setPosition(newPos);
		}
		if(input.isKeyDown(65)){ //a
			Vector newPos = getPosition().add(new Vector(-speed*delta,0));
			if(level.getMap().isCollision(newPos) ||
			  (getPosition().getY()%Block.HEIGHT != 0 && (getPosition().getY()+HEIGHT)%Block.HEIGHT != 0 && level.getMap().isCollision(newPos.add(new Vector(0,HEIGHT))))){
				getPosition().setX(level.getMap().get(newPos).getPosition().getX()+Block.WIDTH);
			}
			else
				setPosition(newPos);
		}
		if(input.isKeyDown(68)){ //d
			Vector newPos = getPosition().add(new Vector(speed*delta, 0));
			if(level.getMap().isCollision(newPos.add(new Vector(WIDTH,0))) ||
			  ((getPosition().getY()+HEIGHT)%Block.HEIGHT != 0 && level.getMap().isCollision(newPos.add(new Vector(WIDTH,HEIGHT))))){
				getPosition().setX(level.getMap().get(newPos.add(new Vector(WIDTH,0))).getPosition().getX()-WIDTH);
			}
			else
				setPosition(newPos);
		}
		
		if(input.isKeyDown(17)){ //d
			level.addBomb(this);
		}
		
		
		if(input.isMouseDown(1)){
			Vector realMousePos = input.getMousePos().add(level.getOffset());
			level.addBullet(new Bullet(level, realMousePos.sub(getPosition()).Normalized()));
		}
	}
	
	public void update(float delta){
		level.getOffset().setX(getPosition().getX() - level.getCanvas().getWidth() / 2);
		level.getOffset().setY(getPosition().getY() - level.getCanvas().getHeight() / 2);

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
        if(getPosition().getX() < 0){
        	getPosition().setX(0);
        }
        if(getPosition().getY() < 0){
        	getPosition().setY(0);
        }
        if(getPosition().getX() + Block.WIDTH > Map.NUM_X * Block.WIDTH){
        	getPosition().setX(Map.NUM_X * Block.WIDTH - Block.WIDTH);
        }
        if(getPosition().getY() + Block.HEIGHT > Map.NUM_Y * Block.HEIGHT){
        	getPosition().setY(Map.NUM_Y * Block.HEIGHT - Block.HEIGHT);
        }
	}
	
	public void render(Graphics2D g2){
		g2.setColor(color);
		g2.fillRect(getPosition().getXi()-level.getOffset().getXi(), getPosition().getYi()-level.getOffset().getYi(), WIDTH, HEIGHT);
	}
}
