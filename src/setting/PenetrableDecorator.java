package setting;

public class PenetrableDecorator extends PlatformDecorator{
	private double strength;
	
	public PenetrableDecorator(Platform decoratedPlatform,  double strength) {
		super(decoratedPlatform);
		this.decoratedPlatform.setPenetrable(true);
		this.strength = strength;
	}
	
	public double getStrength() {
	        return strength;
	}

}
