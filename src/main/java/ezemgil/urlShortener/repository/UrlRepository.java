package ezemgil.urlShortener.repository;

import ezemgil.urlShortener.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByShortKeyAndUserId(String shortKey, Long userId);
    boolean existsUrlByOriginalUrlAndUserId(String originalUrl, Long userId);
    List<Url> findAllByUserId(Long userId);
}
