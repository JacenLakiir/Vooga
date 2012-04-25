package core.playfield.hud;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import com.golden.gamedev.object.GameFont;

public class BarWidget extends HUDWidget {

    String title;
    Double myValue;
    Double maxHP;
    DataProxy dp;

    public BarWidget(String title, DataProxy dp) {
	super(50, 15);
	this.title = title;
	this.dp = dp;
	this.maxHP = (Double) dp.get();
    }

    @Override
    public void update(long t) {
	myValue = (Double) dp.get();
    }

    @Override
    public void render(Graphics2D g, HUD h) {
	h.getFont().drawString(g, title + ":", GameFont.LEFT, xPos, yPos, 100);
	g.draw(new Rectangle(xPos + 22, yPos + 3, 101, 10));
	g.setColor(Color.red);
	if (myValue != null && maxHP != null)
		g.fill(new Rectangle(xPos + 23, yPos + 4,
			(int) (myValue / maxHP * 100), 9));
    }

}
