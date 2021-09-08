package com.hnly.provincial.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

/**
 * @author maqh
 * @version 1.0
 * @date 2021-09-08
 */

@OpenAPIDefinition(
        info = @Info(
                title = "省厅平台重构版接口",
                version = "1.0",
                description = "省厅平台重构版接口",
                contact = @Contact(name = "TOM")
        ),
        security = @SecurityRequirement(name = "Authorization")
)
@SecurityScheme(type = SecuritySchemeType.HTTP, name = "Authorization", scheme = "bearer", in = SecuritySchemeIn.HEADER)
public class OpenApiConfig {
}
