package ezemgil.urlShortener.services.impl;

import ezemgil.urlShortener.dto.UserDTO;
import ezemgil.urlShortener.dto.mappers.UserMapper;
import ezemgil.urlShortener.exception.UserNotFoundException;
import ezemgil.urlShortener.exception.UserWithEmailAlreadyExistsException;
import ezemgil.urlShortener.repository.UserRepository;
import ezemgil.urlShortener.services.UserService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    @Override
    public UserDTO findById(Long id) {
        return userMapper.toDTO(userRepository.findById(id).orElseThrow(UserNotFoundException::new));
    }

    @Override
    public UserDTO create(UserDTO userDTO) {
        checkIfEmailAlreadyExists(userDTO.getEmail());
        return userMapper.toDTO(userRepository.save(userMapper.fromDTO(userDTO)));
    }

    @Override
    public UserDTO updateById(Long id, UserDTO userDTO) {
        findById(id);
        checkIfEmailAlreadyExists(userDTO.getEmail());
        return userMapper.toDTO(userRepository.save(userMapper.fromDTO(userDTO)));
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        userRepository.deleteById(id);
    }

    private void checkIfEmailAlreadyExists(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new UserWithEmailAlreadyExistsException();
        }
    }
}
