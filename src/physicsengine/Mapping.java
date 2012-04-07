/**
 * @author Kuang Han
 */

package physicsengine;

public abstract class Mapping {
    private NewtonianSprite owner;
    
    public Mapping(NewtonianSprite owner) {
        this.owner = owner;
    }

    public abstract double getXforTime(int t);
    
    public abstract double getYforTime(int t) ;
    
    public NewtonianSprite getOwner() {
        return owner;
    }
}
