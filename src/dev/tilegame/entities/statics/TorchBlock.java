package dev.tilegame.entities.statics;

import java.awt.Color;
import java.awt.Graphics;

import dev.tilegame.Handler;
import dev.tilegame.gfx.Asset;
import dev.tilegame.tiles.Tile;
import dev.tilegame.utils.Utils;

public class TorchBlock extends StaticEntity {

	public TorchBlock(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH/2, Tile.TILEHEIGHT/2);
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = 0;
		bounds.height = 0;
		
	}


	
	@Override
	public void die() {
		
	}

	@Override
	public void tick() {
		
		handler.getWorld().getGrid().setGridTileLighted((int)x,(int) y, 5);
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.drawImage(Asset.torch,(int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height,null);
		
		if(health < 5){
			g.setColor(Color.black);
			g.fillRect((int) (x + width/4 - handler.getGameCamera().getxOffset()),(int) ((y + bounds.y -handler.getGameCamera().getyOffset()) + (height/2) - 10),bounds.width / 2,10);
			g.setColor(Color.red);
			g.fillRect((int) (x + width/4 - handler.getGameCamera().getxOffset())  + 2,(int) ((y + 2  + bounds.y -handler.getGameCamera().getyOffset())  + (height/2) -10),(int)Utils.map(health, 0, 5, 0, bounds.width/ 2 - 4) ,6);
			
		}
	}

}
