package core.playfield.hud;

import java.awt.Graphics2D;

public abstract class HUDWidget {
	
	protected int xPos, yPos;
	
	public void setPosition(int x, int y) {
		xPos = x;
		yPos = y;
	}
	
	public abstract void update (long t) ;
	
	public abstract void render (Graphics2D g, HUD h) ;

}
