package charactersprites.ai;

import charactersprites.NPC;

/**
 * @author Eric Mercer (JacenLakiir)
 */
public class DeadState implements State
{
    private NPC myNPC;
    
    public DeadState (NPC npc)
    {
        myNPC = npc;
    }

    
    public void execute (long milliSec)
    {
        myNPC.setActive(false);
    }

    
    public boolean isActive ()
    {
        return true;
    }
    
}
