package ioc;

import java.lang.annotation.*;

/**
 * @author think
 * @version 1.0
 * @date 2022/3/29 16:42
 * 自定义注解
 */
@Target(ElementType.FIELD)
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface GhAnnotation {
}
