package core.playfield.hud;

import java.awt.Graphics2D;

import com.golden.gamedev.object.GameFont;

public class TextWidget extends HUDWidget {

	String title;
	String myValue;
	DataProxy dp;

	public TextWidget(String title, DataProxy dp) {
		this.title = title;
		this.dp = dp;
	}

	@Override
	public void update(long t) {
		System.out.println(dp.getDouble());
		myValue = Double.toString(dp.getDouble());
	}

	@Override
	public void render(Graphics2D g, HUD h) {
		h.getFont().drawString(g, title + ":" + myValue, GameFont.LEFT, xPos,
				yPos, 100);
	}

}
