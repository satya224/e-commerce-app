package org.ecommerce.productservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Satya Prakash",
                        email = "satya1719210224@gmail.com"
                ),
                description = "OpenApi documentation for Product Service",
                title = "OpenApi specification - Product Service",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "local env",
                        url = "${application.config.local.url}"
                ),
                @Server(
                        description = "api gateway",
                        url = "${application.config.api-gateway.url}"
                )
        }
)
public class OpenApiConfig {
}
