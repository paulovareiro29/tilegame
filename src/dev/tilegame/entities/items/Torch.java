package dev.tilegame.entities.items;

import dev.tilegame.gfx.Asset;

public class Torch extends Item {
	public Torch(int id) {
		super(Asset.torch, "Torch", id, 20);
	}
}
