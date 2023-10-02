package com.api.movementsaccount.message;

public final class MessageMistakes {
    private MessageMistakes() {
        throw new IllegalStateException("Utility class");
    }
    public static final String DOES_NOT_HAVE_SUFFICIENT_BALANCE = "No cuenta con saldo suficiente";
    public static String wasAbleToFindTheResource(String id){
        return "No se pudo encontrar el recurso con el Id: " + id;
    }
    public static String theAccountNumberAlreadyExists(String id){
        return "Ya existe el numero de cuenta: " + id;
    }
}
