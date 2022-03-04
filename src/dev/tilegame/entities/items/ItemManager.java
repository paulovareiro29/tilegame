package dev.tilegame.entities.items;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import dev.tilegame.Handler;

public class ItemManager {

	private Handler handler;
	private ArrayList<Item> items;
	
	public ItemManager(Handler handler){
		this.handler = handler;
		items = new ArrayList<Item>();
	}
	
	public void tick(){
		Iterator<Item> item = items.iterator();
		while(item.hasNext()){
			Item i = item.next();
			i.tick();
			if(i.isPickedUp())
				item.remove();
		}
	}
	
	public void render(Graphics g){
		for(Item i : items)
			i.render(g);
	}
	
	public void addItem(Item i){
		i.setHandler(handler);
		items.add(i);
	}

	//getters and setters
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
}
