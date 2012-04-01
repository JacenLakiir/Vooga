package characterSprites.enemyAI;

import com.golden.gamedev.object.sprite.AdvanceSprite;


@SuppressWarnings("serial")
public abstract class CartesianSprite extends AdvanceSprite{
    protected Acceleration acc;
//    protected DuringAcceleration duringAcc;
    protected Velocity vel;
    protected Displacement disp;
    protected double metersPerPixel;

    public CartesianSprite() {
        super();
        acc = new Acceleration(0, 0);
        vel = new Velocity(0, 0);
        disp = new Displacement(0, 0);
    }

    @Override
    public void update(long milliSec) {
        //subclass.update(long elapsedTime);
        updateMovement(milliSec);
    }

    @Override
    protected void updateMovement(long t) {
        vel.addFromAcceleration(acc, t);
        disp.addFromVelocity(vel, t);
        System.out.println("acc:   " + acc.getX());
        System.out.println("vel:   " + vel.getX());
        System.out.println("disp:  " + disp.getX());

        acc.reset();
        moveToDisplacement();
    }

    private void moveToDisplacement() {
        double dx = disp.getX() - this.getX();
        double dy = 480 - disp.getY() - this.getY();    // need to change!!!!
//        this.moveY(dy);
//        this.moveX(dx);
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

    public Acceleration getInstantAcceleration() {
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


}
