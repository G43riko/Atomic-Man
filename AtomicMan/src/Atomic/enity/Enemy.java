package Atomic.enity;

import java.awt.Graphics2D;

import Atomic.component.Explosion;
import Atomic.component.Level;
import Atomic.map.Block;
import Atomic.map.Map;
import Atomic.object.GameObject;
import Atomic.util.GColor;
import Atomic.util.Vector;

public class Enemy  extends GameObject{
	private Vector dir;
	private float speed;
	private GColor color;
	private Level level;
	private int health;
	private boolean dead;
	
	//CONSTRUCTORS
	
	public Enemy(Level level){
		super(new Vector((float)(Math.random()*(Map.NUM_X*Block.WIDTH-Block.WIDTH)),(float)(Math.random()*(Map.NUM_Y*Block.HEIGHT-Block.HEIGHT))));
		this.level = level;
		color = new GColor((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
		double angle = Math.random()*Math.PI*2;
		dir = new Vector((float)Math.sin(angle),(float)Math.cos(angle));
		speed = 4;
		health = 5;
	}
	
	//OVERRIDES
	
	public void update(float delta){
//		pos = pos.add(dir.mul(speed*delta));
		

		if(getPosition().getX() + dir.getX()*speed*delta <= 0 || getPosition().getX() + dir.getX()*speed*delta + Block.WIDTH >= Map.NUM_X * Block.WIDTH){
			dir = dir.mul(new Vector(-1,1));
			return;
		}
		
		if(getPosition().getY() + dir.getY()*speed*delta <= 0 || getPosition().getY() + dir.getY()*speed*delta + Block.HEIGHT >= Map.NUM_Y * Block.HEIGHT){
			dir = dir.mul(new Vector(1,-1));
			return;
		}
		
		if(dir.getY()<0){ //up
			Vector newPos = getPosition().add(new Vector(0,-speed*delta));
			
			if(level.getMap().isCollision(newPos) ||
			  (getPosition().getX()%Block.WIDTH != 0 && (getPosition().getX()+Player.WIDTH)%Block.WIDTH != 0 && level.getMap().isCollision(newPos.add(new Vector(Player.WIDTH,0))))){
				getPosition().setY(level.getMap().get(newPos).getPosition().getY()+Block.HEIGHT);
				dir = dir.mul(new Vector(1,-1));
			}
			else
				setPosition(newPos);
		}
		if(dir.getY()>0){ //down
			Vector newPos = getPosition().add(new Vector(0,speed*delta));
			if(level.getMap().isCollision(newPos.add(new Vector(0,Player.HEIGHT))) ||
			  ((getPosition().getX()+Player.WIDTH)%Block.WIDTH != 0 && level.getMap().isCollision(newPos.add(new Vector(Player.WIDTH,Player.HEIGHT))))){
				getPosition().setY(level.getMap().get(newPos.add(new Vector(0,Player.HEIGHT))).getPosition().getY()-Player.HEIGHT);
				dir = dir.mul(new Vector(1,-1));
			}
			else
				setPosition(newPos);
		}
		if(dir.getX()<0){ //left
			Vector newPos = getPosition().add(new Vector(-speed*delta,0));
			if(level.getMap().isCollision(newPos) ||
			  (getPosition().getY()%Block.HEIGHT != 0 && (getPosition().getY()+Player.HEIGHT)%Block.HEIGHT != 0 && level.getMap().isCollision(newPos.add(new Vector(0,Player.HEIGHT))))){
				getPosition().setX(level.getMap().get(newPos).getPosition().getX()+Block.WIDTH);
				dir = dir.mul(new Vector(-1,1));
			}
			else
				setPosition(newPos);
		}
		if(dir.getX()>0){ //right
			Vector newPos = getPosition().add(new Vector(speed*delta, 0));
			if(level.getMap().isCollision(newPos.add(new Vector(Player.WIDTH,0))) ||
			  ((getPosition().getY()+Player.HEIGHT)%Block.HEIGHT != 0 && level.getMap().isCollision(newPos.add(new Vector(Player.WIDTH,Player.HEIGHT))))){
				getPosition().setX(level.getMap().get(newPos.add(new Vector(Player.WIDTH,0))).getPosition().getX()-Player.WIDTH);
				dir = dir.mul(new Vector(-1,1));
			}
			else
				setPosition(newPos);
		}
	}
	
	public void render(Graphics2D g2){
		g2.setColor(color);
		Vector pos = getPosition().sub(level.getOffset());
		g2.fill3DRect(pos.getXi(), pos.getYi(), Player.WIDTH, Player.HEIGHT, true);
	}

	//OTHERS
	
	public void hit(int damage){
		health -= damage;
		
		if(health <= 0){
			dead = true;
			Explosion e = new Explosion(getPosition(),(int)(Math.random()*3),level,20-(int)(Math.random()*6)-3);
			level.addExplosion(e);
		}
	}
	
	//GETTERS

	public boolean isDead() {
		return dead;
	}

}
