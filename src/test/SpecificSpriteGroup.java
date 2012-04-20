package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;

public class SpecificSpriteGroup<T extends Sprite> {

    private ArrayList<T> mySprites;
    
    public SpecificSpriteGroup() {
	mySprites = new ArrayList<T>();
    }
    
    public void addSprite(T s) {
	mySprites.add(s);
    }
    
    public void removeInactiveSprites() {
	for (int i = 0; i < mySprites.size(); i++)
	    if (!mySprites.get(i).isActive())
		mySprites.remove(i);
    }
    
    public void sort(Comparator<T> comp) {
	Collections.sort(mySprites, comp);
    }
    
    public void addAll(List<? extends T> toAdd) {
	for (T sprite: toAdd)
	    addSprite(sprite);
    }
    
    public static void main(String[] args) {
	AnimatedSprite s = new AnimatedSprite();
	SpecificSpriteGroup<Sprite> group = new SpecificSpriteGroup<Sprite>();
	List<AnimatedSprite> list = new ArrayList<AnimatedSprite>();
	group.addSprite(s);
	group.addAll(list);
    }
    
}