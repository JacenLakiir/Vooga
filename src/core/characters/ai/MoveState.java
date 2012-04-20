package core.characters.ai;

import core.characters.NPC;

/**
 * @author Eric Mercer (JacenLakiir)
 */
public class MoveState extends State
{
    
    private double mySpeed;
        
    public MoveState (NPC npc, double speed, boolean isMovingLeft)
    {
        super(npc);
        mySpeed = speed;
        myNPC.setDirection(isMovingLeft ? -1 : 1);
    }
    
    @Override
    public void execute (long milliSec)
    {
        myNPC.move(myNPC.getDirection() * mySpeed, 0);
    }
    
    @Override
    public boolean isActive ()
    {
        return true;
    }
    
}
