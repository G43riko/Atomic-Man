package Atomic.object;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import Atomic.util.GColor;
import Atomic.util.Vector;

public class Rocket extends Weapon{
	public final static int WIDTH = 4;
	public final static int HEIGHT = 4;
	public final static int STROKE = 6;
	public final static float ACCELERATION = 0.1f;
	
	private float maxSpeed;
	//CONSTRUCTORS
	
	public Rocket(Level level, Vector dir, int accuracy, int demage){
		
		setPosition(level.getPlayer().getPosition().add(new Vector(Player.WIDTH/2, Player.HEIGHT/2)));
		this.level = level;
		this.dir = dir.add(new Vector((float)Math.random()/accuracy, (float)Math.random()/accuracy));
		this.damage = demage;
		init();
			
	}
	
	//OTHERS
	
	private void init(){
		maxSpeed = 6+(float)(Math.random()*6-3);
		speed = 1;
		size = 5;
		stroke = STROKE-1 + (float)Math.random()*STROKE-STROKE/2;
		color = GColor.randomize(100, Color.WHITE);
	}
	
	//OVERRIDES
	
	public void update(float delta){
		if(speed <= maxSpeed)
			speed += ACCELERATION;
		
		setPosition(getPosition().add(dir.mul(speed)));
		
		if(getPosition().getX() + size <= 0 || getPosition().getX() - size >= Map.NUM_X * Block.WIDTH || 
		   getPosition().getY() + size <= 0 || getPosition().getY() - size >= Map.NUM_Y * Block.HEIGHT){
			dead = true;
		}
		Block b = level.getMap().get(getPosition().add(dir));
		if(b.getType()!=0){
			b.hit(damage);
			dead = true;
		}
		ArrayList<Enemy> enemies = level.getEnemies();
		for(Enemy e : enemies){
			if(pointRect(e.getPosition(), new Vector(Player.WIDTH, Player.HEIGHT), getPosition())){
				e.hit(damage);
			}
		}
	}
	
	public void render(Graphics2D g2){
		g2.setColor(color);
		Vector pos = getPosition().sub(level.getOffset());
		Vector sur  = pos.sub(dir.mul(10));
		g2.setStroke(new BasicStroke(stroke));
		
		g2.drawLine(pos.getXi(), pos.getYi(), sur.getXi(), sur.getYi());
	}
}
