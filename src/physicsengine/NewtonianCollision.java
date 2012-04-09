/**
 * @author Kuang Han
 */

package physicsengine;



import charactersprites.GameElement;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;


public class NewtonianCollision extends CollisionGroup{

    protected double collisionTime = 10;   // elapsedTime!!!!

    public NewtonianCollision() {
        super();
        this.pixelPerfectCollision = true;
    }

    @Override
    public void collided(Sprite s1, Sprite s2) {        
        if (((GameElement)s1).isPenetrable() || ((GameElement)s2).isPenetrable()) {
            checkBuoyancy((GameElement)s1, (GameElement)s2);
            checkViscosity((GameElement)s1, (GameElement)s2);
            checkBuoyancy((GameElement)s2, (GameElement)s1);
            checkViscosity((GameElement)s2, (GameElement)s1);
        }
        else {
            if (((GameElement)s1).isUnmovable() && ((GameElement)s2).isUnmovable()) {
                System.err.println("Two unmovable block collided!!!");
            }
            if (((this.collisionSide & LEFT_RIGHT_COLLISION) != 0) || 
                    (this.collisionSide & RIGHT_LEFT_COLLISION) != 0) {
                checkCollisionInXDirection((GameElement) s1, (GameElement) s2);
                checkCollisionInXDirection((GameElement) s2, (GameElement) s1);
            }
            if (((this.collisionSide & BOTTOM_TOP_COLLISION) != 0) ||
                    (this.collisionSide & TOP_BOTTOM_COLLISION) != 0) {
                checkCollisionInYDirection((GameElement) s1, (GameElement) s2);
                checkCollisionInYDirection((GameElement) s2, (GameElement) s1);
            }
        }
    }
    
    protected void checkBuoyancy(GameElement s1, GameElement s2) {
        if (s1.isUnmovable()) return;
        double fb = s1.getMass() / s1.getDensity() * s2.getDensity() * s1.getGravitationalAcceleration();
        s1.givenAForceOf(0, fb);
    }

    protected void checkViscosity(GameElement s1, GameElement s2) {
        // All wrong!!
        if (s1.isUnmovable()) return;
        double fc = s2.getDragCoefficient(),
                vx = s1.getVelocity().getX(),
                vy = s1.getVelocity().getY();
        double fx = fc * vx*vx;
        if (vx > 0) fx *= (-1);
        double fy = fc * vy*vy;
        if (vy > 0) fy *= (-1); 
        s1.givenAForceOf(fx, fy);
        
    }
    
    protected void checkCollisionInXDirection(GameElement s1, GameElement s2) {
        if (s1.isUnmovable()) return;
        double vx1 = s1.getVelocity().getX(), vx2 = s2.getVelocity().getX(), 
                vy1 = s1.getVelocity().getY(), vy2 = s2.getVelocity().getY(), 
                m1 = s1.getMass(), m2 = s2.getMass(),
                cr = s2.getCoefficintOfRestitutionInXDirection(),
                cf = s2.getCoefficintOfFrictionInYDirection();

        double ux1;
        if (s2.isUnmovable()) {
            ux1 = vx2 + cr*(vx2 - vx1);
        }
        else {
            ux1 = (m1*vx1 + m2*vx2 + m2*cr*(vx2-vx1)) / (m1 + m2);
        }
        double uy1 = vy1;
        if (vy1 != vy2) {
            double fNormal = (ux1 - vx1)*m1 / collisionTime;
            double fFraction = Math.abs(fNormal) * cf;
            double a = fFraction / m1;
            if (vy1 > 0) {
                a = -a;
            }
            uy1 += a * collisionTime;
            if ((vy1>0 && uy1<0) || (vy1<0) && (uy1>0)) {
                uy1 = 0;
            }
        }
        s1.setVelocity(ux1, uy1);
        s1.forceX(s1.getOldX());
    }

    protected void checkCollisionInYDirection(GameElement s1, GameElement s2) {
        if (s1.isUnmovable()) return;
        double vx1 = s1.getVelocity().getX(), vx2 = s2.getVelocity().getX(), 
                vy1 = s1.getVelocity().getY(), vy2 = s2.getVelocity().getY(), 
                m1 = s1.getMass(), m2 = s2.getMass(),
                cr = s2.getCoefficintOfRestitutionInYDirection(),
                cf = s2.getCoefficintOfFrictionInXDirection();

        double uy1;
        if (s2.isUnmovable()) {
            uy1 = vy2 + cr*(vy2 - vy1);
        }
        else {
            uy1 = (m1*vy1 + m2*vy2 + m2*cr*(vy2-vy1)) / (m1 + m2);
        }
        double ux1 = vx1;
        if (vx1 != vx2) {
            double fNormal = (uy1 - vy1)*m1 / collisionTime;
            double fFraction = Math.abs(fNormal) * cf;
            double a = fFraction / m1;
            if (vx1 > 0) {
                a = -a;
            }
            ux1 += a * collisionTime;
            if ((vx1>0 && ux1<0) || (vx1<0) && (ux1>0)) {
                ux1 = 0;
            }
        }
        s1.setVelocity(ux1, uy1);
        s1.forceY(s1.getOldY());
    }



}
