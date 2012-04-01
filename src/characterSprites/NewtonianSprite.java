package characterSprites;

@SuppressWarnings("serial")
public class NewtonianSprite extends CartesianSprite{
    protected double 
    mass = 10, 
    coefOfFrictionInX = 0.2,
    coefOfFrictionInY = 0, 
    coefOfRestitutionInX = 0.2, 
    coefOfRestitutionInY = 0,
    coefOfViscosity = 0,
    stdGravity = 0.004;
    protected boolean   
    isUnmovable = false,
    isPenetrable = false;

    protected double collisionTime = 10;   // elapsedTime!!!!

    @Override
    public void update(long milliSec) {
        super.update(milliSec);
        if (isUnmovable == false) {
            this.addAcceleration(0, -stdGravity);
        }
    }

    public void collidedRightToLeftWith(NewtonianSprite s) {
        this.collidedInXDirectionWith(s);
    }

    public void collidedLeftToRightWith(NewtonianSprite s) {
        this.collidedInXDirectionWith(s);
    }    

    public void collidedTopToBottomWith(NewtonianSprite s) {
        this.collidedInYDirectionWith(s);
    }

    public void collidedBottomToTopWith(NewtonianSprite s) {
        this.collidedInYDirectionWith(s);
    }

    protected void collidedInXDirectionWith(NewtonianSprite s) {
        if (this.isPenetrable || s.isPenetrable) {
            return;
        }
        if (this.isUnmovable) {
            if (s.isUnmovable()) {
                System.out.println("Two unmovable block collided!!!");
            }
        }
        else {
            double vx1 = vel.getX(), vx2 = s.getVelocity().getX(), 
                    vy1 = vel.getY(), vy2 = s.getVelocity().getY(), 
                    m1 = mass, m2 = s.getMass();
            double ux1;
            double uy1 = vy1;
            if (s.isUnmovable()) {
                ux1 = s.getCoefficintOfRestitutionInXDirection() * (2.0*vx2 - vx1);
            }
            else {
                ux1 = s.getCoefficintOfRestitutionInXDirection() * ((m1-m2)*vx1 + 2.0*m2*vx2) / (m1+m2);
            }
            if (vy1 != vy2) {
                double fNormal = (ux1 - vx1)*m1 / collisionTime;
                double fFraction = Math.abs(fNormal) * s.getCoefficintOfFrictionInYDirection();
                double a = fFraction / mass;
                if (vy1 > 0) {
                    a = -a;
                }
                uy1 += a * collisionTime;
            }
            vel.set(ux1, uy1);
        }
        this.forceX(this.getOldX());
    }

    protected void collidedInYDirectionWith(NewtonianSprite s) {
        if (this.isPenetrable || s.isPenetrable) {
            return;
        }
        if (this.isUnmovable) {
            if (s.isUnmovable()) {
                System.out.println("Two unmovable block collided!!!");
            }
        }
        else {
            double vx1 = vel.getX(), vx2 = s.getVelocity().getX(), 
                    vy1 = vel.getY(), vy2 = s.getVelocity().getY(), 
                    m1 = mass, m2 = s.getMass();
            double uy1;
            double ux1 = vx1;
            if (s.isUnmovable()) {
                uy1 = s.getCoefficintOfRestitutionInYDirection() * (2.0*vy2 - vy1);
            }
            else {
                uy1 = s.getCoefficintOfRestitutionInYDirection() * ((m1-m2)*vy1 + 2.0*m2*vy2) / (m1+m2);
            }
            if (vx1 != vx2) {
                double fNormal = (uy1 - vy1)*m1 / collisionTime;
                double fFraction = Math.abs(fNormal) * s.getCoefficintOfFrictionInXDirection();
                double a = fFraction / mass;
                if (vx1 > 0) {
                    a = -a;
                }
                
                ux1 += a * collisionTime;
            }
            vel.set(ux1, uy1);
        }
        this.forceY(this.getOldY());
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
