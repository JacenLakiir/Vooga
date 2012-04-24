package core.characters.ai;

import core.characters.Character;

/**
 * @author ericmercer (JacenLakiir)
 */
public class FollowState extends State
{

    private Character beingFollowed;
    private double mySpeed;
    private double myFollowRange;
    
    public FollowState (Character character, Character toFollow, double speed, double followRange)
    {
        super(character);
        beingFollowed = toFollow;
        mySpeed = speed;
        myFollowRange = followRange;
    }

    @Override
    public void execute (long milliSec)
    {
        double horizontalDistance = beingFollowed.getX() - myCharacter.getX();
        int directionOfTarget = (horizontalDistance < 0) ? -1 : 1;
        myCharacter.move(directionOfTarget * mySpeed, 0);
    }

    @Override
    public boolean areConditionsMet ()
    {
        return (myCharacter.getDistance(beingFollowed) <= myFollowRange);
    }
    
}
