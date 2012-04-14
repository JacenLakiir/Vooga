package core.gamestate;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.keyconfiguration.KeyAnnotation;

import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.background.ImageBackground;

import demo.DemoGameEngine;
/**
 * 
 * @author Hui Dong
 *
 */
public class Menu extends MenuGameObject{
    private int optionID = 0;
    DemoGameEngine engine;
    private Background background;
    private BufferedImage arrow;
    private static Map<Integer, String> map = new HashMap<Integer, String>();
    private List<GameObject> list = new ArrayList<GameObject>();
    private List<String> nameList = new ArrayList<String>();
    private int numberOfItems;
    public Menu(DemoGameEngine engine) {
        super(engine);
        this.engine = engine;
        list = engine.getGameObjects();
    }

    @Override
    public void initResources() {
        super.initResources();
        int i = 0;
        for(GameObject object : list){
            String name = object.getClass().getName();
            if(name.equals(Pause.class.getName()) || name.equals(Menu.class.getName()))
                continue;
            map.put(i, name);
            nameList.add(name);
            i++;
        }
        numberOfItems = i;
        background = new ImageBackground(getImage("resources/StarDust.jpg"), 640, 480);
        arrow = getImage("resources/MenuArrow.png");
    }

    @Override
    public void render(Graphics2D graphic) {
        background.render(graphic);
        graphic.setColor( Color.WHITE );
//        graphic.drawString("DemoAI", 320, 240);
//        graphic.drawString("DemoPlayField", 320, 260);     
//        graphic.drawString("DemoHUD", 320, 280);   
        int i = 0;
        for(String name : nameList){
            graphic.drawString(name, 320, 240+i*20);
            i++;
        }
        graphic.drawString("EXIT", 320, 240+i*20);
        graphic.drawImage(arrow, 300, 230 + optionID*20, null);
    }

    @Override
    public void update(long arg0) {
        super.update(arg0);
    }

    @KeyAnnotation(action = "down")
    public void  down(){
        if(optionID < numberOfItems){
            optionID++;
        }
    }
    
    @KeyAnnotation(action = "up")
    public void up(){
        if(optionID > 0){
            optionID--;
        }
    }
    
    @KeyAnnotation(action = "enter")
    public void nextGameObject(){
        if(optionID == numberOfItems){
            finish();
            return;
        }
        engine.nextGameID = engine.getGameID(map.get(optionID));
        engine.setPreviousGameID(engine.getGameID(map.get(optionID)));
        finish();
    }
    

}
