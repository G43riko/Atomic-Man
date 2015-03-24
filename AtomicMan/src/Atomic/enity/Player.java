package Atomic.enity;

import java.awt.Color;
import java.awt.Graphics2D;

import Atomic.component.Level;
import Atomic.core.Input;
import Atomic.enity.weapon.Bullet;
import Atomic.enity.weapon.Rocket;
import Atomic.map.Block;
import Atomic.map.Map;
import Atomic.object.GameObject;
import Atomic.util.GColor;
import Atomic.util.Vector;

public class Player extends GameObject{
	public final static int WIDTH = 30;
	public final static int HEIGHT = 30;
	
	private Color color;
	private Level level;
	private float speed;
	private int range;
	private int bombs;
	private int accularity;
	private int damage;
	private int direction;
	
	private static int upKey = Input.KEY_W;
	private static int downKey = Input.KEY_S;
	private static int rightKey = Input.KEY_D;
	private static int leftKey = Input.KEY_A;
	
	private double lastShot = System.currentTimeMillis();
	
//	private boolean atomBomb;
//	private boolean fireBomb;
	private boolean nano;
	private boolean skejtboard;
//	private boolean kicking;
//	private boolean shield;
//	private boolean ghost;
	
	//CONSTRUCTORS
	
	public Player(Level level){
		super(new Vector (80,80));
		this.level = level;
		color = GColor.BLACK;
		init();
	}
	
	//OTHERS
	
	private void init(){
		speed = 5;
		range = 10;
		bombs = 1;
		damage = 100;
		accularity = 20;
		direction = 2;
	}
	
	//OVERRIDES
	
	public void input(float delta,Input input){
		if(input.isKeyDown(upKey)){
			Vector newPos = getPosition().add(new Vector(0,-speed*delta));
			if(level.getMap().isCollision(newPos) ||
			  (getPosition().getX()%Block.WIDTH != 0 && (getPosition().getX()+WIDTH)%Block.WIDTH != 0 && level.getMap().isCollision(newPos.add(new Vector(WIDTH,0))))){
				getPosition().setY(level.getMap().get(newPos).getPosition().getY()+Block.HEIGHT);
			}
			else
				setPosition(newPos);
			direction = 0;
		}
		if(input.isKeyDown(downKey)){ //s
			Vector newPos = getPosition().add(new Vector(0,speed*delta));
			if(level.getMap().isCollision(newPos.add(new Vector(0,HEIGHT))) ||
			  ((getPosition().getX()+WIDTH)%Block.WIDTH != 0 && level.getMap().isCollision(newPos.add(new Vector(WIDTH,HEIGHT))))){
				getPosition().setY(level.getMap().get(newPos.add(new Vector(0,HEIGHT))).getPosition().getY()-HEIGHT);
			}
			else
				setPosition(newPos);
			direction = 2;
		}
		if(input.isKeyDown(leftKey)){ //a
			Vector newPos = getPosition().add(new Vector(-speed*delta,0));
			if(level.getMap().isCollision(newPos) ||
			  (getPosition().getY()%Block.HEIGHT != 0 && (getPosition().getY()+HEIGHT)%Block.HEIGHT != 0 && level.getMap().isCollision(newPos.add(new Vector(0,HEIGHT))))){
				getPosition().setX(level.getMap().get(newPos).getPosition().getX()+Block.WIDTH);
			}
			else
				setPosition(newPos);
			direction = 3;
		}
		if(input.isKeyDown(rightKey)){ //d
			Vector newPos = getPosition().add(new Vector(speed*delta, 0));
			if(level.getMap().isCollision(newPos.add(new Vector(WIDTH,0))) ||
			  ((getPosition().getY()+HEIGHT)%Block.HEIGHT != 0 && level.getMap().isCollision(newPos.add(new Vector(WIDTH,HEIGHT))))){
				getPosition().setX(level.getMap().get(newPos.add(new Vector(WIDTH,0))).getPosition().getX()-WIDTH);
			}
			else
				setPosition(newPos);
			direction = 1;
		}
		
		if(input.isKeyDown(Input.KEY_LCONTROL)){ //d
			level.addBomb(this);
		}
		
		
		if(input.isMouseDown(1) && System.currentTimeMillis() - lastShot > Bullet.CADENCE){
			
			Vector realMousePos = input.getMousePos().add(level.getOffset());
			Vector pos = getPosition().add(new Vector(WIDTH/2, HEIGHT/2));
			level.addWeapon(new Bullet(level, realMousePos.sub(pos).Normalized(),accularity,damage/20));
			lastShot = System.currentTimeMillis();
		}
		
		if(input.isMouseDown(3) && System.currentTimeMillis() - lastShot > Rocket.CADENCE){
			Vector realMousePos = input.getMousePos().add(level.getOffset());
			Vector pos = getPosition().add(new Vector(WIDTH/2, HEIGHT/2));
			level.addWeapon(new Rocket(level, realMousePos.sub(pos).Normalized(),accularity,damage));
			lastShot = System.currentTimeMillis();
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
		Vector pos = getPosition().sub(level.getOffset());
		
		g2.setColor(color);
		g2.fill3DRect(pos.getXi(), pos.getYi(), WIDTH, HEIGHT, true);
	}
	
	//GETTERS

	public int getRange() {
		return range;
	}

	public int getDamage() {
		return damage;
	}

	public boolean isNano() {
		return nano;
	}

	public int getDirection() {
		return direction;
	}

	public boolean isSkejtboard() {
		return skejtboard;
	}
	
}

