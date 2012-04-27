package io;

import java.util.*;

import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.SpriteGroup;
import io.SpriteWrapper.SpriteGroupIdentifier;

import core.characters.GameElement;
import io.SpriteWrapper.SpriteGroupIdentifier;
import java.util.HashMap;
import java.util.Map;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.SpriteGroup;
import core.playfield.AdvancedPlayField;
import core.tiles.Tile;
import core.tiles.TileDecorator;

public class LevelLoader {

    public static AdvancedPlayField loadLevel(LevelState state, GameObject game) {
	Background bkg = state.getBackground();
	//TODO
	AdvancedPlayField playfield = new AdvancedPlayField(bkg.getWidth(), bkg.getHeight(), 640
		, 480);
	Map<SpriteGroupIdentifier, SpriteGroup> groupmap 
	= new HashMap<SpriteGroupIdentifier, SpriteGroup>();
	playfield.addGroup(new SpriteGroup("Player"));
	groupmap.put(SpriteGroupIdentifier.PLAYER, playfield.getPlayers());
	groupmap.put(SpriteGroupIdentifier.CHARACTER, playfield.getCharacters());
	groupmap.put(SpriteGroupIdentifier.SETTING, playfield.getSetting());
	groupmap.put(SpriteGroupIdentifier.ITEM, playfield.getItems());
	for (SpriteWrapper swp: state.getSprites()) {
	    //System.out.println(swp.getGameElement().getPhysicsAttribute().getPhysicsAttrMap());
	    GameElement toadd = swp.getGameElement();
	    toadd.setGame(game);
	    toadd.setX(toadd.getX());
	    toadd.setY(toadd.getY());
//	    if (TileDecorator.class.isAssignableFrom(toadd.getClass()))
//		doNastySetGame(toadd, game);
	    groupmap.get(swp.getGroup()).add(toadd);
	}
	playfield.setBackground(bkg);
	return playfield;
    }
    
//    private static void doNastySetGame(GameElement decoratedtile, GameObject game) {
//	TileDecorator t = (TileDecorator) decoratedtile;
//	Tile inner = null;
//	while(inner.getClass().getSimpleName().equals("Tile")) {
//	    inner = t.removeDecorator();
//	    inner.setGame(game);
//	}
//    }
    
}