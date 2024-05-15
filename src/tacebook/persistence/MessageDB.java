/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.persistence;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tacebook.model.Message;
import tacebook.model.Profile;

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
        String sql = "INSERT INTO Message (`text`, `date`, isRead, source, destination) VALUES(?, ?, ?, ?, ?);";
        
        try {
            PreparedStatement pst = TacebookDB.getConnection().prepareStatement(sql);
            
            pst.setString(1, message.getText());
            pst.setDate(2, (Date) message.getDate());
            pst.setBoolean(3, message.isRead());
            pst.setString(4, message.getSourceProfile().getName());
            pst.setString(5, message.getDestProfile().getName());

            pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
             throw new PersistenceException(PersistenceException.CONECTION_ERROR, e.getMessage());
        }
    }
    
    /**
     * Actualiza a información dunha mensaxe.
     * @param message a mensaxe.
     * @throws tacebook.persistence.PersistenceException
     */
    public static void update(Message message) throws PersistenceException{
         String sql = "UPDATE Message SET `text`=?, `date`=?, isRead=?, source=?, destination=? WHERE id=?;";
        
        try {
            PreparedStatement pst = TacebookDB.getConnection().prepareStatement(sql);
            
            pst.setString(1, message.getText());
            pst.setDate(2, (Date) message.getDate());
            pst.setBoolean(3, message.isRead());
            pst.setString(4, message.getSourceProfile().getName());
            pst.setString(5, message.getDestProfile().getName());
            pst.setInt(6, message.getId());

            pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
             throw new PersistenceException(PersistenceException.CONECTION_ERROR, e.getMessage());
        }
    }
    
    /**
     * Borra unha mensaxe.
     * @param message a mensaxe.
     * @throws tacebook.persistence.PersistenceException
     */
    public static void remove(Message message) throws PersistenceException{
        message.getDestProfile().getMessages().remove(message);
    }
    
    public static void main(String[] args) {
        try {
            MessageDB.update(new Message(7, "actualizando...", new java.sql.Date(0, 0, 0), false, new Profile("abel", null, null), new Profile("abel", null, null)));
        } catch (PersistenceException ex) {
            Logger.getLogger(MessageDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
