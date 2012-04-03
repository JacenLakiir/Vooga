/**
 * @author Kuang Han
 */

package charactersprites;

import com.golden.gamedev.Game;
import physicsengine.NewtonianSprite;




@SuppressWarnings("serial")
public abstract class Character extends NewtonianSprite implements GameElement{
    protected Game myGame;
    
    public Character(Game game) {
        super();
        myGame = game;
    }
    
    @Override
    public void update(long milliSec) {
        super.update(milliSec);
    }
    
    public void setGame(Game game) {
        myGame = game;
    }

    public Game getGame() {
        return myGame;
    }
}
