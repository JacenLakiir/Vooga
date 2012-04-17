package core.gamestate;
import java.awt.Graphics2D;
import java.util.List;

import core.keyconfiguration.Key;
import core.keyconfiguration.KeyAnnotation;
import core.keyconfiguration.KeyConfig;


import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
/**
 * 
 * @author Hui Dong
 *
 */
public abstract  class MenuGameObject extends GameObject{
    private List<Key>        keyList;
    protected int optionID = 0;
    protected int numberOfItems;
    public MenuGameObject(GameEngine arg0) {
        super(arg0);
    }

    @Override
    public void initResources() {
        keyList = new KeyConfig(this, true).getKeyList();
        addSystemInputKeyListener(this);
    }

    @Override
    public abstract void render(Graphics2D arg0);

    @Override
    public void update(long milliSec) {
        checkKeyboardInput(milliSec);
    }
    
    
    @KeyAnnotation(action = "up")
    public void up(){
        if(optionID > 0){
            optionID--;
        }
    }
    
    @KeyAnnotation(action = "down")
    public void  down(){
        if(optionID < numberOfItems){
            optionID++;
        }
    }
    
    public abstract void nextGameObject();
    
    public void addSystemInputKeyListener(GameObject object){
        for(Key key : keyList){
            key.addSystemKeyListener(object);
        }
    }
    
    public void checkKeyboardInput(long milliSec) {
        for(Key key : keyList){
            if(key.isKeyDown(milliSec)){
                key.notifyObserver();
            }
        }
    }

}
