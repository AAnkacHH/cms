package cz.ankach.cms.service;

import cz.ankach.cms.api.requests.CreateRoleRequest;
import cz.ankach.cms.entity.Role;
import cz.ankach.cms.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public List<Role> getAllRoles() {
        return repository.findAll();
    }

    public Optional<Role> getById(Long roleId) {
        return repository.findById(roleId);
    }

    public Optional<Role> createRole(CreateRoleRequest request) {
        var role = repository.findByName(request.name);
        if (role != null) {
            return Optional.empty();
        }

        Role newRole = new Role(request.name);
        repository.save(newRole);
        return Optional.of(newRole);
    }
}
