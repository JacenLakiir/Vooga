package core.tiles;

import core.characters.GameElement;

public abstract class ActionDecorator extends TileDecorator {

	private boolean rightAction,leftAction,topAction,bottomAction;
	public ActionDecorator(Tile decoratedPlatform) {
		super(decoratedPlatform);
	}
	
	public abstract void doAction();

	public void setRightAction(boolean active){
		rightAction = active;
	}
	public void setLeftAction(boolean active){
		leftAction = active;
	}
	public void setTopAction(boolean active){
		topAction = active;
	}
	public void setBottomAction(boolean active){
		bottomAction = active;
	}
	
	
	public void afterHitFromBottomBy(GameElement e) {
		super.afterHitFromBottomBy(e);
		if(bottomAction){
			doAction();
		}
	}
	public void afterHitFromTopBy(GameElement e) {
		super.afterHitFromTopBy(e);
		if(topAction){
			doAction();
		}
	}
	public void afterHitFromRightBy(GameElement e) {
		super.afterHitFromRightBy(e);
		if(rightAction){ 
			doAction();
		}
	}
	public void afterHitFromLeftBy(GameElement e) {
		super.afterHitFromLeftBy(e);
		if(leftAction){ 
			doAction();
		}
	}

}
