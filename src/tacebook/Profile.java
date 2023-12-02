/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tacebook;

import java.util.ArrayList;

/**
 * Representa un perfil.
 *
 * @author Abel Iglesias Moure
 */
public class Profile {

    private String name;
    private String password;
    private String status;
    private ArrayList<Message> messages;
    private ArrayList<Post> posts;
    private ArrayList<Profile> friends;
    private ArrayList<Profile> friendshipRequests;

    /**
     * Devolve o nome do perfil.
     *
     * @return o nome do perfil.
     */
    public String getName() {
        return name;
    }

    /**
     * Sobrescribe o nome do perfil.
     *
     * @param name o nome do perfil.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Devolve o contrasinal do perfil.
     *
     * @return o contrasinal do perfil.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sobrescribe o contrasinal do perfil.
     *
     * @param password o contrasinal do perfil.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Devolve o estado do perfil.
     *
     * @return o estado do perfil.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sobrescribe o estado do perfil.
     *
     * @param status o estado do perfil.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Devolve a lista de mensaxes do perfil.
     *
     * @return a lista de mensaxes do perfil.
     */
    public ArrayList<Message> getMessages() {
        return messages;
    }

    /**
     * Sobrescribe a lista de mensaxes do perfil.
     *
     * @param messages a lista de mensaxes do perfil.
     */
    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    /**
     * Devolve a lista de publicacións do perfil.
     *
     * @return a lista de publicacións do perfil.
     */
    public ArrayList<Post> getPosts() {
        return posts;
    }

    /**
     * Sobrescribe a lista de publicacións do perfil.
     *
     * @param posts a lista de publicacións do perfil.
     */
    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    /**
     * Devolve a lista de amigos do perfil.
     *
     * @return a lista de amigos do perfil.
     */
    public ArrayList<Profile> getFriends() {
        return friends;
    }

    /**
     * Sobrescribe a lista de amigos do perfil.
     *
     * @param friends a lista de amigos do perfil.
     */
    public void setFriends(ArrayList<Profile> friends) {
        this.friends = friends;
    }

    /**
     * Devolve a lista de solicitudes de amizade do perfil.
     *
     * @return a lista de solicitudes de amizade do perfil.
     */
    public ArrayList<Profile> getFriendshipRequests() {
        return friendshipRequests;
    }

    /**
     * Sobrescribe a lista de solicitudes de amizade do perfil.
     *
     * @param friendshipRequests a lista de solicitudes de amizade do perfil.
     */
    public void setFriendshipRequests(ArrayList<Profile> friendshipRequests) {
        this.friendshipRequests = friendshipRequests;
    }

    /**
     * Constructor da clase.
     *
     * @param name o nome do perfil.
     * @param password o contrasinal do perfil.
     * @param status o estado do perfil.
     */
    public Profile(String name, String password, String status) {
        this.friends = new ArrayList<>();
        this.posts = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.friendshipRequests = new ArrayList<>();
        this.name = name;
        this.password = password;
        this.status = status;
    }

}
