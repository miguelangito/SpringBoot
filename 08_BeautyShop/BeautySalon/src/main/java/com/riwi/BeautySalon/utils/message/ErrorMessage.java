package com.riwi.BeautySalon.utils.message;

public class ErrorMessage {
    
    public static String idNotFound(String entity){

        final String message = "No hay registros en la entidad %s con el id suministrado";
        return String.format(message,entity);
    }
}
