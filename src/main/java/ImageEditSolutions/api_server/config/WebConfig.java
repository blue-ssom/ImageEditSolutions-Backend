package ImageEditSolutions.api_server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**") // 모든 경로에 대해 CORS 설정
                .allowedOrigins("*") // 모든 출처 허용
                .allowedMethods("GET", "POST") // 허용할 HTTP 메서드
                .allowedHeaders("*"); // 허용할 헤더
    }
}
