package cz.ankach.cms.api.responses;

import java.util.List;

/**
 * This class represents json payload in http response for user objects.
 * */
public class UserResponse {
    public Long userId;
    public String username;
    public String firstname;
    public String lastname;
    public List<String> roles;

    public UserResponse(Long userId, String username, String firstname, String lastname, List<String> roles) {
        this.userId = userId;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.roles = roles;
    }
}
