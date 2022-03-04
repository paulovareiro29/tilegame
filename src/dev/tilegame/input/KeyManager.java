package dev.tilegame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

	private boolean[] keys, justPressed,cantPress;
	public boolean up,down,left,right;
	public boolean space;
	
	
	public KeyManager(){
		keys = new boolean[256];
		justPressed = new boolean[256];
		cantPress = new boolean[256];
	}
	
	public void tick(){
		
		for(int i = 0; i < keys.length;i++){
			if(cantPress[i] && !keys[i]){
				cantPress[i] = false;
			}else if(justPressed[i]){
				cantPress[i] = true;
				justPressed[i] = false;
			}
			if(!cantPress[i] && keys[i]){
				justPressed[i] = true;
			}
		}
		
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		
		space = keys[KeyEvent.VK_SPACE];
	}
	
	
	
	@Override
	public void keyTyped(KeyEvent e) {	
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		keys[e.getKeyCode()] = false;
	}

	
	public boolean keyJustPressed(int keyCode){
		  if(keyCode < 0 || keyCode >= keys.length)
		  return false;
		  return justPressed[keyCode];
		  }
}
