package cz.ankach.cms.api.controller;

import cz.ankach.cms.api.requests.CreateRoleRequest;
import cz.ankach.cms.entity.Role;
import cz.ankach.cms.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController extends AbstractController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public List<Role> getRoles() {
        return this.roleService.getAllRoles();
    }

    @GetMapping("/roles/{roleId}")
    public Role getRole(@PathVariable Long roleId) {
        var role = this.roleService.getById(roleId);
        if (role.isPresent()) {
            return role.get();
        }

        this.sendNotFound("Role not found.");
        return null;
    }


    @PostMapping(path= "/roles", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> createRole(
            @RequestBody CreateRoleRequest formRequest
    ) {
        var newRole = roleService.createRole(formRequest);
        if (newRole.isEmpty()) {
            this.sendConflict("Role has already exist.");
        }
        return this.sendCreated("roleId", String.valueOf(newRole.get().getId()));
    }
/*
    @DeleteMapping(path= "/roles/{roleId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeRole(@PathVariable Long roleId) {
        Role role = this.userRepository.getRoleById(roleId);
        if (role == null) {
            this.sendNotFound("Role not found.");
        }
    }*/
}
