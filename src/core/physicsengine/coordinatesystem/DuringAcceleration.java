/**
 * @author Kuang Han
 */

package core.physicsengine.coordinatesystem;

public class DuringAcceleration extends Acceleration {

    private static final long serialVersionUID = 8037116099628455867L;
    
    private Mapping formula;
    private int duration, currentTime;
    private boolean isActive, isInfinite;

    public DuringAcceleration(Mapping formula) {
        super(formula.getXforTime(0), formula.getYforTime(0));
        this.formula = formula;
        isInfinite = true;
        currentTime = 0;
        isActive = true;
    }

    public DuringAcceleration(Mapping formula, int duration) {
        super(formula.getXforTime(0), formula.getYforTime(0));
        this.formula = formula;
        this.duration = duration;
        isInfinite = false;
        currentTime = 0;
        isActive = true;
    }

    public void update(long t) {
        if (!isActive) return;
        currentTime += t;
        if (!isInfinite && currentTime >= duration) {
            currentTime = duration;
            isActive = false;
        }
    }

    public double getCurrentX() {
        return formula.getXforTime(currentTime);
    }

    public double getCurrentY() {
        return formula.getYforTime(currentTime);
    }

    public boolean isActive() {
        return isActive;
    }
    
}