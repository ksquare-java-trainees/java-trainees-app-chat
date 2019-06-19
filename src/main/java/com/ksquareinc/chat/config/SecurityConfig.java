package com.ksquareinc.chat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

import com.ksquareinc.chat.security.CustomAuthenticationManager;
import com.ksquareinc.chat.security.HttpAuthenticationEntryPoint;
import com.ksquareinc.chat.security.PreAuthTokenHeaderFilter;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:application.properties")
@ComponentScan("com.ksquareinc.chat.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Value("${ksquareChat.http.auth.userName}")
    private String userHeaderName;
    
    @Value("${ksquareChat.http.auth.tokenName}")
    private String tokenHeaderName;
    
    @Autowired
    private HttpAuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
    	PreAuthTokenHeaderFilter filter = new PreAuthTokenHeaderFilter(userHeaderName, tokenHeaderName);
        filter.setAuthenticationManager(new CustomAuthenticationManager());
        
        //SECURITY FOR REST ENDPOINTS
        http.antMatcher("/api/**")
        		.addFilter(filter)
        		.addFilterBefore(new ExceptionTranslationFilter(new Http403ForbiddenEntryPoint()),filter.getClass())
        		.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                /*.and()
                .formLogin()
                .failureHandler(authFailureHandler)*/
                .and()                
        			.authorizeRequests()
        			.anyRequest().authenticated()
        		.and()
            		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            	.and()
            		.csrf().disable();
	}
}
