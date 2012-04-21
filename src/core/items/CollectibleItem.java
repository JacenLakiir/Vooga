package core.items;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import com.golden.gamedev.GameObject;

import core.characters.GameElement;
import core.characters.Player;
import core.physicsengine.physicsplugin.DefaultPhysicsAttribute;
import core.tiles.Tile;

/**
 * @author Kathleen Oshima
 */
@SuppressWarnings("serial")
public abstract class CollectibleItem extends GameElement {

	protected transient GameObject game;
	private boolean isInUse;
	protected transient HashMap<String, Double> myStateValues;

	// Constructor for a collectible item
	public CollectibleItem(GameObject game, DefaultPhysicsAttribute physicsAttribute) {
		super(game, physicsAttribute);
		myStateValues = new HashMap<String, Double>();
	}

	public CollectibleItem() {
		super();
	}

	public void decorate(Player player) {

		for (String state : myStateValues.keySet()) {
			updateStateValues(player, state, myStateValues.get(state));
		}
		this.setIsInUse(true);
	}

	public void set(BufferedImage[] images, double x, double y) {
		this.setImages(images);
		this.setLocation(x, y);
	}

	public void setIsInUse(boolean bool) {
		isInUse = bool;
	}

	public boolean isInUse() {
		return isInUse;
	}

	public void updateStateValues(Player player, String state, double newValue) {
		player.updateStateValues(state, newValue);
	}

	public void updateBaseValues(Player player, String state, double newValue) {
		player.updateBaseValues(state, newValue);
	}

	public void addState(String attribute, double defaultValue) {
		myStateValues.put(attribute, defaultValue);
	}

	public double getStateValue(String state) {
		return myStateValues.get(state);
	}

	public abstract boolean canSetInUse();
}
