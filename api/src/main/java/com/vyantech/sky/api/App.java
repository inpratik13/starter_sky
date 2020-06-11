package com.vyantech.sky.api;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.vyantech.sky.api.rest.config.RestConfig;

public class App {
    
    public static void main(String[] args) {
    	Server server = new Server(8080);

        ServletContextHandler ctx = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
                
        ctx.setContextPath("/");
        ctx.addEventListener(new ContextLoaderListener());
        ctx.addEventListener(new RequestContextListener());
        
        ctx.setInitParameter("contextClass", AnnotationConfigWebApplicationContext.class.getName());
        ctx.setInitParameter("contextConfigLocation", ApiSpringConfig.class.getName());
        
        server.setHandler(ctx);

        ServletHolder serHol = ctx.addServlet(ServletContainer.class, "/rest/*");
        serHol.setInitOrder(1);
        serHol.setInitParameter("jersey.config.server.provider.packages", "com.vyantech.sky.api.rest");
        serHol.setInitParameter("javax.ws.rs.Application", RestConfig.class.getCanonicalName());

        try {
            server.start();
            server.join();
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            server.destroy();
        }
    }
}
