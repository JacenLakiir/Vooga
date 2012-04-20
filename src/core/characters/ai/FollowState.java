package core.characters.ai;

import core.characters.NPC;
import core.characters.Character;

public class FollowState extends State
{

    private Character beingFollowed;
    private double mySpeed;
    private double myFollowRange;
    
    public FollowState (NPC npc, Character toFollow, double speed, double followRange)
    {
        super(npc);
        beingFollowed = toFollow;
        mySpeed = speed;
        myFollowRange = followRange;
    }

    @Override
    public void execute (long milliSec)
    {
        double horizontalDistance = beingFollowed.getX() - myNPC.getX();
        int directionOfTarget = (horizontalDistance < 0) ? -1 : 1;
        myNPC.move(directionOfTarget * mySpeed, 0);
    }

    @Override
    public boolean areConditionsMet ()
    {
        return (myNPC.getDistance(beingFollowed) <= myFollowRange);
    }
    
}
