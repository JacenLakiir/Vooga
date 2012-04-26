package core.playfield.hud;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import core.playfield.hud.IntProxy;

import com.golden.gamedev.object.GameFont;

public class IconWidget extends HUDWidget {
	
	String title;
	int myValue;
	BufferedImage img;
	IntProxy dp;

	public IconWidget(String title, BufferedImage img, IntProxy dp) {
		super(50, 15);
		this.title = title;
		this.img = img;
		this.dp = dp;
	}
	
	@Override
	public void update(long t) {
		myValue = dp.get();
	}

	@Override
	public void render(Graphics2D g, HUDGroup h) {
		h.getFont().drawString(g, title + ":", GameFont.LEFT, xPos,
				yPos, 100);
		for (int i = 0; i < myValue; i++ )
			g.drawImage(img, null, xPos + i*15 +50, yPos);
	}

}
