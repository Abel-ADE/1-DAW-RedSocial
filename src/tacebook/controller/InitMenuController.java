/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.controller;

import tacebook.view.InitMenuView;
import tacebook.model.Profile;
import tacebook.persistence.PersistenceException;
import tacebook.persistence.ProfileDB;

/**
 * Esta clase será a que conteña o método "main()" da aplicación.
 *
 * @author Abel Iglesias Moure
 */
public class InitMenuController {

    private InitMenuView initMenuView;

    /**
     * Método que devolve o atributo que fai referencia a vista do menú.
     *
     * @return o atributo que fai referencia a vista do menú.
     */
    public InitMenuView getInitMenuView() {
        return initMenuView;
    }

    /**
     * Método que sobrescribe o atributo que fai referencia a vista do menú.
     *
     * @param initMenuView o atributo que fai referencia a vista do menú.
     */
    public void setInitMenuView(InitMenuView initMenuView) {
        this.initMenuView = initMenuView;
    }

    /**
     * Constructor da clase.
     */
    public InitMenuController() {
        this.initMenuView = new InitMenuView(this);
    }

    /**
     * Inicia a aplicación, chamando repetidamente o método "showLoginMenu()" do
     * obxecto vista ata que devolva true.
     */
    private void init() {
        do {
            //initMenuView.showLoginMenu();
        } while (!initMenuView.showLoginMenu());
    }

    /**
     * Intenta iniciar sesión na aplicación cun usuario e contrasinal.
     *
     * @param name nome do usuario.
     * @param password contrasinal do usuario.
     */
    public void login(String name, String password) {

        ProfileController profileController = new ProfileController();
        Profile profile = null;

        try {
            profile = ProfileDB.findByNameAndPassword(name, password, 0);
        } catch (PersistenceException ex) {
            proccessPersistenceException(ex);
        }

        if (profile == null) {
            initMenuView.showLoginErrorMessage();
        } else {
            profileController.openSession(profile);
        }

    }

    /**
     * Rexistra un novo usuario, simplemente chamando ao obxecto vista para que
     * mostre o menú de rexistro.
     */
    public void register() {
        initMenuView.showRegisterMenu();
    }

    /**
     * Crea un novo perfil e abre unha sesión con el.
     *
     * @param name nome do usuario.
     * @param password contrasinal do usuario.
     * @param status estado do usuario.
     */
    public void createProfile(String name, String password, String status) {
        Profile existProfile = null;

        try {
            existProfile = ProfileDB.findByName(name, 0);
        } catch (PersistenceException ex) {
            proccessPersistenceException(ex);
        }

        if (existProfile == null) {

            ProfileController profileController = new ProfileController();
            Profile profile = new Profile(name, password, status);

            try {
                ProfileDB.save(profile);

            } catch (PersistenceException ex) {
                proccessPersistenceException(ex);
            }

            profileController.openSession(profile);
        } else {
            String newName = initMenuView.showNewNameMenu();
            createProfile(newName, password, status);
        }
    }

    /**
     * encárgase de procesar unha excepción de persistencia, e en función do
     * código da excepción chamará a un dos tres métodos engadidos nas vistas no
     * punto anterior.
     *
     * @param ex unha excepción de persistencia.
     */
    private void proccessPersistenceException(PersistenceException ex) {
        switch (ex.getCode()) {
            case PersistenceException.CONECTION_ERROR:
                initMenuView.showConnectionErrorMessage();
                break;
            case PersistenceException.CANNOT_READ:
                initMenuView.showReadErrorMessage();
                break;
            case PersistenceException.CANNOT_WRITE:
                initMenuView.showWriteErrorMessage();
                break;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        InitMenuController initMenuController = new InitMenuController();
        initMenuController.init();

    }
}
