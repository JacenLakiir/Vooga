package core.gamestate;

import java.awt.Graphics2D;
import java.util.List;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;

import core.keyconfiguration.Key;
import core.keyconfiguration.KeyAnnotation;
import demo.GameEngine2D;

public class GameObject2D extends GameObject{
    protected List<Key>           keyList;
    GameEngine engine;
    public GameObject2D(GameEngine arg0) {
        super(arg0);
        engine = arg0;
    }

    @Override
    public void initResources() {
        
    }

    @Override
    public void render(Graphics2D arg0) {
        
    }

    @Override
    public void update(long arg0) {
        checkKeyboardInput(arg0);
    }
    
    private void checkKeyboardInput (long milliSec)
    {
        for (Key key : keyList)
            if (key.isKeyDown(milliSec))
                key.notifyObserver();
    }
    
    @KeyAnnotation(action = "ESC")
    public void pause(){
        engine.nextGameID = GameEngine2D.PAUSE;
        finish();
    }
}
