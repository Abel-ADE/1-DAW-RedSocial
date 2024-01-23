/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tacebook.view;

/**
 * Interfaz que representa a vista do menú.
 *
 * @author Abel Iglesias Moure
 */
public interface InitMenuView {
    
    /**
     *
     * Mostra o menú de inicio de sesión, coas opcións de iniciar sesión, crear
     * un novo perfil e saír da aplicación.
     *
     * @return Devolve true se o usuario quere saír da aplicación.
     */
    public boolean showLoginMenu();

    /**
     * Mostra unha mensaxe de usuario e contrasinal incorrectos.
     */
    public void showLoginErrorMessage();

    /**
     * Mostra a mensaxe de erro ao conectar co almacén de datos.
     */
    public void showConnectionErrorMessage();

    /**
     * Mostra a mensaxe de erro durante a lectura do almacén de datos.
     */
    public void showReadErrorMessage();

    /**
     * Mostra a mensaxe de erro durante a escritura do almacén de datos.
     */
    public void showWriteErrorMessage();

    /**
     * Mostra unha mensaxe indicando que o nome de usuario introducido xa estaba
     * en uso e pedido un novo nome para o usuario.
     *
     * @return Devolverá como resultado o novo nome introducido polo usuario.
     */
    public String showNewNameMenu();

    /**
     * Mostra o menú para rexistrarse, no que se pedirá un nome para o perfil,
     * un contrasinal (que se pedirá dúas veces) e un estado. Con esa
     * información, invocarase o método "createProfile()" do controlador.
     */
    public void showRegisterMenu();
}
