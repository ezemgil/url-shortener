package ezemgil.urlShortener.services;

import ezemgil.urlShortener.dto.UserDTO;

public interface UserService {
    UserDTO findById(Long id);
    UserDTO create(UserDTO userDTO) throws Exception;
    UserDTO updateById(Long id, UserDTO userDTO) throws Exception;
    void deleteById(Long id);
}
