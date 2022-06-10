package cz.ankach.cms.repository;

import cz.ankach.cms.entity.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserRepository {
    private final HashMap<Long, User> storage;

    public UserRepository() {
        this.storage = new HashMap<>();
    }

    public void addUser(User user) {
        this.storage.put(user.getId(), user);
    }

    public User getUserById(long id) {
        return this.storage.get(id);
    }

    public User getUserByUsername(String username) {
        for (Map.Entry<Long, User> entry : storage.entrySet()) {
            if (Objects.equals(entry.getValue().getUsername(), username)) {
                return entry.getValue();
            }
        }
        return null;
    }
}
