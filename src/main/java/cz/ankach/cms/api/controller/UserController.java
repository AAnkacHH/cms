package cz.ankach.cms.api.controller;

import cz.ankach.cms.api.requests.CreateUserRequest;
import cz.ankach.cms.api.requests.UpdateUserRequest;
import cz.ankach.cms.api.responses.UserResponse;
import cz.ankach.cms.formatters.UserFormatter;
import cz.ankach.cms.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController extends AbstractController {
    private final UserService userService;
    private final UserFormatter userFormatter;

    public UserController(UserService userService, UserFormatter userFormatter) {
        this.userService = userService;
        this.userFormatter = userFormatter;
    }

    @GetMapping("/users")
    public List<UserResponse> getUsers() {
        var users = this.userService.getAllUsers();
        return this.userFormatter.formatAll(users);
    }

    @PostMapping(path= "/users", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> createUser(
            @RequestBody CreateUserRequest formRequest
    ) {
        var user = this.userService.createUser(formRequest);
        if (user.isEmpty()) {
            this.sendConflict("User can no be created.");
        }

        return this.sendCreated("userId", String.valueOf(user.get().getId()));
    }


    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public UserResponse getUser(@PathVariable Long userId) {
        var user = this.userService.getById(userId);
        if (user.isEmpty()) {
            this.sendNotFound("User not found.");
        }
        return this.userFormatter.format(user.get());
    }

    @PatchMapping(value = "/users/{userId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable Long userId, @RequestBody UpdateUserRequest formRequest) {
        var user = this.userService.getById(userId);
        if (user.isEmpty()) {
            this.sendNotFound("User not found.");
        }

        this.userService.updateUser(formRequest, user.get());
    }

    @DeleteMapping(value = "/users/{userId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        var user = this.userService.getById(userId);
        if (user.isEmpty()) {
            this.sendNotFound("User not found.");
        }

        this.userService.deleteUser(user.get());
    }
}
