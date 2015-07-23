package com.infinity.config;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;

import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.infinity"})

@PropertySource("classpath:application.properties")

public class MvcConfiguration extends WebMvcConfigurerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(MvcConfiguration.class);

    private static final List<String> DEFAULT_TILES_DEFINITIONS = new LinkedList<>();

    static {

        DEFAULT_TILES_DEFINITIONS.add("/WEB-INF/tiles.xml");
        DEFAULT_TILES_DEFINITIONS.add("/WEB-INF/view.xml");
    }

    @Bean
    public TilesViewResolver tilesViewResolver() {
        return new TilesViewResolver();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

//    @Bean
//    public StringHttpMessageConverter stringHttpMessageConverter() {
//        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
//    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(name = "multipartResolver")
    public StandardServletMultipartResolver multipartResolver() {

        return new StandardServletMultipartResolver();
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
    

    
//    @Bean
//    public RequestMappingHandlerAdapter annotationMethodHandlerAdapter(){
//        
//        
//        RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();
//        
//        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
//        requestMappingHandlerAdapter.getMessageConverters().add(stringHttpMessageConverter);
//                       
//        
//        return requestMappingHandlerAdapter;
//    }
    
    @Bean
    public TilesConfigurer tilesConfigurer() {

        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions(DEFAULT_TILES_DEFINITIONS.toArray(new String[DEFAULT_TILES_DEFINITIONS.size()]));

        return tilesConfigurer;
    }
}
