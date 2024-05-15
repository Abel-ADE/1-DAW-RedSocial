/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.persistence;

import java.util.ArrayList;
import org.mariadb.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tacebook.model.Profile;

/**
 * Representa a base de datos do programa.
 *
 * @author Abel Iglesias Moure
 */
public class TacebookDB {
    
    // Referencia á conexión coa BD
    private static Connection connection = null;

    public final static String URL = "URL";
    public final static String USER = "user";
    public final static String PASSWORD = "password";

    /**
     * Obtén unha única conexión coa base de datos, abríndoa se é necesario
     *
     * @return Conexión coa base de datos aberta
     * @throws PersistenceException Se se produce un erro ao conectar coa BD
     */
    public static Connection getConnection() throws PersistenceException {
        // Obtemos unha conexión coa base de datos
        try {
            if (connection == null) {
                connection = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
            }
            return connection;
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceException.CONECTION_ERROR, e.getMessage());
        }
    }

    /**
     * Devolve a lista de perfis do programa.
     *
     * @return a lista de perfis do programa.
     * @throws tacebook.persistence.PersistenceException
     */
    public static ArrayList<Profile> getProfiles() throws PersistenceException {

        return null;
    }

    /**
     * Pecha a conexión coa base de datos.
     *
     */
    public static void close() {
        // Obtemos unha conexión coa base de datos
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(TacebookDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
