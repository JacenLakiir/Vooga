/**
 * @author Kuang Han
 */


package core.physicsengine.physicsplugin;

import core.characters.GameElement;

public class BuoyancyPlugin extends PhysicsPlugin{

    @Override
    public void exertOn(GameElement s1, GameElement s2) {
        if ((s1).isPenetrable() || (s2).isPenetrable()) {
            if (s1.isUnmovable() == false) {
                checkBuoyancy(s1, s2);
            }
            if (s2.isUnmovable() == false) {
                checkBuoyancy(s2, s1);
            }
        }
    }

    protected void checkBuoyancy(GameElement s1, GameElement s2) {
        double fb = s1.getMass() / s1.getDensity() * s2.getDensity() * s1.getGravitationalAcceleration();
        s1.givenAForceOf(0, fb);
    }

}
