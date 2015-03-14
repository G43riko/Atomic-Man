package Atomic.object;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import Atomic.component.Explosion;
import Atomic.core.Input;
import Atomic.object.weapon.Weapon;
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
	private ArrayList<Weapon> weapons;
	private ArrayList<Explosion> explosions;
	private ArrayList<Bomb> bombs;
	private ArrayList<Particle> particles;
	private int drawableEnemies;
	private int drawableWeapons;
	private int drawableExplosions;
	private int drawableBombs;
	private int drawableParticles;

	//CONSTRUCTORS
	
	public Level(Canvas canvas){
		enemies = new ArrayList<Enemy>();
		bombs = new ArrayList<Bomb>();
		weapons = new ArrayList<Weapon>();
		explosions = new ArrayList<Explosion>();
		particles = new ArrayList<Particle>();
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

	//OTHERS
	
	public boolean isVisible(GameObject o){
		if(o.getPosition().getX() + Block.WIDTH < offset.getX() || offset.getX()+canvas.getWidth()<o.getPosition().getX() ||
		   o.getPosition().getY() + Block.HEIGHT < offset.getY() || offset.getY()+canvas.getHeight()<o.getPosition().getY())
			return false;
		return true;
	}
	
	//ADDERS
	
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public void addWeapon(Weapon b){
		weapons.add(b);
	}

	public void addBomb(Player player) {
		Vector pos = player.getPosition().add(new Vector(Player.WIDTH/2, Player.HEIGHT/2));
		pos = getMap().get(pos).getPosition();
		
		Vector direction = new Vector();
		
		if(player.isSkejtboard()){
			switch(player.getDirection()){
				case 0:
					direction = new Vector(0,-4);
					break;
				case 1:
					direction = new Vector(4,0);
					break;
				case 2:
					direction = new Vector(0,4);
					break;
				case 3:
					direction = new Vector(-4,0);
					break;
			}
		}
		
		Bomb b = new Bomb(pos, this, map.calcBombDist(pos, player), player.getDamage(),player.isNano(), direction);
		if(!bombs.contains(b))
			bombs.add(b);
	}

	public void addExplosion(Explosion explosion) {
		explosions.add(explosion);
		
	}

	public void addParticle(Particle particle){
		particles.add(particle);
	}
	
	//OVERRIDES
	
	public void render(Graphics2D g2){
		int drawableEnemies = 0;
		int drawableWeapons = 0;
		int drawableExplosions = 0;
		int drawableBombs = 0;
		int drawableParticles = 0;
		
		map.render(g2);
		
		for(Bomb b: bombs)
			if(isVisible(b)){
				drawableBombs++;
				b.renderRangeArea(g2);
			}
		
		for(Enemy e: enemies)
			if(isVisible(e)){
				drawableEnemies++;
				e.render(g2);
			}
		for(Bomb b: bombs)
				b.render(g2);
		
		player.render(g2);
		
		for(Weapon w: weapons)
			if(isVisible(w)){
				drawableWeapons++;
				w.render(g2);
			}
				
		
		for(Particle p: particles)
			if(isVisible(p)){
				drawableParticles++;
				p.render(g2);
			}
		
		for(Explosion e: explosions)
			if(isVisible(e)){
				drawableExplosions++;
				e.render(g2);
			}
		this.drawableEnemies = drawableEnemies;
		this.drawableWeapons = drawableWeapons;
		this.drawableExplosions = drawableExplosions;
		this.drawableBombs = drawableBombs;
		this.drawableParticles = drawableParticles;
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
		
		for(int i=0 ; i<enemies.size() ; i++){
			Enemy e = enemies.get(i);
			e.update(delta);
			if(e.isDead())
				enemies.remove(i);
		}
		
		for(int i=0 ; i<bombs.size() ; i++){
			Bomb b = bombs.get(i);
			b.update(delta);
			if(b.isDead())
				bombs.remove(i);
		}
		
		for(int i=0 ; i<weapons.size() ; i++){
			Weapon w = weapons.get(i);
			w.update(delta);
			if(w.isDead()){
				weapons.remove(i);
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
		
		for(int i=0 ; i<particles.size() ; i++){
			Particle p = particles.get(i);
			p.update(delta);
			if(p.isDead()){
				particles.remove(p);
				i--;
			}
		}
	}
	
	//GETTERS
	
	public String getNumOfEnemies(){
		return drawableEnemies+" / "+enemies.size();
	}
	
	public String getNumOfWeapons(){
		return drawableWeapons+" / "+weapons.size();
	}
	
	public String getNumOfExplosions(){
		return drawableExplosions+" / "+explosions.size();
	}
	
	public String getNumOfBombs(){
		return drawableBombs+" / "+bombs.size();
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
	
	public String getNumOfParticles(){
		return drawableParticles+" / "+particles.size();
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
