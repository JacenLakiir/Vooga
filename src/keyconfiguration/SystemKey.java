package keyconfiguration;

import charactersprites.Player;

import com.golden.gamedev.Game;
/**
 * 
 * @author Hui Dong
 *
 */
public class SystemKey extends Key{

    public SystemKey(String value, String actionName, Player player, Game game) {
        super(value, actionName, player, game);
    }

    @Override
    public boolean isKeyDown(long milliSec) {
        return false;
    }

}
