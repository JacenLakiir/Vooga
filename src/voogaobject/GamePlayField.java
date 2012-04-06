/**
 * @author Kuang Han
 */


package voogaobject;


import collision.PlayerCollectibleItemCollision;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.util.Utility;

public class GamePlayField extends PlayField{
    protected GameElementCollision[] myCollisionGroups = new GameElementCollision[0];
    
    public GamePlayField() {
        super();
    }
    
    public GamePlayField(Background background) {
        super(background);
    }
    
    public void addCollisionGroup(GameElementCollision collisionGroup) {
        myCollisionGroups = (GameElementCollision[]) Utility.expand(myCollisionGroups,1);
        myCollisionGroups[myCollisionGroups.length - 1] = collisionGroup;
    }
    
    
    @Override
    protected void checkCollisions() {
        for (int i = 0; i < myCollisionGroups.length; i++) {
            if (myCollisionGroups[i].isActive()) {
                myCollisionGroups[i].checkCollision();
            }
        }
    }
    
    @Override
    public CollisionManager[] getCollisionGroups() {
        return myCollisionGroups;
    }
    
    @Override
    public boolean removeCollisionGroup(CollisionManager collisionGroup) {
        for (int i = 0; i < myCollisionGroups.length; i++) {
            if (myCollisionGroups[i].equals(collisionGroup)) {
                myCollisionGroups = (GameElementCollision[]) Utility.cut(myCollisionGroups, i);
                return true; 
            }
        }
        return false;
    }
    
    @Override
    public void addCollisionGroup(SpriteGroup group1, SpriteGroup group2, CollisionManager collisionGroup) {
        System.err.println("Forbidden Method");
    }
    
    @Override
    public CollisionManager getCollisionGroup(SpriteGroup group) {
        System.err.println("Forbidden Method");
        return null;
    }

    @Override
    public CollisionManager getCollisionGroup(SpriteGroup group1, SpriteGroup group2) {
        System.err.println("Forbidden Method");
        return null;
    }

}
