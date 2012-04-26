package core.characters.ai;

import core.characters.Character;

public class HomingState extends State
{
    
    private Character beingTracked;
    private double myHomingRange;
    private double mySpeed;

    public HomingState (Character character, Character toTrack, double homingRange, double speed)
    {
        super(character);
        beingTracked = toTrack;
        myHomingRange = homingRange;
        mySpeed = speed;
    }

    @Override
    public boolean areConditionsMet ()
    {
        return (myCharacter.getDistance(beingTracked) <= myHomingRange);
    }

    @Override
    public void execute (long milliSec)
    {   
        myCharacter.getPhysicsAttribute().setMovable(false);
        int dirX = beingTracked.getX() < myCharacter.getX() ? -1 : 1;
        int dirY = beingTracked.getY() < myCharacter.getY() ? -1 : 1;
        myCharacter.move(dirX * mySpeed, dirY * mySpeed);
    }

}
