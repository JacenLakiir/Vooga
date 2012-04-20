package core.characters.ai;

import core.characters.NPC;
import core.characters.Character;

public class EvadeState extends State
{

    private Character beingAvoided;
    private double mySpeed;
    private double myEvadeRange;
    
    public EvadeState (NPC npc, Character toAvoid, double speed, double evadeRange)
    {
        super(npc);
        beingAvoided = toAvoid;
        mySpeed = speed;
        myEvadeRange = evadeRange;
    }

    @Override
    public void execute (long milliSec)
    {
        double horizontalDistance = beingAvoided.getX() - myNPC.getX();
        int directionToFlee = (horizontalDistance < 0) ? 1 : -1;
        myNPC.move(directionToFlee * mySpeed, 0);
    }

    @Override
    public boolean areConditionsMet ()
    {
        return (myNPC.getDistance(beingAvoided) <= myEvadeRange);
    }
    
}