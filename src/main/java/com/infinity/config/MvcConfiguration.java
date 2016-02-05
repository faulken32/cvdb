package com.infinity.config;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import org.apache.velocity.app.VelocityEngine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.infinity"})
@Import({ SecurityConfig.class })
@PropertySource("classpath:application.properties")
@EnableScheduling

public class MvcConfiguration extends WebMvcConfigurerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(MvcConfiguration.class);

    private static final List<String> DEFAULT_TILES_DEFINITIONS = new LinkedList<>();

    static {

        DEFAULT_TILES_DEFINITIONS.add("/WEB-INF/tiles.xml");
        DEFAULT_TILES_DEFINITIONS.add("/WEB-INF/view.xml");
    }

//    @Bean
//    public TilesViewResolver tilesViewResolver() {
//        return new TilesViewResolver();
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/").setCachePeriod(31556926);;
    }



    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }


    @Bean
    public UrlBasedViewResolver viewResolver(){
    
        UrlBasedViewResolver urlBasedViewResolver = new UrlBasedViewResolver();
        urlBasedViewResolver.setCache(true);
        urlBasedViewResolver.setViewClass(TilesView.class);
        
        return urlBasedViewResolver;
    }
    
    
    /**
     *
     * @return
     */
    @Bean
    public ResourceBundleViewResolver resourceBundleViewResolver(){
    
        ResourceBundleViewResolver resourceBundleViewResolver = new ResourceBundleViewResolver();
        resourceBundleViewResolver.setBasename("views");
        return resourceBundleViewResolver; 
    }
    
   

    /**
     * 
     * @return 
     */
    @Bean
    public TilesConfigurer tilesConfigurer() {

        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions(DEFAULT_TILES_DEFINITIONS.toArray(new String[DEFAULT_TILES_DEFINITIONS.size()]));

        return tilesConfigurer;
    }
    /**
     * 
     * @return
     * @throws IOException 
     */
    @Bean
    public VelocityEngine velocityEngineFactoryBean() throws IOException{
        
        VelocityEngineFactoryBean velocityEngineFactoryBean = new VelocityEngineFactoryBean();
        Properties props = new Properties();
        props.put("resource.loader", "class");
	props.put("class.resource.loader.class", 
			  "org.apache.velocity.runtime.resource.loader." + 
			  "ClasspathResourceLoader");
	velocityEngineFactoryBean.setVelocityProperties(props);
    
        return velocityEngineFactoryBean.createVelocityEngine();
    }
}
