/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.persistence;

import java.util.ArrayList;
import org.mariadb.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
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

    public final static String URL = "jdbc:mariadb://localhost:33006/tacebook";
    public final static String USER = "admin";
    public final static String PASSWORD = "daw2pass";

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

        ArrayList<Profile> profiles = new ArrayList<>();

        String sql = "SELECT name, password, status FROM Profile;";
        Statement st = TacebookDB.getConnection().createStatement();
        
        try {
            ResultSet rst = st.executeQuery(sql);
            while (rst.next()) {
                String name = rst.getString(1);
                String password = rst.getString(2);
                String status = rst.getString(3);

                Profile profile = new Profile(name, password, status);
                profiles.add(profile);
            }

            st.close();
        } catch (SQLException e) {
            throw new PersistenceException(PersistenceException.CONECTION_ERROR, e.getMessage());
        }

        return profiles;
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
