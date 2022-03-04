package dev.tilegame.tiles;

import java.awt.image.BufferedImage;

import dev.tilegame.gfx.Asset;


public class DirtTile extends Tile{

	public DirtTile(int id) {
		super(Asset.dirt ,id);
	}

	
	public boolean isSolid(){
		return true;
	}
	
}
