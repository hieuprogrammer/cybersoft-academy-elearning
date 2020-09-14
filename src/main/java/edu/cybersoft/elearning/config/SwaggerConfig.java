package edu.cybersoft.elearning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("edu.cybersoft.elearning.web.controller.rest"))
                .build().apiInfo(this.apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "E-Learning Web Application",
                "E-Learning Web Application and RESTful Web Service",
                "1.0",
                "https://www.facebook.com/hieucomputerprogrammer",
                new Contact("Hieu Minh Le", "https://www.facebook.com/hieucomputerprogrammer", "hieucoder@outlook.com"),
                "Cybersoft Academy - Java Backend - E-Learning Project","", Collections.emptyList()
        );
    }

//    public void addResourceHanlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**")
//                .addResourceLocations("/WEB-INF/static/");
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
}