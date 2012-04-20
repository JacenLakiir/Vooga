package core.characters.ai;

import core.characters.NPC;

/**
 * @author Eric Mercer (JacenLakiir)
 */
public class DeadState extends State
{
    
    public DeadState (NPC npc)
    {
        super(npc);
    }
    
    @Override
    public void execute (long milliSec)
    {
        myNPC.setActive(false);
    }
    
    @Override
    public boolean isActive ()
    {
        return true;
    }

}
