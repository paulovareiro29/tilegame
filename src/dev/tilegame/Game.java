package dev.tilegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import dev.tilegame.build.Build;
import dev.tilegame.display.Display;
import dev.tilegame.gfx.Asset;
import dev.tilegame.gfx.GameCamera;
import dev.tilegame.gfx.SpriteSheet;
import dev.tilegame.input.KeyManager;
import dev.tilegame.input.MouseManager;
import dev.tilegame.states.*;

public class Game implements Runnable {
	
	private Display display;
	private int width,height;
	public String title;
	
	private Thread thread;
	private boolean running = false;
	
	private BufferStrategy bs;
	private Graphics g;
	
	
	//States
	public State gameState;
	public State menuState;
	
	//Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	
	//Camera
	private GameCamera gameCamera;
	
	//Handler
	private Handler handler;
	
	public Game(String title,int width,int height){
		this.title = title;
		this.width = width;
		this.height = height;	
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}

	private void tick(){
		keyManager.tick();
		if(State.getState()!=null)
			State.getState().tick();
		
	}
	
	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		
		//renders
		g.fillRect(0, 0, width, height);
		
		
		if(State.getState()!=null)
			State.getState().render(g);
		
		//end renders
		bs.show();
		g.dispose();
	}
	
	private void init(){
		display = new Display(title,width,height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Asset.init();
		
		handler = new Handler(this);
		
		mouseManager.setHandler(handler);
		gameCamera = new GameCamera(handler,0,0);
		
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		State.setState(menuState);
	}
	
	public KeyManager getKeyManager(){
		return keyManager;
	}
	
	public MouseManager getMouseManager(){
		return mouseManager;
	}
	
	public GameCamera getGameCamera(){
		return gameCamera;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void run() {
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running){
			now = System.nanoTime();
			delta += (now-lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000){
				System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
			
		}
		
		stop();
	}
	
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
	
}
