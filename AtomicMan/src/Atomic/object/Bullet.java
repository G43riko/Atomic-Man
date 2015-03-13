package Atomic.object;

import java.awt.Color;

import Atomic.util.GColor;
import Atomic.util.Vector;

public class Bullet extends Weapon{
	public final static int WIDTH = 4;
	public final static int HEIGHT = 4;
	public final static int STROKE = 4;
	
	
	//CONSTRUCTORS
	
	public Bullet(Level level, Vector dir, int accuracy, int demage){
		setPosition(level.getPlayer().getPosition().add(new Vector(Player.WIDTH/2, Player.HEIGHT/2)));
		this.level = level;
		this.dir = dir.add(new Vector((float)Math.random()/accuracy, (float)Math.random()/accuracy));
		this.damage = demage;
		init();
	}
	
	//OTHERS
	
	private void init(){
		speed = 15+(float)(Math.random()*10-5);
		size = 5;
		stroke = STROKE-1 + (float)Math.random()*STROKE-STROKE/2;
		color = GColor.randomize(100, Color.YELLOW);
	}
}
