package Atomic.object;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import Atomic.util.GColor;
import Atomic.util.Vector;

public class Bullet extends GameObject{
	public final static int WIDTH = 4;
	public final static int HEIGHT = 4;
	
	private Vector dir;
	private int speed;
	private GColor color;
	private Level level;
	private float stroke;
	private int size;
	private int demage;
	private boolean dead;
	
	//CONSTRUCTORS
	
	public Bullet(Level level, Vector dir, int accuracy, int demage){
		super(level.getPlayer().getPosition().add(new Vector(Player.WIDTH/2, Player.HEIGHT/2)));
		this.level = level;
		this.dir = dir.add(new Vector((float)Math.random()/accuracy, (float)Math.random()/accuracy));
		this.demage = demage;
		init();
	}
	
	//OTHERS
	
	private void init(){
		dead = false;
		speed = 15+(int)(Math.random()*10-5);
		size = 5;
		stroke = 3 + (float)Math.random()*4-2;
		color = GColor.randomize(100, Color.RED);
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
			b.hit(demage);
			dead = true;
		}
	}
	
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
