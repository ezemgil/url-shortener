package ezemgil.url_shortener.services;

import ezemgil.url_shortener.dto.UrlDTO;
import ezemgil.url_shortener.model.Url;

public interface UrlShortenerService {
    UrlDTO createShortUrl(UrlDTO urlRequest);
    UrlDTO getUrl(String shortUrl);
}
