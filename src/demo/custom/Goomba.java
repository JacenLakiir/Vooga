package demo.custom;

import java.util.List;
import com.golden.gamedev.GameObject;
import core.characters.GameElement;
import core.characters.NPC;
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
    
    public void afterHitFromRightBy (GameElement e)
    {
        setDirection(-1);
    }
    
    public void afterHitFromLeftBy (GameElement e)
    {
        setDirection(1);
    }
    
    public void afterHitFromTopBy (Mario e) {
        setCurrentState(new DeadState(this));
    }
    
    public void afterHitFromRightBy (Koopa k)
    {
        handleKoopaSideCollision(k, true);
    }
    
    public void afterHitFromLeftBy (Koopa k)
    {
        handleKoopaSideCollision(k, false);
    }
    
    private void handleKoopaSideCollision (Koopa k, boolean isMovingLeft)
    {
        if (k.isInShellState() && k.getShellSpeed() != 0)
            setActive(false);
        setDirection(isMovingLeft ? -1 : 1);
    }
    
}
