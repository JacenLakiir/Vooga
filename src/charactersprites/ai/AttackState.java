package charactersprites.ai;

import charactersprites.NPC;

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
        // not implemented properly yet
        myNPC.fight();
    }

    @Override
    public void determineNextState ()
    {
        // can switch to dead or previous state (patrol or stationary)        
    }
    
}
