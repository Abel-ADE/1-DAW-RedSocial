/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Representa unha publicación dun perfil.
 *
 * @author Abel Iglesias Moure
 */
public class Post {

    private int id;
    private Date date;
    private String text;
    private Profile profile;
    private Profile author;
    private ArrayList<Profile> profileLikes;
    private ArrayList<Comment> comments;

    /**
     * Devolve o id da publicación.
     *
     * @return o id da publicación.
     */
    public int getId() {
        return id;
    }

    /**
     * Sobrescribe o id da publicación.
     *
     * @param id o id da publicación.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devolve a fecha da publicación.
     *
     * @return a fecha da publicación.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sobrescribe a fecha da publicación.
     *
     * @param date a fecha da publicación.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Devolve o texto da publicación.
     *
     * @return o texto da publicación.
     */
    public String getText() {
        return text;
    }

    /**
     * Sobrescribe o texto da publicación.
     *
     * @param text o texto da publicación.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Devolve o perfil que está vendo a publicación.
     *
     * @return o perfil que está vendo a publicación.
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Sobrescribe o perfil que está vendo a publicación.
     *
     * @param profile o perfil que está vendo a publicación.
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    /**
     * Devolve o autor da publicación.
     *
     * @return o autor da publicación.
     */
    public Profile getAuthor() {
        return author;
    }

    /**
     * Sobrescribe o autor da publicación.
     *
     * @param author o autor da publicación.
     */
    public void setAuthor(Profile author) {
        this.author = author;
    }

    /**
     * Devolve os perfís que lle deron me gusta a publicación.
     *
     * @return os perfís que lle deron me gusta a publicación.
     */
    public ArrayList<Profile> getProfileLikes() {
        return profileLikes;
    }

    /**
     * Sobrescribe os perfís que lle deron me gusta a publicación.
     *
     * @param profileLikes os perfís que lle deron me gusta a publicación.
     */
    public void setProfileLikes(ArrayList<Profile> profileLikes) {
        this.profileLikes = profileLikes;
    }

    /**
     * Devolve os comentarios da publicación.
     *
     * @return os comentarios da publicación.
     */
    public ArrayList<Comment> getComments() {
        return comments;
    }

    /**
     * Sobrescribe os comentarios da publicación.
     *
     * @param comments os comentarios da publicación.
     */
    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    /**
     * Constructor da clase.
     *
     * @param id o id da publicación.
     * @param date a fecha da publicación.
     * @param text o texto da publicación.
     * @param profile o perfil que está vendo a publicación.
     * @param author o autor da publicación.
     */
    public Post(int id, Date date, String text, Profile profile, Profile author) {
        this.profileLikes = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.id = id;
        this.date = date;
        this.text = text;
        this.profile = profile;
        this.author = author;
    }

    @Override
    public String toString() {
        return text;
    }
    
    

}
