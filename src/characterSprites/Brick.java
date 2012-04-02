/**
 * @author Kuang Han
 */

package characterSprites;

import com.golden.gamedev.Game;

import physiceEngine.NewtonianSprite;

@SuppressWarnings("serial")
public class Brick extends NewtonianSprite implements GameElement{
    protected Game myGame;
    
    public Brick(Game game) {
        myGame = game;
    }

    public void setGame(Game game) {
        myGame = game;
    }

    public Game getGame() {
        return myGame;
    }
}
