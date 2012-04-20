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
        myNPC.deactivateAllOtherStates(this);
        myNPC.setActive(isActivated);
    }
    
    @Override
    public boolean areConditionsMet ()
    {
        return true;
    }

}
