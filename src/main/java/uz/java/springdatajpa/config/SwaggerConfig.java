package uz.java.springdatajpa.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author a.ergashev
 * Date: 10/12/2023
 * Time: 8:32 PM
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi userGroup(){
        return GroupedOpenApi.builder()
                .packagesToScan("uz.java.springdatajpa.controller")
                .pathsToMatch("/user/**")
                .group("Users")
                .build();
    }
    @Bean
    public GroupedOpenApi accountGroup(){
        return GroupedOpenApi.builder()
                .packagesToScan("uz.java.springdatajpa.controller")
                .pathsToMatch("/account/**")
                .group("Account")
                .build();
    }

    @Bean
    public GroupedOpenApi restOfApisGroup(){
        return GroupedOpenApi.builder()
                .packagesToScan("uz.java.springdatajpa.controller")
                .pathsToExclude("/account/**", "/user/**")
                .group("Rest of APIs")
                .build();
    }

    @Bean
    public OpenAPI openAPI(){
        SecurityScheme securityScheme = new SecurityScheme();
        securityScheme.name("jwtAuth");
        securityScheme.bearerFormat("JWT");
        securityScheme.scheme("bearer");
        securityScheme.type(SecurityScheme.Type.HTTP);
        securityScheme.in(SecurityScheme.In.HEADER);
        securityScheme.description("JWT based authentication...");


        return new OpenAPI()
                .info(new Info()
                        .title("Account-service")
                        .description("The microservice which is responsible for controlling accounts")
                        .license(new License()
                                .name("MIT")
                                .url("https://opensource.org/license/mit/"))
                        .contact(new Contact()
                                .name("Abduvohid Ergashev")
                                .url("https://t.me/wwis_mwrwq")
                                .email("abduvohid0131@gmail.com")
                        )
                        .version("1.0.0"))
                .components(new Components().addSecuritySchemes("jwtAuth", securityScheme))
                .addSecurityItem(new SecurityRequirement().addList("jwtAuth"));
    }
}
