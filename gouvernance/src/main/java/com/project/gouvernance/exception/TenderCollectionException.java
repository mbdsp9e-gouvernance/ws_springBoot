package com.project.gouvernance.exception;

public class TenderCollectionException extends Exception {

    private static final long serialVersionUID = 1L;

    public TenderCollectionException(String message) {
        super(message);
    }

    public static String NotFoundException(String id) {
        return "Tender with " + id + " not found!";
    }

    public static String TenderAlreadyExists() {
        return "Tender with given reference already exists!";
    }

}
