package Atomic.object;

import java.awt.Graphics2D;

import Atomic.util.Vector;

public class Map extends GameObject{
	public final static int NUM_X = 60;
	public final static int NUM_Y = 40;
	private Block[][] mapa;
	private Level level;
	
	public Map(Level level){
		this.level = level;
		createMap();
	}
	
	public void createMap(){
		mapa = new Block[NUM_X][NUM_Y];
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Y ; j++){
				mapa[i][j] = new Block(new Vector(Block.WIDTH*i, Block.HEIGHT*j), (int)(Math.random()*1.3f),level);
			}
		}
	}
	
	public void render(Graphics2D g2){
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Y ; j++){
				mapa[i][j].render(g2);
			}
		}
	}
	
	public boolean isCollision(Vector p){
		Vector playerPos = p.div(new Vector(Block.WIDTH, Block.HEIGHT));
		if(mapa[playerPos.getXi()][playerPos.getYi()].getType()==0){
			return false;
		}
		return true;
	}
	
	public Block get(Vector p){
		Vector playerPos = p.div(new Vector(Block.WIDTH, Block.HEIGHT));
		return mapa[playerPos.getXi()][playerPos.getYi()];
	}
}
