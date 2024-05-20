/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.controller;

import java.util.Date;
import java.util.GregorianCalendar;
import tacebook.model.Comment;
import tacebook.persistence.CommentDB;
import tacebook.model.Message;
import tacebook.persistence.MessageDB;
import tacebook.model.Post;
import tacebook.persistence.PostDB;
import tacebook.model.Profile;
import tacebook.persistence.PersistenceException;
import tacebook.persistence.ProfileDB;
import tacebook.view.GUIProfileView;
import tacebook.view.ProfileView;
import tacebook.view.TextProfileView;

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
     *
     * @param textMode indica se a interfaz está en modo texto ou non.
     */
    public ProfileController(boolean textMode) {
        this.profileView = textMode ? new TextProfileView(this) : new GUIProfileView(null, true, this);
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
        try {
            shownProfile = ProfileDB.findByName(shownProfile.getName(), 0);
        } catch (PersistenceException ex) {
            proccessPersistenceException(ex);
        }
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
        try {
            ProfileDB.update(sessionProfile);
        } catch (PersistenceException ex) {
            proccessPersistenceException(ex);
        }

        reloadProfile();
    }

    /**
     * Método que crea unha publicación.
     *
     * @param text o texto da publicación.
     * @param destProfile o perfil de destino da publicación.
     */
    public void newPost(String text, Profile destProfile) {
        // Obtener la fecha actual en milisegundos
        long currentTime = System.currentTimeMillis();

        Post post = new Post(0, new Date(currentTime), text, destProfile, sessionProfile);

        try {
            PostDB.save(post);
        } catch (PersistenceException ex) {
            proccessPersistenceException(ex);
        }

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

        Comment comment = new Comment(0, new Date(currentTime), commentText, sessionProfile, post);

        try {
            CommentDB.save(comment);
        } catch (PersistenceException ex) {
            proccessPersistenceException(ex);
        }

        reloadProfile();
    }

    /**
     * Método que garda un like que fai un usuario sobre unha publicación.
     *
     * @param post a publicación.
     */
    public void newLike(Post post) {

        if (!sessionProfile.equals(post.getAuthor())) {
            if (!post.getProfileLikes().contains(sessionProfile)) {
                try {
                    PostDB.saveLike(post, sessionProfile);
                } catch (PersistenceException ex) {
                    proccessPersistenceException(ex);
                }
            }else{
                profileView.showAlreadyLikedPostMessage();
            }
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
        Profile profile = null;

        try {
            profile = ProfileDB.findByName(profileName, 0);
        } catch (PersistenceException ex) {
            proccessPersistenceException(ex);
        }

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
                try {
                    ProfileDB.saveFrienshipRequest(profile, sessionProfile);
                } catch (PersistenceException ex) {
                    proccessPersistenceException(ex);
                }
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
        try {
            ProfileDB.removeFrienshipRequest(sessionProfile, sourceProfile);
            ProfileDB.saveFriendship(sessionProfile, sourceProfile);
        } catch (PersistenceException ex) {
            proccessPersistenceException(ex);
        }
        reloadProfile();
    }

    /**
     * Borrar a solicitude de amizade.
     *
     * @param sourceProfile o perfil de orixe.
     */
    public void rejectFriendshipRequest(Profile sourceProfile) {
        try {
            ProfileDB.removeFrienshipRequest(sessionProfile, sourceProfile);
        } catch (PersistenceException ex) {
            proccessPersistenceException(ex);
        }
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

        Message message = new Message(0, text, new Date(currentTime), false, destProfile, sessionProfile);

        try {
            MessageDB.save(message);
        } catch (PersistenceException ex) {
            proccessPersistenceException(ex);
        }

        reloadProfile();
    }

    /**
     * Elimina unha mensaxe.
     *
     * @param message a mensaxe que imos eliminar.
     */
    public void deleteMessage(Message message) {
        try {
            MessageDB.remove(message);
        } catch (PersistenceException ex) {
            proccessPersistenceException(ex);
        }

        reloadProfile();
    }

    /**
     * Marca a mensaxe como lida.
     *
     * @param message a mensaxe que imos marcar como lida.
     */
    public void markMessageAsRead(Message message) {
        message.setRead(true);

        try {
            MessageDB.update(message);
        } catch (PersistenceException ex) {
            proccessPersistenceException(ex);
        }

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

    /**
     * encárgase de procesar unha excepción de persistencia, e en función do
     * código da excepción chamará a un dos tres métodos engadidos nas vistas no
     * punto anterior.
     *
     * @param ex unha excepción de persistencia.
     */
    private void proccessPersistenceException(PersistenceException ex) {
        switch (ex.getCode()) {
            case PersistenceException.CONECTION_ERROR:
                profileView.showConnectionErrorMessage();
                break;
            case PersistenceException.CANNOT_READ:
                profileView.showReadErrorMessage();
                break;
            case PersistenceException.CANNOT_WRITE:
                profileView.showWriteErrorMessage();
                break;
        }
    }

}
