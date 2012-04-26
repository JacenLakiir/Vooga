package core.characters.ai;

import com.golden.gamedev.object.Timer;
import core.characters.Character;

public class JumpState extends State
{
    
    private double myJumpStrength;
    private int myJumpTime;
    private Timer myJumpTimer;

    public JumpState (Character character, double jumpStrength, int jumpTime)
    {
        super(character);
        myJumpStrength = jumpStrength;
        myJumpTime = jumpTime;
        myJumpTimer = new Timer(myJumpTime);
        myJumpTimer.setActive(false);
    }

    @Override
    public boolean areConditionsMet ()
    {
        return true;
    }

    @Override
    public void execute (long milliSec)
    {
        if (myJumpTimer.action(milliSec))
            myJumpTimer.setActive(false);
        else
        {
            if (myJumpTimer.isActive() == false)
            {
                myJumpTimer.setActive(true);
                myJumpTimer.refresh();
            }
            myCharacter.addAcceleration(0, myJumpStrength * myCharacter.getPhysicsAttribute().getGravitationalAcceleration());
        }
    }

}
