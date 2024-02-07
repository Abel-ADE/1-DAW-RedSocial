/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.persistence;

import java.util.ArrayList;
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

}
