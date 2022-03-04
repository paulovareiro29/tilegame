package dev.tilegame.states;

import java.awt.Graphics;

import dev.tilegame.Game;
import dev.tilegame.Handler;
import dev.tilegame.UI.ClickListener;
import dev.tilegame.UI.UIImageButton;
import dev.tilegame.UI.UIManager;
import dev.tilegame.gfx.Asset;

public class MenuState extends State {

	private UIManager uiManager;
	
	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton(handler.getWidth()/2- 32 * 3 , handler.getHeight()/2-32, 32 * 6,32 * 2, Asset.btn_start, new ClickListener(){
			@Override
			public void onClick() {
				State.setState(handler.getGame().gameState);
			}}));}

	@Override
	public void tick() {
		
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		
		uiManager.render(g);
	}

}
