/**
 * @author Kuang Han
 */

package mario;

import charactersprites.Player;

import com.golden.gamedev.Game;

@SuppressWarnings("serial")
public class Mario extends Player{

    public Mario(Game game) {
        super(game);
        resetStrength();    
    }

    @Override
    public void update(long t) {
        super.update(t);
        resetStrength();    
    }

    public void resetStrength() {
        strengthUp = 2;
        strengthDown = 0;
        strengthLeft = strengthRight = 0.5;
    }

    @Override
    public void specialSkill() {        
    }

    @Override
    public void checkDead() {
        if (this.getX()<-10 || this.getX()>650 || this.getY()>500) {
            System.out.println("Dead");
            myGame.stop();
            return;
        }
    }


}
