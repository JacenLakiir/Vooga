package demo.custom;

import core.characters.ai.State;

public class ShellState extends State
{
    
    private static final String SHELL_IMAGE_FILE = "resources/KoopaShell.png";
    
    private double mySpeed;
    private boolean hasBeenHitFromTopByMario;
        
    public ShellState (Koopa koopa)
    {
        super(koopa);
        mySpeed = 0;
        hasBeenHitFromTopByMario = false;
    } 
    
    @Override
    public void execute (long milliSec)
    {
        myCharacter.move(myCharacter.getDirection() * mySpeed, 0);
    }
    
    @Override
    public boolean areConditionsMet ()
    {
        return hasBeenHitFromTopByMario;
    }
    
    public double getSpeed ()
    {
        return mySpeed;
    }
    
    public void setSpeed (double speed)
    {
        mySpeed = speed;
    }
    
    public void notifyHitFromTopByMario ()
    {
        if (!hasBeenHitFromTopByMario)
        {
            myCharacter.setImages(myCharacter.getGame().getImages(SHELL_IMAGE_FILE, 1, 1));
            myCharacter.deactivateAllOtherStates(this);
        }
        hasBeenHitFromTopByMario = true;
    }

}
