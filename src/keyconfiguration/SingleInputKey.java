package keyconfiguration;

import charactersprites.GameElement;
import charactersprites.Player;

import com.golden.gamedev.Game;
/**
 * 
 * @author Hui Dong
 *
 */
public class SingleInputKey extends Key{
    private Game myGame;   
    private GameElement element;
    public SingleInputKey(String value, String actionName, GameElement element,
            Game game) {
        keyValue = value;
        action = actionName;
        myGame = game;        
        observer = new InputKeyObserver(element);
    }

    public boolean isKeyDown(long milliSec){
        return myGame.keyDown(Integer.parseInt(keyValue));
    }

    @Override
    public void notifyObserver() {
        observer.getActoinMethods(action);        
    }
}
