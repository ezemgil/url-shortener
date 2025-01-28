package ezemgil.urlShortener.services;

import ezemgil.urlShortener.dto.UserDTO;

public interface UserService {
    UserDTO findById(Long id);
    UserDTO create(UserDTO userDTO);
    UserDTO updateById(Long id, UserDTO userDTO);
    void deleteById(Long id);
}
