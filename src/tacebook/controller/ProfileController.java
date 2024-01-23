/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.controller;

import java.util.Date;
import tacebook.model.Comment;
import tacebook.persistence.CommentDB;
import tacebook.model.Message;
import tacebook.persistence.MessageDB;
import tacebook.model.Post;
import tacebook.persistence.PostDB;
import tacebook.model.Profile;
import tacebook.persistence.ProfileDB;
import tacebook.view.ProfileView;

/**
 * Esta clase controlará as accións do menú principal.
 *
 * @author Abel Iglesias Moure
 */
public class ProfileController {

    private final ProfileView profileView;
    private Profile sessionProfile;
    private Profile shownProfile;

    /**
     * Obten a sesión do perfil.
     *
     * @return a sesión do perfil.
     */
    public Profile getSessionProfile() {
        return sessionProfile;
    }

    /**
     * Devolve o perfil que se está visualizando.
     *
     * @return o perfil que se está visualizando.
     */
    public Profile getShownProfile() {
        return shownProfile;
    }

    /**
     * Sobrescribe o perfil que se está visualizando.
     *
     * @param shownProfile o perfil que se está visualizando.
     */
    public void setShownProfile(Profile shownProfile) {
        this.shownProfile = shownProfile;
        reloadProfile();
    }

    /**
     * Constructor da clase.
     */
    public ProfileController() {
        this.profileView = new ProfileView(this);
    }

    /**
     * Obtén o número de publicacións a mostrar, para o que chamará ao método co
     * mesmo nome da vista do perfil.
     *
     * @return o número de publicacións a mostrar.
     */
    public int getPostsShowed() {
        return profileView.getPostsShowed();
    }

    /**
     * Obtén o obxecto do perfil da sesión usando a clase "ProfileDB" e mostra o
     * menú do perfil para el.
     */
    public void reloadProfile() {
        shownProfile = ProfileDB.findByName(shownProfile.getName(), 0);
        profileView.showProfileMenu(shownProfile);
    }

    /**
     * Abre unha sesión con un perfil, almacenando o obxecto "sessionProfile" no
     * seu atributo e chamando ao método "showProfileMenu()" do obxecto vista.
     *
     * @param sessionProfile o perfil que abriu a sesión.
     */
    public void openSession(Profile sessionProfile) {
        this.sessionProfile = sessionProfile;
        this.shownProfile = sessionProfile;
        profileView.showProfileMenu(shownProfile);
    }

    /**
     * Actualiza o estado do perfil, modificando o atributo do obxecto "profile"
     * e chamando á clase "ProfileDB" para gardar o cambio.
     *
     * @param newStatus o novo estado do perfil.
     */
    public void updateProfileStatus(String newStatus) {
        sessionProfile.setStatus(newStatus);
        ProfileDB.update(sessionProfile);
        reloadProfile();
    }

    /**
     * Método que crea unha publicación.
     *
     * @param text o texto da publicación.
     * @param destProfile o perfil que fixo a publicación.
     */
    public void newPost(String text, Profile destProfile) {
        // Obtener la fecha actual en milisegundos
        long currentTime = System.currentTimeMillis();

        // Crear un objeto Date
        Date date = new Date(currentTime);

        Post post = new Post(0, date, text, destProfile, sessionProfile);
        PostDB.save(post);
        reloadProfile();
    }

    /**
     * Método que crea un novo comentario.
     *
     * @param post a publicación sobre a que se fixo o comentario.
     * @param commentText o texto do comentario.
     */
    public void newComment(Post post, String commentText) {
        // Obtener la fecha actual en milisegundos
        long currentTime = System.currentTimeMillis();

        // Crear un objeto Date
        Date date = new Date(currentTime);

        Comment comment = new Comment(0, date, commentText, sessionProfile, post);
        CommentDB.save(comment);
        reloadProfile();
    }

    /**
     * Método que garda un like que fai un usuario sobre unha publicación.
     *
     * @param post a publicación.
     */
    public void newLike(Post post) {

        if (sessionProfile != post.getAuthor() && !post.getProfileLikes().contains(sessionProfile)) {
            PostDB.saveLike(post, sessionProfile);
        } else {
            profileView.showCannotLikeOwnPostMessage();
        }

        reloadProfile();
    }

    /**
     * Crea unha nova solicitude de amizade.
     *
     * @param profileName o nome da persoa coa que quero establecer unha
     * amizade.
     */
    public void newFriendshipRequest(String profileName) {
        Profile profile = ProfileDB.findByName(profileName, 0);

        if (profile != null) {

            if (sessionProfile.getFriendshipRequests().contains(profile)) {
                profileView.showExistsFrienshipRequestMessage(profileName);
            } else if (profile.getFriendshipRequests().contains(sessionProfile)) {
                profileView.showDuplicateFrienshipRequestMessage(profileName);
            } else if (sessionProfile.getFriends().contains(profile)) {
                profileView.showIsAlreadyFriendMessage(profileName);
            } else if (profile.equals(sessionProfile)) {
                profileView.showNotFriendshipYourself();
            } else {
                ProfileDB.saveFrienshipRequest(profile, sessionProfile);
            }
        } else {
            profileView.showProfileNotFoundMessage();
        }

        reloadProfile();
    }

    /**
     * Borra a solicitude de amizade e grava a amizade entre o perfil de orixe e
     * o perfil da sesión.
     *
     * @param sourceProfile o perfil de orixe.
     */
    public void acceptFriendshipRequest(Profile sourceProfile) {
        ProfileDB.removeFrienshipRequest(sessionProfile, sourceProfile);
        ProfileDB.saveFriendship(sessionProfile, sourceProfile);
        reloadProfile();
    }

    /**
     * Borrar a solicitude de amizade.
     *
     * @param sourceProfile o perfil de orixe.
     */
    public void rejectFriendshipRequest(Profile sourceProfile) {
        ProfileDB.removeFrienshipRequest(sessionProfile, sourceProfile);
        reloadProfile();
    }

    /**
     * Crea unha nova mensaxe.
     *
     * @param destProfile o perfil de destino.
     * @param text o testo da mensaxe.
     */
    public void newMessage(Profile destProfile, String text) {
        // Obtener la fecha actual en milisegundos
        long currentTime = System.currentTimeMillis();

        // Crear un objeto Date
        Date date = new Date(currentTime);

        Message message = new Message(0, text, date, false, destProfile, sessionProfile);
        MessageDB.save(message);
        reloadProfile();
    }

    /**
     * Elimina unha mensaxe.
     *
     * @param message a mensaxe que imos eliminar.
     */
    public void deleteMessage(Message message) {
        MessageDB.remove(message);
        reloadProfile();
    }

    /**
     * Marca a mensaxe como lida.
     *
     * @param message a mensaxe que imos marcar como lida.
     */
    public void markMessageAsRead(Message message) {
        message.setRead(true);
        MessageDB.update(message);
        reloadProfile();
    }

    /**
     * Método que contesta a unha mensaxe recibida.
     *
     * @param message a mensaxe recibida.
     * @param text a resposta do usuario.
     */
    public void replyMessage(Message message, String text) {
        markMessageAsRead(message);
        newMessage(sessionProfile, text);
        reloadProfile();
    }

}
