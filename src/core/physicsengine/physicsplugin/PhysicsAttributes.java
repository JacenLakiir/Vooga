/**
 * @author Kuang Han
 */

package core.physicsengine.physicsplugin;

import io.annotations.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

@DefaultPhysicsAttribute
public class PhysicsAttributes implements java.io.Serializable {

/*  @Deprecated   
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
    
    private static final long serialVersionUID = 4195772754476104956L;
    
    @DefaultValueMap(classification = "Physics")
    protected static Map<String, Serializable> defaultPhysicsPara;
    static {
        defaultPhysicsPara = new HashMap<String, Serializable>();
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
    
    @ModifiableMap(classification = "Physics")
    protected HashMap<String, Serializable> physicsPara;

    public PhysicsAttributes() {
        physicsPara = new HashMap<String, Serializable>(defaultPhysicsPara);
    }
    
    public PhysicsAttributes(Map<String, Serializable> physicspara) {
        physicsPara = new HashMap<String, Serializable>(physicspara);
    }
    
    public Map<String, Serializable> getPhysicsAttrMap() {
	return Collections.unmodifiableMap(physicsPara);
    }
    
    protected void setPhysicsParameter(String s, Serializable t) {
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