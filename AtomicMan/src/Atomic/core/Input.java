package Atomic.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class Input implements KeyListener{
	private HashMap<Integer,Boolean> keys = new HashMap<Integer,Boolean>();
	
	public boolean isKeyDown(int key){
		if(keys.containsKey(key)){
			return keys.get(key);
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
}
