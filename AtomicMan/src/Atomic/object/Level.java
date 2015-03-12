package Atomic.object;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import Atomic.component.Explosion;
import Atomic.core.Input;
import Atomic.util.GColor;
import Atomic.util.ResourceLoader;
import Atomic.util.Vector;

public class Level extends GameObject{
	private Vector offset;
	private Color backgroundColor;
	private Image backgroundImage;
	private Map map;
	private Player player;
	private Canvas canvas;
	private ArrayList<Enemy> enemies;
	private ArrayList<Bullet> bullets;
	private ArrayList<Explosion> explosions;
	private ArrayList<Bomb> bombs;

	//CONSTRUCTORS
	
	public Level(Canvas canvas){
		enemies = new ArrayList<Enemy>();
		bombs = new ArrayList<Bomb>();
		bullets = new ArrayList<Bullet>();
		explosions = new ArrayList<Explosion>();
		backgroundColor = GColor.RED;
		backgroundImage = ResourceLoader.loadTexture("stadion.jpg");
		map = new Map(this);
		this.canvas = canvas;
		player = new Player(this);
		offset = new Vector(0 - canvas.getWidth() / 2, 0 - canvas.getHeight() / 2);
		new Explosion();
		for(int i=0 ; i<20 ; i++){
			enemies.add(new Enemy(this));
		}
	}

	//ADDERS
	
	public void addBullet(Bullet b){
		bullets.add(b);
	}

	public void addBomb(Player player) {
		Vector pos = player.getPosition().add(new Vector(Player.WIDTH/2, Player.HEIGHT/2));
		pos = getMap().get(pos).getPosition();
		Bomb b = new Bomb(pos, this, map.calcBombDist(pos, player), player.getDemage(),player.isNano());
		if(!bombs.contains(b))
			bombs.add(b);
	}

	public void addExplosion(Explosion explosion) {
		explosions.add(explosion);
		
	}

	//OVERRIDES
	
	public void render(Graphics2D g2){
		map.render(g2);
		
		for(Bomb b: bombs)
			b.renderRangeArea(g2);
		
		for(Enemy e: enemies)
			e.render(g2);
		
		for(Bomb b: bombs)
			b.render(g2);
		
		player.render(g2);
		
		for(Bullet b: bullets)
			b.render(g2);
		
		for(Explosion e: explosions)
			e.render(g2);
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
		
		for(int i=0 ; i<bombs.size() ; i++){
			Bomb b = bombs.get(i);
			b.update(delta);
			if(b.isDead())
				bombs.remove(i);
		}
		
		for(int i=0 ; i<bullets.size() ; i++){
			Bullet e = bullets.get(i);
			e.update(delta);
			if(e.isDead()){
				bullets.remove(i);
				i--;
			}
		}
		
		for(int i=0 ; i<explosions.size() ; i++){
			Explosion b = explosions.get(i);
			b.update(delta);
			if(b.isDead()){
				explosions.remove(b);
				i--;
			}
		}
	}
	
	//GETTERS
	
	public int getNumOfEnemies(){
		return enemies.size();
	}
	
	public int getNumOfBullets(){
		return bullets.size();
	}
	
	public int getNumOfBombs(){
		return bombs.size();
	}
	
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public Map getMap() {
		return map;
	}

	public Vector getOffset() {
		return offset;
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
	
	
	//SETTERS

	public void setOffset(Vector offset) {
		this.offset = offset;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
}
