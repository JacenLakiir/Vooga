package items;

import java.util.ArrayList;

import setting.Platform;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameObject;

public class CollectibleInventoryItem extends CollectibleItem {

	private double attackPower;
	private double defensePower;
	private double hitPoints;
	private double level;
	private ArrayList<Double> newPowers = new ArrayList<Double>();
	private CollectibleItem collectibleItem;
	
	public CollectibleInventoryItem(GameObject game) {
	    super(game);
	    this.isUnmovable = true;
		this.isPenetrable = true;    }

	public ArrayList<Double> updatePower() {
		return newPowers;
	}
	
	@Override
    public void attackPower() {
	    newPowers.add(attackPower);
    }

	@Override
    public void defensePower( ) {
	    newPowers.add(defensePower);
	 }

	@Override
    public void hitPoints() {
	    newPowers.add(hitPoints);
    }

	@Override
	public void level() {
		newPowers.add(level);
	}
	
}