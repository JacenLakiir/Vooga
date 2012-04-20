package core.characters.ai;

import core.characters.NPC;

/**
 * @author Eric Mercer (JacenLakiir)
 */
public class MoveState implements State {

    private NPC myNPC;

    private double mySpeed;

    public MoveState(NPC npc, double speed, boolean isMovingLeft) {
	myNPC = npc;
	mySpeed = speed;
	myNPC.setDirection(isMovingLeft ? -1 : 1);
    }

    public void execute(long milliSec) {
	myNPC.move(myNPC.getDirection() * mySpeed, 0);
    }

    public boolean isActive() {
	return true;
    }

}
