package core.items;

import java.util.ArrayList;



import com.golden.gamedev.Game;
import com.golden.gamedev.GameObject;

import core.characters.Player;
import core.tiles.Tile;
/**
 * @author Kathleen Oshima
 */
public class CollectibleInventoryItem extends CollectibleItem {

	private double attackPower;
	private double defensePower;
	private double hitPoints;
	private double level;
	private double points;
	private ArrayList<Double> newPowers = new ArrayList<Double>();
	
	public CollectibleInventoryItem(GameObject game) {
	    super(game);
	}
	
	public ArrayList<Double> updatePower() {
		return newPowers;
	}
	
	@Override
    public void updatePlayerAttackPower(Player player) {
	    newPowers.add(attackPower);
    }

	@Override
    public void updatePlayerDefensePower(Player player ) {
	    newPowers.add(defensePower);
	 }

	@Override
    public void updatePlayerHitPoints(Player player) {
	    newPowers.add(hitPoints);
    }

	@Override
	public void updatePlayerLevel(Player player) {
		newPowers.add(level);
	}

	@Override
    public void updatePlayerPoints(Player player) {
		newPowers.add(points);
    }
	
}