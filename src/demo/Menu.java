package demo;

import io.SimpleGameToTestLevelEditor;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.background.ImageBackground;
import core.configuration.key.KeyAnnotation;
import core.configuration.key.KeyParser;
import core.gamestate.GameEngine2D;
import core.gamestate.MenuGameObject;
import demo.custom.DemoKeyAdapter;

/**
 * 
 * @author Hui Dong
 *
 */
@SuppressWarnings("serial")
public class Menu extends MenuGameObject{
   
    private static final int WINDOW_WIDTH = 640;
    private static final int WINDOW_HEIGHT = 480;
    private static final int TEXT_HEIGHT = 20;
    
    private Background background;
    private BufferedImage arrow;
    public Menu(GameEngine2D engine) {
        super(engine);
    }

    @Override
    public void buildMenu() {
        addOptionToMenu("DemoAI", DemoAI.class);
        addOptionToMenu("DemoPlayfield", DemoPlayfield.class);
        addOptionToMenu("DemoHUD", DemoHUD.class);
        addOptionToMenu("DemoAntigravity", DemoAntigravity.class);
        addOptionToMenu("LevelEditorTest", SimpleGameToTestLevelEditor.class);
        addOptionToMenu("Exit");
    }
    
    
    @Override
    public void initResources() {
        super.initResources();
        background = new ImageBackground(getImage("resources/StarDust.jpg"), WINDOW_WIDTH, WINDOW_HEIGHT);
        arrow = getImage("resources/MenuArrow.png");
    }

    @Override
    public void render(Graphics2D graphic) {
        background.render(graphic);
        graphic.setColor( Color.WHITE );  
        int i = 0;
        for(String name : getOptionNames()){
            graphic.drawString(name, WINDOW_WIDTH/2, WINDOW_HEIGHT/2+i*TEXT_HEIGHT);
            i++;
        }
        graphic.drawImage(arrow, WINDOW_WIDTH/2-20, WINDOW_HEIGHT/2 - TEXT_HEIGHT/2 + getOptionID()*20, null);
    }

    @Override
    public void update(long arg0) {
        super.update(arg0);
    }

    
    @KeyAnnotation(action = "enter")
    public void nextGameObject(){
        if(getOptionID() == getNumberOfItems() - 1 ){
            finish();
            return;
        }
        switchToGameObject(getNextGameObject());
        finish();
    }

    @Override
    public void initialKeyList() {
        setKeyList(new KeyParser(this, true, new DemoKeyAdapter("key_type")).parseKeyConfig("configurations/keyconfig.json"));        
    }
}
