package br.com.salao.config;

import java.util.*;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;

import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableWebMvc
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("br.com.salao.controllers"))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .apiInfo(informationsApi());
    }

    private ApiInfo informationsApi() {
        return new ApiInfoBuilder()
                .title("Backend Salão Will")
                .description("API para salão de beleza que desenvolvi para minha mãe.")
                .version("1.0")
                .contact(myContact())
                .build();
    }

    private Contact myContact() {
        return new Contact("Matheus Dalvino",
                "https://github.com/M4TH3US17",
                "matheusdalvino50@gmail.com");
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext
                .builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<SecurityReference> defaultAuth(){
        AuthorizationScope authScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] array = new AuthorizationScope[1];
        array[0] = authScope;
        SecurityReference reference = new SecurityReference("JWT", array);
        List<SecurityReference> auths = new ArrayList<>();
        auths.add(reference);
        return auths;
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer()
    {
        return new WebMvcConfigurer()
        {
            @Override
            public void addResourceHandlers( ResourceHandlerRegistry registry )
            {
                registry.addResourceHandler( "swagger-ui.html" ).addResourceLocations( "classpath:/META-INF/resources/" );
                registry.addResourceHandler( "/webjars/**" ).addResourceLocations( "classpath:/META-INF/resources/webjars/" );
            }
        };
    }
}