/**
 * @author Kuang Han
 */

package core.characters;

import java.util.ArrayList;
import java.util.List;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.sprite.AdvanceSprite;
import core.physicsengine.coordinatesystem.Acceleration;
import core.physicsengine.coordinatesystem.Displacement;
import core.physicsengine.coordinatesystem.DuringAcceleration;
import core.physicsengine.coordinatesystem.Velocity;
import core.physicsengine.physicsplugin.PhysicsAttributes;

public class GameElement extends AdvanceSprite {

    private static final long serialVersionUID = 2989579123989132598L;

    private Acceleration acc;
    private List<DuringAcceleration> duringAccList;
    private Velocity vel;
    private Displacement disp;
        
    private PhysicsAttributes physicsAttribute;

    private double maximumSpeedInX = Double.MAX_VALUE;
    private double maximumSpeedInY = Double.MAX_VALUE;

    protected transient GameObject myGame;
    
    private String tag = "GameElement";

    public GameElement(PhysicsAttributes physicsAttribute) {
        super();
        this.physicsAttribute = physicsAttribute;
        acc = new Acceleration(0, 0);
        vel = new Velocity(0, 0);
        disp = new Displacement(0, 0);
        duringAccList = new ArrayList<DuringAcceleration>();
//        Treat gravity as a during acceleration.
//        duringAccList.add(new DuringAcceleration(new Mapping(this) {
//            @Override
//            public double getXforTime(int t) {
//                return 0;
//            }
//            @Override
//            public double getYforTime(int t) {
//                if (this.getOwner().isUnmovable() == false) {
//                    return this.getOwner().getGravitationalAcceleration() * -1.0;
//                }
//                else {
//                    return 0;
//                }
//            }
//        }));
        //myGame = null;
        //physicsAttribute = null;
    }

    public GameElement(GameObject game, PhysicsAttributes physicsAttribute) {
        this(physicsAttribute);
        myGame = game;
    }
    
    // for decorators
    public GameElement() {}
    
    public void setGame(GameObject game) {
        myGame = game;
    }

    public GameObject getGame() {
        return myGame;
    }
    
    public void setPhysicsAttribute(PhysicsAttributes a) {
        this.physicsAttribute = a;
    }
    
    public PhysicsAttributes getPhysicsAttribute() {
        return this.physicsAttribute;
    }
    
    @Override
    public void update(long milliSec) {
        super.update(milliSec);
        this.addGravity();
    }

    private void addGravity() {
        if (physicsAttribute.isUnmovable() == false) {
            this.addAcceleration(0, -physicsAttribute.getGravitationalAcceleration());
        }
    }
    
    @Override
    protected void updateMovement(long t) {
        updateDuringAcceleration(t);
        vel.addFromAcceleration(acc, t);
        this.checkMaximunSpeed();
        disp.addFromVelocity(vel, t);
        acc.reset();
        moveToDisplacement();
    }

    protected void updateDuringAcceleration(long t) {
        for (DuringAcceleration entry : duringAccList) {
            entry.update(t);
            acc.add(entry.getCurrentX(), entry.getCurrentY());
            if (entry.isActive() == false) {
                duringAccList.remove(entry);
            }
        }
    }

    protected void checkMaximunSpeed() {
        double vx = vel.getX(), vy = vel.getY();
        if (Math.abs(vx) > maximumSpeedInX) {
            vx = maximumSpeedInX;
            if (vel.getX() < 0) {
                vx = -vx;
            }
        }
        if (Math.abs(vy) > maximumSpeedInY) {
            vy = maximumSpeedInY;
            if (vel.getY() < 0) {
                vy = -vy;
            }
        }
        vel.set(vx, vy);
    }

    private void moveToDisplacement() {
        double dx = disp.getX() - this.getX();
        double dy = 480 - disp.getY() - this.getY(); // need to change!!!!
        this.move(dx, dy);
    }

    private void setDisplacementFromLocation() {
        disp.set(this.getX(), 480 - this.getY()); // need to change!!!!
    }

    public void addAcceleration(double x, double y) {
        acc.add(x, y);
    }

    public void setAcceleration(double x, double y) {
        acc.set(x, y);
    }

    public void addVelocity(double x, double y) {
        vel.add(x, y);
    }

    public void setVelocity(double x, double y) {
        vel.set(x, y);
    }

    public void addDisplacement(double x, double y) {
        disp.add(x, y);
    }

    public void setDisplacement(double x, double y) {
        disp.set(x, y);
    }

    public Acceleration getAcceleration() {
        return acc;
    }

    public Velocity getVelocity() {
        return vel;
    }

    public Displacement getDisplacement() {
        return disp;
    }

    @Override
    public void forceX(double xs) {
        super.forceX(xs);
        setDisplacementFromLocation();
    }

    @Override
    public void forceY(double ys) {
        super.forceY(ys);
        setDisplacementFromLocation();
    }

    @Override
    public boolean moveTo(long elapsedTime, double xs, double ys, double speed) {
        boolean flag = super.moveTo(elapsedTime, xs, ys, speed);
        setDisplacementFromLocation();
        return flag;
    }

    @Override
    public void move(double dx, double dy) {
        super.move(dx, dy);
        setDisplacementFromLocation();
    }

    @Override
    public void moveX(double dx) {
        super.moveX(dx);
        setDisplacementFromLocation();
    }

    @Override
    public void moveY(double dy) {
        super.moveY(dy);
        setDisplacementFromLocation();
    }

    @Override
    public void setLocation(double xs, double ys) {
        super.setLocation(xs, ys);
        setDisplacementFromLocation();
    }

    @Override
    public void setX(double xs) {
        super.setX(xs);
        setDisplacementFromLocation();
    }

    @Override
    public void setY(double ys) {
        super.setY(ys);
        setDisplacementFromLocation();
    }

    @Override
    public void setSpeed(double vx, double vy) {
        setVelocity(vx, vy);
    }

    @Override
    public void setVerticalSpeed(double vy) {
        setVelocity(this.getVelocity().getX(), vy);
    }

    @Override
    public void setHorizontalSpeed(double vx) {
        setVelocity(vx, this.getVelocity().getY());
    }

    public void givenAForceOf(double fx, double fy) {
        double ax = fx / this.getPhysicsAttribute().getMass(), ay = fy / this.getPhysicsAttribute().getMass();
        this.addAcceleration(ax, ay);
    }

    public void setMaximumSpeedInX(double x) {
        this.maximumSpeedInX = x;
    }

    public void setMaximumSpeedInY(double y) {
        this.maximumSpeedInY = y;
    }
//    
//    public double getMass() {
//        return this.getPhysicsAttribute().getMass();
//    }
//    public void setMass(double m) {
//        this.getPhysicsAttribute().setMass(m);
//    }
//
//    public double getDensity() {
//        return this.getPhysicsAttribute().getDensity();
//    }
//    public void setDensity(double density) {
//        this.getPhysicsAttribute().setDensity(density);
//    }
//
//    public double getCoefficintOfFrictionInXDirection() {
//        return this.getPhysicsAttribute().getCoefficintOfFrictionInXDirection();
//    }
//    public void setCoefficientOfFrictionInX(double coef) {
//        this.getPhysicsAttribute().setCoefficientOfFrictionInX(coef);
//    }
//
//    public double getCoefficintOfFrictionInYDirection() {
//        return this.getCoefficintOfFrictionInYDirection();
//    }
//    public void setCoefficientOfFrictionInY(double coef) {
//        this.getPhysicsAttribute().setCoefficientOfFrictionInY(coef);
//    }
//
//    public double getCoefficintOfRestitutionInXDirection() {
//        return this.getPhysicsAttribute().getCoefficintOfRestitutionInXDirection();
//    }
//    public void setCoefficientOfRestitutionInX(double coef) {
//        this.getPhysicsAttribute().setCoefficientOfRestitutionInX(coef);
//    }
//
//    public double getCoefficintOfRestitutionInYDirection() {
//        return this.getPhysicsAttribute().getCoefficintOfRestitutionInYDirection();
//    }
//    public void setCoefficientOfRestitutionInY(double coef) {
//        this.getPhysicsAttribute().setCoefficientOfRestitutionInY(coef);
//    }
//    
//    public double getDragCoefficient() {
//        return this.getPhysicsAttribute().getDragCoefficient();
//    }
//    public void setDragCoefficient(double coef) {
//        this.getPhysicsAttribute().setDragCoefficient(coef);
//    }
//
//    public double getGravitationalAcceleration() {
//        return this.getPhysicsAttribute().getGravitationalAcceleration();
//    }
//    public void setGravitationalAcceleration(double coef) {
//        this.getPhysicsAttribute().setGravitationalAcceleration(coef);
//    }
//
//    public boolean isUnmovable() {
//        return this.getPhysicsAttribute().isUnmovable();
//    }
//    public void setMovable(boolean movable) {
//        this.getPhysicsAttribute().setMovable(movable);
//    }
//
//    public boolean isPenetrable() {
//        return this.getPhysicsAttribute().isPenetrable();
//    }
//    public void setPenetrable(boolean penetrable) {
//        this.getPhysicsAttribute().setPenetrable(penetrable);
//    }


    public void beforeHitFromLeftBy(GameElement e, String tag) {};
    public void beforeHitFromRightBy(GameElement e, String tag) {};
    public void beforeHitFromTopBy(GameElement e, String tag) {};
    public void beforeHitFromBottomBy(GameElement e, String tag) {};
    public void afterHitFromLeftBy(GameElement e, String tag) {};
    public void afterHitFromRightBy(GameElement e, String tag) {};
    public void afterHitFromTopBy(GameElement e, String tag) {};
    public void afterHitFromBottomBy(GameElement e, String tag) {};
    
    public void setTag(String s) {
        this.tag = s;
    }
    
    public String getTag() {
        return this.tag;
    }

}
