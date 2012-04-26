package core.items;

import java.util.List;

public class ItemInventory {

	private transient List<CollectibleItem> myInventory, myActiveInventory;

	public ItemInventory() {
	}
	
    public void updateInventory(CollectibleItem item) {
    	myInventory.remove(item);
    }
	
	public List<CollectibleItem> getMyInventory() {
		return myInventory;
	}

	public List<CollectibleItem> getMyActiveInventory() {
		return myActiveInventory;
	}
	
	public void useInventoryItem(CollectibleItem item) {
    	item.setActive(true);
    }

	public void unuseInventoryItem(CollectibleItem item) {
    	item.setActive(false);
    }
}
