package core.playfield.hud;

public abstract class HUDLayoutManager {
	
	protected int width, height;
	protected SimplePoint nextPosition;
	
	public HUDLayoutManager (int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public abstract SimplePoint nextWidgetPosition(HUDWidget h);
	
	public class SimplePoint {
		private int x, y;
		
		public SimplePoint (int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
		
		public String toString() {
			return "X:" + x + " Y:" + y;
		}
	}

}
