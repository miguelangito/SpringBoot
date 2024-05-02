package com.riwi.vacants.utils.exceptions;

public class IdNotFoundExeption extends RuntimeException {
    private static final String ERROR_MESSGE = "No hay registros en la entidad %s con el id suministrado";

    /**
     * Utilizamos el constructor de RuntimeException y enviamos el mensaje
     * inyectando el nombre de la entidad
     * 
     */
    public IdNotFoundExeption(String nameEntity) {
        super(String.format(ERROR_MESSGE, nameEntity));
    }
}