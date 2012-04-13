package demo;

import core.characters.ai.State;

public class ShellState implements State
{

    private Koopa myKoopa;
    
    private double mySpeed;
        
    public ShellState (Koopa koopa)
    {
        myKoopa = koopa;
        mySpeed = 0;
    } 
    
    public void execute (long milliSec)
    {
        myKoopa.move(myKoopa.getDirection() * mySpeed, 0);
    }
    
    public boolean isActive ()
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
