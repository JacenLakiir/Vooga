package core.characters.ai;

import com.golden.gamedev.object.Timer;
import core.characters.Character;

public class JumpState extends State
{
    
    private double myJumpStrength;
    private int myJumpTime;
    private Timer myJumpTimer;
    private boolean canJump;

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
        {
            System.out.println("IF");
            myJumpTimer.setActive(false);
            canJump = false;
        }
        if (canJump)
        {
            System.out.println("ELSE");
            if (myJumpTimer.isActive() == false)
            {
                System.out.println("REFRESH");
                myJumpTimer.setActive(true);
                myJumpTimer.refresh();
            }
            myCharacter.addAcceleration(0, myJumpStrength * myCharacter.getPhysicsAttribute().getGravitationalAcceleration());
        }
    }
    
    public void notifyHitFromBottom ()
    {
        System.out.println("HIT");
        canJump = true;
        myJumpTimer.setActive(false);
    }
}
