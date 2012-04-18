package core.keyconfiguration;

import java.util.ArrayList;
import java.util.List;

import com.golden.gamedev.GameObject;
/**
 * 
 * @author Hui Dong
 *
 */
public abstract class Key {
    protected String keyValue;
    private String action;
    private List<KeyObserver> observers;
    protected GameObject myGame;
    public abstract boolean isKeyDown(long milliSec);
    public Key(String value, String actionName, GameObject game) {
        keyValue = value;
        action = actionName;
        myGame = game;    
        observers = new ArrayList<KeyObserver>();
    }
    public String getAction(){
        return action;
    }
    
    public String getValue(){
        return keyValue;
    }
    
    public  void notifyObserver(){
        for(KeyObserver observer : observers){
            observer.getActionMethods(action);        
        }
    }
    
    public void addKeyListenenr(Object object){
        KeyObserver observer = new KeyObserver(object);
        observers.add(observer);
    }       
}
