package per.wq.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import per.wq.springboot.component.LoginHandlerInterceptor;
import per.wq.springboot.component.MyFormatter;
import per.wq.springboot.component.MyLocaleResolver;

/**
 * @Auther: Anthony
 * @Date: 2018/9/20 22:06
 * @Description:
 */
//使用WebMvcConfigurer可以来扩展SpringMVC的功能
//@EnableWebMvc   不要接管SpringMVC（在默认的基础上添加）
@Configuration
public class myMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //浏览器发送 /atguigu请求到 success
        registry.addViewController("atguigu").setViewName("success");
    }

    //所有的WebMvcConfigurer组件都会一起起作用
    @Bean//将组件注册到容器
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer adapter = new WebMvcConfigurer(){
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //添加拦截器并设置拦截路径
                //静态资源： *.css *.js
                //SpringBoot2.0的所有拦截器都会拦截静态资源，故需要在此将静态资源排除
                //而静态资源分为两部分：1.默认静态资源文件(5大路径)
                //2.webjars等静态资源。这里分别进行了过滤
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/index.html","/","/user/login","/asserts/**","/webjars/**");
         }

        };
        return adapter;
    }

    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }


}
