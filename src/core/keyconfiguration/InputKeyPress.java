package core.keyconfiguration;

import com.golden.gamedev.GameObject;
/**
 * 
 * @author Hui Dong
 *
 */
public class InputKeyPress extends Key{

    public InputKeyPress(String value, String actionName, GameObject game) {
        super(value, actionName, game);
    }

    @Override
    public boolean isKeyDown(long milliSec) {
        return getMyGame().keyPressed(Integer.parseInt(keyValue));
    }
}
