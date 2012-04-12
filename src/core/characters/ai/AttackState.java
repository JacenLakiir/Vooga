package core.characters.ai;

import core.characters.NPC;

/**
 * @author Eric Mercer (JacenLakiir)
 */
public class AttackState implements State
{
    private NPC myNPC;
    
    public AttackState (NPC npc)
    {
        myNPC = npc;
    }

    public void execute (long milliSec)
    {
        // not implemented yet
        myNPC.fight();
    }

    
    public boolean isActive ()
    {
        return false;
    }
    
}
