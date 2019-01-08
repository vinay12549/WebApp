package com.medblaze.beans.exception;

public class NoSuchTenant extends RuntimeException{

    public NoSuchTenant(String message) {
        super(message);
    }

}
