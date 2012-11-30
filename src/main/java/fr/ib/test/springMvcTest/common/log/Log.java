package fr.ib.test.springMvcTest.common.log;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public  @interface  Log {

}
