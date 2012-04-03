/**
 * @author Michael Zhou (Dominator008)
 */
package game;

import java.util.*;
import java.awt.Point;
import leveleditor.VoogaUtilities;

import com.golden.gamedev.object.Sprite;

public class HardCodedLevelGenerator {

    public static void main(String[] args) {
	String herosrc = "resources/W-Gundam.png";
	String badguysrc = "resources/Mario1.png";
	Sprite hero =
		new Sprite(VoogaUtilities.getImageFromString(herosrc));
	Sprite badguy =
		new Sprite(VoogaUtilities.getImageFromString(badguysrc));
	String background = "resources/StarDust.jpg";
	Map<Point, SpriteWrapper> spritemap = new HashMap<Point, SpriteWrapper>();
	spritemap.put(new Point(0, 0), new SpriteWrapper(hero, herosrc));
	spritemap.put(new Point(0, 60), new SpriteWrapper(badguy, badguysrc));
	spritemap.put(new Point(20, 200), new SpriteWrapper(badguy, badguysrc));
	spritemap.put(new Point(373, 238), new SpriteWrapper(badguy, badguysrc));
	LevelState lv1 = new LevelState(background, spritemap);
	VoogaUtilities.saveLevel(lv1, "saves/level1.lvl");
    }
    
}