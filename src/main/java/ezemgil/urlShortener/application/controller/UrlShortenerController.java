package ezemgil.urlShortener.application.controller;

import ezemgil.urlShortener.application.Response;
import ezemgil.urlShortener.dto.UrlDTO;
import ezemgil.urlShortener.services.UrlShortenerService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
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
    public ResponseEntity<Response<UrlDTO>> shortenUrl(@RequestBody UrlDTO urlRequest) {
        UrlDTO urlDTO = urlShortenerService.createShortUrl(urlRequest);
        return Response.created(urlDTO, "Shortened URL created successfully");
    }

    @GetMapping("/urls/{shortKey}")
    public ResponseEntity<Response<UrlDTO>> getUrlInfo(@PathVariable String shortKey, @RequestBody UrlDTO urlRequest) {
        UrlDTO urlDTO = urlShortenerService.getUrl(shortKey, urlRequest);
        return Response.success(urlDTO, "URL info retrieved successfully");
    }

    @GetMapping("/{shortKey}")
    public ResponseEntity<Response<Object>> redirectToOriginalUrl(@PathVariable String shortKey, @RequestBody UrlDTO urlRequest) {
        UrlDTO urlDTO = urlShortenerService.redirect(shortKey, urlRequest);
        return Response.redirect(urlDTO.getOriginalUrl(), HttpStatus.TEMPORARY_REDIRECT);
    }

    @GetMapping("/urls")
    public ResponseEntity<Response<List<UrlDTO>>> getAllUrls(@RequestBody UrlDTO urlRequest) {
        List<UrlDTO> urlDTOs = urlShortenerService.getAllUrls(urlRequest);
        return Response.success(urlDTOs, "All URLs retrieved successfully");
    }

    @DeleteMapping("/delete/{shortKey}")
    public ResponseEntity<Void> deleteUrl(@PathVariable String shortKey, @RequestBody UrlDTO urlRequest) {
        urlShortenerService.deleteByShortKeyAndUserId(shortKey, urlRequest);
        return Response.noContent();
    }
}
