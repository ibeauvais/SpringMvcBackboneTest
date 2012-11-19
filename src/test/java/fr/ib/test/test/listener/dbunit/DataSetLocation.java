package fr.ib.test.test.listener.dbunit;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
public @interface DataSetLocation {

    public String value();
}
