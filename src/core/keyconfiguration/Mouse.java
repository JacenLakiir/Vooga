package core.keyconfiguration;

import java.util.ArrayList;
import java.util.List;

import com.golden.gamedev.GameObject;
public abstract class Mouse {
    private List<KeyObserver> observers;
    
    private GameObject myGame;
    private String action;
    
    public void setAction(String mAction){
        action = mAction;
    }
    
    public Mouse(GameObject game, String action){
        myGame = game;
        observers = new ArrayList<KeyObserver>();
        this.action = action;
    }

    public GameObject getGame(){
        return myGame;
    }
    
    public void notifyObserver(){
        for(KeyObserver observer : observers){
            observer.getActionMethods(action);
        }
    }
    
    
    public void addMouseListenenr(Object object){
        KeyObserver observer = new KeyObserver(object);
        observers.add(observer); 
    }    
    public abstract boolean  isMouseClicked();
}
