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
	private Window window;

	
	public Level(Window window){
		backgroundColor = Color.RED;
		map = new Map(this);
		this.window = window;
		player = new Player(this);
		offset = new Vector(0 - window.getWidth() / 2, 0 - window.getHeight() / 2);
		
	}
	
	public void render(Graphics2D g2){
		map.render(g2);
		player.render(g2);
	}
	
	public void input(float delta, Input input){
		player.input(delta, input);
	}
	
	public void update(float delta){
		map.update(delta);
		player.update(delta);
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

	public Vector getOffset() {
		return offset;
	}

	public void setOffset(Vector offset) {
		this.offset = offset;
	}

	public Window getWindow() {
		return window;
	}
}
