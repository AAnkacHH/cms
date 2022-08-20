package cz.ankach.cms.api.requests;

import java.util.List;

/**
 * Request which represents json body in http request for User object modification.
 * */
public class UpdateUserRequest {
    public String firstname;
    public String lastname;

    public List<String> roles;
}
