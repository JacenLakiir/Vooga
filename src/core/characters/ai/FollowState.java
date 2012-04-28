package core.characters.ai;

import core.characters.Character;

/**
 * @author ericmercer (JacenLakiir)
 */
public class FollowState extends State
{

    /**
     * 
     */
    private static final long serialVersionUID = -5728783313061000178L;
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
    public boolean areConditionsMet ()
    {
        return (myCharacter.getDistance(beingFollowed) <= myFollowRange);
    }

    @Override
    public void execute (long milliSec)
    {
        double horizontalDistance = beingFollowed.getX() - myCharacter.getX();
        int directionOfTarget = (horizontalDistance < 0) ? -1 : 1;
        myCharacter.move(directionOfTarget * mySpeed, 0);
    }
    
}
