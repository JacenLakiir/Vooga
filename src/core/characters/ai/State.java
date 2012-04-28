package core.characters.ai;

import core.characters.Character;

/**
 * @author Eric Mercer (JacenLakiir)
 */
public abstract class State implements java.io.Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 7591491430198918097L;
    protected Character myCharacter;
    private boolean isActivated;
    
    public State (Character character)
    {
        myCharacter = character;
        isActivated = true;
    }
    
    public abstract boolean areConditionsMet ();
    
    public abstract void execute (long milliSec);
        
    public boolean isActive ()
    {
        return (isActivated && areConditionsMet());
    }
    
    public void setActive (boolean active)
    {
        isActivated = active;
    }
    
}
