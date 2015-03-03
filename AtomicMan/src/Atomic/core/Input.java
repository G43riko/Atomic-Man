package Atomic.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;

import Atomic.util.Vector;

public class Input implements KeyListener, MouseListener, MouseMotionListener{
	private HashMap<Integer,Boolean> keys = new HashMap<Integer,Boolean>();
	private HashMap<Integer,Boolean> mouses = new HashMap<Integer,Boolean>();
	private Vector mousePos = new Vector();
	
	public boolean isKeyDown(int key){
		if(keys.containsKey(key)){
			return keys.get(key);
		}
		return false;
	}
	public boolean isMouseDown(int key){
		if(mouses.containsKey(key)){
			return mouses.get(key);
		}
		return false;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		keys.put(e.getKeyCode(), true);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys.put(e.getKeyCode(), false);
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mousePos = new Vector(e.getX(),e.getY());
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mousePos = new Vector(e.getX(),e.getY());
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouses.put(e.getButton(), true);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouses.put(e.getButton(), false);
		
	}
	public Vector getMousePos() {
		return mousePos;
	}
}
