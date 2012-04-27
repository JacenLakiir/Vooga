
package demo.custom;

import com.golden.gamedev.GameObject;

import core.physicsengine.physicsplugin.PhysicsAttributes;
import core.tiles.Liquid;

/**
 * @author Kuang Han
 */
@SuppressWarnings("serial")
public class Water extends Liquid {

    public Water(PhysicsAttributes physicsAttribute, double strength) {
        super(physicsAttribute, strength);
        this.getPhysicsAttribute().setMovable(false);
        this.getPhysicsAttribute().setDensity(1.0);
        this.getPhysicsAttribute().setDragCoefficient(.2);
        this.setTag("Water");
    }
    

}
