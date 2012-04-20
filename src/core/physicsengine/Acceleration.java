/**
 * @author Kuang Han
 */

package core.physicsengine;

public class Acceleration extends Vector {
    
    private static final long serialVersionUID = -6091203340869965662L;

    public Acceleration(double x, double y) {
        super(x, y);
    }

    public Acceleration(double rad, double r, boolean isAngularSystem) {
        super(rad, r, isAngularSystem);
    }
    
}