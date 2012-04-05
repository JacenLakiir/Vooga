package charactersprites.ai;

import charactersprites.NPC;

/**
 * @author Eric Mercer (JacenLakiir)
 */
public class StationaryState implements State
{

    private NPC myNPC;
    
    public StationaryState (NPC npc)
    {
        myNPC = npc;
    }

    public void execute (long milliSec)
    {
        // stationary, does not do anything
    }

    @Override
    public void determineNextState ()
    {
        // can switch to attack or dead        
    }
}
