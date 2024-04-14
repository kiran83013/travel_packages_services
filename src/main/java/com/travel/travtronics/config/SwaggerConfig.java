package com.travel.travtronics.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;


@Configuration
@OpenAPIDefinition(servers = {@Server(url = "${swagger.domain}${server.servlet.context-path}/", description = "Dev Server URL")}) 
@io.swagger.v3.oas.annotations.security.SecurityScheme(name = "bearerAuth",
type = SecuritySchemeType.HTTP,
bearerFormat = "JWT",
scheme = "bearer"
)
public class SwaggerConfig {

	  @Bean
	  public OpenAPI customOpenAPI() {
	    final String securitySchemeName = "bearerAuth";
	    final String apiTitle = String.format("%s API", StringUtils.capitalize("Packages"));
	    return new OpenAPI()
	        .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
	        .components(
	            new Components()
	                .addSecuritySchemes(securitySchemeName,
	                    new SecurityScheme()
	                        .name(securitySchemeName)
	                        .type(SecurityScheme.Type.HTTP)
	                        .scheme("bearer")
	                        .bearerFormat("JWT")
	                )
	        )
	        .info(new Info().title(apiTitle).version("1.0"));
	  }
}
