package keyconfiguration;

import charactersprites.Player;

import com.golden.gamedev.Game;
/**
 * 
 * @author Hui Dong
 *
 */
public class SingleInputKey extends Key{
    public SingleInputKey(String value, String actionName, Player player,
            Game game) {
        super(value, actionName, player, game);
        observer = new InputKeyObserver(player);
    }

    public boolean isKeyDown(long milliSec){
        return myGame.keyDown(Integer.parseInt(keyValue));
    }
}
