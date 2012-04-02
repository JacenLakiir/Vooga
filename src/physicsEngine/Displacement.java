/**
 * @author Kuang Han
 */

package physicsEngine;

public class Displacement extends Vector{
    public Displacement(double x, double y) {
        super(x, y);
    }

    public Displacement(double rad, double r, boolean isAngularSystem) {
        super(rad, r, isAngularSystem);
    }

    public void addFromVelocity(Velocity v, double duration) {
        this.add(v.getX() * duration, v.getY() * duration);
    }

}
