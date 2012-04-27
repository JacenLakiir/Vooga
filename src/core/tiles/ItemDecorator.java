package core.tiles;

import io.annotations.Decorator;

import java.util.LinkedList;
import java.util.Queue;
import core.items.CollectibleItem;

@Decorator(target = Tile.class)
public class ItemDecorator extends ActionDecorator {
    private Queue<CollectibleItem> itemList;

    public ItemDecorator(Tile decoratedPlatform) {
	super(decoratedPlatform);
	itemList = new LinkedList<CollectibleItem>();
    }

    public void addItem(CollectibleItem item) {
	itemList.add(item);
    }

    public CollectibleItem removeItem() {
	return itemList.remove();
    }

    @Override
    public void doAction() {
	if (!itemList.isEmpty()) {
	    CollectibleItem item = removeItem();
	    item.setActive(true);
	    item.setLocation(getX(), getY() - getHeight());
	}
    }

}
