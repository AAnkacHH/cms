package cz.ankach.cms.api.requests;

import java.util.List;

/**
 * Request which represents json body in http request for User object creation.
 * */
public class CreateUserRequest {
    public String firstname;
    public String lastname;
    public String username;

    public List<String> roles;
}
