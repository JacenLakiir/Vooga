package core.configuration.mouse;

import java.util.ArrayList;
import java.util.List;

import com.golden.gamedev.GameObject;

import core.configuration.key.InputObserver;
public abstract class Mouse {
    private List<InputObserver> observers;
    
    private GameObject myGame;
    private String action;
    
    public void setAction(String mAction){
        action = mAction;
    }
    
    public Mouse(GameObject game, String action){
        myGame = game;
        observers = new ArrayList<InputObserver>();
        this.action = action;
    }

    public GameObject getGame(){
        return myGame;
    }
    
    public void notifyObserver(){
        for(InputObserver observer : observers){
            observer.getActionMethods(action);
        }
    }
    
    
    public void addMouseListenenr(Object object){
        InputObserver observer = new InputObserver(object);
        observers.add(observer); 
    }    
    public abstract boolean  isMouseClicked();
}
