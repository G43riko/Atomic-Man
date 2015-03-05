package Atomic.object;

import java.awt.Graphics2D;

import Atomic.util.Vector;

public class Map extends GameObject{
	public final static int NUM_X = 60;
	public final static int NUM_Y = 40;
	private Block[][] mapa;
	private Level level;
	private int destructible;
	private int drawable;
	
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
		int res = 0;
		int res2 = 0;
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Y ; j++){
				if(mapa[i][j].getType()==0){
					continue;
				}
				Vector p = mapa[i][j].getPosition();
				Vector o = level.getOffset();
				if(p.getX() + Block.WIDTH < o.getX() || o.getX()+level.getCanvas().getWidth()<p.getX() ||
				   p.getY() + Block.HEIGHT < o.getY() || o.getY()+level.getCanvas().getHeight()<p.getY())
					continue;
				mapa[i][j].render(g2);
				res2++;
				if(mapa[i][j].getType()!=0)
					res++;
			}
		}
		drawable = res2;
		destructible = res;
	}
	
	private boolean exist(int i, int j){
		return i>=0 && j>=0 && i<NUM_X && j<NUM_Y;
	}
	
	public boolean isCollision(Vector p){
		Vector playerPos = p.div(new Vector(Block.WIDTH, Block.HEIGHT));
//		if()
//			return true;
		if(exist(playerPos.getXi(),playerPos.getYi())&& mapa[playerPos.getXi()][playerPos.getYi()].getType()==0){
			return false;
		}
		return true;
	}
	
	public Block get(Vector p){
		Vector playerPos = p.div(new Vector(Block.WIDTH, Block.HEIGHT));
		if(!exist(playerPos.getXi(),playerPos.getYi()))
			return new Block(new Vector(),0,level);
		return mapa[playerPos.getXi()][playerPos.getYi()];
	}

	public int getDestructible() {
		return destructible;
	}

	public int getDrawable() {
		return drawable;
	}
}
