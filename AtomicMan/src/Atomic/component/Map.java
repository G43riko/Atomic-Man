package Atomic.component;

import java.awt.Graphics2D;

import Atomic.object.GameObject;
import Atomic.util.Vector;

public class Map extends GameObject{
	private Vector blockSize;
	private Vector numberOfBlock;
	private Block[][] mapa;
	private Level level;
	
	public Map(Level level){
		this.level = level;
		blockSize = new Vector(40, 40);
		numberOfBlock = new Vector(30, 30);
		createMap();
	}
	
	public void createMap(){
		mapa = new Block[numberOfBlock.getXi()][numberOfBlock.getYi()];
		for(int i=0 ; i<numberOfBlock.getXi() ; i++){
			for(int j=0 ; j<numberOfBlock.getYi() ; j++){
				mapa[i][j] = new Block(new Vector(blockSize.getXi()*i, blockSize.getYi()*j), (int)(Math.random()*5),level);
			}
		}
	}
	
	public void render(Graphics2D g2){
		for(int i=0 ; i<numberOfBlock.getXi() ; i++){
			for(int j=0 ; j<numberOfBlock.getYi() ; j++){
				mapa[i][j].render(g2);
			}
		}
	}

	public Vector getBlockSize() {
		return blockSize;
	}

	public Vector getNumberOfBlock() {
		return numberOfBlock;
	}
}
