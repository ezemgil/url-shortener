package ezemgil.urlShortener.dto.mappers;

import ezemgil.urlShortener.dto.Mapper;
import ezemgil.urlShortener.dto.UserDTO;
import ezemgil.urlShortener.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<UserDTO, User> {
    @Override
    public UserDTO toDTO(User entity) {
        return UserDTO.builder()
                .name(entity.getName())
                .email(entity.getEmail())
                .urls(entity.getUrls().stream()
                        .map(new UrlMapper()::toDTO).toList())
                .build();
    }

    @Override
    public User fromDTO(UserDTO dto) {
        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .urls(dto.getUrls().stream()
                        .map(new UrlMapper()::fromDTO).toList())
                .build();
    }
}
