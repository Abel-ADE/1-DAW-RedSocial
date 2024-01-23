/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook;

import java.util.ArrayList;

/**
 * Representa a base de datos do programa.
 * @author Abel Iglesias Moure
 */
public class TacebookDB {
    
    private static ArrayList<Profile> profiles = new ArrayList<>();

    /**
     * Devolve a lista de perfis do programa.
     * @return a lista de perfis do programa.
     */
    public static ArrayList<Profile> getProfiles() {
        return profiles;
    }
       
}
