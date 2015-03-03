package Atomic.component;

import java.awt.Color;
import java.awt.Graphics2D;

import Atomic.core.Input;
import Atomic.object.GameObject;
import Atomic.object.Player;
import Atomic.util.Vector;

public class Level extends GameObject{
	private Vector size;
	private Vector offset;
	private Color backgroundColor;
	private Map map;
	private Player player;

	
	public Level(){
		backgroundColor = Color.RED;
		map = new Map();
		player = new Player();
		
	}
	
	public void render(Graphics2D g2){
		player.render(g2);
	}
	
	public void input(float delta, Input input){
		player.input(delta, input);
	}
	
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}
}
