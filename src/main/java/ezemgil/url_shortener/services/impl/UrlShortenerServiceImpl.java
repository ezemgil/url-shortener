package ezemgil.url_shortener.services.impl;

import ezemgil.url_shortener.dto.UrlDTO;
import ezemgil.url_shortener.dto.mappers.UrlMapper;
import ezemgil.url_shortener.model.Url;
import ezemgil.url_shortener.repository.UrlRepository;
import ezemgil.url_shortener.services.UrlShortenerService;
import ezemgil.url_shortener.util.KeyGenerator;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class UrlShortenerServiceImpl implements UrlShortenerService {
    UrlRepository urlRepository;
    UrlMapper urlMapper;

    @Override
    public UrlDTO createShortUrl(UrlDTO urlRequest) {
        Url url = urlMapper.fromDTO(urlRequest);
        url.setShortKey(KeyGenerator.generateKey());
        urlRepository.save(url);
        return urlMapper.toDTO(url);
    }
}
