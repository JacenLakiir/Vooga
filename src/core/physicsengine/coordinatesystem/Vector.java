/**
 * @author Kuang Han
 */

package core.physicsengine.coordinatesystem;

public abstract class Vector implements java.io.Serializable {
    
    private static final long serialVersionUID = 7740517023922848213L;
    
    private double x;
    private double y; 
    
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public Vector(double rad, double r, boolean isAngularSystem) {
        if (isAngularSystem) {
            x = r * Math.cos(rad);
            y = r * Math.sin(rad);
        }
        else {
            this.x = rad;
            this.y = r;
        }
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public void add(Vector v) {
	x += v.x;
	y += v.y;
    }
    
    public void minus(Vector v) {
	x -= v.x;
	y -= v.y;
    }
        
    public void add(double x, double y) {
        this.x += x;
        this.y += y;
    }
    
    protected void minus(double x, double y) {
        this.x -= x;
        this.y -= y;
    }
    
    
    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public void reset() {
        x = 0;
        y = 0;
    }
        
    public double getMagnitude() {
        return Math.hypot(x, y);
    }
    
    public String toString() {
	return x + " " + y;
    }
    
}