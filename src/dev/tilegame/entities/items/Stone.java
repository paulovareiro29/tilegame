package dev.tilegame.entities.items;

import java.awt.image.BufferedImage;

import dev.tilegame.gfx.Asset;

public class Stone extends Item{

	public Stone( int id) {
		super(Asset.stone, "Stone", id, 20);
	}

}
