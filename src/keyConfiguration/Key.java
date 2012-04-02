package keyConfiguration;

import characterSprites.Player;

/**
 * 
 * @author Hui Dong
 *
 */
public class Key {
    private int keyValue;
    private String action;
    private KeyObserver observer;
    private Player player;
    
    public Key(int value, String actionName, Player player){
        keyValue = value;
        action = actionName;
        this.player = player;
        observer = new KeyObserver(player);
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
