/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.persistence;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        String sql = "INSERT INTO Message (`text`,`date`, isRead, source, destination) VALUES(?, CURRENT_TIMESTAMP(), ?, ?, ?);";
        
        try {
            PreparedStatement pst = TacebookDB.getConnection().prepareStatement(sql);
            
            pst.setString(1, message.getText());
            pst.setBoolean(2, message.isRead());
            pst.setString(3, message.getSourceProfile().getName());
            pst.setString(4, message.getDestProfile().getName());

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
         String sql = "UPDATE Message SET `text`=?, isRead=?, source=?, destination=? WHERE id=?;";
        
        try {
            PreparedStatement pst = TacebookDB.getConnection().prepareStatement(sql);
            
            pst.setString(1, message.getText());
            pst.setBoolean(2, message.isRead());
            pst.setString(3, message.getSourceProfile().getName());
            pst.setString(4, message.getDestProfile().getName());
            pst.setInt(5, message.getId());

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
        String sql = "DELETE FROM Message WHERE id=?;";
        
        try {
            PreparedStatement pst = TacebookDB.getConnection().prepareStatement(sql);
            
            pst.setInt(1, message.getId());

            pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
             throw new PersistenceException(PersistenceException.CONECTION_ERROR, e.getMessage());
        }
    }
}
