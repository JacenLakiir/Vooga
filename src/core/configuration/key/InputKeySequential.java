package core.configuration.key;

import com.golden.gamedev.GameObject;

/**
 * 
 * @author Hui Dong
 *
 */
public class InputKeySequential extends Key{
    private static long LATENCY = 400;
    private long lastUpdate = 0;
    private boolean[] iskeyPressed;
    private  String[] values;
    private boolean isInitial  = true;
  
    @Override
    public void initial(GameObject game, boolean isSystemOnly) {
        super.initial(game, isSystemOnly);
        values = keyValue.split(",");
        iskeyPressed = new boolean[values.length];
        invalidate();
    }



    private void invalidate() {
        isInitial = true;
        for(int i = 0; i < iskeyPressed.length; i++){
            iskeyPressed[i] = false;
        }
    }
    
    public boolean isKeyDown(long milliSec){
        for(int i = 0; i < values.length; i++){

            if(getMyGame().bsInput.isKeyPressed(Integer.parseInt(values[i]))){
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
