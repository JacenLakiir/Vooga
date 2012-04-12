package core.keyconfiguration;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import core.characters.GameElement;
import core.characters.Player;

public class InputKeyObserver extends KeyObserver{
    private GameElement element;
    public InputKeyObserver(GameElement element) {
        this.element = element;
    }
    
    public void getActoinMethods(String action){
        Class<?> c = element.getClass();
        Method[] methods = c.getMethods();
        for(Method m : methods){
            Annotation annotation = m.getAnnotation(KeyAnnotation.class);
            if(annotation instanceof KeyAnnotation ){
                KeyAnnotation key = (KeyAnnotation) annotation;
                if(key.action().equals(action)){
                    try {
                        m.invoke(element);
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

}
