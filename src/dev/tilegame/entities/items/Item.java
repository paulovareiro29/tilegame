package dev.tilegame.entities.items;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.tilegame.Handler;
import dev.tilegame.gfx.Asset;

public class Item {

	public static Item[] items = new Item[256];
	public static Item woodItem = new WoodLog(0);
	public static Item stoneItem = new Stone(1);
	public static Item torchItem = new Torch(2);
	//class
	
	public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32, PICKED_UP = -1;
	
	protected Handler handler;
	protected BufferedImage texture;
	protected String name;
	protected final int id;
	
	protected Rectangle bounds;
	
	protected int x,y,count,maxStack;
	protected boolean pickedUp = false;
	
	
	public Item(BufferedImage texture,String name, int id,int maxStack){
		this.texture = texture;
		this.name = name;
		this.id = id;
		this.maxStack = maxStack;
		bounds = new Rectangle(x,y,ITEMWIDTH , ITEMHEIGHT);
		
		items[id] = this;
		
		count = 1;
	}
	
	public void tick(){
		if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(bounds)){
			pickedUp = true;
			handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
		}
				
	}
	
	public void render(Graphics g){
		render(g,x,y);
	}
	
	public void render(Graphics g,int x, int y){
		if(handler == null)
			return;
		g.drawImage(texture, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), ITEMWIDTH, ITEMHEIGHT, null);
	}

	public Item createNew(int x , int y){
		Item i = new Item(texture,name,id,maxStack);
		i.setPosition(x, y);
		return i;
		
	}
	
	public Item createNew(int count){
		Item i = new Item(texture,name,id,maxStack);
		i.setPickedUp(true);
		i.setCount(count);
		return i;
		
	}
	
	public void setPosition(int x , int y){
		this.x = x;
		this.y = y;
		bounds.x = x;
		bounds.y = y;
	}
	
	//GETTERS AND SETTERS
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getId() {
		return id;
	}

	public boolean isPickedUp() {
		return pickedUp;
	}

	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}

	public static int getPickedUp() {
		return PICKED_UP;
	}

	public int getMaxStack() {
		return maxStack;
	}

	public void setMaxStack(int maxStack) {
		this.maxStack = maxStack;
	}
}
