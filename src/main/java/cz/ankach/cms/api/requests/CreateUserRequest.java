package cz.ankach.cms.api.requests;

import java.util.List;

public class CreateUserRequest {
    public String firstname;
    public String lastname;
    public String username;

    public List<String> roles;
}
