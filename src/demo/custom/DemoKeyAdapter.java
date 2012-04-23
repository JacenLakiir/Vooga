package demo.custom;

import core.configuration.key.InputKeyDown;
import core.configuration.key.InputKeyPress;
import core.configuration.key.InputKeySequential;
import core.configuration.key.KeyAdapter;
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
