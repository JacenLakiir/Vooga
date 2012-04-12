package core.keyconfiguration;

import core.characters.GameElement;
import com.golden.gamedev.GameObject;
/**
 * 
 * @author Hui Dong
 *
 */
public class SequentialInputKey extends Key{
    private static long LATENCY = 400;
    private long lastUpdate = 0;
    private boolean[] iskeyPressed;
    private  String[] values;
    private boolean isInitial  = true;
    private GameObject myGame;    
    public SequentialInputKey(String value, String actionName, GameElement element,
            GameObject game) {
        keyValue = value;
        action = actionName;
        myGame = game;
        values = keyValue.split(",");
        iskeyPressed = new boolean[values.length];
        invalidate();
        observer = new KeyObserver(element);
    }
  
    
    private void invalidate() {
        isInitial = true;
        for(int i = 0; i < iskeyPressed.length; i++){
            iskeyPressed[i] = false;
        }
    }
    
    public boolean isKeyDown(long milliSec){
        for(int i = 0; i < values.length; i++){

            if(myGame.bsInput.isKeyPressed(Integer.parseInt(values[i]))){
                iskeyPressed[i] = true;                
                if(isInitial){
                    lastUpdate = milliSec;
                    isInitial = false;
                }
                if((milliSec - lastUpdate) > LATENCY){
                    invalidate();
                    return false;
                }

                lastUpdate = milliSec;
                if(i > 0){
                    for(int j = 0; j < i; j++){
                        if(!iskeyPressed[j]){
                            invalidate();
                            return false;
                        }
                    }
                }
            }
        }
        boolean keyDown = true;
        for(boolean valid : iskeyPressed){
            keyDown &= valid;
        }
        if(keyDown){
            invalidate();
            return true;
        }else{
            return false;
        }
        
    }


    @Override
    public void notifyObserver() {
        observer.getActoinMethods(action);
    }
}
