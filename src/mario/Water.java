/**
 * @author Kuang Han
 */

package mario;

import com.golden.gamedev.Game;

import characterSprites.Brick;

@SuppressWarnings("serial")
public class Water extends Brick{
    private double strengthForMario;

    public Water(Game game) {
        super(game);
        strengthForMario = 0.2;
    }
    
    public double getStrengthForMario() {
        return strengthForMario;
    }

}
