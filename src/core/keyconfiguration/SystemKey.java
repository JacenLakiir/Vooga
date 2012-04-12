package core.keyconfiguration;

import com.golden.gamedev.GameObject;
/**
 * 
 * @author Hui Dong
 *
 */
public class SystemKey extends Key{

    private GameObject myGame;
    
    public SystemKey(String value, String actionName, GameObject game) {
        keyValue = value;
        action = actionName;
        myGame = game;        
        observer = new KeyObserver(game);
    }

    @Override
    public boolean isKeyDown(long milliSec) {
        return myGame.keyPressed(Integer.parseInt(keyValue));
    }

    @Override
    public void notifyObserver() {
        observer.getActoinMethods(action);

    }

}
