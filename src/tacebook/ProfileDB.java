/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook;

/**
 * Onde implementaremos a persistencia dos obxectos da clase Profile.
 *
 * @author Abel Iglesias Moure
 */
public class ProfileDB {

    /**
     * Recibe como parámetro o nome dun usuario e o número de publicacións dese
     * perfil que queremos recuperar, e devolve o obxecto "Profile" asociado a
     * ese usuario, ou "null" se o usuario non existe.
     *
     * @param name
     * @param numberOfPosts
     * @return
     */
    public static Profile findByName(String name, int numberOfPosts) {

        for (Profile profile : TacebookDB.getProfiles()) {
            if (name.equals(profile.getName())) {
                return profile;
            }
        }
        return null;
    }

    /**
     * Recibe como parámetros o nome dun usuario, o contrasinal e o número de
     * publicacións dese perfil que queremos recuperar, e devolve o obxecto
     * "Profile" asociado a ese usuario, ou "null" se ese usuario non existe.
     *
     * @param name
     * @param password
     * @param numberOfPosts
     * @return
     */
    public static Profile findByNameAndPassword(String name, String password, int numberOfPosts) {

        for (Profile profile : TacebookDB.getProfiles()) {
            if (name.equals(profile.getName()) && password.equals(profile.getPassword())) {
                return profile;
            }
        }
        return null;
    }

    /**
     * Almacena o perfil no almacenamento.
     *
     * @param profile
     */
    public static void save(Profile profile) {

        TacebookDB.getProfiles().add(profile);
    }

    /**
     * Actualiza o perfil no almacemento (neste caso, non ten que facer nada).
     *
     * @param profile
     */
    public static void update(Profile profile) {

    }

    /**
     * Almacena unha nova solicitude de amizade.
     *
     * @param destProfile perfil que ten a solicitud de amizade.
     * @param sourceProfile perfil que solicitou a amizade.
     */
    public static void saveFrienshipRequest(Profile destProfile, Profile sourceProfile) {
        destProfile.getFriendshipRequests().add(sourceProfile);
    }

    /**
     * Borra unha solicitude de amizade.
     *
     * @param destProfile perfil que ten a solicitud de amizade.
     * @param sourceProfile perfil que solicitou a amizade.
     */
    public static void removeFrienshipRequest(Profile destProfile, Profile sourceProfile) {
        destProfile.getFriendshipRequests().remove(sourceProfile);
    }

    /**
     * Almacena unha amizade entre dous perfís.
     *
     * @param profile1 obxecto perfil que ten amizade co outro obxecto perfil.
     * @param profile2 obxecto perfil que ten amizade co outro obxecto perfil.
     */
    public static void saveFriendship(Profile profile1, Profile profile2) {
        profile1.getFriends().add(profile2);
        profile2.getFriends().add(profile1);
    }

}
