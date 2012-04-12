/**
 * @author Kuang Han
 */

package core.physicsengine;

public class Velocity extends Vector{

    public Velocity(double x, double y) {
        super(x, y);
    }

    public Velocity(double rad, double r, boolean isAngularSystem) {
        super(rad, r, isAngularSystem);
    }
    
    public void addFromAcceleration(Acceleration a, double duration) {
        this.add(a.getX() * duration, a.getY() * duration);
    }

}
