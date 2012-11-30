package fr.ib.test.springMvcTest.common.aspect;

import ch.qos.logback.classic.Level;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class LogAspectTest {

    @Mock
    private ProceedingJoinPoint proceedingJoinPoint;

    @Mock
    private Signature signature;

    private OutputStream stream = new ByteArrayOutputStream();
    private PrintStream out,systemOut;


    @Before
    public void init() {
       systemOut=System.out;
        out = new PrintStream(stream);
        System.setOut(out);
        Mockito.when(signature.getName()).thenReturn("methodTest");
        Mockito.when(proceedingJoinPoint.getSignature()).thenReturn(signature);
        Mockito.when(proceedingJoinPoint.getTarget()).thenReturn(this);
        Mockito.when(proceedingJoinPoint.getArgs()).thenReturn(new Object[]{"abc", 1, 5.4});
    }

    @Test
    public void testLogMethodDebug() throws Throwable {
             ((ch.qos.logback.classic.Logger) LoggerFactory.getLogger(LogAspectTest.class)).setLevel(Level.DEBUG);

        new LogAspect().logMethod(proceedingJoinPoint,null);

        out.flush();
        assertThat( stream.toString()).contains(">> methodTest [abc, 1, 5.4]").contains("<< methodTest").contains("DEBUG");


    }

    @Test
      public void testLogMethodWarn() throws Throwable {
               ((ch.qos.logback.classic.Logger) LoggerFactory.getLogger(LogAspectTest.class)).setLevel(Level.WARN);

          new LogAspect().logMethod(proceedingJoinPoint,null);

          out.flush();

          assertThat( stream.toString()).isEmpty();


      }



    @After
    public void after() {
        out.close();
          System.setOut(systemOut);
    }
}
