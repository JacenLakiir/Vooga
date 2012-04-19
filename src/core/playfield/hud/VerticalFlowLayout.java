package core.playfield.hud;

public class VerticalFlowLayout extends HUDLayoutManager {

	public VerticalFlowLayout(int width, int height) {
		super(width, height);
		this.nextPosition = new SimplePoint(0,0);
	}

	public SimplePoint nextWidgetPosition(HUDWidget w) {
		SimplePoint temp = this.nextPosition;
		this.nextPosition =  new SimplePoint(temp.getX(), temp.getY() + w.height);
		return temp;
	}

}
