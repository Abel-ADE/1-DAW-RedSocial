/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.persistence;

import java.util.ArrayList;
import java.util.Date;
import tacebook.model.Comment;
import tacebook.model.Message;
import tacebook.model.Post;
import tacebook.model.Profile;

/**
 * Representa a base de datos do programa.
 *
 * @author Abel Iglesias Moure
 */
public class TacebookDB {

    private static ArrayList<Profile> profiles = new ArrayList<>();

    /**
     * Devolve a lista de perfis do programa.
     *
     * @return a lista de perfis do programa.
     * @throws tacebook.persistence.PersistenceException
     */
    public static ArrayList<Profile> getProfiles() throws PersistenceException {

        return profiles;
    }

    /**
     * Método para pechar a conexión coa base de datos, que de momento non fará
     * nada.
     */
    public static void close() {

    }

    public static void addProfiles() {
        Profile abel = new Profile("abel", "123", "Programando");
        //Amigos de abel
        Profile carlos = new Profile("carlos", "123", "Programando");
        Profile marcos = new Profile("marcos", "123", "Programando");
        Profile carla = new Profile("carla", "123", "Programando");
        //Solicitudes de amistad
        Profile maria = new Profile("maria", "123", "Programando");
        Profile antonio = new Profile("antonio", "123", "Programando");
        Profile sanchez = new Profile("sanchez", "123", "Programando");

        //Crear posts con comentarios
        ArrayList<Post> posts = new ArrayList<>();
        ArrayList<Comment> comentarios = null;
        for (int i = 0; i < 10; i++) {
            Post post = new Post(i, new Date(), ("Post: " + i), abel, abel);
            for (int j = 0; j < 5; j++) {
                comentarios = new ArrayList<>();
                Comment comment = new Comment(i, new Date(), ("Comentario: " + i), abel, post);
                comentarios.add(comment);
            }
            post.setComments(comentarios);
            posts.add(post);

        }
        abel.setPosts(posts);

        //Añadir amigos
        ArrayList<Profile> amigos = new ArrayList<>();
        amigos.add(carla);
        amigos.add(carlos);
        amigos.add(marcos);
        abel.setFriends(amigos);

        //Añadir solicitudes de amistad
        ArrayList<Profile> solicitudesAmistad = new ArrayList<>();
        solicitudesAmistad.add(maria);
        solicitudesAmistad.add(antonio);
        solicitudesAmistad.add(sanchez);
        abel.setFriendshipRequests(solicitudesAmistad);

        //Añadir mensajes privadas
        ArrayList<Message> mensajes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Message message = new Message(0, "ola", new Date(), false, abel, carla);
            mensajes.add(message);
        }
        abel.setMessages(mensajes);

        profiles.add(abel);
    }

}
