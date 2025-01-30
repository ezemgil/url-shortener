package ezemgil.urlShortener.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@FieldDefaults(level = PRIVATE)
public class UrlDTO {
    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    String name;

    String shortKey;

    @NotBlank(message = "Original URL is mandatory")
    @Pattern(regexp = "^(http|https)://.*$", message = "Invalid URL")
    @Size(min = 3, max = 500, message = "URL must be between 3 and 500 characters")
    String originalUrl;

    LocalDateTime createdAt;

    @NotNull(message = "User ID is mandatory")
    Long userId;

    Integer clicks;
}
