package Atomic.object;

import java.awt.Color;
import java.awt.Graphics2D;

import Atomic.component.Explosion;
import Atomic.util.GColor;
import Atomic.util.Vector;

public class Bomb extends GameObject{
	public final static int OVERDRAW_BLOCK = 1;
	private double startTime;
	private int maxLife;
	private Level level;
	private int exploded = -1;
	private boolean dead;
	private int[] dist;
	private GColor range = new GColor(255,255,0,155); 
	
	public Bomb(Vector position,  Level level, int[] dist){
		super(position);
		this.dist = dist;
		this.level = level;
		maxLife = 1000;
		startTime = System.currentTimeMillis();
	}
	
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
	
	public void renderRangeArea(Graphics2D g2){
		if(exploded > 0)
			return;
		int x = getPosition().getXi()-level.getOffset().getXi();
		int y = getPosition().getYi()-level.getOffset().getYi();
		g2.setColor(range);
		g2.fillRect(x-Block.WIDTH*dist[3], y, Block.WIDTH * (dist[3]+dist[1]+1), Block.HEIGHT);
		g2.fillRect(x, y-Block.HEIGHT*dist[0], Block.WIDTH, Block.HEIGHT * (dist[2]+dist[0]+1));
	}
	
	public void update(float delta){
		if(System.currentTimeMillis() - startTime > maxLife && exploded<0){
			exploded = 40;
			level.addExplosion(new Explosion(getPosition(), 7, level,10));
		}
			
	}
	
	//GETTERS

	public boolean isDead() {
		return dead;
	}
}
