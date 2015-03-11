package Atomic.object;

import java.awt.Color;
import java.awt.Graphics2D;

import Atomic.util.Vector;

public class Bomb extends GameObject{
	private int range;
	private double startTime;
	private int maxLife;
	private Level level;
	private int exploded = -1;
	private boolean dead;
	
	public Bomb(Vector position, int range, Level level){
		super(level.getMap().get(position).getPosition());
		this.level = level;
		this.range = range;
		maxLife = 1000;
		startTime = System.currentTimeMillis();
	}
	
	public void render(Graphics2D g2){
		if(exploded < 0){
			g2.setColor(Color.BLACK);
			int x = getPosition().getXi()-level.getOffset().getXi();
			int y = getPosition().getYi()-level.getOffset().getYi();
			g2.fillArc(x , y, Block.WIDTH, Block.HEIGHT, 0, 360);
			return;
		}
		if(exploded>0){
			g2.setColor(Color.YELLOW);

			int x = getPosition().getXi()-level.getOffset().getXi();
			int y = getPosition().getYi()-level.getOffset().getYi();
			g2.fillRect(x-Block.WIDTH, y, Block.WIDTH * 3, Block.HEIGHT);
			g2.fillRect(x, y-Block.HEIGHT, Block.WIDTH, Block.HEIGHT * 3);
			exploded--;
		}
		if(exploded == 0)
			dead = true;
	}
	
	public boolean isDead() {
		return dead;
	}

	public void update(float delta){
		if(System.currentTimeMillis() - startTime >maxLife && exploded<0)
			exploded = 40;
	}
}
