/**
 * @author Michael Zhou (Dominator008)
 */
package io.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Modifiable {
    
    public String classification();
    public String type();
    
}