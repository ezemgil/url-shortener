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
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    @Override
    public User fromDTO(UserDTO dto) {
        return User.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .createdAt(dto.getCreatedAt())
                .build();
    }
}
