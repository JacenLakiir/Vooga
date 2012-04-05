package keyconfiguration;

import java.util.ArrayList;

import com.golden.gamedev.Game;

import charactersprites.Player;

/**
 * 
 * @author Hui Dong
 *
 */
public class Key {
    private String keyValue;
    private String action;
    private KeyObserver observer;
    private Game myGame;
    private static long LATENCY = 10;
    private long lastUpdate = 0;
    private boolean[] isValid;
    private  String[] values;
    private boolean isInitial  = true;
    
    public Key(String value, String actionName, Player player, Game game){
        keyValue = value;
        values = keyValue.split(",");
        isValid = new boolean[values.length];
        invalidate();
        action = actionName;
        observer = new KeyObserver(player);
        myGame = game;
    }

    private void invalidate() {
        isInitial = true;
        for(int i = 0; i < isValid.length; i++){
            isValid[i] = false;
        }
    }
    
    public boolean isKeyDown(long milliSec){
   
        if(values.length == 1){
            if (myGame.keyDown(Integer.parseInt(values[0]))){ 
                return true;
            } 
        }
        if(values.length > 1){
            for(int i = 0; i < values.length; i++){
                if(isInitial){
                    lastUpdate = milliSec;
                    isInitial = false;
                }
                if(myGame.keyDown(Integer.parseInt(values[i]))){
                    if((milliSec - lastUpdate) > LATENCY){
                        invalidate();
                        return false;
                    }
                    isValid[i] = true;
                    lastUpdate = milliSec;
                    if(i > 0){
                        for(int j = 0; j < i; j++){
                            if(!isValid[j]){
                                invalidate();
                                return false;
                            }
                        }
                    }
                }
            }
            boolean keyDown = true;
            for(boolean valid : isValid){
                keyDown &= valid;
            }
            if(keyDown){
                invalidate();
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
    
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
