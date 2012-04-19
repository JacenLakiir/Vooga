package core.playfield.hud;

import java.awt.Graphics2D;

import core.playfield.hud.HUDLayoutManager.SimplePoint;

public abstract class HUDWidget {
	
	protected int xPos, yPos, height, width;
	
	public HUDWidget (int w, int h) {
		width = w;
		height = h;
	}
	
	public void setPosition(SimplePoint p) {
		xPos = p.getX()+10;
		yPos = p.getY()+10;
	}
	
	public abstract void update (long t) ;
	
	public abstract void render (Graphics2D g, HUD h) ;

}
