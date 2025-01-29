package ezemgil.urlShortener.model;

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

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "short_key")
    String shortKey;

    @Column(name = "original_url", nullable = false)
    String originalUrl;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "clicks")
    Integer clicks;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}