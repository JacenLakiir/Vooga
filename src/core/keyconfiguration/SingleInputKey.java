package core.keyconfiguration;

import com.golden.gamedev.GameObject;
/**
 * 
 * @author Hui Dong
 *
 */
public class SingleInputKey extends Key{
    public SingleInputKey(String value, String actionName, GameObject game) {
        super(value, actionName, game);
    }

    public boolean isKeyDown(long milliSec){
        return myGame.keyDown(Integer.parseInt(keyValue));
    }
}
