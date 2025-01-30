package ezemgil.urlShortener.services.impl;

import ezemgil.urlShortener.dto.UserDTO;
import ezemgil.urlShortener.dto.mappers.UserMapper;
import ezemgil.urlShortener.exception.EncryptionException;
import ezemgil.urlShortener.exception.UserNotFoundException;
import ezemgil.urlShortener.exception.UserWithEmailAlreadyExistsException;
import ezemgil.urlShortener.model.User;
import ezemgil.urlShortener.repository.UserRepository;
import ezemgil.urlShortener.services.UserService;
import ezemgil.urlShortener.util.encryption.EncryptionStrategy;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
@Slf4j
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    EncryptionStrategy encryptionStrategy;

    @Override
    public UserDTO findById(Long id) {
        log.info("Getting user with id {}", id);
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user.setEmail(decryptEmail(user.getEmail()));
        return userMapper.toDTO(user);
    }

    @Override
    public UserDTO create(UserDTO userDTO) {
        log.info("Creating user with email {}", userDTO.getEmail());
        checkIfEmailAlreadyExists(userDTO.getEmail());
        userDTO.setEmail(encryptEmail(userDTO.getEmail()));
        return userMapper.toDTO(userRepository.save(userMapper.fromDTO(userDTO)));
    }

    @Override
    public UserDTO updateById(Long id, UserDTO userDTO) {
        log.info("Updating user with id {}", id);
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException();
        }
        checkIfEmailAlreadyExists(userDTO.getEmail());
        userDTO.setId(id);
        userDTO.setEmail(encryptEmail(userDTO.getEmail()));
        return userMapper.toDTO(userRepository.save(userMapper.fromDTO(userDTO)));
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting user with id {}", id);
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException();
        }
        userRepository.deleteById(id);
    }

    private void checkIfEmailAlreadyExists(String email) {
        if (userRepository.existsByEmail(email)) {
            log.error("User with email {} already exists", email);
            throw new UserWithEmailAlreadyExistsException();
        }
    }

    private String encryptEmail(String email) {
        try {
            return encryptionStrategy.encrypt(email);
        } catch (EncryptionException e) {
            log.error("Error encrypting email: {}", e.getMessage(), e);
            throw e;
        }
    }

    private String decryptEmail(String email) {
        try {
            return encryptionStrategy.decrypt(email);
        } catch (EncryptionException e) {
            log.error("Error decrypting email: {}", e.getMessage(), e);
            throw e;
        }
    }
}
