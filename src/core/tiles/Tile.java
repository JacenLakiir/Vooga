/**
 * Class to handle all platform and ground types
 * 
 * @author Glenn Rivkees
 * @author Ian McMahon
 */

package core.tiles;

import io.annotations.Decorable;
import com.golden.gamedev.GameObject;
import core.characters.GameElement;
import core.physicsengine.physicsplugin.PhysicsAttributes;

@Decorable
public class Tile extends GameElement {

    /**
     * 
     */
    private static final long serialVersionUID = 8811036246722569523L;

    /*
     * Constructor for a Concrete Platform
     */
    public Tile(GameObject owner, PhysicsAttributes physicsAttribute) {
        this(physicsAttribute);
        setGame(owner);
    }

    public Tile(PhysicsAttributes physicsAttribute) {
        super(physicsAttribute);
        this.getPhysicsAttribute().setMovable(false);
        this.getPhysicsAttribute().setPenetrable(false);
    }
    
    /*
     * Constructor for a Platform Decorator
     */
    public Tile() {
        super();
    }
    
    public Tile removeDecorator(){
    	return this;
    }
}
