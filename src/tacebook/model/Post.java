/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Representa unha publicaci�n dun perfil.
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
     * Devolve o id da publicaci�n.
     *
     * @return o id da publicaci�n.
     */
    public int getId() {
        return id;
    }

    /**
     * Sobrescribe o id da publicaci�n.
     *
     * @param id o id da publicaci�n.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devolve a fecha da publicaci�n.
     *
     * @return a fecha da publicaci�n.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sobrescribe a fecha da publicaci�n.
     *
     * @param date a fecha da publicaci�n.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Devolve o texto da publicaci�n.
     *
     * @return o texto da publicaci�n.
     */
    public String getText() {
        return text;
    }

    /**
     * Sobrescribe o texto da publicaci�n.
     *
     * @param text o texto da publicaci�n.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Devolve o perfil que est� vendo a publicaci�n.
     *
     * @return o perfil que est� vendo a publicaci�n.
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Sobrescribe o perfil que est� vendo a publicaci�n.
     *
     * @param profile o perfil que est� vendo a publicaci�n.
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    /**
     * Devolve o autor da publicaci�n.
     *
     * @return o autor da publicaci�n.
     */
    public Profile getAuthor() {
        return author;
    }

    /**
     * Sobrescribe o autor da publicaci�n.
     *
     * @param author o autor da publicaci�n.
     */
    public void setAuthor(Profile author) {
        this.author = author;
    }

    /**
     * Devolve os perf�s que lle deron me gusta a publicaci�n.
     *
     * @return os perf�s que lle deron me gusta a publicaci�n.
     */
    public ArrayList<Profile> getProfileLikes() {
        return profileLikes;
    }

    /**
     * Sobrescribe os perf�s que lle deron me gusta a publicaci�n.
     *
     * @param profileLikes os perf�s que lle deron me gusta a publicaci�n.
     */
    public void setProfileLikes(ArrayList<Profile> profileLikes) {
        this.profileLikes = profileLikes;
    }

    /**
     * Devolve os comentarios da publicaci�n.
     *
     * @return os comentarios da publicaci�n.
     */
    public ArrayList<Comment> getComments() {
        return comments;
    }

    /**
     * Sobrescribe os comentarios da publicaci�n.
     *
     * @param comments os comentarios da publicaci�n.
     */
    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    /**
     * Constructor da clase.
     *
     * @param id o id da publicaci�n.
     * @param date a fecha da publicaci�n.
     * @param text o texto da publicaci�n.
     * @param profile o perfil que est� vendo a publicaci�n.
     * @param author o autor da publicaci�n.
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
