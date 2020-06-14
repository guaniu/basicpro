package com.yiding.saas.ydsaas.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 注册登录拦截器 RestTemplate
 */
@Configuration
public class CoreConfig implements WebMvcConfigurer {

    /**
     * 不需要登录接口规则
     */
    String[] excludePathPatterns = new String[]{"/web/login", "/api/login", "/api/open/**", "/favicon.ico", "/swagger-ui.html", "/swagger/**", "/swagger-resources/**", "/webjars/**"};
    /**
     * 需要登录拦截接口规则
     */
    String[] pathPatterns = new String[]{"/**"};


    @Autowired
    private LoginInterceptor loginInterceptor;

    /**
     * restTemplate超时时间 2m
     */
    private int outtime = 2 * 60 * 1000;

    /**
     * 注册拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptor = registry.addInterceptor(loginInterceptor);
        interceptor.addPathPatterns(pathPatterns);
        interceptor.excludePathPatterns(excludePathPatterns);
    }


    /**
     * http 调用
     *
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(outtime);
        requestFactory.setReadTimeout(outtime);
        return new RestTemplate(requestFactory);
    }

    /**
     * 跨域处理
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.setAllowCredentials(true);
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        return new CorsFilter(configSource);
    }

}