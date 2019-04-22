package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author wangbin
 */
@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.apiInfo())
                .select()
                .paths(p -> !p.contains("actuator") && !p.contains("error"))
                .build()
                ;
    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("Flowable DEMO")
                .description("flowable 示例")
                .contact(new Contact("大胃王", null, "253498229@qq.com"))
                .build()
                ;
    }

}
