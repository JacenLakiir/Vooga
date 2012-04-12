package core.gamestate;
import java.awt.Graphics2D;
import java.util.List;

import core.keyconfiguration.Key;
import core.keyconfiguration.KeyConfig;


import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
/**
 * 
 * @author Hui Dong
 *
 */
public abstract class MenuGameObject extends GameObject{
    private List<Key>        keyList;

    public MenuGameObject(GameEngine arg0) {
        super(arg0);
    }

    @Override
    public void initResources() {
        keyList = new KeyConfig(this, true).getKeyList();
        addSystemInputKeyListener(this);
    }

    @Override
    public void render(Graphics2D arg0) {
        
    }

    @Override
    public void update(long arg0) {
        checkKeyboardInput(arg0);
    }
    
    public abstract void  down();
    
    public abstract void up();
    
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