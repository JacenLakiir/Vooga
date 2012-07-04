/**
 * @author Michael Zhou (Dominator008)
 */
package io;

import io.SpriteWrapper.SpriteGroupIdentifier;
import java.util.HashMap;
import java.util.Map;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.SpriteGroup;
import core.characters.GameElement;
import core.playfield.AdvancedPlayField;

public class LevelLoader {

    public static AdvancedPlayField loadLevel(LevelState state, GameObject game) {
	Background bkg = state.getBackground();
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
	    GameElement toadd = swp.getGameElement();
	    toadd.setGame(game);
	    toadd.setX(toadd.getX());
	    toadd.setY(toadd.getY());
	    groupmap.get(swp.getGroup()).add(toadd);
	}
	playfield.setBackground(bkg);
	return playfield;
    }
    
}