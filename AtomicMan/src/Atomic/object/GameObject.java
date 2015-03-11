package Atomic.object;

import java.awt.Graphics2D;

import Atomic.core.Input;
import Atomic.util.Vector;

public abstract class GameObject {
	private Vector position;
	public GameObject(){
		this.position = new Vector();
	}
	
	public GameObject(Vector position){
		this.position = position;
	}

	public void update(float delta){
		
	}
	
	public void render(Graphics2D g2){
		
	}
	
	public void input(float delta, Input input){
		
	}
	
	public Vector getPosition() {
		return position;
	}

	public void setPosition(Vector position) {
		this.position = position;
	}
}
