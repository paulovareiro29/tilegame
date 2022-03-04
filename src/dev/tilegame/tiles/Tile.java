
package dev.tilegame.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class Tile {

	protected final int id;
	protected BufferedImage texture;
	
	public static final int TILEWIDTH = 64, TILEHEIGHT = 64;

	
	public static Tile[] tiles = new Tile[255];
	public static Tile grassTile = new GrassTile(0);
	public static Tile dirtTile = new DirtTile(1);
	
	
	public Tile(BufferedImage texture,int id){
		this.texture= texture;
		this.id=id;
		tiles[id] = this;
	}
	
	public boolean isSolid(){
		return false;
	}
	
	public int getId(){
		return id;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g, int x , int y){
		g.drawImage(texture,x,y,TILEWIDTH,TILEHEIGHT,null);
	}
	

	
	
	
}
