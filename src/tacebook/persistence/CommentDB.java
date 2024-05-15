/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.persistence;

import tacebook.model.Comment;

/**
 * Onde implementaremos a persistencia dos obxectos da clase comentario.
 * @author Abel Iglesias Moure
 */
public class CommentDB {
    /**
     * Almacena un novo comentario.
     * @param comment o comentario.
     * @throws tacebook.persistence.PersistenceException
     */
    public static void save(Comment comment) throws PersistenceException{
        comment.getPost().getComments().add(0, comment);
    }
}
