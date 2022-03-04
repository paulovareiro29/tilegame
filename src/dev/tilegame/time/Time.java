package dev.tilegame.time;

import java.awt.Color;
import java.awt.Graphics;

import dev.tilegame.Handler;
import dev.tilegame.gfx.Asset;
import dev.tilegame.gfx.Text;
import dev.tilegame.utils.Utils;

public class Time {
	
	private boolean day;

	private int aMax = 70;
	private float darkLevel = aMax;
	private float tempA;
	
	private Handler handler;
	
	private String fulltime = "";
	private int minutes,hours,days;
	private long lastTime;
	private boolean[][] gridTiles;
	private float lightLevel;
	
	public Time(Handler handler){
		this.handler = handler;
		
	}
	
	public void tick(){
		if(handler.getWorld().getGrid().getGridTilesLight() != null)
			gridTiles = handler.getWorld().getGrid().getGridTilesLight();
		long time = System.currentTimeMillis();
	    if (time > lastTime + 100) {
	    	lastTime = time;
	    	minutes++;
	    	if(minutes >= 60){
	    		hours++;
	    		minutes = 0;
	    		if(hours >= 24){
	    			days++;
	    			hours = 0;
	    		}		
	    	}		
	    }
	    
	    if(hours >= 19 && hours <= 22){
	    	if(tempA == 0)	
	    		tempA = Utils.map(hours,19,22,0,70);
	    	if(tempA <Utils.map(hours,19,22,0,70))
	    		tempA += 0.1;
	    }
	    
	    if(hours >= 8 && hours < 19){
	    	if(tempA > 0)
	    		tempA -= 0.1;
	    	day = true;
	    }
	    
	    if(hours > 22 || hours < 5){
	    	tempA = 70;
	    	day = false;
	    }
	    
	    if(hours >= 5 && hours < 8){
	    	if(tempA == 0)
	    		tempA = Utils.map(hours,5,8,70,0);
	    	if(tempA >= Utils.map(hours,5,8,70,0))
	    		tempA -= 0.1;
	    }
	   
	    darkLevel = tempA / 100;
	    lightLevel =(float) Utils.map((long) tempA, 0, 70, 0, 20) / 100;
	    fulltime ="Day: "+ days + " " + String.format("%02d:%02d", hours,minutes);
	}
	
	public void render(Graphics g){

		for(int i = 0; i < handler.getWorld().getWidth() * 2;i++){
			for(int j = 0; j < handler.getWorld().getHeight() * 2;j++){
				if(!gridTiles[i][j]){
					g.setColor(new Color(0,0,0,darkLevel));
				}else{
					g.setColor(new Color(0,0,0,lightLevel));
				}
				g.fillRect((int)(i*32-handler.getGameCamera().getxOffset()),(int) (j*32-handler.getGameCamera().getyOffset()), 32, 32);
			}
		}
		
		Text.drawString(g, fulltime, 700, 20, true, Color.red, Asset.font28);
	}

	public boolean isDay() {
		return day;
	}

	public String getFullTime(){
		return String.format("%02d:%02d", hours,minutes);
	}
	
	public void setDay(boolean day) {
		this.day = day;
	}

	public float getDarkLevel() {
		return darkLevel;
	}

	public void setDarkLevel(int a) {
		long tempa;
		tempa = Utils.map(a, 0, 100, 0, 70);
		this.darkLevel =(float) tempa / 100;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	

}
