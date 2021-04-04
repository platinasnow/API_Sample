package org.api.sample.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(){
        Info info = new Info()
                .title("Swagger API Test")
                .version("1.0")
                .description("Test API");

        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("ApiKeyAuth"))
                .components(new Components()
                        .addSecuritySchemes("ApiKeyAuth", new SecurityScheme()
                                .in(SecurityScheme.In.HEADER)
                                .type(SecurityScheme.Type.APIKEY)
                                .name("X-AUTH-TOKEN")
                    )).info(info);
    }
}
