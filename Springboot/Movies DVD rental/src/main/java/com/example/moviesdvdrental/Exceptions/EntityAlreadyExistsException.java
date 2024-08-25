package com.example.moviesdvdrental.Exceptions;

public class EntityAlreadyExistsException extends Exception {
    private static final long serialVersionUID = 1L;

    public EntityAlreadyExistsException(Class<?> entityClass, String username) {
        super("Entity " + entityClass.getSimpleName() + " with field name " + username + " already exist"); // username for user entity or other field for other entities
    }
}
