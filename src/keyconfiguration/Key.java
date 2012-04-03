package keyconfiguration;

import com.golden.gamedev.Game;

import charactersprites.Player;

/**
 * 
 * @author Hui Dong
 *
 */
public class Key {
    private int keyValue;
    private String action;
    private KeyObserver observer;
    private Game myGame;
    
    public Key(int value, String actionName, Player player, Game game){
        keyValue = value;
        action = actionName;
        observer = new KeyObserver(player);
        myGame = game;
    }
    
    public boolean isKeyDown(){
        return myGame.keyDown(keyValue);
    }
    
    public String getAction(){
        return action;
    }
    
    public int getValue(){
        return keyValue;
    }
    
    public void notifyObserver(){
        observer.getActoinMethods(action);
    }
    
}
