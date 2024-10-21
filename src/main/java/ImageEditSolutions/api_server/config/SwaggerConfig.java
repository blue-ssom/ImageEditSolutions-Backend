package ImageEditSolutions.api_server.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("ImageEditSolutions Swagger") // API의 제목
                .description("프로젝트 저장하기, 가져오기에 관한 REST API") // API에 대한 설명
                .version("1.0.0"); // API의 버전
    }
}
