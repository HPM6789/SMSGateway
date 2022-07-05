package com.mycompany.smsgateway.config;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        stringHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "plain", UTF8)));
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//       registry.addResourceHandler("/css/**").addResourceLocations("/assets/css/").setCachePeriod(31556926);
//       registry.addResourceHandler("/img/**").addResourceLocations("/assets/img/").setCachePeriod(31556926);
//       registry.addResourceHandler("/js/**").addResourceLocations("/assets/js/").setCachePeriod(31556926);
//       registry.addResourceHandler("/vendor/**").addResourceLocations("/assets/vendor/").setCachePeriod(31556926);
        registry.addResourceHandler("/assets/**").addResourceLocations("/assets/").setCachePeriod(31556926);

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}
