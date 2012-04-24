package core.characters.ai;

import core.characters.Character;

/**
 * @author Eric Mercer (JacenLakiir)
 */
public abstract class State
{
    protected Character myCharacter;
    protected boolean isActivated;
    
    public State (Character character)
    {
        myCharacter = character;
        isActivated = true;
    }
    
    public abstract void execute (long milliSec);
    
    public abstract boolean areConditionsMet ();
    
    public boolean isActive ()
    {
        return (isActivated && areConditionsMet());
    }
    
    public void setActive (boolean active)
    {
        isActivated = active;
    }
    
}
