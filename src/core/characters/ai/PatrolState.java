package core.characters.ai;

import core.characters.NPC;

/**
 * @author Eric Mercer (JacenLakiir)
 */
public class PatrolState implements State
{

    private NPC myNPC;
    
    private double myPatrolRadius;
    private double mySpeed;
    private double myDistanceTraveled;
    
    public PatrolState (NPC npc, double speed, double patrolRadius)
    {
        myNPC = npc;
        mySpeed = speed;
        myPatrolRadius = patrolRadius;
        myDistanceTraveled = 0;
    }

    public void execute (long milliSec)
    {
        if (myDistanceTraveled >= myPatrolRadius)
            updateDirection();
        myNPC.move(myNPC.getDirection() * mySpeed, 0);
        myDistanceTraveled += Math.abs(mySpeed);
    }
    
    
    public boolean isActive ()
    {
        return true;
    }

    private void updateDirection ()
    {
        int dir = myNPC.getDirection();
        if (dir == -1)
            myNPC.setDirection(1);
        else if (dir == 1)
            myNPC.setDirection(-1);
        myDistanceTraveled = 0;
    }

}