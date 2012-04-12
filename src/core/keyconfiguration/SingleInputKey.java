package core.keyconfiguration;

import core.characters.GameElement;
import com.golden.gamedev.GameObject;
/**
 * 
 * @author Hui Dong
 *
 */
public class SingleInputKey extends Key{
    private GameObject myGame;   
    public SingleInputKey(String value, String actionName, GameElement element,
            GameObject game) {
        keyValue = value;
        action = actionName;
        myGame = game;        
        observer = new KeyObserver(element);
    }

    public boolean isKeyDown(long milliSec){
        return myGame.keyDown(Integer.parseInt(keyValue));
    }

    @Override
    public void notifyObserver() {
        observer.getActoinMethods(action);        
    }
}
