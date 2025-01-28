package ezemgil.urlShortener.services;

import ezemgil.urlShortener.dto.UrlDTO;

import java.util.List;

public interface UrlShortenerService {
    UrlDTO createShortUrl(UrlDTO urlRequest);
    UrlDTO getUrl(String shortUrl, UrlDTO urlRequest);
    List<UrlDTO> getAllUrls(UrlDTO urlRequest);
    UrlDTO redirect(String shortUrl, UrlDTO urlRequest);
}
