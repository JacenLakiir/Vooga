/**
 * @author Kuang Han
 */

package core.physicsengine.coordinatesystem;

public class Velocity extends Vector {

    private static final long serialVersionUID = 2041735834117436056L;

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
