package cz.ankach.cms.api.controller;

import cz.ankach.cms.api.requests.CreateUserRequest;
import cz.ankach.cms.api.requests.UpdateUserRequest;
import cz.ankach.cms.entity.User;
import cz.ankach.cms.repository.UserRepository;
import cz.ankach.cms.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController extends AbstractController {
    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(
        UserRepository userRepository,
        UserService userService
    ) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return this.userRepository.getUsers();
    }

    @PostMapping(path= "/users", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> createUser(
            @RequestBody CreateUserRequest formRequest
    ) {
        User user = this.userService.createUser(formRequest);
        return this.sendCreated("userId", String.valueOf(user.getId()));
    }

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long userId) {
        User user = this.userRepository.getUserById(userId);
        if (user == null) {
            this.sendNotFound("User not found.");
        }
        return user;
    }

    @PatchMapping(value = "/users/{userId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable Long userId, @RequestBody UpdateUserRequest formRequest) {
        User user = this.userRepository.getUserById(userId);
        if (user == null) {
            this.sendNotFound("User not found.");
        }

        this.userService.updateUser(formRequest, user);
    }

    @DeleteMapping(value = "/users/{userId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        User user = this.userRepository.getUserById(userId);
        if (user == null) {
            this.sendNotFound("User not found.");
        }

        this.userService.deleteUser(user);
    }
}
