package cz.ankach.cms.entity;

import java.util.ArrayList;

public class User {

    private static long idGenerator = 1;
    private final long id;
    private String firstname;
    private String lastName;
    private final String username;

    private final ArrayList<Role> roles;

    public User(String firstname, String lastName, String username) {
        this.id = idGenerator++;
        this.firstname = firstname;
        this.lastName = lastName;
        this.username = username;
        this.roles = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<Role> getRoles() {
        return roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
