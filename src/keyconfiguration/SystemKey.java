package keyconfiguration;

import charactersprites.Player;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameEngine;
/**
 * 
 * @author Hui Dong
 *
 */
public class SystemKey extends Key{

    private GameEngine myEngine;
    
    public SystemKey(String value, String actionName, Game game) {
        
    }

    @Override
    public boolean isKeyDown(long milliSec) {
        return myEngine.keyDown(Integer.parseInt(keyValue));
    }

}
