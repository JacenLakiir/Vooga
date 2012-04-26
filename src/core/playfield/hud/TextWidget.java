package core.playfield.hud;

import java.awt.Graphics2D;

import com.golden.gamedev.object.GameFont;

import core.playfield.hud.StringProxy;

public class TextWidget extends HUDWidget {

	String title;
	String myValue;
	StringProxy dp;

	public TextWidget(String title, StringProxy dp) {
		super(50,15);
		this.title = title;
		this.dp = dp;
	}

	@Override
	public void update(long t) {
		myValue = dp.get();
	}

	@Override
	public void render(Graphics2D g, HUDGroup h) {
		h.getFont().drawString(g, title + ":" + myValue, GameFont.LEFT, xPos,
				yPos, 100);
	}

}
