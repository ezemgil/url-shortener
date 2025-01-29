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
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class UrlShortenerServiceImpl implements UrlShortenerService {
    UrlRepository urlRepository;
    UrlMapper urlMapper;
    UserMapper userMapper;
    KeyGeneratorStrategy keyGenerator;
    UserService userService;

    @Override
    public UrlDTO createShortUrl(UrlDTO urlRequest) {
        boolean exists = urlRepository.existsUrlByOriginalUrlAndUserId(urlRequest.getOriginalUrl(), urlRequest.getUserId());
        if (exists) {
            throw new UrlAlreadyExistsException();
        }
        Url newUrl = urlMapper.fromDTO(urlRequest);
        newUrl.setShortKey(keyGenerator.generateKey());
        newUrl.setUser(userMapper.fromDTO(userService.findById(urlRequest.getUserId())));
        urlRepository.save(newUrl);
        return urlMapper.toDTO(newUrl);
    }

    @Override
    public UrlDTO getUrl(String shortUrl, UrlDTO urlRequest) {
        Optional<Url> url = urlRepository.findByShortKeyAndUserId(shortUrl, urlRequest.getUserId());
        return url.map(urlMapper::toDTO).orElseThrow(UrlNotFoundException::new);
    }

    @Override
    public List<UrlDTO> getAllUrls(UrlDTO urlRequest) {
        List<Url> urls = urlRepository.findAllByUserId(urlRequest.getUserId());
        return urls.stream().map(urlMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public UrlDTO redirect(String shortUrl, UrlDTO urlRequest) {
        Url url = urlMapper.fromDTO(getUrl(shortUrl, urlRequest));
        url.setClicks(url.getClicks() + 1);
        urlRepository.save(url);
        return urlMapper.toDTO(url);
    }

    @Override
    public void deleteByShortKeyAndUserId(String shortUrl, UrlDTO urlRequest) {
        urlRepository.findByShortKeyAndUserId(shortUrl, urlRequest.getUserId())
                .ifPresentOrElse(
                        urlRepository::delete,
                        UrlNotFoundException::new
                );
    }
}

