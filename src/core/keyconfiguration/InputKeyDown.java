package core.keyconfiguration;

import com.golden.gamedev.GameObject;
/**
 * 
 * @author Hui Dong
 *
 */
public class InputKeyDown extends Key{
    public InputKeyDown(String value, String actionName, GameObject game) {
        super(value, actionName, game);
    }

    public boolean isKeyDown(long milliSec){
        return getMyGame().keyDown(Integer.parseInt(keyValue));
    }
}
