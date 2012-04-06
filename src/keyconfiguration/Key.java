package keyconfiguration;

import com.golden.gamedev.Game;

import charactersprites.Player;

/**
 * 
 * @author Hui Dong
 *
 */
public abstract class Key {
    protected String keyValue;
    private String action;
    protected KeyObserver observer;
    protected Game myGame;    
    public Key(String value, String actionName, Player player, Game game){
        keyValue = value;
        action = actionName;
        myGame = game;
    }

    
    public abstract boolean isKeyDown(long milliSec);
    
    public String getAction(){
        return action;
    }
    
    public String getValue(){
        return keyValue;
    }
    
    public void notifyObserver(){
        observer.getActoinMethods(action);
    }
    
}
