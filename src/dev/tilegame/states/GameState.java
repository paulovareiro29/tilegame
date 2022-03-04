package dev.tilegame.states;

import java.awt.Graphics;

import dev.tilegame.Game;
import dev.tilegame.Handler;
import dev.tilegame.entities.creatures.Player;
import dev.tilegame.entities.statics.Tree;
import dev.tilegame.worlds.World;

public class GameState extends State{


	private World world;

	
	
	public GameState(Handler handler){
		super(handler);		
		world = new World(handler,"res/worlds/world1.txt");
		handler.setWorld(world);

	}
	
	@Override
	public void tick() {
		world.tick();


		
	}

	@Override
	public void render(Graphics g) {
		world.render(g);

	}

}
