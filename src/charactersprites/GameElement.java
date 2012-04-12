/**
 * @author Kuang Han
 */

package charactersprites;

import java.util.ArrayList;
import java.util.List;

import physicsengine.Acceleration;
import physicsengine.Displacement;
import physicsengine.DuringAcceleration;
import physicsengine.Velocity;


import collision.GameElementCollision;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.sprite.AdvanceSprite;

@SuppressWarnings("serial") 
public abstract class GameElement extends AdvanceSprite{
    protected Acceleration acc;
    protected List<DuringAcceleration> duringAccList;
    protected Velocity vel;
    protected Displacement disp;
    protected double metersPerPixel;
    
    protected double mass = 10;
    protected double density = 1.01;
    protected double coefOfFrictionInX = 0.2;
    protected double coefOfFrictionInY = 0;
    protected double coefOfRestitutionInX = 0.2;
    protected double coefOfRestitutionInY = 0.1;
    protected double dragCoef = 0;
    protected double stdGravity = 0.004;
    protected boolean isUnmovable = false;
    protected boolean isPenetrable = false;
    protected Game myGame;

    public GameElement() {
        super();
        acc = new Acceleration(0, 0);
        vel = new Velocity(0, 0);
        disp = new Displacement(0, 0);
        duringAccList = new ArrayList<DuringAcceleration>();
        // Treat gravity as a during acceleration.
//        duringAccList.add(new DuringAcceleration(new Mapping(this) {
//            @Override
//            public double getXforTime(int t) {
//                    return 0;
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
        myGame = null;
    }
    
    public GameElement(Game game) {
        this();
        myGame = game;
    }

    @Override
    public void update(long milliSec) {
        super.update(milliSec);
        this.addGravity();
    }

    private void addGravity() {
        if (isUnmovable == false) {
            this.addAcceleration(0, -stdGravity);
        }
    }

    @Override
    protected void updateMovement(long t) {
        for (DuringAcceleration entry:duringAccList) {
            entry.update(t);
            acc.add(entry.getCurrentX(), entry.getCurrentY());
            if (entry.isActive() == false) {
                duringAccList.remove(entry);
            }
        }
        vel.addFromAcceleration(acc, t);
        disp.addFromVelocity(vel, t);
        acc.reset();
        moveToDisplacement();
    }

    private void moveToDisplacement() {
        double dx = disp.getX() - this.getX();
        double dy = 480 - disp.getY() - this.getY();    // need to change!!!!
        this.move(dx, dy);
    }

    private void setDisplacementFromLocation() {
        disp.set(this.getX(), 480 - this.getY());       // need to change!!!!
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

    public void setMass(double m) {
        this.mass = m;
    }

    public void setGame(Game game) {
        myGame = game;
    }

    public Game getGame() {
        return myGame;
    }

    public void beforeCollidedWith(GameElement e, int collisionSide) {
        switch (collisionSide) {
            case GameElementCollision.RIGHT_LEFT_COLLISION:{
                beforeHitFromRightBy(e);
                break;
            }
            case GameElementCollision.LEFT_RIGHT_COLLISION:{
                beforeHitFromLeftBy(e);
                break;
            }
            case GameElementCollision.BOTTOM_TOP_COLLISION:{
                beforeHitFromBottomBy(e);                
                break;
            }
            case GameElementCollision.TOP_BOTTOM_COLLISION:{
                beforeHitFromTopBy(e);
                break;
            }
        }
    }

    public void afterCollidedWith(GameElement e, int collisionSide) {
        switch (collisionSide) {
            case GameElementCollision.RIGHT_LEFT_COLLISION:{
                afterHitFromRightBy(e);
                break;
            }
            case GameElementCollision.LEFT_RIGHT_COLLISION:{
                afterHitFromLeftBy(e);
                break;
            }
            case GameElementCollision.BOTTOM_TOP_COLLISION:{
                afterHitFromBottomBy(e);
                break;
            }
            case GameElementCollision.TOP_BOTTOM_COLLISION:{
                afterHitFromTopBy(e);
                break;
            }
        }
    }

    public void beforeHitFromLeftBy(GameElement e) {}

    public void beforeHitFromRightBy(GameElement e) {}

    public void beforeHitFromTopBy(GameElement e) {}

    public void beforeHitFromBottomBy(GameElement e) {}

    public void afterHitFromLeftBy(GameElement e) {}

    public void afterHitFromRightBy(GameElement e) {}

    public void afterHitFromTopBy(GameElement e) {}

    public void afterHitFromBottomBy(GameElement e) {}

}
