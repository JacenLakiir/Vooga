/**
 * @author Kuang Han
 */

package characterSprites;

import com.golden.gamedev.Game;
import physicsEngine.NewtonianSprite;




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
