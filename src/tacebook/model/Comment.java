/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.model;

import java.util.Date;

/**
 * Representa un comentario.
 *
 * @author Abel Iglesias Moure
 */
public class Comment {

    private int id;
    private Date date;
    private String text;
    private Profile sourceProfile;
    private Post post;

    /**
     * Devolve o id do comentario.
     *
     * @return o id do comentario.
     */
    public int getId() {
        return id;
    }

    /**
     * Sobrescribe o id do comentario.
     *
     * @param id o id do comentario.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devolve a fecha do comentario.
     *
     * @return a fecha do comentario.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sobrescribe a fecha do comentario.
     *
     * @param date a fecha do comentario.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Devolve o texto do comentario.
     *
     * @return o texto do comentario.
     */
    public String getText() {
        return text;
    }

    /**
     * Sobrescribe o texto do comentario.
     *
     * @param text o texto do comentario.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Devolve o perfil que creou o comentario.
     *
     * @return o perfil que creou o comentario.
     */
    public Profile getSourceProfile() {
        return sourceProfile;
    }

    /**
     * Sobrescribe o perfil que creou o comentario.
     *
     * @param sourceProfile o perfil que creou o comentario.
     */
    public void setSourceProfile(Profile sourceProfile) {
        this.sourceProfile = sourceProfile;
    }

    /**
     * Devolve o post ao que pertenece o comentario.
     *
     * @return o post ao que pertenece o comentario.
     */
    public Post getPost() {
        return post;
    }

    /**
     * Sobrescribe o post ao que pertenece o comentario.
     *
     * @param post o post ao que pertenece o comentario.
     */
    public void setPost(Post post) {
        this.post = post;
    }

    /**
     * Constructor do obxecto comentario.
     *
     * @param id o id do comentario.
     * @param date a fecha na que se fixo o comentario.
     * @param text o texto do comentario.
     * @param sourceProfile o perfil que fixo o comentario.
     * @param post o post no que se fixo o comentario.
     */
    public Comment(int id, Date date, String text, Profile sourceProfile, Post post) {
        this.id = id;
        this.date = date;
        this.text = text;
        this.sourceProfile = sourceProfile;
        this.post = post;
    }

    @Override
    public String toString() {
        return text;
    }

    @Override
    public boolean equals(Object obj) {
         //Si el objeto es de tipo Comment
        if (obj instanceof Comment) {
            Comment comment2 = (Comment) obj;
            //Devuelve true cuando tienen el mismo id
            return this.id == comment2.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.id;
        return hash;
    }
    
    

}
