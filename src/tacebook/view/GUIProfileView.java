/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.view;

import tacebook.model.Profile;

/**
 * Vista do perfil en formato interfaz gr�fica.
 *
 * @author Abel Iglesias Moure
 */
public class GUIProfileView implements ProfileView {

    /**
     * M�todo que permite ver o n�mero de publicaci�ns que se mostran na
     * visualizaci�n.
     *
     * @return o n�mero de publicaci�ns que se mostran na visualizaci�n.
     */
    @Override
    public int getPostsShowed() {

        return 0;

    }

    /**
     * Chamar� ao m�todo "showProfileInfo" para mostrar a informaci�n do perfil,
     * e a continuaci�n mostrar� as opci�ns de cambiar o estado do perfil (que
     * provocar� a chamada ao m�todo "changeStatus") e pechar a sesi�n (que non
     * far� nada, simplemente sair� do m�todo).
     *
     * @param profile o perfil do usuario.
     */
    @Override
    public void showProfileMenu(Profile profile) {

    }

    /**
     * Alerta de que non se atopou o perfil co que se pretend�a establecer
     * amizade.
     */
    @Override
    public void showProfileNotFoundMessage() {

    }

    /**
     * Informa de que non se pode facer like sobre unha publicaci�n propia.
     */
    @Override
    public void showCannotLikeOwnPostMessage() {

    }

    /**
     * Informa de que non se pode facer like sobre unha publicaci�n sobre a que
     * xa se fixo like.
     */
    @Override
    public void showAlreadyLikedPostMessage() {

    }

    /**
     * Informa de que xa tes amizade con ese perfil.
     *
     * @param profileName o nome do perfil que se pretend�a establecer amizade.
     */
    @Override
    public void showIsAlreadyFriendMessage(String profileName) {

    }

    /**
     * Informa de que ese perfil xa ten unha solicitude de amizade contigo.
     *
     * @param profileName o nome do perfil que se pretend�a establecer amizade.
     */
    @Override
    public void showExistsFrienshipRequestMessage(String profileName) {

    }

    /**
     * Informa de que xa tes unha solicitude de amizade con ese perfil.
     *
     * @param profileName o nome do perfil que se pretend�a establecer amizade.
     */
    @Override
    public void showDuplicateFrienshipRequestMessage(String profileName) {

    }

    /**
     * Mostra a mensaxe de erro ao conectar co almac�n de datos.
     */
    @Override
    public void showConnectionErrorMessage() {

    }

    /**
     * Mostra a mensaxe de erro durante a lectura do almac�n de datos.
     */
    @Override
    public void showReadErrorMessage() {

    }

    /**
     * Mostra a mensaxe de erro durante a escritura do almac�n de datos.
     */
    @Override
    public void showWriteErrorMessage() {

    }

}
