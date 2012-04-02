package characterSprites;

@SuppressWarnings("serial")
public class NewtonianSprite extends CartesianSprite{
    protected double 
    mass = 10, 
    coefOfFrictionInX = 0,
    coefOfFrictionInY = 0, 
    coefOfRestitutionInX = 1, 
    coefOfRestitutionInY = 1,
    coefOfViscosity = 0,
    stdGravity = 0.004;
    protected boolean   
    isUnmovable = false,
    isPenetrable = false;

    @Override
    public void update(long milliSec) {
        super.update(milliSec);
        if (isUnmovable == false) {
            this.addAcceleration(0, -stdGravity);
        }
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



}
