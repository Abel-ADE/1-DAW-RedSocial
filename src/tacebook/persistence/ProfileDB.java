/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.persistence;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import tacebook.model.Comment;
import tacebook.model.Message;
import tacebook.model.Post;
import tacebook.model.Profile;

/**
 * Onde implementaremos a persistencia dos obxectos da clase Profile.
 *
 * @author Abel Iglesias Moure
 */
public class ProfileDB {

    /**
     * Recibe como parámetro o nome dun usuario e o número de publicacións dese
     * perfil que queremos recuperar, e devolve o obxecto "Profile" asociado a
     * ese usuario, ou "null" se o usuario non existe.
     *
     * @param name o nome dun usuario.
     * @param numberOfPosts o número de publicacións dese perfil.
     * @return devolve o obxecto "Profile" asociado a ese usuario, ou "null" se
     * o usuario non existe.
     * @throws tacebook.persistence.PersistenceException
     */
    public static Profile findByName(String name, int numberOfPosts) throws PersistenceException {

        return getProfile(name, null, numberOfPosts);
    }

    /**
     * Recibe como parámetros o nome dun usuario, o contrasinal e o número de
     * publicacións dese perfil que queremos recuperar, e devolve o obxecto
     * "Profile" asociado a ese usuario, ou "null" se ese usuario non existe.
     *
     * @param name o nome dun usuario.
     * @param password o contrasinal dun usuario.
     * @param numberOfPosts o número de publicacións dese perfil.
     * @return devolve o obxecto "Profile" asociado a ese usuario, ou "null" se
     * ese usuario non existe.
     * @throws tacebook.persistence.PersistenceException
     */
    public static Profile findByNameAndPassword(String name, String password, int numberOfPosts) throws PersistenceException {

        return getProfile(name, password, numberOfPosts);
    }

    private static Profile getProfile(String name, String password, int numberOfPosts) throws PersistenceException {

        String sqlWhere = password != null ? "WHERE name = ? AND password = ? " : "WHERE name = ?";

        String sql = "SELECT profile.name, profile.password, profile.status, "
                + "post.id, post.`date`, post.`text`, post.profile, post.author, "
                + "comment.id as comment_id, comment.`date`, comment.`text`, comment.author, comment.idPost  "
                + "FROM Profile profile "
                + "LEFT JOIN Post post ON post.author = profile.name "
                + "LEFT JOIN Comment comment ON comment.idPost = post.id   "
                + sqlWhere
                + "ORDER BY profile.name, post.`date` DESC , comment.`date` DESC";

        Profile profile = null;

        try {

            PreparedStatement pst = TacebookDB.getConnection().prepareStatement(sql);

            pst.setString(1, name);
            if (password != null) {
                pst.setString(2, password);
            }

            ResultSet rs = pst.executeQuery();

            // Listas para almacenar los objetos
            HashSet<Post> posts = new HashSet<>();
            HashSet<Comment> comments = new HashSet<>();

            // Construir objeto de tipo Usuario
            if (rs.next()) {
                String nameUser = rs.getString(1);
                String pass = rs.getString(2);
                String status = rs.getString(3);

                profile = new Profile(nameUser, pass, status);
            }

            // Construir objetos Publicacion
            rs.beforeFirst(); // Reiniciar el cursor del ResultSet
            while (rs.next()) {
                int idPost = rs.getInt(4);
                Date datePost = rs.getDate(5);
                String textPost = rs.getString(6);
                String nameProfile = rs.getString(7);
                String nameAuthorPost = rs.getString(8);

                if (textPost != null) {
                    Post post = new Post(idPost, datePost, textPost, new Profile(nameProfile, null, null), new Profile(nameAuthorPost, null, null));
                    posts.add(post);
                }

            }

            // Construir objetos Comentario
            rs.beforeFirst(); // Reiniciar el cursor del ResultSet
            while (rs.next()) {
                int idComment = rs.getInt(9);
                Date dateComment = rs.getDate(10);
                String textComment = rs.getString(11);
                String nameAuthorComment = rs.getString(12);
                int idPost = rs.getInt(13);

                if (textComment != null) {
                    Comment comment = new Comment(idComment, dateComment, textComment, new Profile(nameAuthorComment, null, null), new Post(idPost, null, null, null, null));
                    comments.add(comment);
                }
            }

            // Enlazar posts y comments con el usuario
            for (Post post : posts) {

                if (profile.getName().equals(post.getProfile().getName())) {
                    profile.getPosts().add(post);

                    for (Comment comment : comments) {
                        if (comment.getPost().getId() == post.getId()) {
                            post.getComments().add(comment);
                        }
                    }
                }
            }

            pst.close();

        } catch (SQLException e) {
            Logger.getLogger(ProfileDB.class.getName()).log(Level.SEVERE, null, e);
            throw new PersistenceException(PersistenceException.CONECTION_ERROR, e.getMessage());
        }

        //Añadimos a lista de amigos dese usuario
        ArrayList<Profile> friends = getFriendsToBD(profile);
        profile.setFriends(friends);

        ArrayList<Message> messages = getMessagesToBD(profile);
        profile.setMessages(messages);

        //Añadimos a lista de peticions de amizade dese usuario
        ArrayList<Profile> friendsRequests = getFriendsRequestToBD(profile);
        profile.setFriendshipRequests(friendsRequests);

        return profile;
    }

    private static ArrayList<Profile> getFriendsToBD(Profile profile) throws PersistenceException {

        ArrayList<Profile> friends = new ArrayList<>();

        String sql = "SELECT DISTINCT name, status \n"
                + "FROM Profile p \n"
                + "WHERE name IN (SELECT f.profile1 \n"
                + "		FROM Friend f \n"
                + "		WHERE f.profile2 = ?)\n"
                + "	OR name IN (SELECT f.profile2 \n"
                + "		FROM Friend f \n"
                + "		WHERE f.profile1 = ?)";

        try {
            PreparedStatement pst = TacebookDB.getConnection().prepareStatement(sql);

            pst.setString(1, profile.getName());
            pst.setString(2, profile.getName());

            ResultSet rst = pst.executeQuery();

            //Recorro o resultado e devolvo a lista de amigos
            while (rst.next()) {
                String nameFriend = rst.getString(1);
                String statusFriend = rst.getString(2);

                Profile friend = new Profile(nameFriend, null, statusFriend);
                friends.add(friend);
            }

            pst.close();
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceException.CONECTION_ERROR, e.getMessage());
        }

        return friends;
    }

    private static ArrayList<Message> getMessagesToBD(Profile profile) throws PersistenceException {

        ArrayList<Message> messages = new ArrayList<>();

        String sql = "SELECT id, `text`, `date`, isRead, source, destination\n"
                + "   FROM Message\n"
                + "   WHERE destination = ? \n"
                + "   ORDER BY `date` DESC ;";

        try {
            PreparedStatement pst = TacebookDB.getConnection().prepareStatement(sql);

            pst.setString(1, profile.getName());

            ResultSet rst = pst.executeQuery();

            while (rst.next()) {
                int idMessage = rst.getInt(1);
                String textMessage = rst.getString(2);
                Date dateMessage = rst.getDate(3);
                Boolean isReadMessage = rst.getBoolean(4);
                Profile sourceProfileMessage = new Profile(rst.getString(5), null, null);

                Message message = new Message(idMessage, textMessage, dateMessage, isReadMessage, profile, sourceProfileMessage);
                messages.add(message);
            }

            pst.close();
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceException.CONECTION_ERROR, e.getMessage());
        }

        return messages;
    }

    private static ArrayList<Profile> getFriendsRequestToBD(Profile profile) throws PersistenceException {

        ArrayList<Profile> friendsRequests = new ArrayList<>();

        String sql = "SELECT sourceProfile\n"
                + "FROM FriendRequest fr\n"
                + "WHERE fr.destinationProfile = ? ;";

        try {
            PreparedStatement pst = TacebookDB.getConnection().prepareStatement(sql);

            pst.setString(1, profile.getName());

            ResultSet rst = pst.executeQuery();

            while (rst.next()) {
                String nameProfile = rst.getString(1);

                Profile friendRequest = new Profile(nameProfile, null, null);
                friendsRequests.add(friendRequest);
            }

            pst.close();
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceException.CONECTION_ERROR, e.getMessage());
        }

        return friendsRequests;
    }

    /**
     * Almacena o perfil no almacenamento.
     *
     * @param profile o perfil que queremos almacenar.
     * @throws tacebook.persistence.PersistenceException
     */
    public static void save(Profile profile) throws PersistenceException {

        String sql = "INSERT INTO Profile (name, password, status) VALUES(?, ?, ?);";

        try {
            PreparedStatement pst = TacebookDB.getConnection().prepareStatement(sql);

            pst.setString(1, profile.getName());
            pst.setString(2, profile.getPassword());
            pst.setString(3, profile.getStatus());

            pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceException.CONECTION_ERROR, e.getMessage());
        }
    }

    /**
     * Actualiza o perfil no almacemento (neste caso, non ten que facer nada).
     *
     * @param profile o perfil que queremos actualizar.
     * @throws tacebook.persistence.PersistenceException
     */
    public static void update(Profile profile) throws PersistenceException {
        String sql = "UPDATE Profile SET password=?, status=? WHERE name=?;";

        try {
            PreparedStatement pst = TacebookDB.getConnection().prepareStatement(sql);

            pst.setString(1, profile.getPassword());
            pst.setString(2, profile.getStatus());
            pst.setString(3, profile.getName());

            pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceException.CONECTION_ERROR, e.getMessage());
        }
    }

    /**
     * Almacena unha nova solicitude de amizade.
     *
     * @param destProfile perfil que ten a solicitud de amizade.
     * @param sourceProfile perfil que solicitou a amizade.
     * @throws tacebook.persistence.PersistenceException
     */
    public static void saveFrienshipRequest(Profile destProfile, Profile sourceProfile) throws PersistenceException {
        String sql = "INSERT INTO FriendRequest (sourceProfile, destinationProfile) VALUES(?, ?);";

        try {
            PreparedStatement pst = TacebookDB.getConnection().prepareStatement(sql);

            pst.setString(1, sourceProfile.getName());
            pst.setString(2, destProfile.getName());

            pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceException.CONECTION_ERROR, e.getMessage());
        }
    }

    /**
     * Borra unha solicitude de amizade.
     *
     * @param destProfile perfil que ten a solicitud de amizade.
     * @param sourceProfile perfil que solicitou a amizade.
     * @throws tacebook.persistence.PersistenceException
     */
    public static void removeFrienshipRequest(Profile destProfile, Profile sourceProfile) throws PersistenceException {
        String sql = "DELETE FROM FriendRequest WHERE sourceProfile=? AND destinationProfile=?;";

        try {
            PreparedStatement pst = TacebookDB.getConnection().prepareStatement(sql);

            pst.setString(1, sourceProfile.getName());
            pst.setString(2, destProfile.getName());

            pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceException.CONECTION_ERROR, e.getMessage());
        }
    }

    /**
     * Almacena unha amizade entre dous perfís.
     *
     * @param profile1 obxecto perfil que ten amizade co outro obxecto perfil.
     * @param profile2 obxecto perfil que ten amizade co outro obxecto perfil.
     * @throws tacebook.persistence.PersistenceException
     */
    public static void saveFriendship(Profile profile1, Profile profile2) throws PersistenceException {
        String sql = "INSERT INTO Friend (profile1, profile2) VALUES(?, ?);";

        try {
            PreparedStatement pst = TacebookDB.getConnection().prepareStatement(sql);

            pst.setString(1, profile1.getName());
            pst.setString(2, profile2.getName());

            pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceException.CONECTION_ERROR, e.getMessage());
        }
    }
}
