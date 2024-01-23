/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.persistence;

/**
 * Representa unha excepci�n que se pode producir nunha operaci�n de persistencia.
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
     * Representa o c�digo de error da excepci�n.
     */
    private int code;
    
    /**
     * Devolve o c�digo de error da excepci�n.
     * @return o c�digo de error da excepci�n.
     */
    public int getCode() {
        return code;
    }

    /**
     * Sobrescribe o c�digo de error da excepci�n.
     * @param code o c�digo de error da excepci�n.
     */
    public void setCode(int code) {
        this.code = code;
    }   

    /**
     * Constructor da excepcion.
     * @param code o c�digo de error da excepci�n.
     * @param message a mensaxe da excepci�n.
     */
    public PersistenceException(int code, String message) {
        super(message);
        this.code = code;
    }
}
