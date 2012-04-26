package core.characters.ai;

import core.characters.Character;

/**
 * @author Eric Mercer (JacenLakiir)
 */
public class PatrolState extends State
{
    
    private double myPatrolRange;
    private double mySpeed;
    private double myDistanceTraveled;
    
    public PatrolState (Character character, double speed, double patrolRange)
    {
        super(character);
        mySpeed = speed;
        myPatrolRange = patrolRange;
        myDistanceTraveled = 0;
    }
    
    @Override
    public boolean areConditionsMet ()
    {
        return true;
    }

    @Override
    public void execute (long milliSec)
    {
        if (myDistanceTraveled >= myPatrolRange)
            updateDirection();
        myCharacter.move(myCharacter.getDirection() * mySpeed, 0);
        myDistanceTraveled += Math.abs(mySpeed);
    }

    private void updateDirection()
    {
    	int dir = myCharacter.getDirection();
    	if (dir == -1)
    	    myCharacter.setDirection(1);
    	else if (dir == 1)
    	    myCharacter.setDirection(-1);
    	myDistanceTraveled = 0;
    }

}