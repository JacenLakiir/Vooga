package core.gamestate;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import com.golden.gamedev.GameObject;

import core.configuration.key.Key;
import core.configuration.mouse.Mouse;

public abstract class GameObject2D extends GameObject {
    private List<Key> keyList;
    private List<Mouse> mouseList = new ArrayList<Mouse>();

    public List<Mouse> getMouseList() {
	return mouseList;
    }

    private GameEngine2D engine;

    public void setKeyList(List<Key> keys) {
	keyList = keys;
    }

    public void addMouse(Mouse mouse) {
	mouseList.add(mouse);
    }

    public GameObject2D(GameEngine2D engine) {
	super(engine);
	this.engine = engine;
    }

    @Override
    public abstract void initResources();

    @Override
    public abstract void render(Graphics2D graphic);

    @Override
    public void update(long milliSec) {
	checkKeyboardInput(milliSec);
	checkMouseInput(milliSec);
    }

    protected GameEngine2D getEngine() {
	return engine;
    }

    public void addKeyListeners(Object object) {
	for (Key key : keyList) {
	    key.addKeyListenenr(object);
	}
    }

    public void addMouseListeners(Object object) {
	for (Mouse mouse : mouseList) {
	    mouse.addMouseListenenr(object);
	}
    }

    private void checkKeyboardInput(long milliSec) {
	for (Key key : keyList)
	    if (key.isKeyDown(milliSec))
		key.notifyObserver();
    }

    private void checkMouseInput(long milliSec) {
	for (Mouse mouse : getMouseList()) {
	    if (mouse.isMouseClicked()) {
		mouse.notifyObserver();
	    }
	}
    }

    public void continueGame() {
	engine.nextGameID = engine.getPreviousGameID();
    }

    public void restartGame() {
	continueGame();
	engine.initResources();
    }
    
    public void reset(){
        engine.initResources();
    }

    public void resetEngine() {
	engine.initResources();
    }
    
    public void switchToGameObject(Class<? extends GameObject2D> gameClass) {
	engine.nextGameID = engine.getIdMap().get(gameClass.getName());
	finish();
    }
}
