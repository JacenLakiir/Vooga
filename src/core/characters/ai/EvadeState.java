package core.characters.ai;

import core.characters.Character;

/**
 * @author ericmercer (JacenLakiir)
 */
public class EvadeState extends State
{

    private Character beingAvoided;
    private double mySpeed;
    private double myEvadeRange;
    
    public EvadeState (Character character, Character toAvoid, double speed, double evadeRange)
    {
        super(character);
        beingAvoided = toAvoid;
        mySpeed = speed;
        myEvadeRange = evadeRange;
    }

    @Override
    public void execute (long milliSec)
    {
        double horizontalDistance = beingAvoided.getX() - myCharacter.getX();
        int directionToFlee = (horizontalDistance < 0) ? 1 : -1;
        myCharacter.move(directionToFlee * mySpeed, 0);
    }

    @Override
    public boolean areConditionsMet ()
    {
        return (myCharacter.getDistance(beingAvoided) <= myEvadeRange);
    }
    
}