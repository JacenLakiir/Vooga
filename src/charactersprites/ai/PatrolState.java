package charactersprites.ai;

import charactersprites.NPC;

/**
 * @author Eric Mercer (JacenLakiir)
 */
public class PatrolState implements State
{

    private NPC myNPC;
    
    private double myPatrolRadius;
    private Double myStartX;
    private Double myStartY;
    
    public PatrolState (NPC npc, double patrolRadius)
    {
        myNPC = npc;
        myPatrolRadius = patrolRadius;
    }

    public void execute (long milliSec)
    {
        storeStartLocation();
        updateDirection();
        myNPC.move(myNPC.getDirection(), 0);
    }
    
    @Override
    public boolean isActive ()
    {
        return (myNPC.getY() == myNPC.getOldY());
    }

    private void updateDirection ()
    {
        int dir = myNPC.getDirection();
        
        if (dir == -1 && myNPC.getX() < myStartX - myPatrolRadius)
        {
            myNPC.setDirection(1);
        }
        else if (dir == 1 && myNPC.getX() > myStartX + myPatrolRadius)
        {
            myNPC.setDirection(-1);
        }
    }

    private void storeStartLocation ()
    {
        if (myStartX == null || myStartY == null)
        {
            System.out.println("Location stored");
            myStartX = myNPC.getX();
            myStartY = myNPC.getY();
        }
    }

}