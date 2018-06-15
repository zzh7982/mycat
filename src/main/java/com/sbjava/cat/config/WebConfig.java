package com.sbjava.cat.config;

import com.sbjava.cat.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.LinkedList;
import java.util.List;

/**
 * description: WebConfig
 *
 * @author ralf
 * @version [1.0, 2018/6/15]
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Autowired
    private TokenInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> list = new LinkedList<>();
        list.add("/");
        list.add("/wx/login");
        list.add("/error");
        list.add("/swagger**/**");
        list.add("/webjars/**");
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(list);
        super.addInterceptors(registry);
    }

    /**
     * 发现如果继承了WebMvcConfigurationSupport，则在yml中配置的相关内容会失效。 需要重新指定静态资源
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(
                "classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations(
                "classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations(
                "classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }
}
