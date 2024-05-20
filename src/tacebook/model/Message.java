/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.model;

import java.util.Date;

/**
 * Representa unha mensaxe entre perfís.
 *
 * @author Abel Iglesias Moure
 */
public class Message {

    private int id;
    private String text;
    private Date date;
    private boolean read;
    private Profile destProfile;
    private Profile sourceProfile;

    /**
     * Devolve o id da mensaxe.
     *
     * @return o id da mensaxe.
     */
    public int getId() {
        return id;
    }

    /**
     * Sobrescribe o id da mensaxe.
     *
     * @param id o id da mensaxe.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devolve o texto da mensaxe.
     *
     * @return o texto da mensaxe.
     */
    public String getText() {
        return text;
    }

    /**
     * Sobrescribe o texto da mensaxe.
     *
     * @param text o texto da mensaxe.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Devolve a fecha da mensaxe.
     *
     * @return a fecha da mensaxe.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sobrescribe a fecha da mensaxe.
     *
     * @param date a fecha da mensaxe.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Devolve se o mensaxe foi lido.
     *
     * @return (true) se o mensaxe foi lido.
     */
    public boolean isRead() {
        return read;
    }

    /**
     * Sobrescribe se o mensaxe foi lido.
     *
     * @param read (true) se o mensaxe foi lido.
     */
    public void setRead(boolean read) {
        this.read = read;
    }

    /**
     * Devolve o perfil de destino da mensaxe.
     *
     * @return o perfil de destino da mensaxe.
     */
    public Profile getDestProfile() {
        return destProfile;
    }

    /**
     * Sobrescribe o perfil de destino da mensaxe.
     *
     * @param destProfile o perfil de destino da mensaxe.
     */
    public void setDestProfile(Profile destProfile) {
        this.destProfile = destProfile;
    }

    /**
     * Devolve o perfil que enviou a mensaxe.
     *
     * @return o perfil que enviou a mensaxe.
     */
    public Profile getSourceProfile() {
        return sourceProfile;
    }

    /**
     * Sobrescribe o perfil que enviou a mensaxe.
     *
     * @param sourceProfile o perfil que enviou a mensaxe.
     */
    public void setSourceProfile(Profile sourceProfile) {
        this.sourceProfile = sourceProfile;
    }

    /**
     * Constructor da clase.
     *
     * @param id o id da mensaxe.
     * @param text o texto da mensaxe.
     * @param date a data da mensaxe.
     * @param read (true) se a mensaxe foi lida.
     * @param destProfile o perfil de destino da mensaxe.
     * @param sourceProfile o perfil que enviou a mensaxe.
     */
    public Message(int id, String text, Date date, boolean read, Profile destProfile, Profile sourceProfile) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.read = read;
        this.destProfile = destProfile;
        this.sourceProfile = sourceProfile;
    }

    @Override
    public String toString() {
        return text;
    }

    @Override
    public boolean equals(Object obj) {
         //Si el objeto es de tipo Message
        if (obj instanceof Message) {
            Message message2 = (Message) obj;
            //Devuelve true cuando tienen el mismo id
            return this.id == message2.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.id;
        return hash;
    }
}
