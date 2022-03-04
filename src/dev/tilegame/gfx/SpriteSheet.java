
package dev.tilegame.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	BufferedImage sprite;
	public SpriteSheet(BufferedImage sprite){
		this.sprite=sprite;
	}
	
	public BufferedImage crop(int row,int column, int width, int height){
		int y = (row*height)-height;
		  int x = (column*width)-width;
		  return sprite.getSubimage(x, y, width, height);

		
	}
}
