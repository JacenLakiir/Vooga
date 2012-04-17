package core.keyconfiguration;

import com.golden.gamedev.GameObject;
/**
 * 
 * @author Hui Dong
 *
 */
public class SystemKey extends Key{

    public SystemKey(String value, String actionName, GameObject game) {
        super(value, actionName, game);
    }

    @Override
    public boolean isKeyDown(long milliSec) {
        return myGame.keyPressed(Integer.parseInt(keyValue));
    }

    @Override
    public void notifyObserver() {
        observer.getActionMethods(action);
    }

}
