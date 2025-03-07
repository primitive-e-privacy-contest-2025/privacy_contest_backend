package com.primitive.privacy_contest.Configurartion;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Privacy Contest API")
                        .version("1.0")
                        .description("이 API는 개인정보 보호 대회 관련 API 문서입니다."))
                .servers(List.of(new Server().url("https://primitive-backend.run.goorm.site/")  // 실제 API 요청을 보낼 서버 URL
                ));
    }
}
