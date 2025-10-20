package org.example.swagger;

import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .globalOperationParameters(getGlobalParams())
                .useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("User Registry API")
                .description("""
                    Simple REST API for register and entering new users. Has main CRUD operations above user's entity.
                    
                    Available methods:
                    - getUserById
                    - getAllUsers
                    - saveUser
                    - updateUser
                    - deleteUser
                    """)
                .version("1.0.0")
                .contact(new Contact(
                        "Developer",
                        "https://github.com/ternopolskaiasa",
                        "serafimaternopolskaya@gmail.com"))
                .license("Apache License Version 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }

    private List<Parameter> getGlobalParams() {
        List<Parameter> params = new ArrayList<>();
        params.add(new ParameterBuilder()
                .name("Authorization")
                .description("API Key for Authorization")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build());

        return params;
    }
}
