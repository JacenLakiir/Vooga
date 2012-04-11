package mario;

import java.util.List;


import com.golden.gamedev.Game;
import com.golden.gamedev.GameObject;

import charactersprites.GameElement;
import charactersprites.NPC;
import charactersprites.ai.DeadState;
import charactersprites.ai.State;

/**
 * @author Eric Mercer (JacenLakiir)
 */
public class Goomba extends NPC
{

    public Goomba (GameObject game)
    {
        super(game);
    }
    
    public Goomba (GameObject game, List<State> possibleStates)
    {
        super(game, possibleStates);
    }
    
    @Override
    public void afterHitFromTopBy (GameElement e)
    {
        if (e instanceof Mario)
            setCurrentState(new DeadState(this));
    }
    
    @Override
    public void afterHitFromRightBy (GameElement e)
    {
        setDirection(-1);
    }
    
    @Override
    public void afterHitFromLeftBy (GameElement e)
    {
        setDirection(1);
    }
    
}
