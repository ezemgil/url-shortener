package ezemgil.urlShortener.dto;

import jakarta.validation.constraints.NotBlank;
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
public class UserDTO {
    Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    String name;

    @NotBlank(message = "Email is mandatory")
    @Pattern(regexp = "^(.+)@(.+)$", message = "Invalid email")
    String email;

    LocalDateTime createdAt;
}
