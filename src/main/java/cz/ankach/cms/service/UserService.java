package cz.ankach.cms.service;

import cz.ankach.cms.api.requests.CreateUserRequest;
import cz.ankach.cms.api.requests.UpdateUserRequest;
import cz.ankach.cms.entity.Role;
import cz.ankach.cms.entity.User;
import cz.ankach.cms.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Method creates a user and his roles.
     * @param request - values for user fields.
     * @return User - created user.
     * */
    public User createUser(CreateUserRequest request) {
        User user = new User(request.firstname, request.lastname, request.username);
        request.roles.forEach((roleName) -> {
            Role role = this.userRepository.getRole(roleName);
            if (role == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User role not found");
            }
            user.addRole(role);
        });
        this.userRepository.addUser(user);

        return user;
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
        if (request.roles == null) {
            return;
        }

        List<String> newRoles = new ArrayList<>(request.roles);
        List<String> userRoles = new ArrayList<>(user.getRoleIdents());
        Set<String> commonRoles = newRoles.stream()  // get common roles
                .distinct()
                .filter(userRoles::contains)
                .collect(Collectors.toSet());

        newRoles.removeAll(commonRoles);   // roles to add
        userRoles.removeAll(commonRoles);  // roles to remove

        newRoles.forEach(newRole -> {
            Role role = this.userRepository.getRole(newRole);
            if (role == null) {
                return;
            }
            user.addRole(role);
        });

        userRoles.forEach(newRole -> {
            Role role = this.userRepository.getRole(newRole);
            if (role == null) {
                return;
            }
            user.removeRole(role);
        });
    }

    /**
     * Method delete user.
     * */
    public void deleteUser(User user) {
        this.userRepository.removeUser(user);
    }
}
