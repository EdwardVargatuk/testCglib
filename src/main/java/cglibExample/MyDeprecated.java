package cglibExample;

import java.lang.annotation.*;

/**
 * 11.09.2019 20:15
 *
 * @author Edward
 */
@Inherited
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface MyDeprecated {
}
