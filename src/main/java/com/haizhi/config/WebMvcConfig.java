package com.haizhi.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class WebMvcConfig implements WebMvcConfigurer {

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("开始设置静态资源映射。。");
//        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//        registry.addResourceHandler("/swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");

    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/druidMonitor").setViewName("redirect:druid/index.html");
        registry.addViewController("/swaggerApi").setViewName("redirect:swagger-ui.html");
    }
}
