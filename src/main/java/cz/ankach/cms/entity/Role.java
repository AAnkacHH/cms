package cz.ankach.cms.entity;

import java.util.ArrayList;

public class Role {

    private static long idGenerator = 1;
    private final long id;
    private final String name;

    private final ArrayList<String> actions;

    public Role(String name) {
        this.id = idGenerator++;
        this.name = name;
        actions = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getActions() {
        return actions;
    }

    public void addAction(String action) {
        this.actions.add(action);
    }
}
