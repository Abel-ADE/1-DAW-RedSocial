/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.persistence;

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
     */
    public static void save(Post post) {
        post.getProfile().getPosts().add(0, post);
    }

    /**
     * Garda un Like sobre unha publicación.
     *
     * @param post unha publicación.
     * @param profile o perfil que lle da like a publicación.
     */
    public static void saveLike(Post post, Profile profile) {
        post.getProfileLikes().add(profile);
    }

}
