/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.controller;

import tacebook.view.TextInitMenuView;
import tacebook.model.Profile;
import tacebook.persistence.PersistenceException;
import tacebook.persistence.ProfileDB;
import tacebook.persistence.TacebookDB;
import tacebook.view.GUIInitMenuView;
import tacebook.view.InitMenuView;

/**
 * Esta clase será a que conteña o método "main()" da aplicación.
 *
 * @author Abel Iglesias Moure
 */
public class InitMenuController {

    private InitMenuView initMenuView;
    private boolean textMode;

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
     *
     * @param textMode indica se a interfaz está en modo texto ou non.
     */
    public InitMenuController(boolean textMode) {
        this.textMode = textMode;
        this.initMenuView = textMode ? new TextInitMenuView(this) : new GUIInitMenuView(this);
    }

    /**
     * Inicia a aplicación, chamando repetidamente o método "showLoginMenu()" do
     * obxecto vista ata que devolva true.
     */
    private void init() {
        boolean exit = false;
        do {
            exit = initMenuView.showLoginMenu();
        } while (!exit);
    }

    /**
     * Intenta iniciar sesión na aplicación cun usuario e contrasinal.
     *
     * @param name nome do usuario.
     * @param password contrasinal do usuario.
     */
    public void login(String name, String password) {

        ProfileController profileController = new ProfileController(textMode);
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

            ProfileController profileController = new ProfileController(textMode);
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
           
        TacebookDB.addProfiles();
        try {
            System.out.println(TacebookDB.getProfiles().size());
        } catch (PersistenceException ex) {
            System.out.println("sen perfiles");
        }
        
        //empeza o código
        
        InitMenuController initMenuController;
                
        if (args.length == 1 && args[0].equals("text")) {
            initMenuController = new InitMenuController(true);
        }else{
            initMenuController = new InitMenuController(false);
        }
        
        initMenuController.init();
        TacebookDB.close();
    }
}
