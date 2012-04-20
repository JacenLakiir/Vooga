package levelio;

import com.golden.gamedev.GameObject;

import core.playfield.AdvancedPlayField;

public class LevelLoaderForGame {

    public AdvancedPlayField load(GameObject game, LevelState state, AdvancedPlayField playfield) {
	//playfield.addPlayer(p);
	try {
	    state.getClass().getField("ASD").getTy
	} catch (NoSuchFieldException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (SecurityException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
    
}
