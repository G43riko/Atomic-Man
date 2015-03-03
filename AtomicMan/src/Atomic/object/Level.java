package Atomic.object;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import Atomic.core.Input;
import Atomic.util.GColor;
import Atomic.util.ResourceLoader;
import Atomic.util.Vector;

public class Level extends GameObject{
	private Vector size;
	private Vector offset;
	private Color backgroundColor;
	private Image backgroundImage;
	private Map map;
	private Player player;
	private Canvas canvas;
	private ArrayList<Enemy> enemies;
	private ArrayList<Bullet> bullets;

	
	public Level(Canvas canvas){
		enemies = new ArrayList<Enemy>();
		bullets = new ArrayList<Bullet>();
		backgroundColor = GColor.RED;
		backgroundImage = ResourceLoader.loadTexture("stadion.jpg");
		map = new Map(this);
		this.canvas = canvas;
		player = new Player(this);
		offset = new Vector(0 - canvas.getWidth() / 2, 0 - canvas.getHeight() / 2);
		
		for(int i=0 ; i<20 ; i++){
			enemies.add(new Enemy(this));
		}
	}
	
	public void render(Graphics2D g2){
		map.render(g2);
		System.out.println(bullets.size());
		player.render(g2);
		for(Enemy e: enemies){
			e.render(g2);
		}
		for(Bullet b: bullets){
			b.render(g2);
		}
	}
	
	public void input(float delta, Input input){
		player.input(delta, input);
	}
	
	public void update(float delta){
		map.update(delta);
		player.update(delta);
		for(Enemy e: enemies){
			e.update(delta);
		}
		ArrayList<Bullet> forRemove = new ArrayList<Bullet>();
		for(Bullet b: bullets){
			b.update(delta);
//			if(b.isDead()){
//				forRemove.add(b);
//			}
		}
//		bullets.removeAll(bullets);
	}
	
	public void addBullet(Bullet b){
		bullets.add(b);
	}
	
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Vector getOffset() {
		return offset;
	}

	public void setOffset(Vector offset) {
		this.offset = offset;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public Image getBackgroundImage() {
		return backgroundImage;
	}

	public Player getPlayer() {
		return player;
	}
}
