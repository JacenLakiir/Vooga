package charactersprites.ai;

import charactersprites.NPC;

/**
 * @author Eric Mercer (Jacen Lakiir)
 */
public class EvasiveState implements State
{
    
    private NPC myNPC;
    
    private double myEvasionRadius;
    
    public EvasiveState (NPC npc, double evasionRadius)
    {
        myNPC = npc;
        myEvasionRadius = evasionRadius;
    }

    
    public void execute (long milliSec)
    {
        // TODO Auto-generated method stub
    }

    
    public boolean isActive ()
    {
        // TODO Auto-generated method stub
        return false;
    }
    
}
