package core.characters.ai;

import com.golden.gamedev.object.Timer;
import core.characters.Character;

public class JumpState extends State
{
    
    /**
     * 
     */
    private static final long serialVersionUID = -6350855638094972957L;
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
            myJumpTimer.setActive(false);
            canJump = false;
        }
        if (canJump)
        {
            if (myJumpTimer.isActive() == false)
            {
                myJumpTimer.setActive(true);
                myJumpTimer.refresh();
            }
            myCharacter.addAcceleration(0, myJumpStrength * myCharacter.getPhysicsAttribute().getGravitationalAcceleration());
        }
    }
    
    public void notifyHitFromBottom ()
    {
        canJump = true;
        myJumpTimer.setActive(false);
    }
}
