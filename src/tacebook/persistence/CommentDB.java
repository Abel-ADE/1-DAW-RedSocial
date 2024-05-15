/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.persistence;

import java.sql.Date;
import tacebook.model.Comment;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Onde implementaremos a persistencia dos obxectos da clase comentario.
 *
 * @author Abel Iglesias Moure
 */
public class CommentDB {

    /**
     * Almacena un novo comentario.
     *
     * @param comment o comentario.
     * @throws tacebook.persistence.PersistenceException
     */
    public static void save(Comment comment) throws PersistenceException {

        String sql = "INSERT INTO Comment (`text`, `date`, author, idPost) VALUES (?, ?, ?, ?);";
        
        try {
            PreparedStatement pst = TacebookDB.getConnection().prepareStatement(sql);
            
            pst.setString(1, comment.getText());
            pst.setDate(2, (Date) comment.getDate());
            pst.setString(3, comment.getSourceProfile().getName());
            pst.setInt(4, comment.getPost().getId());

            pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
             throw new PersistenceException(PersistenceException.CONECTION_ERROR, e.getMessage());
        }
    }
}
