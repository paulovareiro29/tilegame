package dev.tilegame.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import dev.tilegame.Handler;
import dev.tilegame.UI.UIManager;

public class MouseManager implements MouseListener, MouseMotionListener {

	private boolean leftPressed, rightPressed;
	private int mouseX,mouseY;
	private UIManager uiManager;
	private Handler handler;
	public MouseManager(){

	}
	
	public void setUIManager(UIManager uiManager){
		this.uiManager = uiManager;
	}
	
	public void setHandler(Handler handler){
		this.handler = handler;
	}
	
	//getters
	public boolean isLeftPressed(){
		return leftPressed;
	}
	
	public boolean isRightPressed(){
		return rightPressed;
	}
	
	public int getMouseX(){
		return mouseX;
	}
	
	public int getMouseY(){
		return mouseY;
	}
	
	//Implemented
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
		if(uiManager != null)
			uiManager.onMouseMove(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == e.BUTTON1)
			leftPressed = true;
		else if(e.getButton() == e.BUTTON3)
			rightPressed = true;
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == e.BUTTON1)
			leftPressed = false;
		else if(e.getButton() == e.BUTTON3)
			rightPressed = false;
		
		
		
		if(uiManager != null)
			uiManager.onMouseRealease(e);
		
		if(handler.getWorld()!= null)
			handler.getWorld().onMouseRealease(e);
		
		if(handler.getWorld().getEntityManager().getPlayer() != null)
			handler.getWorld().getBuild().onMouseRealease(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

}
