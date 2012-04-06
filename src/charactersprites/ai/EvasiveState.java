package charactersprites.ai;

import charactersprites.NPC;

/**
 * @author Eric Mercer (Jacen Lakiir)
 */
public class EvasiveState implements State
{
    
    private NPC myNPC;
    
    public EvasiveState (NPC npc)
    {
        myNPC = npc;
    }

    @Override
    public void execute (long milliSec)
    {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean isActive ()
    {
        // TODO Auto-generated method stub
        return false;
    }

}
