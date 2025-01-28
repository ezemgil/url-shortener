package ezemgil.url_shortener.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@Setter
@Builder
@FieldDefaults(level = PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "urls")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "short_key")
    String shortKey;

    @Column(name = "original_url", nullable = false)
    String originalUrl;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "last_accessed_at")
    LocalDateTime lastAccessedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}