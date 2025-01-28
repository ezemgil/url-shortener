package ezemgil.url_shortener.services.impl;

import ezemgil.url_shortener.dto.UrlDTO;
import ezemgil.url_shortener.dto.mappers.UrlMapper;
import ezemgil.url_shortener.model.Url;
import ezemgil.url_shortener.repository.UrlRepository;
import ezemgil.url_shortener.services.UrlShortenerService;
import ezemgil.url_shortener.util.KeyGeneratorStrategy;
import ezemgil.url_shortener.util.Sha256KeyGenerator;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class UrlShortenerServiceImpl implements UrlShortenerService {
    UrlRepository urlRepository;
    UrlMapper urlMapper;
    KeyGeneratorStrategy keyGenerator;

    @Override
    public UrlDTO createShortUrl(UrlDTO urlRequest) {
        Url url = urlMapper.fromDTO(urlRequest);
        url.setShortKey(keyGenerator.generateKey());
        urlRepository.save(url);
        return urlMapper.toDTO(url);
    }

    @Override
    public UrlDTO getUrl(String shortUrl) {
        Optional<Url> url = urlRepository.findByShortKey(shortUrl);
        return url.map(urlMapper::toDTO).orElse(null);
    }
}

