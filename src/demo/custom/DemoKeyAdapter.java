package demo.custom;

import core.keyconfiguration.InputKeyDown;
import core.keyconfiguration.InputKeyPress;
import core.keyconfiguration.InputKeySequential;
import core.keyconfiguration.KeyAdapter;
/**
 * 
 * @author Hui Dong
 *
 */
public class DemoKeyAdapter extends KeyAdapter{

    public DemoKeyAdapter(String elementName) {
        super(elementName);
    }

    @Override
    public void registerKeys() {
      registerKey("keydown", InputKeyDown.class);
      registerKey("keypress", InputKeyPress.class);
      registerKey("keysequence", InputKeySequential.class);    
    }

}
