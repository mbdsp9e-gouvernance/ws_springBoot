package com.project.gouvernance.exception;

public class CritereCollectionException extends Exception {

    private static final long serialVersionUID = 1L;

    public CritereCollectionException(String message) {
        super(message);
    }

    public static String NotFoundException(String id) {
        return "Critere with " + id + " not found!";
    }

    public static String CritereAlreadyExists() {
        return "Cretere with given name already exists!";
    }

}
