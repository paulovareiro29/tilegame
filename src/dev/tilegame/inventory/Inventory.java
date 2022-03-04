package dev.tilegame.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import dev.tilegame.Handler;
import dev.tilegame.entities.items.Item;
import dev.tilegame.gfx.Asset;
import dev.tilegame.gfx.Text;

public class Inventory {

	private Handler handler;
	private boolean active = false;
	
	private int invWidth =Asset.inventory.getWidth(), invHeight =Asset.inventory.getHeight(),invX,invY,
				invCenterX, invCenterY,
				invListSpace = 30;

	private int invImageX, invImageY,invImageWidth = 64, invImageHeight = 64;
	private int invCountX, invCountY;
	
	private int uniqueItems = 2;
	
	private int selectedItem = 0;
	
	private ArrayList<Item> inventoryItems;
	
	private int[] materials;
	
	public Inventory(Handler handler){
		this.handler = handler;
		inventoryItems = new ArrayList<Item>();
		materials = new int[2];
		invX =(int) (handler.getGame().getWidth() - invWidth) / 2;
		invY =  (int) (handler.getGame().getHeight() - invHeight) / 2;
		invCenterX = (invX + invWidth / 2) - invWidth / 6;
		invCenterY = (invY + invHeight / 2) + 4 ;
		
		invImageX = (invX + invWidth) - 124;
		invImageY = (invY + 34);
		
		invCountX = invImageX + 32;
		invCountY = invImageY + invImageHeight + 26;
		
		addItem(Item.torchItem.createNew(18));
		addItem(Item.stoneItem.createNew(18));
		addItem(Item.woodItem.createNew(18));
	}
	
	public void tick(){
		updateItems();
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_I))
			active = !active;
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_Q))
			selectedItem--;
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E ))
			selectedItem++;
		
		if(selectedItem < 0)
			selectedItem = inventoryItems.size() - 1;
		else if (selectedItem >= inventoryItems.size())
			selectedItem = 0;
		if(!active)
			return;
		
		
		
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_Z)){
			if(inventoryItems.size() == 0)
				return;
			dropItem(selectedItem,1);
			}
	}
	
	public void updateItems(){
		for(int i = 0; i < uniqueItems; i++){
			int temp = 0;
			for(Item item : inventoryItems){
				if(item.getId() == i)
					temp += item.getCount();
			}
			materials[i] = temp;
		}
		
		for(Item i : inventoryItems){
			if(i.getCount() > i.getMaxStack()){
				int newAmount = i.getCount() - i.getMaxStack();
				i.setCount(i.getMaxStack());
				switch(i.getId()){
				case 0:
					addItem(Item.woodItem.createNew(newAmount));
					return;
				case 1:
					addItem(Item.stoneItem.createNew(newAmount));
					return;
				}
			}
		}
		
		
		
	}
	
	public int getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(int selectedItem) {
		this.selectedItem = selectedItem;
	}

	public void render(Graphics g){
		if(!active)
			return;
		g.drawImage(Asset.inventory,invX,invY,invWidth,invHeight,null);
		
		int len = inventoryItems.size();
		if(len == 0)
			return;
		
		
		
		for(int i = -5; i < 6; i++){
			if(selectedItem + i < 0 || selectedItem + i >= len){
				continue;
			}

			if(i == 0){
				Text.drawString(g,"> " +inventoryItems.get(selectedItem + i).getName() + " <", invCenterX, invCenterY + i * invListSpace, true, Color.YELLOW, Asset.font28);
			}else{
				Text.drawString(g, inventoryItems.get(selectedItem + i).getName(), invCenterX, invCenterY + i * invListSpace, true, Color.WHITE, Asset.font28);
			}
		}
		
		if(selectedItem >= inventoryItems.size())
			return;
		
		Item item = inventoryItems.get(selectedItem);
		g.drawImage(item.getTexture(), invImageX,invImageY,invImageWidth,invImageHeight,null);
		Text.drawString(g, Integer.toString(item.getCount()), invCountX, invCountY, true, Color.WHITE, Asset.font28);	
		
	}

	//inventory methods
	
	public void dropItem(int item, int amount){
		Item i = inventoryItems.get(item);
		int direction = handler.getWorld().getEntityManager().getPlayer().getLastKey();
		int pWidth = handler.getWorld().getEntityManager().getPlayer().getWidth();
		int pHeight = handler.getWorld().getEntityManager().getPlayer().getHeight();
		int pX = (int) handler.getWorld().getEntityManager().getPlayer().getX();
		int pY = (int) handler.getWorld().getEntityManager().getPlayer().getY();
		int itemX = 0;
		int itemY = 0;
		switch(direction){
		case 0:
			itemX = (pX + pWidth / 2) - Item.ITEMWIDTH/2;
			itemY = pY - Item.ITEMHEIGHT;
			break;
		case 1:
			itemX = (pX + pWidth / 2 ) - Item.ITEMWIDTH/2;
			itemY = (pY + pHeight) + 5;
			break;
		case 2:
			itemX = (pX - 5) - Item.ITEMWIDTH;
			itemY = (pY + pHeight / 2) - Item.ITEMHEIGHT /2 ;
			break;
		case 3:
			itemX = pX + pWidth + 5;
			itemY = (pY + pHeight / 2) - Item.ITEMHEIGHT /2 ;
		}
		
	
			handler.getWorld().getItemManager().addItem(handler.getWorld().getItemManager().getItems().get(i.getId()).createNew(amount));
		
		

		
		if(i.getCount()-amount <= 0){
			inventoryItems.remove(item);
			return;
		}
		i.setCount(i.getCount()-amount);
		
	}
	
	public void useItem(int item, int amount){
		Item i = inventoryItems.get(item);
		if(i.getCount()-amount <= 0){
			inventoryItems.remove(item);
			return;
		}
		i.setCount(i.getCount()-amount);
	}
	
	public void addItem(Item item){
		for( Item i : inventoryItems){
			if(i.getId() == item.getId()){
				if(i.getCount() >= i.getMaxStack()){// se for maior que stack nao adiciona e cria novo item
					continue;
					}
				i.setCount(i.getCount() + item.getCount());
				return;
			}
		}
		inventoryItems.add(item);
	}
	//GETTERS AND SETTERS
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public ArrayList<Item> getInventoryItems() {
		return inventoryItems;
	}

	public void setInventoryItems(ArrayList<Item> inventoryItems) {
		this.inventoryItems = inventoryItems;
	}

	public int[] getMaterials() {
		return materials;
	}

	public void setMaterials(int[] materials) {
		this.materials = materials;
	}
}
