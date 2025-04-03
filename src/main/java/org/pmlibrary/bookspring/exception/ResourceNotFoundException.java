package org.pmlibrary.bookspring.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String entityName, Long id) {
        super(entityName + " with id " + id + " not found");
    }
}
