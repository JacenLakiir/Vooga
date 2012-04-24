/**
 * @author Michael Zhou (Dominator008), Siyang Chen
 */
package io;

import core.characters.*;
import core.characters.Character;
import core.physicsengine.physicsplugin.PhysicsAttributes;
import demo.*;
import io.SpriteWrapper.SpriteGroupIdentifier;
import java.util.*;
import java.io.Serializable;

public class HardCodedLevelGenerator {
    
    public static void main(String[] args) {
	String herosrc = "resources/W-Gundam.png";
	String badguysrc = "resources/Mario1.png";
	DemoPlayfield dummygame = new DemoPlayfield(new DemoGameEngine());
	String background = "resources/mario2s.gif";
	Character hero = new Player(dummygame, new PhysicsAttributes());
	List<SpriteWrapper> sprites = new ArrayList<SpriteWrapper>();
	hero.setLocation(0, 0);
	NPC badguyone = new NPC(dummygame, new PhysicsAttributes());
	badguyone.setLocation(20, 200);
	NPC badguytwo = new NPC(dummygame, new PhysicsAttributes());
	badguytwo.setLocation(373, 238);
	Map<String, Serializable> dummymap = hero.getPhysicsAttribute().getPhysicsAttrMap();
	Map<SpriteAttribute, Serializable> realmap = 
		SpriteAttribute.convertkeyToAttribute(dummymap, "Physics");
	SpriteWrapper herow = new SpriteWrapper("W-Gundam",
		SpriteGroupIdentifier.PLAYER, realmap, herosrc, hero);
	SpriteWrapper badguyonew = new SpriteWrapper("Mario",
		SpriteGroupIdentifier.CHARACTER, realmap, badguysrc, badguyone);
	SpriteWrapper badguytwow = new SpriteWrapper("Mario",
		SpriteGroupIdentifier.CHARACTER, realmap, badguysrc, badguytwo);
	sprites.add(herow);
	sprites.add(badguyonew);
	sprites.add(badguytwow);
	LevelState lv1 = new LevelState(background, sprites);
	lv1.saveLevel("saves/level1.lvl");
    }

}
