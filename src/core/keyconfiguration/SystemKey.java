package core.keyconfiguration;


import com.golden.gamedev.Game;
import com.golden.gamedev.GameEngine;

import core.characters.Player;
/**
 * 
 * @author Hui Dong
 *
 */
public class SystemKey extends Key{

    private Game myGame;
    
    public SystemKey(String value, String actionName, Game game) {
        keyValue = value;
        action = actionName;
        myGame = game;        
        observer = new SystemKeyObserver(game);
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
