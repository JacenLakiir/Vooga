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
        if (e instanceof Player)
            setCurrentState(new DeadState(this));
    }
    
    @Override
    public void afterHitFromRightBy (GameElement e)
    {
        if (e instanceof Koopa)
            handleKoopaSideCollision((Koopa) e);
        setDirection(-1);
    }
    
    @Override
    public void afterHitFromLeftBy (GameElement e)
    {
        if (e instanceof Koopa)
            handleKoopaSideCollision((Koopa) e);
        setDirection(1);
    }
    
    private void handleKoopaSideCollision (Koopa k)
    {
        if (k.isInShellState() && k.getShellSpeed() != 0)
            setActive(false);
    }
    
}
