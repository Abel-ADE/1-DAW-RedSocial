/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.persistence;

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

        String sql = "INSERT INTO Comment (`text`, `date`, author, idPost) VALUES (?, CURRENT_TIMESTAMP(), ?, ?);";
        
        try {
            PreparedStatement pst = TacebookDB.getConnection().prepareStatement(sql);
            
            pst.setString(1, comment.getText());
            pst.setString(2, comment.getSourceProfile().getName());
            pst.setInt(3, comment.getPost().getId());

            pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
             throw new PersistenceException(PersistenceException.CONECTION_ERROR, e.getMessage());
        }
    }
}
