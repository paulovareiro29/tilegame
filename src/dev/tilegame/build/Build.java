package dev.tilegame.build;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import dev.tilegame.Handler;
import dev.tilegame.entities.Entity;
import dev.tilegame.entities.items.Item;
import dev.tilegame.entities.statics.StoneBlock;
import dev.tilegame.entities.statics.TorchBlock;
import dev.tilegame.entities.statics.WoodBlock;
import dev.tilegame.gfx.Asset;
import dev.tilegame.gfx.Text;

public class Build {
	private Handler handler;
	private boolean building;
	private Rectangle ar;
	private int[] materials;
	ArrayList<Item> invItems;
	
	private int selectedItem;
	
	public Build(Handler handler){
		this.handler = handler;
		ar = new Rectangle();
		invItems = new ArrayList<Item>();
	}
	

	
	public void onMouseRealease(MouseEvent e){
		if(e.getButton() == e.BUTTON1){
			if(building){
				
				int mX =(int) (e.getX() + handler.getGameCamera().getxOffset());
				int mY =(int) ( e.getY() + handler.getGameCamera().getyOffset());
				int tX = handler.getWorld().getGrid().getTileX(mX);
				int tY = handler.getWorld().getGrid().getTileX(mY);
				ar.x = tX * 32;
				ar.y = tY * 32;
				ar.height = 32;
				ar.width = 32;
				
				//CHECKS IF TILE IS OCUPPIED BY ANOTHER ENTITY
				for(Entity entity : handler.getWorld().getEntityManager().getEntities()){
					if(entity.getCollisionBounds(0, 0).intersects(ar)){
						return;
					}
				}
				
				//IF NOT CONTINUES
				
				handler.getWorld().getGrid().setGridTileOcuppied(mX, mY);
				
				
				for(Item item : invItems){
					if(item.getCount() <= 0)
						return;
					switch(item.getId()){
					case 0:
						handler.getWorld().getEntityManager().addEntity(new WoodBlock(handler,tX*32,tY*32));
						break;
					
					case 1:
						handler.getWorld().getEntityManager().addEntity(new StoneBlock(handler,tX*32,tY*32));
						break;
					case 2:
						handler.getWorld().getEntityManager().addEntity(new TorchBlock(handler,tX*32,tY*32));
						break;
					}
					
					if(item.getId() == invItems.get(selectedItem).getId()){
						handler.getWorld().getEntityManager().getPlayer().getInventory().useItem(selectedItem, 1);
						return;
					}
						

				}
				
			}
		}
	}
	
	
	public void tick(){
		invItems = handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems();
		materials = handler.getWorld().getEntityManager().getPlayer().getInventory().getMaterials();
		if(invItems == null)
			return;
		if(invItems.size()== 0)
			return;
		
		selectedItem = handler.getWorld().getEntityManager().getPlayer().getInventory().getSelectedItem();
		
//		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_Q))
//			selectedItem--;
//		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E))
//			selectedItem++;
//		
//		if(selectedItem < 0)
//			selectedItem = invItems.size() - 1;
//		else if(selectedItem >= invItems.size())
//			selectedItem = 0;
		
	}

	public void render(Graphics g){
		if(!building)
			return;
		g.setColor(Color.DARK_GRAY);
		g.fillRect(20, handler.getGame().getHeight() - 66 - 20, 66, 66);
		
		if(selectedItem >= invItems.size())
			return;

		
		g.drawImage(invItems.get(selectedItem).getTexture(), 21 + 16, handler.getGame().getHeight() + 16 - 66 - 21,32,32,null );
		Text.drawString(g,Integer.toString(invItems.get(selectedItem).getCount()), 21 + 45, handler.getGame().getHeight() - 40, true, Color.WHITE, Asset.font28);
		
	
	}
	
	public boolean isBuilding() {
		return building;
	}

	public void setBuilding(boolean building) {
		this.building = building;
	}
}
