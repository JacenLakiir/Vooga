package characterSprites.cartesianSystem;
import com.golden.gamedev.object.Timer;
import characterSprites.cartesianSystem.*;


public class DuringAcceleration extends Acceleration{

    private Timer timer;
    private Mapping formula;
    private int duration, currentTime;
    private boolean active;

    public DuringAcceleration(Mapping formula, int duration) {
        super(formula.getXforTime(0), formula.getYforTime(0));
        this.formula = formula;
        this.duration = duration;
        currentTime = 0;
        timer = new Timer(duration);
        active = true;
    }

    public void update(long t) {
        if (!active) return;
        if (timer.action(t)) {
            active = false;
            timer.setActive(false);
        }
        else {
            currentTime = (int)timer.getCurrentTick();
        }
    }
    
    public double getCurrentX() {
        return formula.getXforTime(currentTime);
    }
    
    public double getCurrentY() {
        return formula.getYforTime(currentTime);
    }


}
