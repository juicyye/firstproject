package kozin.first;


import kozin.first.aspect.Aspect;
import kozin.first.aspect.trace.logtrace.LogTrace;
import kozin.first.aspect.trace.logtrace.ThreadLocalLogTrace;
import kozin.first.web.argumentresolver.LoginMemberArgumentResolver;
import kozin.first.web.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/*.ico","/login","/logout","/css/**","/js/**","/error","/","/members/join");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArgumentResolver());
    }

    @Bean
    public LogTrace logTrace(){
        return new ThreadLocalLogTrace();
    }

    @Bean
    public Aspect aspect(){
        return new Aspect(logTrace());
    }
}
