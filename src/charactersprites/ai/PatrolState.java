package charactersprites.ai;

import charactersprites.NPC;

/**
 * @author Eric Mercer (JacenLakiir)
 */
public class PatrolState implements State
{

    private NPC myNPC;
    
    public PatrolState (NPC npc)
    {
        myNPC = npc;
    }

    public void execute (long milliSec)
    {
        if (myNPC.getVelocity().getY() != 0)   return;
        
        int dir = myNPC.getDirection();
        if (dir == -1)
        {
            if (myNPC.getX() < myNPC.getStartX() - myNPC.getPatrolRadius())
                myNPC.setDirection(1);
        }
        else if (dir == 1)
        {
            if (myNPC.getX() > myNPC.getStartX() + myNPC.getPatrolRadius())
                myNPC.setDirection(-1);
        }
        myNPC.move(myNPC.getDirection(), 0);
    }

    @Override
    public void determineNextState ()
    {
        // can switch to attack or dead
    }
}
