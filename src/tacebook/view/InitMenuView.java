/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tacebook.view;

/**
 * Interfaz que representa a vista do men�.
 *
 * @author Abel Iglesias Moure
 */
public interface InitMenuView {
    
    /**
     *
     * Mostra o men� de inicio de sesi�n, coas opci�ns de iniciar sesi�n, crear
     * un novo perfil e sa�r da aplicaci�n.
     *
     * @return Devolve true se o usuario quere sa�r da aplicaci�n.
     */
    public boolean showLoginMenu();

    /**
     * Mostra unha mensaxe de usuario e contrasinal incorrectos.
     */
    public void showLoginErrorMessage();

    /**
     * Mostra a mensaxe de erro ao conectar co almac�n de datos.
     */
    public void showConnectionErrorMessage();

    /**
     * Mostra a mensaxe de erro durante a lectura do almac�n de datos.
     */
    public void showReadErrorMessage();

    /**
     * Mostra a mensaxe de erro durante a escritura do almac�n de datos.
     */
    public void showWriteErrorMessage();

    /**
     * Mostra unha mensaxe indicando que o nome de usuario introducido xa estaba
     * en uso e pedido un novo nome para o usuario.
     *
     * @return Devolver� como resultado o novo nome introducido polo usuario.
     */
    public String showNewNameMenu();

    /**
     * Mostra o men� para rexistrarse, no que se pedir� un nome para o perfil,
     * un contrasinal (que se pedir� d�as veces) e un estado. Con esa
     * informaci�n, invocarase o m�todo "createProfile()" do controlador.
     */
    public void showRegisterMenu();
}
