/**
 * @author Kuang Han
 */

package charactersprites;

import com.golden.gamedev.Game;

import physicsengine.NewtonianSprite;

@SuppressWarnings("serial")
public class Brick extends NewtonianSprite implements GameElement{
    protected Game myGame;
    
    public Brick(Game game) {
        super();
        myGame = game;
        this.setMovable(false);
    }

    public void setGame(Game game) {
        myGame = game;
    }

    public Game getGame() {
        return myGame;
    }
}
