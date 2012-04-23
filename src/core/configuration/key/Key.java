package core.configuration.key;

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
    private String keyType;
    private List<KeyObserver> observers;
    private GameObject myGame;
    private boolean isSystemOnly;
    
    public boolean isSystemOnly(){
        return isSystemOnly;
    }
    
    protected GameObject getMyGame() {
        return myGame;
    }
    
    public void setKeyValue(String keyValue, String action){
        this.keyValue = keyValue;
        this.action = action;
    }

    public abstract boolean isKeyDown(long milliSec);
    
    public void initial(GameObject game, boolean isSystemOnly){
        myGame = game;
        observers = new ArrayList<KeyObserver>();
        this.isSystemOnly = isSystemOnly;
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
