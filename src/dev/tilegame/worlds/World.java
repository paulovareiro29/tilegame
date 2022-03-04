package dev.tilegame.worlds;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import dev.tilegame.Game;
import dev.tilegame.Handler;
import dev.tilegame.build.Build;
import dev.tilegame.build.Grid;
import dev.tilegame.entities.EntityManager;
import dev.tilegame.entities.creatures.Player;
import dev.tilegame.entities.items.ItemManager;
import dev.tilegame.entities.statics.*;
import dev.tilegame.inventory.Inventory;
import dev.tilegame.tiles.Tile;
import dev.tilegame.time.Time;
import dev.tilegame.utils.Utils;


public class World {
	
	private Handler handler;
	
	private int width,height,spawnX,spawnY;
	public static int[][] tiles;
	
	private EntityManager entityManager;
	private ItemManager itemManager;
	private Grid grid;
	private Build build;
	private Time time;
	
	public World(Handler handler,String path){
		this.handler=handler;
		loadWorld(path);
		entityManager = new EntityManager(handler, new Player(handler,spawnX,spawnY));
		itemManager = new ItemManager(handler);
		build = new Build(handler);
		grid = new Grid(handler);
		time = new Time(handler);
		entityManager.addEntity(new Tree(handler,400,200));
		entityManager.addEntity(new Tree(handler,600,200));
		entityManager.addEntity(new Rock(handler,500,500));

		
	}
	
	public void onMouseRealease(MouseEvent e){
	}
	
	public void onMouseDrag(){
	
	}

	public void tick(){
		entityManager.tick();
		itemManager.tick();
		time.tick();
		build.tick();
		grid.tick();
		handler.getWorld().getEntityManager().getPlayer().postTick();
	}
	
	public void render(Graphics g){
		
		int xStart = (int)  Math.max(0,handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int)  Math.min(width,(handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH +1 );
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT +1 );
		
		for(int y = yStart;y < yEnd;y++ ){
			for(int x = xStart;x < xEnd; x++){
				getTile(x,y).render(g,(int) (x*Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
									  (int) (y*Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()) );
			}
		}
		
		
		itemManager.render(g);
		entityManager.render(g);
		time.render(g);
		grid.render(g);
		build.render(g);
		handler.getWorld().getEntityManager().getPlayer().postRender(g);
		
	}
	
	 
	public Tile getTile(int x, int y){
		if (x<0||y<0 || x>=width || y>= height)
			return Tile.grassTile;
		
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null){
			return Tile.dirtTile;
		}
		return t;
	}
	
	private void loadWorld(String path){
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		
		
		for(int x = 0;x < width;x++ ){
			for(int y = 0;y < height; y++){
				tiles[x][y] = Utils.parseInt(tokens[(x+y*width)+4]);
	
			}
		}
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}



	public Handler getHandler() {
		return handler;
	}



	public void setHandler(Handler handler) {
		this.handler = handler;
	}



	public int getSpawnX() {
		return spawnX;
	}



	public void setSpawnX(int spawnX) {
		this.spawnX = spawnX;
	}



	public int getSpawnY() {
		return spawnY;
	}



	public void setSpawnY(int spawnY) {
		this.spawnY = spawnY;
	}



	public ItemManager getItemManager() {
		return itemManager;
	}



	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}



	public void setWidth(int width) {
		this.width = width;
	}



	public void setHeight(int height) {
		this.height = height;
	}



	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}



	public Grid getGrid() {
		return grid;
	}



	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	
	public Build getBuild() {
		return build;
	}

	public void setBuild(Build build) {
		this.build = build;
	}
}
