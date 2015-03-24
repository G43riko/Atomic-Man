package Atomic.enity.weapon;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import Atomic.component.Level;
import Atomic.enity.Enemy;
import Atomic.enity.Player;
import Atomic.map.Block;
import Atomic.map.Map;
import Atomic.particles.Particle;
import Atomic.util.GColor;
import Atomic.util.Vector;

public class Rocket extends Weapon{
	public final static int WIDTH = 4;
	public final static int HEIGHT = 4;
	public final static int STROKE = 6;
	public final static float ACCELERATION = 0.1f;
	
	public final static int CADENCE = 1000;
	
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
		
		if(isOutOfView()){
			dead = true;
		}
		
		Block b = level.getMap().get(getPosition().add(dir));
		if(b.getType()!=0){
			b.hit(damage);
			dead = true;
		}
		
		ArrayList<Enemy> enemies = level.getEnemies();
		for(Enemy e : enemies){
			if(getPosition().isInRect(e.getPosition(), new Vector(Player.WIDTH, Player.HEIGHT))){
				e.hit(damage);
				dead = true;
			}
		}
		
		if(level.get("showParticles") && getPosition().getX() % 4 <2){
			float size = 10+(int)(Math.random()*10)-5;
			int maxLife = 100+(int)(Math.random()*100)-50;
			float rotSpeed = (float)Math.random()*10-20;
			float opacity = (float)Math.random()/4 + 0.75f;
			float fadding = (float)Math.random()/50;
			level.addParticle(new Particle(getPosition(), new Vector(size, size), Particle.defaultImage, level, maxLife, rotSpeed, opacity, fadding));
		}
	}
	
	public void render(Graphics2D g2){
		g2.setColor(color);
		Vector pos = getPosition().sub(level.getOffset());
		Vector sur  = pos.sub(dir.mul(10));
		g2.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
		
		g2.drawLine(pos.getXi(), pos.getYi(), sur.getXi(), sur.getYi());
	}
}
