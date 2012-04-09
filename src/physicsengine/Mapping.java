/**
 * @author Kuang Han
 */

package physicsengine;

import charactersprites.GameElement;

public abstract class Mapping {
    private GameElement owner;
    
    public Mapping(GameElement owner) {
        this.owner = owner;
    }

    public abstract double getXforTime(int t);
    
    public abstract double getYforTime(int t) ;
    
    public GameElement getOwner() {
        return owner;
    }
}
