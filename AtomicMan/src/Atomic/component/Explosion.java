package Atomic.component;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import Atomic.object.Block;
import Atomic.object.GameObject;
import Atomic.object.Level;
import Atomic.util.ResourceLoader;
import Atomic.util.Vector;

public class Explosion extends GameObject{
	private class Explos{
		private Image i;
		private int x;
		private int y;
		public Explos(String n, int x, int y){
			this.i = ResourceLoader.loadTexture(n);
			this.x = x;
			this.y = y;
		}
	}
	
	private static ArrayList<Explos> explosions = new ArrayList<Explos>();
	private int numOfImages;
	private int imageWidth;
	private int imageHeight;
	private Explos e;
	private int actImage;
	private boolean dead;
	private Level level;
	private Vector size;
	
	//CONSTRUCTORS
	
	public Explosion(){
		createExplosions();
	}
	
	public Explosion(Vector pos, int id, Level level){
		this(pos, id, level, 0);
	}
	
	public Explosion(Vector pos, int id, Level level, int offset){
		super(pos.sub(offset));
		this.level = level;
		size = new Vector(Block.WIDTH + 2*offset, Block.HEIGHT + 2*offset);
		e = explosions.get(id);
		numOfImages = e.x * e.y;
		imageWidth = e.i.getWidth(null) / e.x;
		imageHeight = e.i.getHeight(null) / e.y;
		actImage = 0;
		
	}

	//OTHERS
	
	private void createExplosions(){
		explosions.add(new Explos("explosion1.png",5,5));
		explosions.add(new Explos("explosion2.png",4,4));
		explosions.add(new Explos("explosion3.png",5,5));
		explosions.add(new Explos("explosion4.png",4,4));
		explosions.add(new Explos("explosion5.png",4,3));
		explosions.add(new Explos("explosion6.png",4,2));
		explosions.add(new Explos("explosion7.png",4,4));
		explosions.add(new Explos("explosion8.png",9,9));
		explosions.add(new Explos("explosion9.png",5,3));
	}
	
	//OVERRIDES
	
	public void render(Graphics2D g2){
		Vector pos = getPosition().sub(level.getOffset());
		Vector a = pos.add(size);
		int x0 = (actImage % e.x) * imageWidth;
		int y0 = (actImage / e.y) * imageHeight;
		int x1 = x0 + imageWidth;
		int y1 = y0 + imageHeight;
		g2.drawImage(e.i, pos.getXi(),pos.getYi(), a.getXi(), a.getYi(), x0, y0,  x1, y1, null);
	}
	
	public void update(float delta){
		if(actImage == numOfImages)
			dead = true;
		
		actImage++;
	}

	//GETTERS
	
	public boolean isDead() {
		return dead;
	}
}
