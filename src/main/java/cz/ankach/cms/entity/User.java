package cz.ankach.cms.entity;

import java.util.ArrayList;
import java.util.List;

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

    public List<String> getRoleIdents() {
        return roles.stream().map(Role::getName).toList();
    }

    public boolean hasRole(String roleName) {
        return roles.stream().anyMatch(r -> r.getName().equals(roleName));
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
