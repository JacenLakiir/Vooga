/**
 * @author Michael Zhou (Dominator008)
 */
package io;

import java.awt.Graphics2D;
import java.io.File;
import core.characters.Character;
import core.configuration.key.KeyAnnotation;
import core.configuration.key.KeyParser;
import core.configuration.mouse.MouseInput;
import core.gamestate.Game2D;
import core.gamestate.GameEngine2D;
import core.playfield.AdvancedPlayField;
import core.playfield.scroller.GameScroller;
import core.playfield.scroller.KeepLeftFirstPlayerGameScroller;
import demo.Pause;
import demo.custom.DemoKeyAdapter;

@SuppressWarnings("serial")
public class SimpleGameToTestLevelEditor extends Game2D {

    public SimpleGameToTestLevelEditor(GameEngine2D engine) {
	super(engine);
    }

    private AdvancedPlayField myPlayfield;
    private GameScroller myGameScroller;
    private Character myHero;

    private void hardCodedLoadLevel() {
	LevelState loadedState = LevelState.load(new File("saves/level1.lvl"));
	myPlayfield = LevelLoader.loadLevel(loadedState, this);
	myHero = (Character) myPlayfield.getPlayers().getActiveSprite(); // RANDOM
    }

    public void initResources() {
	hardCodedLoadLevel();
	myGameScroller = new KeepLeftFirstPlayerGameScroller();
	myPlayfield.setGameScroller(myGameScroller);
	setKeyList(new KeyParser(this, false, new DemoKeyAdapter("key_type"))
		.parseKeyConfig("configurations/keyconfig.json"));
	addMouse(new MouseInput(this, myHero, "sequence"));
	addKeyListeners(myHero);
	addKeyListeners(this);
	addMouseListeners(myHero);
    }

    public void update(long arg0) {
	super.update(arg0);
	myPlayfield.update(arg0);
    }

    public void render(Graphics2D arg0) {
	myPlayfield.render(arg0);
    }

    @Override
    public boolean isWin() {
	return false;
    }

    @Override
    public boolean isFail() {
	return false;
    }

    @Override
    public void registerNextLevel() {}

    @Override
    public void registerGameOverEvent() {}

    @KeyAnnotation(action = "ESC")
    public void pause() {
	switchToGameObject(Pause.class);
    }

}
