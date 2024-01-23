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
        this.initMenuView = new InitMenuView(this);
    }

    /**
     * Inicia a aplicaci�n, chamando repetidamente o m�todo "showLoginMenu()" do
     * obxecto vista ata que devolva true.
     */
    private void init() {
        do {
            //initMenuView.showLoginMenu();
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
     * enc�rgase de procesar unha excepci�n de persistencia, e en funci�n do
     * c�digo da excepci�n chamar� a un dos tres m�todos engadidos nas vistas no
     * punto anterior.
     *
     * @param ex unha excepci�n de persistencia.
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
