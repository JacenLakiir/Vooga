/**
 * @author Michael Zhou (Dominator008), Siyang Chen
 */
package levelio;

import leveleditor.VoogaUtilities;
import levelio.SpriteWrapper.SpriteGroupIdentifier;
import core.characters.*;
import demo.*;
import java.util.*;

public class HardCodedLevelGenerator {
    
    public static void main(String[] args) {
	String herosrc = "resources/W-Gundam.png";
	String badguysrc = "resources/Mario1.png";
	DemoPlayfield dummygame = new DemoPlayfield(new DemoGameEngine());
	String background = "resources/mario2s.gif";
	Player hero = new Player(dummygame);
	List<SpriteWrapper> sprites = new ArrayList<SpriteWrapper>();
	hero.setLocation(0, 0);
	NPC badguyone = new NPC(dummygame);
	badguyone.setLocation(20, 200);
	NPC badguytwo = new NPC(dummygame);
	badguytwo.setLocation(373, 238);
	SpriteWrapper herow = new SpriteWrapper("W-Gundam",
		SpriteGroupIdentifier.PLAYER, herosrc, hero);
	SpriteWrapper badguyonew = new SpriteWrapper("Mario",
		SpriteGroupIdentifier.CHARACTER, badguysrc, badguyone);
	SpriteWrapper badguytwow = new SpriteWrapper("Mario",
		SpriteGroupIdentifier.CHARACTER, badguysrc, badguytwo);
	sprites.add(herow);
	sprites.add(badguyonew);
	sprites.add(badguytwow);
	LevelState lv1 = new LevelState(background, sprites);
	VoogaUtilities.serialize("saves/level1.lvl", lv1);
    }

}