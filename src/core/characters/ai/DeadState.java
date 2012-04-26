package core.characters.ai;

import core.characters.Character;

/**
 * @author Eric Mercer (JacenLakiir)
 */
public class DeadState extends State
{
    
    public DeadState (Character character)
    {
        super(character);
    }
    
    @Override
    public boolean areConditionsMet ()
    {
        return true;
    }
    
    @Override
    public void execute (long milliSec)
    {
        myCharacter.deactivateAllOtherStates(this);
        myCharacter.setActive(!isActivated);
    }

}
