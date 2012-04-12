package tiles;


import charactersprites.GameElement;


public class MovingDecorator extends TileDecorator{
	private double startX, startY, endX, endY, speed;


	public MovingDecorator(Tile decoratedPlatform, double startX, double startY, double endX, double endY, double speed) {
		super(decoratedPlatform);
		setLocation(startX, startY);
		setEndLocation(endX, endY);
		this.speed = speed;
	}
	
	@Override
	public void setLocation(double xs, double ys){
		startX = xs;
		startY = ys;
		decoratedPlatform.setLocation(xs, ys);
	}
	
	public void setEndLocation (double endX, double endY){
		this.endX = endX;
		this.endY = endY;
	}
	
	public void afterHitFromBottomBy(GameElement e) {
		//Will only affect the vertical factor of a sprite beneath
		e.move(0, getY()-getOldY());
		super.afterHitFromBottomBy(e);
	}

	public void afterHitFromTopBy(GameElement e) {
		//Will carry player on top
		e.move(getX()-getOldX(), getY()-getOldY());
		super.afterHitFromTopBy(e);
	}
	
	public void afterHitFromLeftBy(GameElement e) {
		e.move(getX()-getOldX(), 0);
		super.afterHitFromLeftBy(e);
	}
	
	public void afterHitFromRightBy(GameElement e) {
		e.move(getX()-getOldX(), 0);
		super.afterHitFromRightBy(e);
	}
	
	@Override
	public void update(long elapsedTime){
		super.update(elapsedTime);
		if(moveTo(elapsedTime, endX, endY, speed)){
			double tempX = endX;
			double tempY = endY;
			endX = startX;
			endY = startY;
			startX = tempX;
			startY = tempY;
		}
		
	}
}
