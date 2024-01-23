/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Enc�rgase de mostrar as opci�ns do men� inicial e recoller os datos de
 * entrada,invocando ao obxecto controlador para que execute as acci�ns
 * correspondentes.
 *
 * @author Abel Iglesias Moure
 */
public class InitMenuView {

    /**
     * Mant�n a referencia ao obxecto controlador.
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
     * Mostra o men� de inicio de sesi�n, coas opci�ns de iniciar sesi�n, crear
     * un novo perfil e sa�r da aplicaci�n.
     *
     * @return Devolve true se o usuario quere sa�r da aplicaci�n.
     */
    public boolean showLoginMenu() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Benvid@ a tacebook - A rede social do IES Pazo da Merc�");
        System.out.println("Escolle unha opci�n:");
        System.out.println("1 - Iniciar sesi�n");
        System.out.println("2 - Crear un novo perfil");
        System.out.println("3 - Sa�r da aplicaci�n");

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
     * Mostra o men� para rexistrarse, no que se pedir� un nome para o perfil,
     * un contrasinal (que se pedir� d�as veces) e un estado. Con esa
     * informaci�n, invocarase o m�todo "createProfile()" do controlador.
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
     * @return Devolver� como resultado o novo nome introducido polo usuario.
     */
    public String showNewNameMenu() {
        Scanner scan = new Scanner(System.in);
        System.out.println();

        System.out.println("O nome do usuario xa est� en uso");
        System.out.print("Dime outro nome: ");

        return scan.nextLine();
    }
    
    /**
     * Este m�todo ler� un dato num�rico por teclado.
     * @param scanner un obxecto de tipo scanner.
     * @return o n�mero introducido polo usuario.
     */
    public static int readNumber(Scanner scanner){
        int number;
        
        try {
            number = scanner.nextInt();
            scanner.nextLine();
            
        } catch (NoSuchElementException e) {
            System.err.println("Debes introducir un n�mero!");
            
            scanner.nextLine();
            return readNumber(scanner);
        }
        
        return number;
    }

}
