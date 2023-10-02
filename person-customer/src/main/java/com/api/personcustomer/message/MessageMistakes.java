package com.api.personcustomer.message;

public final class MessageMistakes {
    private MessageMistakes() {
        throw new IllegalStateException("Utility class");
    }
    public static String wasAbleToFindTheResource(String id){
        return "No se pudo encontrar el recurso con el Id: " + id;
    }

    public static String itAlreadyExists(String id){
        return "Ya existe el recurso con el Id: " + id;
    }
}
