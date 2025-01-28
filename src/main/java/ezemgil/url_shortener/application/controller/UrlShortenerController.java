package ezemgil.url_shortener.application.controller;

import ezemgil.url_shortener.dto.UrlDTO;
import ezemgil.url_shortener.services.UrlShortenerService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<UrlDTO> getUrlInfo(@PathVariable String shortKey) {
        UrlDTO urlDTO = urlShortenerService.getUrl(shortKey);
        return ResponseEntity.ok(urlDTO);
    }

    @GetMapping("/{shortKey}")
    public ResponseEntity<String> redirectToOriginalUrl(@PathVariable String shortKey) {
        UrlDTO urlDTO = urlShortenerService.getUrl(shortKey);
        return ResponseEntity.status(302).header("Location", urlDTO.getOriginalUrl()).build();
    }
}
