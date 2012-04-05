package keyconfiguration;

import charactersprites.Player;

import com.golden.gamedev.Game;
/**
 * 
 * @author Hui Dong
 *
 */
public class SequentialInputKey extends Key{
    private static long LATENCY = 1000;
    private long lastUpdate = 0;
    private boolean[] iskeyPressed;
    private  String[] values;
    private boolean isInitial  = true;
    
    public SequentialInputKey(String value, String actionName, Player player,
            Game game) {
        super(value, actionName, player, game);
        values = keyValue.split(",");
        iskeyPressed = new boolean[values.length];
        invalidate();
        observer = new InputKeyObserver(player);
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
}
