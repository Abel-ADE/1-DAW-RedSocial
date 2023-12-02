/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook;

import java.util.Date;

/**
 * Esta clase ser� a que conte�a o m�todo "main()" da aplicaci�n.
 *
 * @author Abel Iglesias Moure
 */
public class InitMenuController {

    private InitMenuView initMenuView;

    /**
     * M�todo que devolve o atributo que fai referencia a vista do men�.
     *
     * @return o atributo que fai referencia a vista do men�.
     */
    public InitMenuView getInitMenuView() {
        return initMenuView;
    }

    /**
     * M�todo que sobrescribe o atributo que fai referencia a vista do men�.
     *
     * @param initMenuView o atributo que fai referencia a vista do men�.
     */
    public void setInitMenuView(InitMenuView initMenuView) {
        this.initMenuView = initMenuView;
    }

    /**
     * Constructor da clase.
     */
    public InitMenuController() {
        InitMenuView initMenuView = new InitMenuView(this);
        this.initMenuView = initMenuView;
    }

    /**
     * Inicia a aplicaci�n, chamando repetidamente o m�todo "showLoginMenu()" do
     * obxecto vista ata que devolva true.
     */
    private void init() {
        do {
            initMenuView.showLoginMenu();
        } while (!initMenuView.showLoginMenu());
    }

    /**
     * Intenta iniciar sesi�n na aplicaci�n cun usuario e contrasinal.
     *
     * @param name nome do usuario.
     * @param password contrasinal do usuario.
     */
    public void login(String name, String password) {

        ProfileController profileController = new ProfileController();
        Profile profile = ProfileDB.findByNameAndPassword(name, password, 0);

        if (profile == null) {
            initMenuView.showLoginErrorMessage();
        } else {
            profileController.openSession(profile);
        }

    }

    /**
     * Rexistra un novo usuario, simplemente chamando ao obxecto vista para que
     * mostre o men� de rexistro.
     */
    public void register() {
        initMenuView.showRegisterMenu();
    }

    /**
     * Crea un novo perfil e abre unha sesi�n con el.
     *
     * @param name nome do usuario.
     * @param password contrasinal do usuario.
     * @param status estado do usuario.
     */
    public void createProfile(String name, String password, String status) {
        if (ProfileDB.findByName(name, 0) == null) {

            ProfileController profileController = new ProfileController();
            Profile profile = new Profile(name, password, status);

            ProfileDB.save(profile);
            profileController.openSession(profile);
        } else {
            String newName = initMenuView.showNewNameMenu();
            createProfile(newName, password, status);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        InitMenuController initMenuController = new InitMenuController();

        //Metemos a man datos na aplicaci�n
        ProfileController abelController = new ProfileController();
        Profile abel = new Profile("abel", "123", "Programando...");
        ProfileDB.save(abel);

        ProfileController carlosController = new ProfileController();
        Profile carlos = new Profile("carlos", "123", "Leendo...");
        ProfileDB.save(carlos);

        ProfileController marcosController = new ProfileController();
        Profile marcos = new Profile("marcos", "123", "Xogando...");
        ProfileDB.save(marcos);        
        
        ProfileDB.saveFrienshipRequest(abel, carlos);
        ProfileDB.saveFriendship(marcos, carlos);
        ProfileDB.saveFriendship(marcos, abel);

        // Obtener la fecha actual en milisegundos
        long currentTime = System.currentTimeMillis();

        // Crear un objeto Date
        Date actualDate = new Date(currentTime);

        Post postAbel = new Post(0, actualDate, "ola, son abel", abel, abel);
        PostDB.save(postAbel);
        
        Post postCarlos = new Post(0, actualDate, "ola, son carlos", carlos, carlos);
        PostDB.save(postCarlos);

        initMenuController.init();

    }
}
