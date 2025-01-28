package ezemgil.url_shortener;

import ezemgil.url_shortener.dto.mappers.UrlMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public UrlMapper urlMapper() {
        return new UrlMapper();
    }
}