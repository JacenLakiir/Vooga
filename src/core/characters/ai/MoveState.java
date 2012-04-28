package core.characters.ai;

import core.characters.Character;

/**
 * @author Eric Mercer (JacenLakiir)
 */
public class MoveState extends State
{
    
    /**
     * 
     */
    private static final long serialVersionUID = 7007814870157012084L;
    private double mySpeed;
        
    public MoveState (Character character, double speed, boolean isMovingLeft)
    {
        super(character);
        mySpeed = speed;
        myCharacter.setDirection(isMovingLeft ? -1 : 1);
    }
    
    @Override
    public boolean areConditionsMet ()
    {
        return true;
    }
    
    @Override
    public void execute (long milliSec)
    {
        myCharacter.move(myCharacter.getDirection() * mySpeed, 0);
    }

}
