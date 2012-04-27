package core.playfield.hud;

import java.util.List;
import core.items.CollectibleItem;

public abstract class InventoryProxy extends DataProxy {
	
	public abstract List<CollectibleItem> get();

}
