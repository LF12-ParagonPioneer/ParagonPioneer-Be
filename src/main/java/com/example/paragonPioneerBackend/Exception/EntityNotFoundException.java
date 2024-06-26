package com.example.paragonPioneerBackend.Exception;


import org.springframework.http.HttpStatusCode;

import java.util.UUID;

public class EntityNotFoundException extends ParagonPioneerBeException {
    public EntityNotFoundException(String identifier, String value) {
        super(HttpStatusCode.valueOf(404), "Entity Not Found Exception: No Entity " + identifier + " \"" + value + "\" found");
    }

    public EntityNotFoundException(String identifier, UUID value) {
        this(identifier, value.toString());
    }

    public EntityNotFoundException(UUID id) {
        this("id", id.toString());
    }
}
