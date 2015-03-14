package Atomic.object.weapon;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.util.ArrayList;

import Atomic.object.Block;
import Atomic.object.Enemy;
import Atomic.object.GameObject;
import Atomic.object.Level;
import Atomic.object.Map;
import Atomic.object.Player;
import Atomic.util.GColor;
import Atomic.util.Vector;

public class Weapon extends GameObject{
	protected Vector dir;
	protected GColor color;
	protected Level level;
	protected boolean dead;
	protected float speed;
	protected int size;
	protected int damage;
	protected float stroke;
	
	//OVERRIDES
	
	public void update(float delta){
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
	
	public static boolean pointRect(Vector aPos, Vector aSize, Vector bPos){
		return bPos.getX() > aPos.getX() && bPos.getX() < aPos.getX() + aSize.getX() && bPos.getY() > aPos.getY() && bPos.getY() < aPos.getY() + aSize.getY();
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


