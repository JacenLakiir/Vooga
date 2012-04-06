package setting;

import java.util.LinkedList;
import java.util.Queue;

import voogaobject.GameElement;

import charactersprites.CollectibleItemSprite;

public class ItemDecorator extends PlatformDecorator{
	private Queue<CollectibleItemSprite> itemList;
	
	public ItemDecorator(Platform decoratedPlatform) {
		super(decoratedPlatform);
		itemList = new LinkedList<CollectibleItemSprite>();
	}
	
	public void addItem(CollectibleItemSprite item){
		itemList.add(item);
	}
	
	public CollectibleItemSprite removeItem(){
		return itemList.remove();
	}

	@Override
	public void afterHitFromBottomBy(GameElement e){
		if(!itemList.isEmpty()){
			CollectibleItemSprite item = removeItem();
			item.setLocation(getX()+getWidth(), getY()+getHeight());
		}
		else{
			setImages(myGame.getImages("resources/Block3.png", 1, 1));
		}
		decoratedPlatform.afterHitFromBottomBy(e);
	}
	
}
