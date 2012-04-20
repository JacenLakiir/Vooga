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
        myNPC.move(myNPC.getDirection() * mySpeed, 0);
    }
    
    @Override
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
