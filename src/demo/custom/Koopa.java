package demo.custom;

import com.golden.gamedev.GameObject;
import core.characters.Character;
import core.characters.GameElement;
import core.characters.ai.DeadState;
import core.physicsengine.physicsplugin.PhysicsAttributes;
import demo.custom.ShellState;

/**
 * @author Eric Mercer (JacenLakiir)
 */
@SuppressWarnings("serial")
public class Koopa extends Character {

    private static final String IMAGE_FILE = "resources/Koopa.png";

    private ShellState myShellState;

    public Koopa(GameObject game, PhysicsAttributes physicsAttribute) {
        super(game, physicsAttribute);
        addDefaultBaseAttributeEntry("hitPoints", 1);
        setTag("Koopa");
        setImages(game.getImages(IMAGE_FILE, 1, 1));
        getPhysicsAttribute().setMovable(true);
        myShellState = new ShellState(this);
        addPossibleState("Shell", myShellState);
    }

    @Override
    public void afterHitFromLeftBy(GameElement e, String tag) {
        if (tag.equals("Mario")) {
            handleMarioCollision((Mario)e, true);
        }
        else if (tag.equals("Goomba")) {
            setDirection(myShellState.isActive() ? getDirection() : -1
                    * getDirection());
        }
        else if (tag.equals("Koopa")) {
            handleKoopaCollision((Koopa)e, true);
        }
        else {
            setDirection(1);
        }
    }

    @Override
    public void afterHitFromRightBy(GameElement e, String tag) {
        if (tag.equals("Mario")) {
            handleMarioCollision((Mario)e, false);
        }
        else if (tag.equals("Goomba")) {
            setDirection(myShellState.isActive() ? getDirection() : -1
                    * getDirection());
        }
        else if (tag.equals("Koopa")) {
            handleKoopaCollision((Koopa)e, false);
        }
        else {
            setDirection(-1);
        }
    }

    @Override
    public void afterHitFromTopBy(GameElement e, String tag) {
        if (tag.equals("Mario")) {
            myShellState.notifyHitFromTopByMario();
            myShellState.setSpeed(0);
        }
    }

    public double getShellSpeed() {
        return myShellState.getSpeed();
    }

    public void setShellSpeed(double speed) {
        myShellState.setSpeed(speed);
    }

    public boolean isInShellState() {
        return myShellState.isActive();
    }
    
    public static void handleSideCollision(Character c, Koopa k, boolean isHitOnLeft) {
        if (k.isInShellState() && k.getShellSpeed() != 0)
            c.addPossibleState("Dead", new DeadState(c));
        else
            c.setDirection(isHitOnLeft ? 1 : -1);
    }

    private void handleMarioCollision(Mario m, boolean isThisHitOnLeft) {
        boolean isInShell = myShellState.isActive();
        if (isInShell && getShellSpeed() == 0) {
            myShellState.setSpeed(50 * Math.abs(m.getVelocity().getX()));
            setDirection(isThisHitOnLeft ? 1 : -1);
        } else if (isInShell && getShellSpeed() != 0) {
            m.updateAttributeValue("hitPoints", -1 * m.getAttributeValue("hitPoints"));
        } else if (!isInShell) {
            m.updateAttributeValue("hitPoints", -1 * m.getAttributeValue("hitPoints"));
        }
    }

    private void handleKoopaCollision(Koopa k, boolean isThisHitOnLeft) {
        if (myShellState.isActive()) {
            if (k.isInShellState() && k.getShellSpeed() != 0) {
                setShellSpeed(50 * Math.abs(k.getVelocity().getX()));
                setDirection(-1 * getDirection());
            } else if (k.isInShellState() && k.getShellSpeed() == 0)
                k.addPossibleState("Dead", new DeadState(this));
        } else {
            if (k.getShellSpeed() != 0)
                k.addPossibleState("Dead", new DeadState(this));
            else
                setDirection(isThisHitOnLeft ? 1 : -1);
        }
    }

}