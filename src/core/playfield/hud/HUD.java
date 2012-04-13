package core.playfield.hud;

import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.font.SystemFont;

public class HUD {
	
	private GameFont font = new SystemFont( new Font( "Monospaced", Font.BOLD, 12 ) );
	private ArrayList<HUDWidget> myWidgets = new ArrayList<HUDWidget>();
	
	int y = 10;
	
	public HUD () {
	}
	
	public void addWidget (HUDWidget w) {
		myWidgets.add(w);
		w.setPosition(10, y);
		y += 10;
	}
	
	public void update (long t) {
		for (HUDWidget w : myWidgets) {
			w.update(t);
		}
	}
	
	public void render ( Graphics2D g ) {
		g.clearRect(0,0,100,100);
		for (HUDWidget w : myWidgets) {
			w.render(g, this);
		}
	}
	
	public GameFont getFont() {
		return font;
	}

}
