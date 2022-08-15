package cz.ankach.cms.service;

import cz.ankach.cms.api.requests.CreateUserRequest;
import cz.ankach.cms.api.requests.UpdateUserRequest;
import cz.ankach.cms.entity.Role;
import cz.ankach.cms.entity.User;
import cz.ankach.cms.entity.UserRole;
import cz.ankach.cms.repository.RoleRepository;
import cz.ankach.cms.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public Optional<User> getById(Long userId) {
        return this.userRepository.findById(userId);
    }

    /**
     * Method creates a user and his roles.
     * @param request - values for user fields.
     * @return User - created user.
     * */
    public Optional<User> createUser(CreateUserRequest request) {
        var existing = userRepository.findByUsername(request.username);
        if (existing.isPresent()) {
            return Optional.empty();
        }

        User user = new User(request.firstname, request.lastname, request.username);
        request.roles.forEach((roleName) -> {
            Role role = this.roleRepository.findByName(roleName);
            if (role == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User role not found");
            }
            UserRole userRole = new UserRole(user, role);
            user.addRole(userRole);
        });
        this.userRepository.save(user);

        return Optional.of(user);
    }

    /**
     * Method update user and his roles.
     * @param request - new values for user fields.
     * @param user - current user.
     * */
    public void updateUser(UpdateUserRequest request, User user) {
        if (request.firstname != null) {
            user.setFirstname(request.firstname);
        }
        if (request.lastname != null) {
            user.setLastName(request.lastname);
        }

        this.userRepository.save(user);
    }

    /**
     * Method delete user.
     * */
    public void deleteUser(User user) {
        this.userRepository.deleteById(user.getId());
        this.userRepository.flush();
    }
}
