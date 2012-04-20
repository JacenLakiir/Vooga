package core.characters.ai;

import core.characters.NPC;

/**
 * @author Eric Mercer (JacenLakiir)
 */
public abstract class State
{
    protected NPC myNPC;
    protected boolean isActive;
    
    public State (NPC npc)
    {
        myNPC = npc;
    }
    
    public abstract void execute (long milliSec);
    
    public abstract boolean isActive ();
    
    public void setActive (boolean active)
    {
        isActive = active;
    }
    
}
