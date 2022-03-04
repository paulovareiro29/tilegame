package dev.tilegame.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import dev.tilegame.Game;
import dev.tilegame.Handler;
import dev.tilegame.entities.Entity;
import dev.tilegame.entities.statics.WoodBlock;
import dev.tilegame.gfx.Animation;
import dev.tilegame.gfx.Asset;
import dev.tilegame.inventory.Inventory;

public class Player extends Creature{

	private Handler handler;
	private Inventory inventory;
	//Animations
	private Animation animDown,animUp,animLeft,animRight,
	animAtcDown,animAtcUp,animAtcLeft,animAtcRight;
	
	
	private int lastKey = 1;
	private int animSpeed = 100;
	
	private boolean walking,attacking;
	
	private long lastAttack, attackCooldown = 600;
	
	
	public Player(Handler handler,int x, int y) {
		super(handler,x, y,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT);
		this.handler = handler;
		
		bounds.x = 16;
		bounds.y = 32;
		bounds.width = 28;
		bounds.height = 28; 
		
		//Animations
		animDown = new Animation(animSpeed,Asset.player_down);
		animUp = new Animation(animSpeed,Asset.player_up);
		animLeft = new Animation(animSpeed,Asset.player_left);
		animRight = new Animation(animSpeed,Asset.player_right);
		
		animAtcUp = new Animation(animSpeed,Asset.player_atc_up);
		animAtcDown = new Animation(animSpeed,Asset.player_atc_down);
		animAtcLeft = new Animation(animSpeed,Asset.player_atc_left);
		animAtcRight = new Animation(animSpeed,Asset.player_atc_right);
		
		inventory = new Inventory(handler);
		
		
	}
	

	
	@Override
	public void die(){
		System.out.println("Player died");
	}
	
	@Override
	public void tick() {
		
		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();
		animAtcDown.tick();
		animAtcUp.tick();
		animAtcLeft.tick();
		animAtcRight.tick();
		
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
		checkAttacks();
		
	}
	
	public void postTick(){
		inventory.tick();
	}
	
	public boolean isOnCooldown(){
		long time = System.currentTimeMillis();
	    if (time > lastAttack + attackCooldown) {
	    	lastAttack = time;
	    	return false;
	    } 
		return true;
		
	}
	
	public boolean isBuilding(){
		return handler.getWorld().getBuild().isBuilding();
	}
	
	private void checkAttacks(){ 
		if(isOnCooldown())
			return;
		if(isBuilding())
			return;
		
		setAttacking(false);
		Rectangle cb = getCollisionBounds(0,0);
		Rectangle ar = new Rectangle();
		int arSize = 20;
		ar.width = arSize;
		ar.height = arSize;
		
		if(handler.getKeyManager().space && !walking){
			
			setAttacking(true);
			
			switch(lastKey){
			case 0:
				ar.x = cb.x + cb.width / 2  - arSize/2;
				ar.y = cb.y - arSize;
				break;
			case 1:
				ar.x = cb.x + cb.width / 2  - arSize/2;
				ar.y = cb.y + cb.height; 
				break;
			case 2:
				ar.x = cb.x - arSize;
				ar.y =  cb.y + cb.height / 2  - arSize/2; 
				break;
			case 3:
				ar.x = cb.x + cb.width;
				ar.y =  cb.y + cb.height / 2  - arSize/2; 
				break;
			}
		}else{
			return;
		}
				
		for(Entity e : handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0, 0).intersects(ar)){
				e.hurt(1);
			}
		}
	}
	
	
	
	private void getInput(){
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyManager().up){
			lastKey = 0;
			yMove = -speed;
		}
		if(handler.getKeyManager().down){
			lastKey = 1;
			yMove = speed;
		}
		if(handler.getKeyManager().left){
			lastKey = 2;
			xMove = -speed;
		}
		if(handler.getKeyManager().right){
			lastKey = 3;
			xMove = speed;
		}
		
		if(xMove == 0 && yMove == 0){
			setWalking(false);
		}else{
			setWalking(true);
		}
		
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_C))
			handler.getWorld().getBuild().setBuilding(!handler.getWorld().getBuild().isBuilding());
	}

	
	public boolean isAttacking() {
		return attacking;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	public boolean isWalking() {
		return walking;
	}

	public void setWalking(boolean walking) {
		this.walking = walking;
	}

	@Override
	public void render(Graphics g) {
//		g.setColor(Color.red);
//		g.fillRect((int) (x +bounds.x - handler.getGameCamera().getxOffset()),(int) (y + bounds.y-handler.getGameCamera().getyOffset()),bounds.width,bounds.height);
		g.drawImage(getCurrentAnimationFrame(),(int) (x - handler.getGameCamera().getxOffset()),(int) (y-handler.getGameCamera().getyOffset()),width,height, null);	
	}

	public void postRender(Graphics g){
		inventory.render(g);
	}
	
	private BufferedImage getCurrentAnimationFrame(){
		if(yMove < 0)
			return animUp.getCurrentFrame();
		if(yMove > 0)
			return animDown.getCurrentFrame();
		if(xMove > 0)
			return animRight.getCurrentFrame();
		if(xMove < 0)
			return animLeft.getCurrentFrame();
		
		if(isAttacking() && !isBuilding()){
			switch(lastKey){
			case 0:
				return animAtcUp.getCurrentFrame();
			case 1:
				return animAtcDown.getCurrentFrame();
			case 2:
				return animAtcLeft.getCurrentFrame();
			case 3:
				return animAtcRight.getCurrentFrame();
			}
		}
		
		switch(lastKey){
		case 0:
			return animUp.getSpecificFrame(0);
		case 1:
			return animDown.getSpecificFrame(0);
		case 2:
			return animLeft.getSpecificFrame(0);
		default:
			return animRight.getSpecificFrame(0);
		}
			
		
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public int getLastKey() {
		return lastKey;
	}



	
	
}
