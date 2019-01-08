package com.medblaze.auth.document;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
    public Docket api(ServletContext servletContext) {
        Set<String> shemes = new HashSet<String>();
        shemes.add("https");
        return new Docket(DocumentationType.SWAGGER_2)
                .host("ec2-18-222-147-136.us-east-2.compute.amazonaws.com:8443")
                .protocols(shemes)
        .pathProvider(new RelativePathProvider(servletContext) {
            @Override
            public String getApplicationBasePath() {
                return "/OauthService";
            }
        })
          .select()
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())
          .build();

    }

}
