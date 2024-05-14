/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.view;

import java.awt.GridLayout;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import tacebook.controller.InitMenuController;

/**
 * Vista do menu inicial en formato interfaz gráfica.
 *
 * @author Abel Iglesias Moure
 */
public class GUIInitMenuView implements InitMenuView {

    /**
     * Mantén a referencia ao obxecto controlador.
     */
    private final InitMenuController initMenuController;

    private JOptionPane registerMenu, loginMenu;

    /**
     * Constructor da clase.
     *
     * @param initMenuController
     */
    public GUIInitMenuView(InitMenuController initMenuController) {
        this.initMenuController = initMenuController;
        
        //Cambio de look and Feel
         try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIProfileView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    private int userOption(JOptionPane jOptionPane) {
        String[] options = null;
        if (jOptionPane.equals(registerMenu)) {
            options = new String[]{"Aceptar", "Cancelar"};
        }

        if (jOptionPane.equals(loginMenu)) {
            options = new String[]{"Iniciar sesión", "Rexistrarse", "Saír"};
        }

        String selectedValue = (String) jOptionPane.getValue();

        if (options != null) {

            for (int i = 0; i < options.length; i++) {
                if (options[i].equals(selectedValue)) {
                    return i;
                }
            }
        }
        return JOptionPane.CLOSED_OPTION;
    }

    /**
     * Mostra o menú de inicio de sesión, coas opcións de iniciar sesión, crear
     * un novo perfil e saír da aplicación.
     *
     * @return Devolve true se o usuario quere saír da aplicación.
     */
    @Override
    public boolean showLoginMenu() {
        String[] options = new String[]{"Iniciar sesión", "Rexistrarse", "Saír"};
        javax.swing.JPanel panel = new JPanel(new GridLayout(2, 2, 0, 10));
        javax.swing.JLabel userLabel = new JLabel("Nome de usuario");
        javax.swing.JLabel passLabel = new JLabel("Contrasinal");
        javax.swing.JTextField userField = new JTextField();
        javax.swing.JPasswordField passField = new JPasswordField();

        panel.add(userLabel);
        panel.add(userField);
        panel.add(passLabel);
        panel.add(passField);

        loginMenu = new JOptionPane(panel, JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION, null, options, options[0]);
        loginMenu.createDialog(null, "Entrar en tacebook").setVisible(true);
        int userSelected = userOption(loginMenu);

        switch (userSelected) {
            case 0:
                String name = userField.getText();
                String password = String.valueOf(passField.getPassword());
                initMenuController.login(name, password);
                break;
            case 1:
                showRegisterMenu();
                break;
            default:
                loginMenu.setVisible(false);
                JOptionPane.getRootFrame().dispose();
                return true;
        }
        return false;
    }

    /**
     * Mostra unha mensaxe de usuario e contrasinal incorrectos.
     */
    @Override
    public void showLoginErrorMessage() {
        JOptionPane.showMessageDialog(loginMenu, "Usuario e contrasinal incorrectos", "Erro nos datos", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Mostra o menú para rexistrarse, no que se pedirá un nome para o perfil,
     * un contrasinal (que se pedirá dúas veces) e un estado. Con esa
     * información, invocarase o método "createProfile()" do controlador.
     */
    @Override
    public void showRegisterMenu() {

        String[] options = new String[]{"Aceptar", "Cancelar"};
        javax.swing.JPanel panel = new JPanel(new GridLayout(4, 2, 0, 5));
        javax.swing.JLabel userLabel = new JLabel("Nome de usuario: ");
        javax.swing.JTextField userField = new JTextField();
        javax.swing.JLabel passLabel = new JLabel("Contrasinal: ");
        javax.swing.JPasswordField passField = new JPasswordField();
        javax.swing.JLabel repeatPassLabel = new JLabel("Repito o contrasinal: ");
        javax.swing.JPasswordField repeatPassField = new JPasswordField();
        javax.swing.JLabel statusLabel = new JLabel("Estado: ");
        javax.swing.JTextField statusField = new JTextField();

        panel.add(userLabel);
        panel.add(userField);
        panel.add(passLabel);
        panel.add(passField);
        panel.add(repeatPassLabel);
        panel.add(repeatPassField);
        panel.add(statusLabel);
        panel.add(statusField);

        boolean closeWindow = false;
        do {
            registerMenu = new JOptionPane(panel, JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION, null, options, options[0]);
            registerMenu.createDialog(loginMenu, "Rexistrarse").setVisible(true);
            int userSelected = userOption(registerMenu);

            String name, password, password2, status;

            if (userSelected == 0) {

                name = userField.getText();
                password = String.valueOf(passField.getPassword());
                password2 = String.valueOf(repeatPassField.getPassword());
                status = statusField.getText();

                if (password.equals(password2)) {
                    initMenuController.createProfile(name, password, status);
                    closeWindow = true;
                } else {
                    JOptionPane.showMessageDialog(registerMenu, "Os contrasinais non coinciden!", "Erro nos datos", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                closeWindow = true;
            }
        } while (!closeWindow);
    }

    /**
     * Mostra unha mensaxe indicando que o nome de usuario introducido xa estaba
     * en uso e pedido un novo nome para o usuario.
     *
     * @return Devolverá como resultado o novo nome introducido polo usuario.
     */
    @Override
    public String showNewNameMenu() {
        return JOptionPane.showInputDialog(registerMenu, "O nome do usuario xa está en uso, debes introducir outro", "Usuario xa existente", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Este método lerá un dato numérico por teclado.
     *
     * @param scanner un obxecto de tipo scanner.
     * @return o número introducido polo usuario.
     */
    public static int readNumber(Scanner scanner) {
        int number;

        try {
            number = scanner.nextInt();
            scanner.nextLine();

            System.out.println();

            return number;
        } catch (NoSuchElementException e) {
            System.err.println("Debes introducir un número!");

            scanner.nextLine();
            return readNumber(scanner);
        }
    }

    /**
     * Mostra a mensaxe de erro ao conectar co almacén de datos.
     */
    @Override
    public void showConnectionErrorMessage() {
        System.out.println("Erro na conexión co almacén de datos!");
    }

    /**
     * Mostra a mensaxe de erro durante a lectura do almacén de datos.
     */
    @Override
    public void showReadErrorMessage() {
        System.out.println("Erro na lectura de datos!");
    }

    /**
     * Mostra a mensaxe de erro durante a escritura do almacén de datos.
     */
    @Override
    public void showWriteErrorMessage() {
        System.out.println("Erro na escritura dos datos!");
    }
}
