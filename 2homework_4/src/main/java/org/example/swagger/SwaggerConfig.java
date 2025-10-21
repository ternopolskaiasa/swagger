package org.example.swagger;

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

/**
 * Configuration class for enabling Swagger documentation generation.
 * Defines the base package where controllers reside and custom global parameters.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Bean that configures Swagger Docket.
     * Specifies the API type, controller package, path selectors, and API info.
     *
     * @return a configured Docket instance for Swagger 2 documentation
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.controller")) // Specify the package where controllers live
                .paths(PathSelectors.any()) // Include all paths under the selected package
                .build()
                .apiInfo(apiInfo()) // Add metadata about the API
                .globalOperationParameters(getGlobalParams()) // Define global headers (like authorization)
                .useDefaultResponseMessages(false); // Disable default HTTP response messages
    }

    /**
     * Builds and returns API Info object.
     * Contains title, description, version, contact information, license, and more.
     *
     * @return ApiInfo object describing the API
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("User Registry API") // Title of the API
                .description("""
                     Simple REST API for registering and managing users.\n\n
                     Available methods:\n
                     - getUserById\n
                     - getAllUsers\n
                     - saveUser\n
                     - updateUser\n
                     - deleteUser
                     """) // Description of the API
                .version("1.0.0") // Current version of the API
                .contact(new Contact(
                        "Developer", // Developer name
                        "https://github.com/ternopolskaiasa", // Developer website
                        "serafimaternopolskaya@gmail.com")) // Developer email
                .license("Apache License Version 2.0") // License type
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0") // License URL
                .build();
    }

    /**
     * Generates a list of global parameters applicable across all endpoints.
     * Used for things like authentication tokens passed in headers.
     *
     * @return list of global parameters (headers)
     */
    private List<Parameter> getGlobalParams() {
        List<Parameter> params = new ArrayList<>();
        params.add(new ParameterBuilder()
                .name("Authorization") // Header parameter name
                .description("API Key for Authorization") // Short description
                .modelRef(new ModelRef("string")) // Expected type (string)
                .parameterType("header") // Parameter type (in header)
                .required(false) // Optional flag
                .build());

        return params;
    }
}
