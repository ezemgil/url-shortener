package ezemgil.urlShortener.services;

import ezemgil.urlShortener.dto.UserDTO;

public interface UserService {
    UserDTO findById(Long id);
}
