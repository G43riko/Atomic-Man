package Atomic.enity;

import java.awt.Color;
import java.awt.Graphics2D;

import Atomic.component.Explosion;
import Atomic.component.Level;
import Atomic.map.Block;
import Atomic.object.GameObject;
import Atomic.util.GColor;
import Atomic.util.Vector;

public class Bomb extends GameObject{
	public final static int OVERDRAW_BLOCK = 1;
	private final static int ROUND = 40;
	private final static int OFFSET = 1;
	private double startTime;
	private int maxLife;
	private Level level;
	private int exploded = -1;
	private boolean dead;
	private int[] dist;
	private int damage;
	private boolean nano;
	private Vector dir;
	private GColor range = new GColor(255,255,0,155); 
	
	//CONTRUCTORS
	
	public Bomb(Vector position,  Level level, int[] dist, int demage, boolean nano, Vector dir){
		super(position);
		this.nano = nano;
		this.dir = dir;
		this.dist = dist;
		this.level = level;
		this.damage = demage;
		maxLife = 1000;
		startTime = System.currentTimeMillis();
	}
	
	//OTHERS

	public void renderRangeArea(Graphics2D g2){
		//vykreslí to dlžku dostrelu bomby
		if(exploded > 0)
			return;
		int x = getPosition().getXi()-level.getOffset().getXi()+OFFSET;
		int y = getPosition().getYi()-level.getOffset().getYi()+OFFSET;
		g2.setColor(range);
		g2.fillRoundRect(x-Block.WIDTH*dist[3], y, Block.WIDTH * (dist[3]+dist[1]+1)-OFFSET*2, Block.HEIGHT-OFFSET*2, ROUND, ROUND);
		g2.fillRoundRect(x, y-Block.HEIGHT*dist[0], Block.WIDTH-OFFSET*2, Block.HEIGHT * (dist[2]+dist[0]+1)-OFFSET*2, ROUND, ROUND);
	}
	
	private void killBlocksAndEnemies(){
		if(nano){
			Block a;
			for(int i=0 ; i<=dist[0] ; i++){
				a = level.getMap().get(new Vector(getPosition().add(new Vector(0,i*-Block.HEIGHT))));
				if(a != null && a.getType()>0)
					a.hit(damage);
				
				a = level.getMap().get(new Vector(getPosition().add(new Vector(0,i*Block.HEIGHT))));
				if(a != null && a.getType()>0)
					a.hit(damage);
				
				a = level.getMap().get(new Vector(getPosition().add(new Vector(i*Block.WIDTH,0))));
				if(a != null && a.getType()>0)
					a.hit(damage);
				
				a = level.getMap().get(new Vector(getPosition().add(new Vector(i*-Block.WIDTH,0))));
				if(a != null && a.getType()>0)
					a.hit(damage);
			}
		}
		else{
			Block a = level.getMap().get(new Vector(getPosition().add(new Vector(0,dist[0]*-Block.HEIGHT))));
			if(a != null && a.getType()>0)
				a.hit(damage);
			
			a = level.getMap().get(new Vector(getPosition().add(new Vector(0,dist[2]*Block.HEIGHT))));
			if(a != null && a.getType()>0)
				a.hit(damage);
			
			a = level.getMap().get(new Vector(getPosition().add(new Vector(dist[1]*Block.WIDTH,0))));
			if(a != null && a.getType()>0)
				a.hit(damage);
			
			a = level.getMap().get(new Vector(getPosition().add(new Vector(dist[3]*-Block.WIDTH,0))));
			if(a != null && a.getType()>0)
				a.hit(damage);
		}
		
		for(Enemy e : level.getEnemies()){
			
		}
	}
	
	//OVERRIDES
	
	public boolean equals(Object o){
		return ((Bomb)o).getPosition().equals(getPosition());
	}
	
	public void render(Graphics2D g2){
		if(exploded < 0){

			int x = getPosition().getXi()-level.getOffset().getXi();
			int y = getPosition().getYi()-level.getOffset().getYi();
//			range = new GColor(range.getRed(), range.getGreen(), range.getBlue(),System.currentTimeMillis()%255);
			g2.setColor(Color.BLACK);
			g2.fillArc(x , y, Block.WIDTH, Block.HEIGHT, 0, 360);
			return;
		}
		if(exploded>0){
			exploded--;
		}
		if(exploded == 0)
			dead = true;
	}
	
	public void update(float delta){
		if(System.currentTimeMillis() - startTime > maxLife && exploded<0){
			exploded = 40;
			level.addExplosion(new Explosion(getPosition(), 7, level,10));
			killBlocksAndEnemies();
			dead = true;
		}
		setPosition(getPosition().add(dir));
			
	}
	
	//GETTERS

	public boolean isDead() {
		return dead;
	}
}
