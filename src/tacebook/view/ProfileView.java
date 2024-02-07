/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tacebook.view;

import tacebook.model.Profile;

/**
 * Interfaz que representa a vista do perfil.
 *
 * @author Abel Iglesias Moure
 */
public interface ProfileView {

    /**
     * M�todo que permite ver o n�mero de publicaci�ns que se mostran na
     * visualizaci�n.
     *
     * @return o n�mero de publicaci�ns que se mostran na visualizaci�n.
     */
    public int getPostsShowed();

    /**
     * Chamar� ao m�todo "showProfileInfo" para mostrar a informaci�n do perfil,
     * e a continuaci�n mostrar� as opci�ns de cambiar o estado do perfil (que
     * provocar� a chamada ao m�todo "changeStatus") e pechar a sesi�n (que non
     * far� nada, simplemente sair� do m�todo).
     *
     * @param profile o perfil do usuario.
     */
    public void showProfileMenu(Profile profile);

    /**
     * Alerta de que non se atopou o perfil co que se pretend�a establecer
     * amizade.
     */
    public void showProfileNotFoundMessage();

    /**
     * Informa de que non se pode facer like sobre unha publicaci�n propia.
     */
    public void showCannotLikeOwnPostMessage();

    /**
     * Informa de que non se pode facer like sobre unha publicaci�n sobre a que
     * xa se fixo like.
     */
    public void showAlreadyLikedPostMessage();

    /**
     * Informa de que xa tes amizade con ese perfil.
     *
     * @param profileName o nome do perfil que se pretend�a establecer amizade.
     */
    public void showIsAlreadyFriendMessage(String profileName);

    /**
     * Informa de que ese perfil xa ten unha solicitude de amizade contigo.
     *
     * @param profileName o nome do perfil que se pretend�a establecer amizade.
     */
    public void showExistsFrienshipRequestMessage(String profileName);

     /**
     * Informa de que xa tes unha solicitude de amizade con ese perfil.
     *
     * @param profileName o nome do perfil que se pretend�a establecer amizade.
     */
    public void showDuplicateFrienshipRequestMessage(String profileName);

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
     * Indorma ao usuario que non se pode establecer amizade consigo mesmo.
     */
    public void showNotFriendshipYourself();
}
