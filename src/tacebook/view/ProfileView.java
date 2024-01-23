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
     * Método que permite ver o número de publicacións que se mostran na
     * visualización.
     *
     * @return o número de publicacións que se mostran na visualización.
     */
    public int getPostsShowed();

    /**
     * Chamará ao método "showProfileInfo" para mostrar a información do perfil,
     * e a continuación mostrará as opcións de cambiar o estado do perfil (que
     * provocará a chamada ao método "changeStatus") e pechar a sesión (que non
     * fará nada, simplemente sairá do método).
     *
     * @param profile o perfil do usuario.
     */
    public void showProfileMenu(Profile profile);

    /**
     * Alerta de que non se atopou o perfil co que se pretendía establecer
     * amizade.
     */
    public void showProfileNotFoundMessage();

    /**
     * Informa de que non se pode facer like sobre unha publicación propia.
     */
    public void showCannotLikeOwnPostMessage();

    /**
     * Informa de que non se pode facer like sobre unha publicación sobre a que
     * xa se fixo like.
     */
    public void showAlreadyLikedPostMessage();

    /**
     * Informa de que xa tes amizade con ese perfil.
     *
     * @param profileName o nome do perfil que se pretendía establecer amizade.
     */
    public void showIsAlreadyFriendMessage(String profileName);

    /**
     * Informa de que ese perfil xa ten unha solicitude de amizade contigo.
     *
     * @param profileName o nome do perfil que se pretendía establecer amizade.
     */
    public void showExistsFrienshipRequestMessage(String profileName);

     /**
     * Informa de que xa tes unha solicitude de amizade con ese perfil.
     *
     * @param profileName o nome do perfil que se pretendía establecer amizade.
     */
    public void showDuplicateFrienshipRequestMessage(String profileName);

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
     * Indorma ao usuario que non se pode establecer amizade consigo mesmo.
     */
    public void showNotFriendshipYourself();
}
