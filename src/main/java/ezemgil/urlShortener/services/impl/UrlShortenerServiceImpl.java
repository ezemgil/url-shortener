package ezemgil.urlShortener.services.impl;

import ezemgil.urlShortener.dto.UrlDTO;
import ezemgil.urlShortener.dto.mappers.UrlMapper;
import ezemgil.urlShortener.dto.mappers.UserMapper;
import ezemgil.urlShortener.exception.UrlAlreadyExistsException;
import ezemgil.urlShortener.exception.UrlNotFoundException;
import ezemgil.urlShortener.model.Url;
import ezemgil.urlShortener.repository.UrlRepository;
import ezemgil.urlShortener.services.UrlShortenerService;
import ezemgil.urlShortener.services.UserService;
import ezemgil.urlShortener.util.KeyGeneratorStrategy;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
@Slf4j
public class UrlShortenerServiceImpl implements UrlShortenerService {
    UrlRepository urlRepository;
    UrlMapper urlMapper;
    UserMapper userMapper;
    KeyGeneratorStrategy keyGenerator;
    UserService userService;

    @Override
    public UrlDTO createShortUrl(UrlDTO urlRequest) {
        log.info("Creating short url for {} for user {}", urlRequest.getOriginalUrl(), urlRequest.getUserId());
        if (urlRepository.existsUrlByOriginalUrlAndUserId(urlRequest.getOriginalUrl(), urlRequest.getUserId())) {
            throw new UrlAlreadyExistsException();
        }
        Url newUrl = urlMapper.fromDTO(urlRequest);
        String shortKey = generateUniqueShortKey();
        newUrl.setShortKey(shortKey);
        newUrl.setUser(userMapper.fromDTO(userService.findById(urlRequest.getUserId())));
        urlRepository.save(newUrl);
        return urlMapper.toDTO(newUrl);
    }

    @Override
    public UrlDTO getUrl(String shortUrl, UrlDTO urlRequest) {
        log.info("Getting url for short url {} for user {}", shortUrl, urlRequest.getUserId());
        return findUrlByShortKeyAndUserId(shortUrl, urlRequest.getUserId());
    }

    @Override
    public List<UrlDTO> getAllUrls(UrlDTO urlRequest) {
        log.info("Getting all urls for user {}", urlRequest.getUserId());
        return urlRepository.findAllByUserId(urlRequest.getUserId())
                .stream()
                .map(urlMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UrlDTO redirect(String shortUrl, UrlDTO urlRequest) {
        log.info("Redirecting to url for short url {} for user {}", shortUrl, urlRequest.getUserId());
        Url url = urlMapper.fromDTO(getUrl(shortUrl, urlRequest));
        incrementClicks(url);
        return urlMapper.toDTO(url);
    }

    @Override
    public void deleteByShortKeyAndUserId(String shortUrl, UrlDTO urlRequest) {
        log.info("Deleting url for short url {} for user {}", shortUrl, urlRequest.getUserId());
        urlRepository.findByShortKeyAndUserId(shortUrl, urlRequest.getUserId())
                .ifPresentOrElse(
                        urlRepository::delete,
                        () -> { throw new UrlNotFoundException(); }
                );
    }

    private UrlDTO findUrlByShortKeyAndUserId(String shortUrl, Long userId) {
        return urlRepository.findByShortKeyAndUserId(shortUrl, userId)
                .map(urlMapper::toDTO)
                .orElseThrow(UrlNotFoundException::new);
    }

    private String generateUniqueShortKey() {
        String shortKey;
        do {
            shortKey = keyGenerator.generateKey();
        } while (urlRepository.existsByShortKey(shortKey));
        return shortKey;
    }

    private void incrementClicks(Url url) {
        url.setClicks(url.getClicks() + 1);
        urlRepository.save(url);
    }
}
