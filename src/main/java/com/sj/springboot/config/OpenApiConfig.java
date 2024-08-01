package com.sj.springboot.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Spring Boot Project",
                version = "1.0",
                description = "This project serves as a comprehensive practice for mastering Spring Boot modules, concepts, and fundamental principles. It includes various examples and use cases to understand and implement best practices in Spring Boot development.",
                termsOfService = "http://springboot.example.com/terms",
                contact = @Contact(
                        name = "Samarth Jain",
                        email = "samarthj0612@gmail.com",
                        url = "https://www.linkedin.com/in/samarthjain02"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html"
                ),
                extensions = {
                        @Extension(name = "x-company-info", properties = {
                                @ExtensionProperty(name = "department", value = "Engineering"),
                                @ExtensionProperty(name = "team", value = "Backend Development")
                        })
                }
        ),
        security = {
                @SecurityRequirement(name = "bearerAuth")
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT authentication",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)

public class OpenApiConfig {
}
