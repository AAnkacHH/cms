package cz.ankach.cms.formatters;

import cz.ankach.cms.api.responses.UserResponse;
import cz.ankach.cms.entity.User;
import cz.ankach.cms.entity.UserRole;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFormatter {

    /**
     * Method formats a user object as response object.
     * @return UserResponse
     * */
    public UserResponse format(User user) {
        var roles = user.getRoles().stream().map((UserRole role) -> role.getRole().getName()).toList();
        return new UserResponse(user.getId(), user.getUsername(), user.getFirstname(), user.getLastname(), roles);
    }

    /**
     * Method formats a list of users as response object.
     * */
    public List<UserResponse> formatAll(List<User> users) {
        return users.stream().map(this::format).toList();
    }
}
