package demo.custom;

import java.util.List;
import com.golden.gamedev.GameObject;
import core.characters.GameElement;
import core.characters.NPC;
import core.characters.Player;
import core.characters.ai.State;
import core.tiles.Tile;
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

    public void afterHitFromTopBy (Player e)
    {
        if (!isInShell)
        {
            setImages(engine.getImages("resources/KoopaShell.png", 1, 1));
            getPossibleStates().clear();
            addPossibleState(myShellState);
            isInShell = true;
        }
        myShellState.setSpeed(0);
    }

    public void afterHitFromRightBy (Koopa e)
    {
        handleKoopaCollision(e, true);
    }
    
    public void afterHitFromRightBy (Player e)
    {
        if (isInShell)
            handlePlayerCollision(e, true);
    }
    
    public void afterHitFromRightBy (Tile e)
    {
        setDirection(-1);
    }

    public void afterHitFromLeftBy (Koopa e)
    {
        handleKoopaCollision(e, false);
    }
    
    public void afterHitFromLeftBy (Player e)
    {
        if (isInShell)
            handlePlayerCollision(e, false);
    }
    
    public void afterHitFromLeftBy (Tile e)
    {
        setDirection(1);
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

    private void handlePlayerCollision (Player p, boolean isMovingLeft)
    {
        myShellState.setSpeed(50*Math.abs(p.getVelocity().getX()));
        setDirection(isMovingLeft ? -1 : 1);
    }

    private void handleKoopaCollision (Koopa k, boolean isMovingLeft)
    {
        if (this.isInShell)
        {
            if (k.isInShellState() && k.getShellSpeed() != 0)
            {
                this.setShellSpeed(50*Math.abs(k.getVelocity().getX()));
                this.setDirection(isMovingLeft ? -1 : 1);
            }
            else if (k.isInShellState() && k.getShellSpeed() == 0)
                k.setActive(false);
        }
        else
        {
            if (k.getShellSpeed() != 0)
                this.setActive(false);
            else
                this.setDirection(isMovingLeft ? -1 : 1);
        }
    }

}