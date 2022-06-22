package cz.ankach.cms.api.controller;

import cz.ankach.cms.api.requests.CreateRoleRequest;
import cz.ankach.cms.entity.Role;
import cz.ankach.cms.enums.Action;
import cz.ankach.cms.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class RoleController extends AbstractController {
    private final UserRepository userRepository;

    public RoleController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/roles")
    public List<Role> getRoles() {
        return this.userRepository.getRoles();
    }

    @GetMapping("/roles/{roleId}")
    public Role getRole(@PathVariable Long roleId) {
        Role role = this.userRepository.getRoleById(roleId);
        if (role == null) {
            this.sendNotFound("Role not found.");
        }
        return role;
    }

    @GetMapping("/roles/by-name/{roleName}")
    public Role getRole(@PathVariable String roleName) {
        Role role = this.userRepository.getRole(roleName);
        if (role == null) {
            this.sendNotFound("Role not found.");
        }
        return role;
    }

    @PostMapping(path= "/roles", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> createRole(
            @RequestBody CreateRoleRequest formRequest
    ) {
        String[] allowedActions = Action.getActions();
        formRequest.actions.forEach((a) -> {
           if (!Arrays.asList(allowedActions).contains(a)) {
               this.sendNotFound("Unknown action. Action: " + a);
           }
        });

        Role role = new Role(formRequest.name);
        formRequest.actions.forEach(role::addAction);
        this.userRepository.addRole(role);

        return this.sendCreated("roleId", String.valueOf(role.getId()));
    }

    @DeleteMapping(path= "/roles/{roleId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeRole(@PathVariable Long roleId) {
        Role role = this.userRepository.getRoleById(roleId);
        if (role == null) {
            this.sendNotFound("Role not found.");
        }
    }
}
