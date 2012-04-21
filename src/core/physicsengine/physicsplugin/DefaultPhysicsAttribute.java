package core.physicsengine.physicsplugin;

import java.util.HashMap;

public class DefaultPhysicsAttribute {

/*    
    public static enum Attribute {
        mass,
        density,
        coefOfFrictionInX,
        coefOfFrictionInY,
        coefOfRestitutionInX,
        coefOfRestitutionInY,
        dragCoef,
        stdGravity,
        isUnmovable,
        isPenetrable
    }
*/
    
    protected static HashMap<String, Object> defaultPhysicsPara;
    static {
        defaultPhysicsPara = new HashMap<String, Object>();
        defaultPhysicsPara.put("mass", 10.0);
        defaultPhysicsPara.put("density", 1.0);
        defaultPhysicsPara.put("coefOfFrictionInX", 0.5);
        defaultPhysicsPara.put("coefOfFrictionInY", 0.0);
        defaultPhysicsPara.put("coefOfRestitutionInX", 0.2);
        defaultPhysicsPara.put("coefOfRestitutionInY", 0.1);
        defaultPhysicsPara.put("dragCoef", 0.0);
        defaultPhysicsPara.put("stdGravity", 0.004);
        defaultPhysicsPara.put("isUnmovable", false);
        defaultPhysicsPara.put("isPenetrable", false);
    }
    
    protected HashMap<String, Object> physicsPara;

    public DefaultPhysicsAttribute() {
        physicsPara = new HashMap<String, Object>(defaultPhysicsPara);
    }
    
    
    protected void setPhysicsParameter(String s, Object t) {
        physicsPara.put(s, t);
    }
    
    protected Object getPhysicsParameter(String s) {
        return physicsPara.get(s);
    }

    public double getMass() {
        return (Double) this.getPhysicsParameter("mass");
    }
    public void setMass(double m) {
        this.setPhysicsParameter("mass", m);
    }

    public double getDensity() {
        return (Double) this.getPhysicsParameter("density");
    }
    public void setDensity(double density) {
        this.setPhysicsParameter("density", density);
    }

    public double getCoefficintOfFrictionInXDirection() {
        return (Double) this.getPhysicsParameter("coefOfFrictionInX");
    }
    public void setCoefficientOfFrictionInX(double coef) {
        this.setPhysicsParameter("coefOfFrictionInX", coef);
    }

    public double getCoefficintOfFrictionInYDirection() {
        return (Double) this.getPhysicsParameter("coefOfFrictionInY");
    }
    public void setCoefficientOfFrictionInY(double coef) {
        this.setPhysicsParameter("coefOfFrictionInY", coef);
    }

    public double getCoefficintOfRestitutionInXDirection() {
        return (Double) this.getPhysicsParameter("coefOfRestitutionInX");
    }
    public void setCoefficientOfRestitutionInX(double coef) {
        this.setPhysicsParameter("coefOfRestitutionInX", coef);
    }

    public double getCoefficintOfRestitutionInYDirection() {
        return (Double) this.getPhysicsParameter("coefOfRestitutionInY");
    }
    public void setCoefficientOfRestitutionInY(double coef) {
        this.setPhysicsParameter("coefOfRestitutionInY", coef);
    }
    
    public double getDragCoefficient() {
        return (Double) this.getPhysicsParameter("dragCoef");
    }
    public void setDragCoefficient(double coef) {
        this.setPhysicsParameter("dragCoef", coef);
    }

    public double getGravitationalAcceleration() {
        return (Double) this.getPhysicsParameter("stdGravity");
    }
    public void setGravitationalAcceleration(double coef) {
        this.setPhysicsParameter("stdGravity", coef);
    }

    public boolean isUnmovable() {
        return (Boolean) this.getPhysicsParameter("isUnmovable");
    }
    public void setMovable(boolean movable) {
        this.setPhysicsParameter("isUnmovable", !movable);
    }

    public boolean isPenetrable() {
        return (Boolean) this.getPhysicsParameter("isPenetrable");
    }
    public void setPenetrable(boolean penetrable) {
        this.setPhysicsParameter("isPenetrable", penetrable);
    }


}
