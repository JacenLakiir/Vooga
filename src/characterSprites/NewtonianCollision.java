package characterSprites;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;


public class NewtonianCollision extends CollisionGroup{

    protected double collisionTime = 10;   // elapsedTime!!!!

    public NewtonianCollision() {
        this.pixelPerfectCollision = true;
    }

    @Override
    public void collided(Sprite s1, Sprite s2) {        
        //        if ((this.collisionSide & CollisionGroup.LEFT_RIGHT_COLLISION) != 0) {
        //            ((NewtonianSprite)s1).collidedLeftToRightWith((NewtonianSprite)s2);
        //            ((NewtonianSprite)s2).collidedRightToLeftWith((NewtonianSprite)s1);
        //        }
        //        if  ((this.collisionSide & CollisionGroup.RIGHT_LEFT_COLLISION) != 0) {
        //            ((NewtonianSprite)s1).collidedRightToLeftWith((NewtonianSprite)s2);
        //            ((NewtonianSprite)s2).collidedLeftToRightWith((NewtonianSprite)s1);
        //        }
        //        if ((this.collisionSide & CollisionGroup.BOTTOM_TOP_COLLISION) != 0) {
        //            ((NewtonianSprite)s1).collidedBottomToTopWith((NewtonianSprite)s2);
        //            ((NewtonianSprite)s2).collidedTopToBottomWith((NewtonianSprite)s1);
        //        }
        //        if ((this.collisionSide & CollisionGroup.TOP_BOTTOM_COLLISION) != 0) {
        //            ((NewtonianSprite)s1).collidedTopToBottomWith((NewtonianSprite)s2);
        //            ((NewtonianSprite)s2).collidedBottomToTopWith((NewtonianSprite)s1);
        //        }
        if (((NewtonianSprite)s1).isPenetrable || ((NewtonianSprite)s2).isPenetrable) {
            return;
        }
        if (((NewtonianSprite)s1).isUnmovable() && ((NewtonianSprite)s2).isUnmovable()) {
            System.err.println("Two unmovable block collided!!!");
        }
        if (((this.collisionSide & CollisionGroup.LEFT_RIGHT_COLLISION) != 0) || 
                (this.collisionSide & CollisionGroup.RIGHT_LEFT_COLLISION) != 0) {
            collisionInXDirection((NewtonianSprite) s1, (NewtonianSprite) s2);
            collisionInXDirection((NewtonianSprite) s2, (NewtonianSprite) s1);
        }
        if (((this.collisionSide & CollisionGroup.BOTTOM_TOP_COLLISION) != 0) ||
                (this.collisionSide & CollisionGroup.TOP_BOTTOM_COLLISION) != 0) {
            collisionInYDirection((NewtonianSprite) s1, (NewtonianSprite) s2);
            collisionInYDirection((NewtonianSprite) s2, (NewtonianSprite) s1);
        }
    }

    protected void collisionInXDirection(NewtonianSprite s1, NewtonianSprite s2) {
        if (s1.isUnmovable() == false) {
            double vx1 = s1.getVelocity().getX(), vx2 = s2.getVelocity().getX(), 
                    vy1 = s1.getVelocity().getY(), vy2 = s2.getVelocity().getY(), 
                    m1 = s1.getMass(), m2 = s2.getMass();
            double ux1;
            double uy1 = vy1;
            if (s2.isUnmovable()) {
                ux1 = s2.getCoefficintOfRestitutionInXDirection() * (2.0*vx2 - vx1);
            }
            else {
                ux1 = s2.getCoefficintOfRestitutionInXDirection() * ((m1-m2)*vx1 + 2.0*m2*vx2) / (m1+m2);
            }
            if (vy1 != vy2) {
                double fNormal = (ux1 - vx1)*m1 / collisionTime;
                double fFraction = Math.abs(fNormal) * s2.getCoefficintOfFrictionInYDirection();
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
        }
        s1.forceX(s1.getOldX());
    }

    protected void collisionInYDirection(NewtonianSprite s1, NewtonianSprite s2) {
        if (s1.isUnmovable() == false) {
            double vx1 = s1.getVelocity().getX(), vx2 = s2.getVelocity().getX(), 
                    vy1 = s1.getVelocity().getY(), vy2 = s2.getVelocity().getY(), 
                    m1 = s1.getMass(), m2 = s2.getMass();
            double uy1;
            double ux1 = vx1;
            if (s2.isUnmovable()) {
                uy1 = s2.getCoefficintOfRestitutionInYDirection() * (2.0*vy2 - vy1);
            }
            else {
                uy1 = s2.getCoefficintOfRestitutionInYDirection() * ((m1-m2)*vy1 + 2.0*m2*vy2) / (m1+m2);
            }
            if (vx1 != vx2) {
                double fNormal = (uy1 - vy1)*m1 / collisionTime;
                double fFraction = Math.abs(fNormal) * s2.getCoefficintOfFrictionInXDirection();
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
        }
        s1.forceY(s1.getOldY());
    }



}
