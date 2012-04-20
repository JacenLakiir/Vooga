package demo.custom;

import java.util.List;
import com.golden.gamedev.GameObject;
import core.characters.NPC;
import core.characters.ai.DeadState;
import core.characters.ai.State;
import demo.custom.ShellState;

/**
 * @author Eric Mercer (JacenLakiir)
 */
@SuppressWarnings("serial")
public class Koopa extends NPC
{

    private GameObject engine;
    private ShellState myShellState;
    private boolean isInShell;

    public Koopa (GameObject game)
    {
        super(game);
        engine = game;
        myShellState = new ShellState(this);
        isInShell = false;
    }

    public Koopa (GameObject game, List<State> possibleStates)
    {
        super(game, possibleStates);
    }

    public void afterHitFromTopBy (Mario m)
    {
        if (!isInShell)
        {
            setImages(engine.getImages("resources/KoopaShell.png", 1, 1));
            addPossibleState(myShellState);
            deactivateAllOtherStates(myShellState);
            isInShell = true;
        }
        myShellState.setSpeed(0);
    }
    
    
    public void afterHitFromLeftBy (Mario m)
    {
        if (isInShell)
            handleMarioCollision(m, true);
    }
    
    public void afterHitFromRightBy (Mario m)
    {
        if (isInShell)
            handleMarioCollision(m, false);
    }
    
    public void afterHitFromLeftBy (Goomba g)
    {
        setDirection(isInShell ? getDirection() : -1 * getDirection());
    }
    
    public void afterHitFromRightBy (Goomba g)
    {
        setDirection(isInShell ? getDirection() : -1 * getDirection());
    }

    public void afterHitFromLeftBy (Koopa k)
    {
        handleKoopaCollision(k, true);
    }
    
    public void afterHitFromRightBy (Koopa k)
    {
        handleKoopaCollision(k, false);
    }

    public double getShellSpeed ()
    {
        return myShellState.getSpeed();
    }

    public void setShellSpeed (double speed)
    {
        myShellState.setSpeed(speed);
    }

    public boolean isInShellState ()
    {
        return isInShell;
    }

    private void handleMarioCollision (Mario m, boolean isHitOnLeft)
    {
        if (isInShell && getShellSpeed() == 0)
        {
            myShellState.setSpeed(50*Math.abs(m.getVelocity().getX()));
            setDirection(isHitOnLeft ? 1 : -1);
        }
        else if (isInShell && getShellSpeed() != 0)
        {
            m.updateStateValues("hitPoints", -1 * m.getMyStateValue("hitPoints"));
        }
        else if (!isInShell)
        {
            m.updateStateValues("hitPoints", -1 * m.getMyStateValue("hitPoints"));
        }
    }

    private void handleKoopaCollision (Koopa k, boolean isThisHitOnLeft)
    {
        if (isInShell)
        {
            if (k.isInShellState() && k.getShellSpeed() != 0)
            {
                setShellSpeed(50*Math.abs(k.getVelocity().getX()));
                setDirection(-1 * getDirection());
            }
            else if (k.isInShellState() && k.getShellSpeed() == 0)
                k.addPossibleState(new DeadState(this));
        }
        else
        {
            if (k.getShellSpeed() != 0)
                k.addPossibleState(new DeadState(this));
            else
                setDirection(isThisHitOnLeft ? 1 : -1);
        }
    }

}