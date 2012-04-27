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
	
	public static int gameHeight, gameWidth;
	
	public static void setGameStatics(int w, int h) {
		gameHeight = h;
		gameWidth = w;
	}

    private static final long serialVersionUID = 2989579123989132598L;

    private Acceleration acc;
    private List<DuringAcceleration> duringAccList;
    private Velocity vel;
    private Displacement disp;
        
    private PhysicsAttributes physicsAttribute;

    private double maximumSpeedInX = Double.MAX_VALUE;
    private double maximumSpeedInY = Double.MAX_VALUE;
    
    private String tag = "GameElement";

    public GameElement(PhysicsAttributes physicsAttribute) {
        super();
        this.physicsAttribute = physicsAttribute;
        acc = new Acceleration(0, 0);
        vel = new Velocity(0, 0);
        disp = new Displacement(0, 0);
        duringAccList = new ArrayList<DuringAcceleration>();
    }
    
    // for decorators
    public GameElement() {}
    
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
        double dy = gameHeight - disp.getY() - this.getY(); // need to change!!!!
        this.move(dx, dy);
    }

    private void setDisplacementFromLocation() {
        disp.set(this.getX(), gameHeight - this.getY()); // need to change!!!!
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
