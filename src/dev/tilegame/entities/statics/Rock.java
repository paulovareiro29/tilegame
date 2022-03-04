package dev.tilegame.entities.statics;

import java.awt.Color;
import java.awt.Graphics;

import dev.tilegame.Handler;
import dev.tilegame.entities.Entity;
import dev.tilegame.entities.items.Item;
import dev.tilegame.gfx.Asset;
import dev.tilegame.tiles.Tile;
import dev.tilegame.utils.Utils;

public class Rock extends StaticEntity{

	public Rock(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH , Tile.TILEHEIGHT);

		bounds.width =(int) width - 10;
		bounds.height = (int) (height - height / 2);
		bounds.y =(int) ( height / 2.7);
	}

	@Override
	public void tick() {

		
	}

	@Override
	public void die(){
		handler.getWorld().getItemManager().addItem(Item.stoneItem.createNew((int)(x + bounds.x), (int)(y + bounds.y)));
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(Asset.rock,(int) (x - handler.getGameCamera().getxOffset()),(int) (y-handler.getGameCamera().getyOffset()) , width,height,null);	
		
		if(health < Entity.DEFAULT_HEALTH){
			g.setColor(Color.black);
			g.fillRect((int) (x + width/4 - handler.getGameCamera().getxOffset()),(int) (y + bounds.y -handler.getGameCamera().getyOffset()),bounds.width / 2,10);
			g.setColor(Color.red);
			g.fillRect((int) (x + width/4 - handler.getGameCamera().getxOffset())  + 2,(int) (y + 2  + bounds.y -handler.getGameCamera().getyOffset()),(int)Utils.map(health, 0, 10, 0, bounds.width/ 2 - 4) ,6);
			
		}
//		g.setColor(Color.red);
//		g.fillRect((int) (x +bounds.x - handler.getGameCamera().getxOffset()),(int) (y + bounds.y-handler.getGameCamera().getyOffset()),bounds.width,bounds.height);
		
	}

	

}
