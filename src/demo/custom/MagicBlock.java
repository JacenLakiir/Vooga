package demo.custom;


import core.characters.GameElement;
import core.items.AutoInUseAutoNotInUseItem;
import core.physicsengine.physicsplugin.PhysicsAttributes;
import demo.DemoAntigravity;

@SuppressWarnings("serial")
public class MagicBlock extends AutoInUseAutoNotInUseItem{

    public MagicBlock(PhysicsAttributes physicsAttribute) {
        super(physicsAttribute);
        setTag("MagicBlock");
        this.getPhysicsAttribute().setPenetrable(false);
    }

    @Override
    public void afterHitFromBottomBy(GameElement e, String tag) {
        if (tag.equals("Mario")) {
            triggerAntigravity();
        }
    }

    @Override
    public void afterHitFromLeftBy(GameElement e, String tag) {
        if (tag.equals("Mario")) {
            triggerAntigravity();
        }
    }

    @Override
    public void afterHitFromRightBy(GameElement e, String tag) {
        if (tag.equals("Mario")) {
            triggerAntigravity();
        }    
    }

    @Override
    public void afterHitFromTopBy(GameElement e, String tag) {
        if (tag.equals("Mario")) {
            triggerAntigravity();
        }
    }

    public void triggerAntigravity() {
        ((DemoAntigravity)myGame).triggerAntigravity();
    }



}
