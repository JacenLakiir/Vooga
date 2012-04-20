package core.characters.ai;

import core.characters.NPC;

/**
 * @author Eric Mercer (JacenLakiir)
 */
public abstract class State
{
    protected NPC myNPC;
    protected boolean isActivated;
    
    public State (NPC npc)
    {
        myNPC = npc;
        isActivated = true;
    }
    
    public abstract void execute (long milliSec);
    
    public abstract boolean areConditionsMet ();
    
    public boolean isActive ()
    {
        return (isActivated && areConditionsMet());
    }
    
    public void setActive (boolean active)
    {
        isActivated = active;
    }
    
}
