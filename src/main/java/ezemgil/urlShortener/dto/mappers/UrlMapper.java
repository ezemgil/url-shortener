package ezemgil.urlShortener.dto.mappers;

import ezemgil.urlShortener.dto.Mapper;
import ezemgil.urlShortener.dto.UrlDTO;
import ezemgil.urlShortener.model.Url;
import ezemgil.urlShortener.model.User;
import org.springframework.stereotype.Component;

@Component
public class UrlMapper implements Mapper<UrlDTO, Url> {
    @Override
    public UrlDTO toDTO(Url entity) {
        return UrlDTO.builder()
                .shortKey(entity.getShortKey())
                .originalUrl(entity.getOriginalUrl())
                .createdAt(entity.getCreatedAt())
                .userId(entity.getUser().getId())
                .clicks(entity.getClicks())
                .build();
    }

    @Override
    public Url fromDTO(UrlDTO dto) {
        return Url.builder()
                .shortKey(dto.getShortKey())
                .originalUrl(dto.getOriginalUrl())
                .createdAt(dto.getCreatedAt())
                .user(User.builder().id(dto.getUserId()).build())
                .clicks(dto.getClicks())
                .build();
    }
}
