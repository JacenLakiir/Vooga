/**
 * @author Kuang Han
 */

package core.physicsengine.coordinatesystem;

public class Displacement extends Vector {
    
    private static final long serialVersionUID = 6076266612415296300L;

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
