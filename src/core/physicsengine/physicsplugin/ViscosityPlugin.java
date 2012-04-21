/**
 * @author Kuang Han
 */

package core.physicsengine.physicsplugin;

import core.characters.GameElement;

public class ViscosityPlugin extends PhysicsPlugin{

    @Override
    public void exertOn(GameElement s1, GameElement s2) {
        if (s1.getPhysicsAttribute().isPenetrable() || s2.getPhysicsAttribute().isPenetrable()) {
            if (s1.getPhysicsAttribute().isUnmovable() == false) {
                checkViscosity(s1, s2);
            }
            if (s2.getPhysicsAttribute().isUnmovable() == false) {
                checkViscosity(s2, s1);
            }
        }
    }

    protected void checkViscosity(GameElement s1, GameElement s2) {
        double fc = s2.getPhysicsAttribute().getDragCoefficient(),
                vx = s1.getVelocity().getX(),
                vy = s1.getVelocity().getY();
        double fx = fc * vx*vx;
        if (vx > 0) fx *= (-1);
        double fy = fc * vy*vy;
        if (vy > 0) fy *= (-1); 
        s1.givenAForceOf(fx, fy);
    }

}
