package core.gamestate;

import java.awt.Graphics2D;
import java.util.List;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;

import core.characters.GameElement;
import core.keyconfiguration.Key;
import core.keyconfiguration.KeyAnnotation;
import demo.DemoGameEngine;

public abstract class GameObject2D extends GameObject{
    protected List<Key>           keyList;
    GameEngine2D engine;
    public GameObject2D(GameEngine2D arg0) {
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
    
    public void addInputKeyListener(GameElement element){
        for(Key key : keyList){
            key.addInputKeyListener(element);
        }
    }
    
    public void addSystemInputKeyListener(GameObject object){
        for(Key key : keyList){
            key.addSystemKeyListener(object);
        }
    }
    
    private void checkKeyboardInput (long milliSec)
    {
        for (Key key : keyList)
            if (key.isKeyDown(milliSec))
                key.notifyObserver();
    }
    
    @KeyAnnotation(action = "ESC")
    public void pause(){
        engine.nextGameID = engine.getGameID(Pause.class.getName());
        finish();
    }
}
