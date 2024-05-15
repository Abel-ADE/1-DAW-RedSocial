/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.persistence;

import tacebook.model.Message;

/**
 * Onde implementaremos a persistencia dos obxectos da clase mensaje.
 * @author Abel Iglesias Moure
 */
public class MessageDB {
    
    /**
     * Almacena unha nova mensaxe.
     * @param message a mensaxe.
     * @throws tacebook.persistence.PersistenceException
     */
    public static void save(Message message) throws PersistenceException{
        message.getDestProfile().getMessages().add(message);
    }
    
    /**
     * Actualiza a información dunha mensaxe.
     * @param message a mensaxe.
     * @throws tacebook.persistence.PersistenceException
     */
    public static void update(Message message) throws PersistenceException{
        
    }
    
    /**
     * Borra unha mensaxe.
     * @param message a mensaxe.
     * @throws tacebook.persistence.PersistenceException
     */
    public static void remove(Message message) throws PersistenceException{
        message.getDestProfile().getMessages().remove(message);
    }
    
}
