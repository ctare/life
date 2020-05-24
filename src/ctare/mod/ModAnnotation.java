package ctare.mod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ctare on 2020/05/23.
 */

@Target({
        ElementType.METHOD
})
@Retention(RetentionPolicy.RUNTIME)
public @interface ModAnnotation {
    enum ModType {
        INITIALIZER
    }
    ModType type();
}
