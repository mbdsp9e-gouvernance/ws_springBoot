package com.project.gouvernance.exception;

public class SoumissionCollectionException extends Exception {
    private static final long serialVersionUID = 1L;

    public SoumissionCollectionException(String message) {
        super(message);
    }

    public static String NotFoundException(String id) {
        return "Soumission with society" + id + " not found!";
    }
}
