package setting;

import items.CollectibleItem;


import java.util.LinkedList;
import java.util.Queue;

import charactersprites.GameElement;




public class ItemDecorator extends PlatformDecorator{
	private Queue<CollectibleItem> itemList;
	
	public ItemDecorator(Platform decoratedPlatform) {
		super(decoratedPlatform);
		itemList = new LinkedList<CollectibleItem>();
	}
	
	public void addItem(CollectibleItem item){
		itemList.add(item);
	}
	
	public CollectibleItem removeItem(){
		return itemList.remove();
	}

	@Override
	public void afterHitFromBottomBy(GameElement e){
		if(!itemList.isEmpty()){
			CollectibleItem item = removeItem();
			item.setActive(true);
			item.setLocation(getX(), getY()-getHeight());
		}
		if (itemList.size() == 0){
			setImages(myGame.getImages("resources/Block3.png", 1, 1));
		}
		decoratedPlatform.afterHitFromBottomBy(e);
	}
	
}
