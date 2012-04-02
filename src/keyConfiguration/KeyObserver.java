package keyConfiguration;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import characterSprites.Player;

/**
 * 
 * @author Hui Dong
 *
 */
public class KeyObserver {
    Player player;
    public KeyObserver(Player player){
        this.player = player;
    }
    public void Update(){
        
    }
    
    public void getActoinMethods(String action){
        Class<?> c = Player.class;
        Method[] methods = c.getMethods();
        for(Method m : methods){
            Annotation annotation = m.getAnnotation(KeyAnnotation.class);
            if(annotation instanceof KeyAnnotation ){
                KeyAnnotation key = (KeyAnnotation) annotation;
                if(key.action().equals(action)){
                    try {
                        m.invoke(player);
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    
    public void executeAction(){
        
    }
}
