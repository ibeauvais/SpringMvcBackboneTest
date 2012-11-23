package fr.ib.test.springMvcTest.configuration;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;

public class WebConfigurer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        XmlWebApplicationContext rootContext = new XmlWebApplicationContext();
        rootContext.setConfigLocation("classpath:/spring/*.xml");
        rootContext.refresh();
        servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, rootContext);

        DispatcherServlet d = new DispatcherServlet();
        d.setContextConfigLocation("classpath:mvc.xml");
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
                "mainServlet", d);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
