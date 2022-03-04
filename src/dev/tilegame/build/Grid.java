package dev.tilegame.build;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import dev.tilegame.Handler;
import dev.tilegame.entities.Entity;
import dev.tilegame.tiles.Tile;

public class Grid {

	private Handler handler;
	private boolean visible;
	private boolean[][] gridTiles;
	private boolean[][] gridTilesLight;
	
	public Grid(Handler handler){
		this.handler=handler;
		gridTiles = new boolean[handler.getGame().getWidth() * 2][handler.getGame().getHeight() * 2];
		gridTilesLight = new boolean[handler.getGame().getWidth() * 2][handler.getGame().getHeight() * 2];
	}

	public void tick(){
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_G))
			visible = !visible;	
	}
	
	public void render(Graphics g){
		if(visible){
			for(int i = 0; i < handler.getWorld().getWidth() * 2;i++){
				g.setColor(Color.DARK_GRAY);
				g.drawRect((int) (i*(Tile.TILEWIDTH/2) - handler.getGameCamera().getxOffset()) ,(int) (0 - handler.getGameCamera().getyOffset()), 1, Tile.TILEHEIGHT*handler.getWorld().getHeight());
			}
			for(int i = 0; i < handler.getWorld().getHeight() * 2;i++){
				g.setColor(Color.DARK_GRAY);
				g.drawRect((int)(0- handler.getGameCamera().getxOffset()),(int)(i*(Tile.TILEWIDTH/2)- handler.getGameCamera().getyOffset()), Tile.TILEWIDTH*handler.getWorld().getWidth(), 1);
			}
		}
	}
	
	public int getTileX(int x){
		int tX = x/(Tile.TILEWIDTH/2);
		return tX;
	}
	public int getTileY(int y){
		int tY = y/(Tile.TILEHEIGHT/2);
		return tY;
	}
	
	public boolean isGridTileOcuppied(int x,int y){
		return gridTiles[getTileX(x)][getTileY(y)];
	}
	
	public void setGridTileOcuppied(int x, int y){
		gridTiles[getTileX(x)][getTileY(y)] = true;
	}
	
	public void setGridTileLighted(int x,int y,int area){
		int tempX = getTileX(x);
		int tempY = getTileY(y);
		for(int i = (int) -(area/2);i <= (int)(area/2);i++){
			for(int j = (int) -(area/2);j <= (int)(area/2);j++){
//				
				gridTilesLight[tempX + i][tempY + j] = true;
			}
		}
	}
	
	public boolean[][] getGridTilesLight() {
		return gridTilesLight;
	}


	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean[][] getGridTiles() {
		return gridTiles;
	}

	public void setGridTiles(boolean[][] gridTiles) {
		this.gridTiles = gridTiles;
	}
	
	
}
