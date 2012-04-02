/**
 * @author Kuang Han
 */

package physicsEngine;

@SuppressWarnings("serial")
public class NewtonianSprite extends CartesianSprite{
    protected double 
    mass = 10, 
    density = 1.01,
    coefOfFrictionInX = 0.2,
    coefOfFrictionInY = 0, 
    coefOfRestitutionInX = 0.2, 
    coefOfRestitutionInY = 0.1,
    dragCoef = 0,
    stdGravity = 0.004;
    protected boolean   
    isUnmovable = false,
    isPenetrable = false;

    @Override
    public void update(long milliSec) {
        super.update(milliSec);
        acc.reset();
        this.addGravity();
    }
    
    private void addGravity() {
        if (isUnmovable == false) {
            this.addAcceleration(0, -stdGravity);
        }
    }
    
    public void givenAForceOf(double fx, double fy) {
        double ax = fx / mass, ay = fy / mass;
        this.addAcceleration(ax, ay);
    }

    public double getMass() {
        return mass;
    }

    public double getCoefficintOfFrictionInXDirection() {
        return coefOfFrictionInX;
    }

    public double getCoefficintOfFrictionInYDirection() {
        return coefOfFrictionInY;
    }

    public double getCoefficintOfRestitutionInXDirection() {
        return coefOfRestitutionInX;
    }

    public double getCoefficintOfRestitutionInYDirection() {
        return coefOfRestitutionInY;
    }

    public boolean isUnmovable() {
        return isUnmovable;
    }

    public boolean isPenetrable() {
        return isPenetrable;
    }

    public double getGravitationalAcceleration() {
        return stdGravity;
    }

    public void setMovable(boolean movable) {
        isUnmovable = !movable; 
    }
    
    public void setPenetrable(boolean penetrable) {
        isPenetrable = penetrable;
    }
    
    public double getDragCoefficient() {
        return dragCoef;
    }

    public double getDensity() {
        return density;
    }
    
    public void setDensity(double density) {
        this.density = density;
    }
    
    public void setCoefficientOfFrictionInX(double coef) {
        this.coefOfFrictionInX = coef;
    }
    
    public void setCoefficientOfFrictionInY(double coef) {
        this.coefOfFrictionInY = coef;
    }
    
    public void setCoefficientOfRestitutionInX(double coef) {
        this.coefOfRestitutionInX = coef;
    }
    
    public void setCoefficientOfRestitutionInY(double coef) {
        this.coefOfRestitutionInY = coef;
    }
    
    public void setDragCoefficient(double coef) {
        this.dragCoef = coef;
    }


}
