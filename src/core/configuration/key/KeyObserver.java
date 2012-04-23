package core.configuration.key;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;



/**
 * 
 * @author Hui Dong
 *
 */
public  class KeyObserver { 
    private Object object;
    public KeyObserver(Object object) {
        this.object = object;
    }
    
    public void getActionMethods(String action){
        Class<?> c = object.getClass();
        Method[] methods = c.getMethods();
        for(Method m : methods){
            Annotation annotation = m.getAnnotation(KeyAnnotation.class);
            if(annotation instanceof KeyAnnotation ){
                KeyAnnotation key = (KeyAnnotation) annotation;
                if(key.action().equals(action)){
                    try {
                        m.invoke(object);
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
    }}
