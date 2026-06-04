package br.com.gscarbono.gs_carbono_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class OpenApiConfig implements WebMvcConfigurer {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("GS Carbono API")
                        .version("2.0")
                        .description("API REST para monitoramento e gestão de emissões de carbono " +
                                "conectada ao ecossistema espacial — Global Solution FIAP 2025")
                        .contact(new Contact()
                                .name("GS Carbono Team")
                                .email("gscarbono@fiap.com.br")));
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Redirect root "/" to Swagger UI
        registry.addRedirectViewController("/", "/swagger-ui.html");
    }
}