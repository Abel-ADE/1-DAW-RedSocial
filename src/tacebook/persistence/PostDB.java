/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.persistence;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import tacebook.model.Post;
import tacebook.model.Profile;

/**
 * Onde implementaremos a persistencia dos obxectos da clase Post.
 *
 * @author Abel Iglesias Moure
 */
public class PostDB {

    /**
     * Almacena unha nova publicación.
     *
     * @param post unha publicación.
     * @throws tacebook.persistence.PersistenceException
     */
    public static void save(Post post) throws PersistenceException {
        String sql = "INSERT INTO Post (`text`, `date`, profile, author) VALUES(?, CURRENT_TIMESTAMP(), ?, ?);";
        
        try {
            PreparedStatement pst = TacebookDB.getConnection().prepareStatement(sql);
            
            pst.setString(1, post.getText());
            pst.setString(2, post.getProfile().getName());
            pst.setString(3, post.getAuthor().getName());

            pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
             throw new PersistenceException(PersistenceException.CONECTION_ERROR, e.getMessage());
        }
    }

    /**
     * Garda un Like sobre unha publicación.
     *
     * @param post unha publicación.
     * @param profile o perfil que lle da like a publicación.
     * @throws tacebook.persistence.PersistenceException
     */
    public static void saveLike(Post post, Profile profile) throws PersistenceException {
        String sql = "INSERT INTO ProfileLikesPost (idPost, profile) VALUES(?, ?);";
        
        try {
            PreparedStatement pst = TacebookDB.getConnection().prepareStatement(sql);
            
            pst.setInt(1, post.getId());
            pst.setString(2, profile.getName());

            pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
             throw new PersistenceException(PersistenceException.CONECTION_ERROR, e.getMessage());
        }
    }
}
