package ezemgil.urlShortener.application.controller;

import ezemgil.urlShortener.dto.UrlDTO;
import ezemgil.urlShortener.services.UrlShortenerService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Controller
@RequestMapping()
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class UrlShortenerController {
    UrlShortenerService urlShortenerService;

    @PostMapping("/shorten")
    public ResponseEntity<UrlDTO> shortenUrl(@RequestBody UrlDTO urlRequest) {
        UrlDTO urlDTO = urlShortenerService.createShortUrl(urlRequest);
        return ResponseEntity.ok(urlDTO);
    }

    @GetMapping("/info/{shortKey}")
    public ResponseEntity<UrlDTO> getUrlInfo(@PathVariable String shortKey, @RequestBody UrlDTO urlRequest) {
        UrlDTO urlDTO = urlShortenerService.getUrl(shortKey, urlRequest);
        return ResponseEntity.ok(urlDTO);
    }

    @GetMapping("/{shortKey}")
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortKey, @RequestBody UrlDTO urlRequest) {
        UrlDTO urlDTO = urlShortenerService.redirect(shortKey, urlRequest);
        return ResponseEntity.status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, urlDTO.getOriginalUrl())
                .build();
    }

    @GetMapping("/info")
    public ResponseEntity<List<UrlDTO>> getAllUrls(@RequestBody UrlDTO urlRequest) {
        List<UrlDTO> urlDTOs = urlShortenerService.getAllUrls(urlRequest);
        return ResponseEntity.ok(urlDTOs);
    }
}
