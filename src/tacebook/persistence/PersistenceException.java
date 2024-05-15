/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.persistence;

/**
 * Representa unha excepción que se pode producir nunha operación de persistencia.
 * @author Abel Iglesias Moure
 */
public class PersistenceException extends Exception {
    
    /**
     * Cando non podemos conectar co elemento de persistencia dos datos.
     */
    public static final int CONECTION_ERROR = 0;

    /**
     * Cando non podemos leer o elemento de persistencia dos datos.
     */
    public static final int CANNOT_READ = 1;

    /**
     * Cando non podemos escribir sobre o elemento de persistencia dos datos.
     */
    public static final int CANNOT_WRITE = 2;

    /**
     * Representa o código de error da excepción.
     */
    private int code;
    
    /**
     * Devolve o código de error da excepción.
     * @return o código de error da excepción.
     */
    public int getCode() {
        return code;
    }

    /**
     * Sobrescribe o código de error da excepción.
     * @param code o código de error da excepción.
     */
    public void setCode(int code) {
        this.code = code;
    }   

    /**
     * Constructor da excepcion.
     * @param code o código de error da excepción.
     * @param message a mensaxe da excepción.
     */
    public PersistenceException(int code, String message) {
        super(message);
        this.code = code;
    }
}
