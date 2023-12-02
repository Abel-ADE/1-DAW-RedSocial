/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook;

/**
 * Onde implementaremos a persistencia dos obxectos da clase mensaje.
 * @author Abel Iglesias Moure
 */
public class MessageDB {
    
    /**
     * Almacena unha nova mensaxe.
     * @param message a mensaxe.
     */
    public static void save(Message message){
        message.getDestProfile().getMessages().add(message);
    }
    
    /**
     * Actualiza a información dunha mensaxe.
     * @param message a mensaxe.
     */
    public static void update(Message message){
        
    }
    
    /**
     * Borra unha mensaxe.
     * @param message a mensaxe.
     */
    public static void remove(Message message){
        message.getDestProfile().getMessages().remove(message);
    }
    
}
