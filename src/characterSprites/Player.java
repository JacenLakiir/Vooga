package characterSprites;

import java.awt.event.KeyEvent;

import com.golden.gamedev.Game;


@SuppressWarnings("serial")
public class Player extends Character{
    Game myGame;
    
    public Player(Game game) {
        myGame = game;
    }
    
    @Override
    public void update(long milliSec) {
        if (this.getX()<0 || this.getX()>640 || this.getY()>500) {
            System.out.println("Dead");
            myGame.stop();
            return;
        }
        if (myGame.keyDown(KeyEvent.VK_UP)) {
            this.addAcceleration(0, 2*stdGravity);
        }
        if (myGame.keyDown(KeyEvent.VK_LEFT)) {
            this.addAcceleration(-0.5*stdGravity, 0);
        }
        if (myGame.keyDown(KeyEvent.VK_RIGHT)) {
            this.addAcceleration(0.5*stdGravity, 0);
        }
        super.update(milliSec);
    }


}
