/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Encárgase de mostrar as opcións do menú inicial e recoller os datos de
 * entrada,invocando ao obxecto controlador para que execute as accións
 * correspondentes.
 *
 * @author Abel Iglesias Moure
 */
public class InitMenuView {

    /**
     * Mantén a referencia ao obxecto controlador.
     */
    private InitMenuController initMenuController;

    /**
     * Constructor da clase.
     *
     * @param initMenuController
     */
    public InitMenuView(InitMenuController initMenuController) {
        this.initMenuController = initMenuController;
    }

    /**
     * Mostra o menú de inicio de sesión, coas opcións de iniciar sesión, crear
     * un novo perfil e saír da aplicación.
     *
     * @return Devolve true se o usuario quere saír da aplicación.
     */
    public boolean showLoginMenu() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Benvid@ a tacebook - A rede social do IES Pazo da Mercé");
        System.out.println("Escolle unha opción:");
        System.out.println("1 - Iniciar sesión");
        System.out.println("2 - Crear un novo perfil");
        System.out.println("3 - Saír da aplicación");

        switch (scan.nextInt()) {
            case 1:
                scan.nextLine();
                System.out.println();

                System.out.print("Nome do usuario: ");
                String name = scan.nextLine();
                System.out.print("Contrasinal: ");
                String password = scan.nextLine();

                initMenuController.login(name, password);
                break;
            case 2:
                showRegisterMenu();
                break;
            case 3:
                return true;
        }
        return false;
    }

    /**
     * Mostra unha mensaxe de usuario e contrasinal incorrectos.
     */
    public void showLoginErrorMessage() {
        System.out.println();
        System.out.println("Usuario e contrasinal incorrectos");
    }

    /**
     * Mostra o menú para rexistrarse, no que se pedirá un nome para o perfil,
     * un contrasinal (que se pedirá dúas veces) e un estado. Con esa
     * información, invocarase o método "createProfile()" do controlador.
     */
    public void showRegisterMenu() {
        Scanner scan = new Scanner(System.in);
        System.out.println();

        String name, password, password2, status;
        boolean passwordTrue = false;

        System.out.print("Nome do usuario: ");
        name = scan.nextLine();

        do {
            System.out.print("Contrasinal: ");
            password = scan.nextLine();

            System.out.print("Volva a introducir o contrasinal: ");
            password2 = scan.nextLine();

            if (password.equals(password2)) {
                passwordTrue = true;
            } else {
                System.out.println();
                System.out.println("Os contrasinais non coinciden!");
            }

        } while (!passwordTrue);

        System.out.print("Dime o teu estado: ");
        status = scan.nextLine();

        initMenuController.createProfile(name, password, status);
    }

    /**
     * Mostra unha mensaxe indicando que o nome de usuario introducido xa estaba
     * en uso e pedido un novo nome para o usuario.
     *
     * @return Devolverá como resultado o novo nome introducido polo usuario.
     */
    public String showNewNameMenu() {
        Scanner scan = new Scanner(System.in);
        System.out.println();

        System.out.println("O nome do usuario xa está en uso");
        System.out.print("Dime outro nome: ");

        return scan.nextLine();
    }
    
    /**
     * Este método lerá un dato numérico por teclado.
     * @param scanner un obxecto de tipo scanner.
     * @return o número introducido polo usuario.
     */
    public static int readNumber(Scanner scanner){
        int number;
        
        try {
            number = scanner.nextInt();
            scanner.nextLine();
            
        } catch (NoSuchElementException e) {
            System.err.println("Debes introducir un número!");
            
            scanner.nextLine();
            return readNumber(scanner);
        }
        
        return number;
    }

}
