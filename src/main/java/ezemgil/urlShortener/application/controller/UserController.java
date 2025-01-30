package ezemgil.urlShortener.application.controller;

import ezemgil.urlShortener.application.Response;
import ezemgil.urlShortener.dto.UserDTO;
import ezemgil.urlShortener.services.UserService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static lombok.AccessLevel.PRIVATE;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<Response<UserDTO>> getUser(@PathVariable Long userId) {
        UserDTO userDTO = userService.findById(userId);
        return Response.success(userDTO, "User retrieved successfully");
    }

    @PostMapping("/create")
    public ResponseEntity<Response<UserDTO>> createUser(@RequestBody UserDTO userRequest) throws Exception {
        UserDTO userDTO = userService.create(userRequest);
        return Response.created(userDTO, "User created successfully");
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Response<UserDTO>> updateUser(@PathVariable Long userId, @RequestBody UserDTO userRequest) throws Exception {
        UserDTO userDTO = userService.updateById(userId, userRequest);
        return Response.created(userDTO, "User updated successfully");
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteById(userId);
        return Response.noContent();
    }
}
