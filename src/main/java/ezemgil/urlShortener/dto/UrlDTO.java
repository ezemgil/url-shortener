package ezemgil.urlShortener.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

    @NotBlank(message = "Original URL is mandatory")
    @Pattern(regexp = "^(http|https)://.*$", message = "Invalid URL")
    String originalUrl;

    LocalDateTime createdAt;

    @NotNull(message = "User ID is mandatory")
    Long userId;

    Integer clicks;
}
