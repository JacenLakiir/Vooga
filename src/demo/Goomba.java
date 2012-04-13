package demo;

import java.util.List;
import com.golden.gamedev.GameObject;
import core.characters.GameElement;
import core.characters.NPC;
import core.characters.Player;
import core.characters.ai.DeadState;
import core.characters.ai.State;


/**
 * @author Eric Mercer (JacenLakiir)
 */
@SuppressWarnings("serial")
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
        super.afterHitFromTopBy(e);
    }
    
    @Override
    public void afterHitFromRightBy (GameElement e)
    {
        super.afterHitFromRightBy(e);
        setDirection(-1);
    }
    
    @Override
    public void afterHitFromLeftBy (GameElement e)
    {
        super.afterHitFromLeftBy(e);
        setDirection(1);
    }
    
    public void afterHitFromTopBy (Mario e) {
        setCurrentState(new DeadState(this));
    }
    
}
