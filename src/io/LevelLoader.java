package io;

import java.util.*;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.SpriteGroup;
import io.SpriteWrapper.SpriteGroupIdentifier;

import core.playfield.AdvancedPlayField;

public class LevelLoader {

    public static AdvancedPlayField loadLevel(LevelState state) {
	Background bkg = state.getBackground();
	AdvancedPlayField playfield = new AdvancedPlayField(bkg.getWidth(), bkg.getHeight());
	Map<SpriteGroupIdentifier, SpriteGroup> groupmap 
	= new HashMap<SpriteGroupIdentifier, SpriteGroup>();
	playfield.addGroup(new SpriteGroup("Player"));
	groupmap.put(SpriteGroupIdentifier.PLAYER, playfield.getPlayers());
	groupmap.put(SpriteGroupIdentifier.CHARACTER, playfield.getCharacters());
	groupmap.put(SpriteGroupIdentifier.SETTING, playfield.getSetting());
	groupmap.put(SpriteGroupIdentifier.ITEM, playfield.getItems());
	for (SpriteWrapper swp: state.getSprites())
	    groupmap.get(swp.getGroup()).add(swp.getGameElement());
	playfield.setBackground(bkg);
	return playfield;
    }
    
}