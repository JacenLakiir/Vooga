package io;

import core.playfield.AdvancedPlayField;

public class LevelLoader {

    public static AdvancedPlayField loadLevel(LevelState state) {
	int width = state
	AdvancedPlayField playfield = new AdvancedPlayField();
	playfield.setGameScroller(new GameScroller(););
    }
    
}
