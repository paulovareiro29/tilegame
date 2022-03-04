package dev.tilegame.entities.items;

import java.awt.image.BufferedImage;

import dev.tilegame.gfx.Asset;

public class WoodLog extends Item{

	public WoodLog(int id) {
		super(Asset.wood_log, "Wood", id, 20);
	}

}
