package cz.ankach.cms.api.controller;

import cz.ankach.cms.api.requests.CreateUserRequest;
import cz.ankach.cms.entity.Comment;
import cz.ankach.cms.entity.Role;
import cz.ankach.cms.entity.User;
import cz.ankach.cms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {
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
        return this.userRepository.getUserById(userId);
    }

    @PostMapping(path= "/users", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> createUser(
            @RequestBody CreateUserRequest formRequest
    ) {
        User user = new User(formRequest.firstname, formRequest.lastname, formRequest.username);
        user.addRole(new Role("READER"));

        this.userRepository.addUser(user);
        //Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }


}
