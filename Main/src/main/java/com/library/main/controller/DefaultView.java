package com.library.main.controller;
import org.springframework.core.Ordered;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
    public class DefaultView extends WebMvcConfigurerAdapter {

    //        @Override
//        public void addViewControllers( ViewControllerRegistry registry ) {
//            registry.addViewController( "/" ).setViewName( "forward:/yourpage.html" );
//            registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
//            super.addViewControllers( registry );
//        }
//public void addViewControllers(ViewControllerRegistry registry) {
//    registry.addViewController("/").setViewName("/login");
//    registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//}
//    }
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:login.jsp");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }
}

