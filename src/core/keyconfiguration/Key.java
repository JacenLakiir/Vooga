package core.keyconfiguration;

import com.golden.gamedev.GameObject;

import core.characters.GameElement;

/**
 * 
 * @author Hui Dong
 *
 */
public abstract class Key {
    protected String keyValue;
    protected String action;
    protected KeyObserver observer;
    protected GameObject myGame;
    public abstract boolean isKeyDown(long milliSec);
    public Key(String value, String actionName, GameObject game) {
        keyValue = value;
        action = actionName;
        myGame = game;        
    }
    public String getAction(){
        return action;
    }
    
    public String getValue(){
        return keyValue;
    }
    
    public  void notifyObserver(){
        observer.getActoinMethods(action);        
    }
    
    public void addInputKeyListener(GameElement element){
        if(this != null){
            if(this instanceof SingleInputKey || this instanceof SequentialInputKey){
                observer = new KeyObserver(element);
            }
        }
    }
    
    public void addSystemKeyListener(GameObject game){
        if(this != null){
            if(this instanceof SystemKey){
                observer = new KeyObserver(game);
            }
        }
    }
    
}
