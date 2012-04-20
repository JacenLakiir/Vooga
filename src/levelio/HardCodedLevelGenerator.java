/**
 * @author Michael Zhou (Dominator008), Siyang Chen
 */
package levelio;

import java.awt.Point;
import java.util.Map;
import java.util.HashMap;
import leveleditor.VoogaUtilities;
import levelio.SpriteWrapper.SpriteGroupIdentifier;

import com.golden.gamedev.*;
import com.golden.gamedev.object.Sprite;

import core.characters.*;
import core.gamestate.*;
import demo.*;
import java.util.*;

public class HardCodedLevelGenerator {
    public static void main(String[] args) {
	String herosrc = "resources/W-Gundam.png";
	String badguysrc = "resources/Mario1.png";
	DemoPlayfield dummygame = new DemoPlayfield(new DemoGameEngine());
	Sprite badguy = new Sprite(VoogaUtilities.getImageFromString(badguysrc));
	String background = "resources/mario2s.gif";
	Player hero = new Player(dummygame);
	List<SpriteWrapper> sprites = new ArrayList<SpriteWrapper>();
	hero.setLocation(0, 0);
	NPC badguyone = new NPC(dummygame);
	badguyone.setLocation(20, 200);
	NPC badguytwo = new NPC(dummygame);
	badguytwo.setLocation(373, 238);
	SpriteWrapper herow = new SpriteWrapper("W-Gundam", 
		SpriteGroupIdentifier.PLAYER, Player.class, herosrc);
	SpriteWrapper badguyonew = new SpriteWrapper("Mario", 
		SpriteGroupIdentifier.CHARACTER, NPC.class, badguysrc);
	SpriteWrapper badguytwow = new SpriteWrapper("Mario", 
		SpriteGroupIdentifier.CHARACTER, NPC.class, badguysrc);
	LevelState lv1 = new LevelState(background, sprites);
	lv1.save("saves/level1.lvl");
    }
}
