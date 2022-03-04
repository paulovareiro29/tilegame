package dev.tilegame.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Asset {

	public static Font font28;
	
	public static BufferedImage[] btn_start;
	public static BufferedImage grass,dirt,tree,rock,wood_block,stone_block,torch,normalEnemy;
	public static BufferedImage inventory,wood_log,stone;
	public static BufferedImage[] player_down,player_up,player_left,player_right,
									player_atc_down,player_atc_up,player_atc_left,player_atc_right;

	
	
	public static void init(){
		
		font28 = FontLoader.loadFont("res/fonts/Arial.ttf", 28);
		
		inventory =  ImageLoader.loadImage("/textures/inventory.png");
		
		SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/textures/playerSheet.png"));
		player_down = new BufferedImage[9];
		player_up = new BufferedImage[9];
		player_left = new BufferedImage[9];
		player_right = new BufferedImage[9];
		player_atc_down = new BufferedImage[6];
		player_atc_up = new BufferedImage[6];
		player_atc_left = new BufferedImage[6];
		player_atc_right = new BufferedImage[6];
		
		
		
		player_down[0] = playerSheet.crop(3, 1,64, 64);
		player_down[1] = playerSheet.crop(3, 2,64, 64);
		player_down[2] = playerSheet.crop(3, 3,64, 64);
		player_down[3] = playerSheet.crop(3, 4,64, 64);
		player_down[4] = playerSheet.crop(3, 5,64, 64);
		player_down[5] = playerSheet.crop(3, 6,64, 64);
		player_down[6] = playerSheet.crop(3, 7,64, 64);
		player_down[7] = playerSheet.crop(3, 8,64, 64);
		player_down[8] = playerSheet.crop(3, 9,64, 64);
		
		player_up[0] = playerSheet.crop(1, 1,64, 64);
		player_up[1] = playerSheet.crop(1, 2,64, 64);
		player_up[2] = playerSheet.crop(1, 3,64, 64);
		player_up[3] = playerSheet.crop(1, 4,64, 64);
		player_up[4] = playerSheet.crop(1, 5,64, 64);
		player_up[5] = playerSheet.crop(1, 6,64, 64);
		player_up[6] = playerSheet.crop(1, 7,64, 64);
		player_up[7] = playerSheet.crop(1, 8,64, 64);
		player_up[8] = playerSheet.crop(1, 9,64, 64);
		
		player_left[0] = playerSheet.crop(2, 1,64, 64);
		player_left[1] = playerSheet.crop(2, 2,64, 64);
		player_left[2] = playerSheet.crop(2, 3,64, 64);
		player_left[3] = playerSheet.crop(2, 4,64, 64);
		player_left[4] = playerSheet.crop(2, 5,64, 64);
		player_left[5] = playerSheet.crop(2, 6,64, 64);
		player_left[6] = playerSheet.crop(2, 7,64, 64);
		player_left[7] = playerSheet.crop(2, 8,64, 64);
		player_left[8] = playerSheet.crop(2, 9,64, 64);
		
		player_right[0] = playerSheet.crop(4, 1,64, 64);
		player_right[1] = playerSheet.crop(4, 2,64, 64);
		player_right[2] = playerSheet.crop(4, 3,64, 64);
		player_right[3] = playerSheet.crop(4, 4,64, 64);
		player_right[4] = playerSheet.crop(4, 5,64, 64);
		player_right[5] = playerSheet.crop(4, 6,64, 64);
		player_right[6] = playerSheet.crop(4, 7,64, 64);
		player_right[7] = playerSheet.crop(4, 8,64, 64);
		player_right[8] = playerSheet.crop(4, 9,64, 64);
		
		player_atc_up[0] = playerSheet.crop(5, 1,64, 64);
		player_atc_up[1] = playerSheet.crop(5, 2,64, 64);
		player_atc_up[2] = playerSheet.crop(5, 3,64, 64);
		player_atc_up[3] = playerSheet.crop(5, 4,64, 64);
		player_atc_up[4] = playerSheet.crop(5, 5,64, 64);
		player_atc_up[5] = playerSheet.crop(5,6,64, 64);
		
		player_atc_left[0] = playerSheet.crop(6, 1,64, 64);
		player_atc_left[1] = playerSheet.crop(6, 2,64, 64);
		player_atc_left[2] = playerSheet.crop(6, 3,64, 64);
		player_atc_left[3] = playerSheet.crop(6, 4,64, 64);
		player_atc_left[4] = playerSheet.crop(6, 5,64, 64);
		player_atc_left[5] = playerSheet.crop(6, 6,64, 64);
		
		player_atc_down[0] = playerSheet.crop(7, 1,64, 64);
		player_atc_down[1] = playerSheet.crop(7, 2,64, 64);
		player_atc_down[2] = playerSheet.crop(7, 3,64, 64);
		player_atc_down[3] = playerSheet.crop(7 ,4,64, 64);
		player_atc_down[4] = playerSheet.crop(7, 5,64, 64);
		player_atc_down[5] = playerSheet.crop(7, 6,64, 64);
		
		player_atc_right[0] = playerSheet.crop(8, 1,64, 64);
		player_atc_right[1] = playerSheet.crop(8, 2,64, 64);
		player_atc_right[2] = playerSheet.crop(8, 3,64, 64);
		player_atc_right[3] = playerSheet.crop(8, 4,64, 64);
		player_atc_right[4] = playerSheet.crop(8, 5,64, 64);
		player_atc_right[5] = playerSheet.crop(8, 6,64, 64);

		
		SpriteSheet mapSheet = new SpriteSheet(ImageLoader.loadImage("/textures/mapSheet.png"));
		grass = mapSheet.crop(1, 1,32, 32);
		dirt = mapSheet.crop(1,2,32,32);
		tree = mapSheet.crop(1, 2, 32*2, 32*3);
		rock = mapSheet.crop(2, 1, 32 ,32);
		
		
		wood_block = mapSheet.crop(4, 1, 32, 32);
		stone_block = mapSheet.crop(4, 2, 32, 32);
		torch = mapSheet.crop(4, 3, 32, 32);
		
		SpriteSheet itemSheet = new SpriteSheet(ImageLoader.loadImage("/textures/itemSheet.png"));
		wood_log = itemSheet.crop(1, 1, 32, 32);
		stone = itemSheet.crop(1, 2, 32, 32);
		
		SpriteSheet enemySheet = new SpriteSheet(ImageLoader.loadImage("/textures/enemySheet.png"));
		normalEnemy = enemySheet.crop(1,1,32,32);
		
		SpriteSheet menuSheet = new SpriteSheet(ImageLoader.loadImage("/textures/menuSheet.png"));
		btn_start = new BufferedImage[2];
		btn_start[0] = menuSheet.crop(1,1, 32*3, 32);
		btn_start[1] = menuSheet.crop(2,1, 32*3, 32);
		

	}
}
