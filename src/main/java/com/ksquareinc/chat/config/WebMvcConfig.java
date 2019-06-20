package com.ksquareinc.chat.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@ComponentScan("com.ksquareinc.chat.controller")
public class WebMvcConfig implements WebMvcConfigurer{

	// WEBJARS CONFIGURATION ONLY NEED FOR CLIENT
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/webjars/**")
	            .addResourceLocations("/webjars/").resourceChain(true);
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
}