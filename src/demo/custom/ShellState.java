package demo.custom;

import core.characters.ai.State;

public class ShellState extends State
{
    
    private double mySpeed;
        
    public ShellState (Koopa koopa)
    {
        super(koopa);
        mySpeed = 0;
    } 
    
    @Override
    public void execute (long milliSec)
    {
        myCharacter.move(myCharacter.getDirection() * mySpeed, 0);
    }
    
    @Override
    public boolean areConditionsMet ()
    {
        return true;
    }
    
    public double getSpeed ()
    {
        return mySpeed;
    }
    
    public void setSpeed (double speed)
    {
        mySpeed = speed;
    }

}
