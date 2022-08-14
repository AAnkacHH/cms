package cz.ankach.cms.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class AbstractController {

    public ResponseEntity<Object> sendCreated(String idKey, String id) {
        //Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{" + idKey + "}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    public void sendNotFound(String message) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, message);
    }

    public void sendConflict(String message) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, message);
    }
}
