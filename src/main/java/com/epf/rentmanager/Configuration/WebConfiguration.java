package com.epf.rentmanager.Configuration;


import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(AppConfiguration.class);
        servletContext.addListener(new ContextLoaderListener(rootContext));
        }
    }

}
