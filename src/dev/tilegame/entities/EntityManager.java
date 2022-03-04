package dev.tilegame.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import dev.tilegame.Handler;
import dev.tilegame.entities.creatures.Player;

public class EntityManager {

	private Handler handler;
	private Player player;
	
	private ArrayList<Entity> entities;
	private Comparator<Entity> renderSorter = new Comparator<Entity>(){

		@Override
		public int compare(Entity a, Entity b) {
			if(a.getY() + a.height < b.getY() + b.getHeight())
				return -1;
			return 1;
		}
		
	};
	
	public EntityManager(Handler handler, Player player){
		this.handler = handler;
		this.player = player;
		entities = new ArrayList<Entity>();
		addEntity(player);
		
	}
	
	public void tick(){
		
		Iterator<Entity> it = entities.iterator();
		while(it.hasNext()){
			Entity e = it.next();
			e.tick();
			if(!e.isActive())
				it.remove();
		}
		entities.sort(renderSorter);
	
	}
	
	public void render(Graphics g){
		for(Entity e : entities){
			e.render(g);
		}
		
	}

	public void addEntity(Entity e){
		entities.add(e);
	}
	
	public void removeEntity(Entity e){
		entities.remove(e);
	}
	
	//GETTERS SETTERS 
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
}
