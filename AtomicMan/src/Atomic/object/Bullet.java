package Atomic.object;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import Atomic.util.GColor;
import Atomic.util.Vector;

public class Bullet extends GameObject{
	public final static int WIDTH = 4;
	public final static int HEIGHT = 4;
	public final static int STROKE = 4;
	
	private Vector dir;
	private GColor color;
	private Level level;
	private boolean dead;
	private int speed;
	private int size;
	private int damage;
	private float stroke;
	
	//CONSTRUCTORS
	
	public Bullet(Level level, Vector dir, int accuracy, int demage){
		super(level.getPlayer().getPosition().add(new Vector(Player.WIDTH/2, Player.HEIGHT/2)));
		this.level = level;
		this.dir = dir.add(new Vector((float)Math.random()/accuracy, (float)Math.random()/accuracy));
		this.damage = demage;
		init();
	}
	
	//OTHERS
	
	private void init(){
		dead = false;
		speed = 15+(int)(Math.random()*10-5);
		size = 5;
		stroke = STROKE-1 + (float)Math.random()*STROKE-STROKE/2;
		color = GColor.randomize(100, Color.YELLOW);
	}
	
	//OVERRIDES
	
	public void update(float delta){
		setPosition(getPosition().add(dir.mul(speed)));
		
		if(getPosition().getX() + size <= 0 || getPosition().getX() - size >= Map.NUM_X * Block.WIDTH || 
		   getPosition().getY() + size <= 0 || getPosition().getY() - size >= Map.NUM_Y * Block.HEIGHT){
			dead = true;
		}
		Block b = level.getMap().get(getPosition().add(new Vector(WIDTH/2,HEIGHT/2)));
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
	
	public static boolean pointRect(Vector aPos, Vector aSize, Vector bPos){
		return bPos.getX() > aPos.getX() && bPos.getX() < aPos.getX() + aSize.getX() && bPos.getX() > aPos.getX() && bPos.getX() < aPos.getX() + aSize.getY();
	};
	
	public void render(Graphics2D g2){
		g2.setColor(color);
		Vector pos = getPosition().sub(level.getOffset());
		Vector sur  = pos.sub(dir.mul(speed));
		g2.setStroke(new BasicStroke(stroke));
		
		g2.drawLine(pos.getXi(), pos.getYi(), sur.getXi(), sur.getYi());
	}

	//GETTERS 
	
	public boolean isDead() {
		return dead;
	}
}
