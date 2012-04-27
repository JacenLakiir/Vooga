package io;

import io.SpriteWrapper.SpriteGroupIdentifier;
import java.util.HashMap;
import java.util.Map;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.SpriteGroup;
import core.playfield.AdvancedPlayField;

public class LevelLoader {

    public static AdvancedPlayField loadLevel(LevelState state) {
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
	for (SpriteWrapper swp: state.getSprites())
	    groupmap.get(swp.getGroup()).add(swp.getGameElement());
	playfield.setBackground(bkg);
	return playfield;
    }
    
}