/**
 * @author Kuang Han
 */


package core.physicsengine.physicsplugin;

import core.characters.GameElement;

public class BuoyancyPlugin extends PhysicsPlugin{

    @Override
    public void exertOn(GameElement s1, GameElement s2) {
        if (s1.getPhysicsAttribute().isPenetrable() || s2.getPhysicsAttribute().isPenetrable()) {
            if (s1.getPhysicsAttribute().isUnmovable() == false) {
                checkBuoyancy(s1, s2);
            }
            if (s2.getPhysicsAttribute().isUnmovable() == false) {
                checkBuoyancy(s2, s1);
            }
        }
    }

    protected void checkBuoyancy(GameElement s1, GameElement s2) {
        double fb = s1.getPhysicsAttribute().getMass() / s1.getPhysicsAttribute().getDensity() * s2.getPhysicsAttribute().getDensity() * s1.getPhysicsAttribute().getGravitationalAcceleration();
        s1.givenAForceOf(0, fb);
    }

}
