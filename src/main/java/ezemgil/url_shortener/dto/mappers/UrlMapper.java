package ezemgil.url_shortener.dto.mappers;

import ezemgil.url_shortener.dto.Mapper;
import ezemgil.url_shortener.dto.UrlDTO;
import ezemgil.url_shortener.model.Url;

public class UrlMapper implements Mapper<UrlDTO, Url> {
    @Override
    public UrlDTO toDTO(Url entity) {
        return UrlDTO.builder()
                .shortKey(entity.getShortKey())
                .originalUrl(entity.getOriginalUrl())
                .createdAt(entity.getCreatedAt())
                .lastAccessedAt(entity.getLastAccessedAt())
                .build();
    }

    @Override
    public Url fromDTO(UrlDTO dto) {
        return Url.builder()
                .shortKey(dto.getShortKey())
                .originalUrl(dto.getOriginalUrl())
                .createdAt(dto.getCreatedAt())
                .lastAccessedAt(dto.getLastAccessedAt())
                .build();
    }
}
