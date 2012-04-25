package core.gamestate;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.configuration.key.KeyAnnotation;
/**
 * 
 * @author Hui Dong
 *
 */
public abstract  class MenuGameObject extends GameObject2D{
    private int optionID = 0;
    private int id = 0;
    private int numberOfItems;
    private  Map<Integer, Class<? extends GameObject2D>> map = new HashMap<Integer, Class<? extends GameObject2D>>();
    private List<String> nameList = new ArrayList<String>();
        
    protected int getOptionID(){
        return optionID;
    }
    
    protected void setNumberOfItems(int num){
        numberOfItems = num;
    }
    
    protected int getNumberOfItems(){
        return numberOfItems;
    }
    
    public MenuGameObject(GameEngine2D engine) {
        super(engine);
    }

    public abstract void buildMenu();
    
    public abstract void initialKeyList();
    
    protected Class<? extends GameObject2D> getNextGameObject(){
        getEngine().storeCurrentGameID(map.get(optionID));
        return map.get(optionID);
    }
    
    
    @Override
    public void initResources() {
        initialKeyList();
        addKeyListeners(this);
        buildMenu();
    }

    @Override
    public abstract void render(Graphics2D arg0);
    
    protected void addOptionToMenu(String optionName, Class<? extends GameObject2D> mclass){
        map.put(id++, mclass);
        nameList.add(optionName);
        numberOfItems++;
    }
    
    protected void addOptionToMenu(String optionName){
        nameList.add(optionName);
        numberOfItems++;
    }
    
    
    protected List<String> getOptionNames(){
        return nameList;
    }
        
    @KeyAnnotation(action = "up")
    public void up(){
        if(optionID > 0){
            optionID--;
        }
    }
    
    @KeyAnnotation(action = "down")
    public void  down(){
        if(optionID < numberOfItems - 1){
            optionID++;
        }
    }
    
    public abstract void nextGameObject();
      

}
