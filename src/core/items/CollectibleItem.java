package core.items;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import com.golden.gamedev.GameObject;

import core.characters.Character;
import core.characters.GameElement;
import core.physicsengine.physicsplugin.PhysicsAttributes;

/**
 * @author Kathleen Oshima
 */
@SuppressWarnings("serial")
public abstract class CollectibleItem extends GameElement {

	protected transient GameObject game;
	private boolean isInUse;
	protected transient HashMap<String, Double> myAttributeValues;

	// Constructor for a collectible item
	public CollectibleItem(GameObject game, PhysicsAttributes physicsAttribute) {
		super(game, physicsAttribute);
		myAttributeValues = new HashMap<String, Double>();
	}

	public CollectibleItem() {
		super();
	}

	public void updatePlayerAttributes(Character player) {

		for (String state : myAttributeValues.keySet()) {
			updateAttributeValues(player, state, myAttributeValues.get(state));
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

	public void updateAttributeValues(Character player, String state, double newValue) {
		player.updateAttributeValue(state, newValue);
	}

	public void updateBaseValues(Character player, String state, double newValue) {
		player.updateBaseValue(state, newValue);
	}

	public void addAttribute(String attribute, double defaultValue) {
		myAttributeValues.put(attribute, defaultValue);
	}

	public double getAttributeValue(String state) {
		return myAttributeValues.get(state);
	}

	public abstract boolean canSetInUse();
}
