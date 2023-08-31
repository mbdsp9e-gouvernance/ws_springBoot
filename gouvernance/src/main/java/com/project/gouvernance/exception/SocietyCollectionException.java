package com.project.gouvernance.exception;

public class SocietyCollectionException extends Exception {
    private static final long serialVersionUID = 1L;

    public SocietyCollectionException(String message) {
        super(message);
    }

    public static String NotFoundException(String id) {
        return "Society with " + id + " not found!";
    }

    public static String SocietyAlreadyExists() {
        return "Society with given reference already exists!";
    }
}
