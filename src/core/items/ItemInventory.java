package core.items;

import java.util.ArrayList;
import java.util.List;

public class ItemInventory implements java.io.Serializable {

	private List<CollectibleItem> myInventory;

	public ItemInventory() {
		myInventory = new ArrayList<CollectibleItem>();
	}
	
    public void updateInventory(CollectibleItem item) {
    	myInventory.remove(item);
    }
	
	public List<CollectibleItem> getInventory() {
		return myInventory;
	}
	
	public void useInventoryItem(CollectibleItem item) {
    	item.setActive(true);
    }

	public void unuseInventoryItem(CollectibleItem item) {
    	item.setActive(false);
    }
}
