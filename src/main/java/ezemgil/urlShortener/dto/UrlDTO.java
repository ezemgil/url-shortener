package ezemgil.urlShortener.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@FieldDefaults(level = PRIVATE)
public class UrlDTO {
    String shortKey;
    String originalUrl;
    LocalDateTime createdAt;
    Long userId;
    Integer clicks;
}
