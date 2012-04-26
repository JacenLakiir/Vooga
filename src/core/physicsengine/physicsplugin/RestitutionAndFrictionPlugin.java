/**
 * @author Kuang Han
 */

package core.physicsengine.physicsplugin;

import core.characters.GameElement;
import core.collision.GameElementCollision;


public class RestitutionAndFrictionPlugin extends PhysicsPlugin
{
    private final static long serialVersionUID = 412310992842831577L;

    private double defaultCollisionTime = 10; // elapsedTime!!!!


    @Override
    public void exertOn (GameElement s1, GameElement s2)
    {
        if (s1.getPhysicsAttribute().isPenetrable() || s2.getPhysicsAttribute().isPenetrable()) {
            return;
        }
        int collisionSide = myCollision.getCollisionSide();
        if (((collisionSide & GameElementCollision.LEFT_RIGHT_COLLISION) != 0) ||
            (collisionSide & GameElementCollision.RIGHT_LEFT_COLLISION) != 0)
        {
            if (s1.getPhysicsAttribute().isUnmovable() == false)
            {
                checkCollisionInXDirection(s1, s2);
            }
            if (s2.getPhysicsAttribute().isUnmovable() == false)
            {
                checkCollisionInXDirection(s2, s1);
            }
        }
        if (((collisionSide & GameElementCollision.TOP_BOTTOM_COLLISION) != 0) ||
            (collisionSide & GameElementCollision.BOTTOM_TOP_COLLISION) != 0)
        {
            if (s1.getPhysicsAttribute().isUnmovable() == false)
            {
                checkCollisionInYDirection(s1, s2);
            }
            if (s2.getPhysicsAttribute().isUnmovable() == false)
            {
                checkCollisionInYDirection(s2, s1);
            }
        }
    }


    protected void checkCollisionInXDirection (GameElement s1, GameElement s2)
    {
        double vx1 = s1.getVelocity().getX(), vx2 = s2.getVelocity().getX(), vy1 =
            s1.getVelocity().getY(), vy2 = s2.getVelocity().getY(), m1 =
            s1.getPhysicsAttribute().getMass(), m2 =
            s2.getPhysicsAttribute().getMass(), cr =
            s2.getPhysicsAttribute().getCoefficintOfRestitutionInXDirection(), cf =
            s2.getPhysicsAttribute().getCoefficintOfFrictionInYDirection();

        double ux1;
        if (s2.getPhysicsAttribute().isUnmovable())
        {
            ux1 = vx2 + cr * (vx2 - vx1);
        }
        else
        {
            ux1 = (m1 * vx1 + m2 * vx2 + m2 * cr * (vx2 - vx1)) / (m1 + m2);
        }
        double uy1 = vy1;
        if (vy1 != vy2)
        {
            double fNormal = (ux1 - vx1) * m1 / defaultCollisionTime;
            double fFraction = Math.abs(fNormal) * cf;
            double a = fFraction / m1;
            if (vy1 > 0)
            {
                a = -a;
            }
            uy1 += a * defaultCollisionTime;
            if ((vy1 > 0 && uy1 < 0) || (vy1 < 0) && (uy1 > 0))
            {
                uy1 = 0;
            }
        }
        s1.setVelocity(ux1, uy1);
        s1.forceX(s1.getOldX());
    }


    protected void checkCollisionInYDirection (GameElement s1, GameElement s2)
    {
        double vx1 = s1.getVelocity().getX(), vx2 = s2.getVelocity().getX(), vy1 =
            s1.getVelocity().getY(), vy2 = s2.getVelocity().getY(), m1 =
            s1.getPhysicsAttribute().getMass(), m2 =
            s2.getPhysicsAttribute().getMass(), cr =
            s2.getPhysicsAttribute().getCoefficintOfRestitutionInYDirection(), cf =
            s2.getPhysicsAttribute().getCoefficintOfFrictionInXDirection();

        double uy1;
        if (s2.getPhysicsAttribute().isUnmovable())
        {
            uy1 = vy2 + cr * (vy2 - vy1);
        }
        else
        {
            uy1 = (m1 * vy1 + m2 * vy2 + m2 * cr * (vy2 - vy1)) / (m1 + m2);
        }
        double ux1 = vx1;
        if (vx1 != vx2)
        {
            double fNormal = (uy1 - vy1) * m1 / defaultCollisionTime;
            double fFraction = Math.abs(fNormal) * cf;
            double a = fFraction / m1;
            if (vx1 > 0)
            {
                a = -a;
            }
            ux1 += a * defaultCollisionTime;
            if ((vx1 > 0 && ux1 < 0) || (vx1 < 0) && (ux1 > 0))
            {
                ux1 = 0;
            }
        }
        s1.setVelocity(ux1, uy1);
        s1.forceY(s1.getOldY());
    }

}