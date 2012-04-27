/**
 * @author Ian McMahon
 */
package core.tiles;

import io.annotations.Decorator;
import io.annotations.Modifiable;


@Decorator(target = Tile.class)
public class BreakableDecorator extends ActionDecorator {
    
    private static final long serialVersionUID = 7455338340514721807L;

	@Modifiable(classification = "Gameplay", type = "Individual")
	private boolean broken;
    	
        @Modifiable(classification = "Gameplay", type = "Individual")
	private int blockStrength;

	public BreakableDecorator(Tile decoratedPlatform) {
		super(decoratedPlatform);
		this.blockStrength = 1;
		setAnimate(false);
	}
	
	public BreakableDecorator(Tile decoratedPlatform, int blockStrength) {
		super(decoratedPlatform);
		this.blockStrength = blockStrength;
		setAnimate(false);
	}
	
	public void doAction(){
		blockStrength--;
		if(blockStrength<=0){
			broken = true;
			getAnimationTimer().setDelay(20);
			setAnimate(true);
		}
	}
	
	public void update(long elapsedTime) {
        super.update(elapsedTime);
        if (broken && !this.isAnimate()) {
                this.setActive(false);
        }
	}
}
