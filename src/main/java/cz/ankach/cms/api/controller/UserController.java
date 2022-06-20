package cz.ankach.cms.api.controller;

import cz.ankach.cms.api.requests.CreateUserRequest;
import cz.ankach.cms.entity.Role;
import cz.ankach.cms.entity.User;
import cz.ankach.cms.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class UserController extends AbstractController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return this.userRepository.getUsers();
    }

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long userId) {
        User user = this.userRepository.getUserById(userId);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return user;
    }

    @PostMapping(path= "/users", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> createUser(
            @RequestBody CreateUserRequest formRequest
    ) {
        User user = new User(formRequest.firstname, formRequest.lastname, formRequest.username);
        formRequest.roles.forEach((roleName) -> {
            Role role = this.userRepository.getRole(roleName);
            if (role == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User role has not found");
            }
            user.addRole(role);
        });

        this.userRepository.addUser(user);
        return this.sendCreated("userId", String.valueOf(user.getId()));
    }
}
