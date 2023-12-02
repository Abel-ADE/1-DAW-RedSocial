/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook;

import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * Enc�rgase de mostrar as opci�ns do men� principal.
 *
 * @author Abel Iglesias Moure
 */
public class ProfileView {

    /**
     * Indica o n�mero de publicaci�ns que se mostran na visualizaci�n.
     */
    private int postsShowed = 10;
    /**
     * Para formatear as datas
     */
    private SimpleDateFormat formatter;

    /**
     * Mant�n a referencia ao obxecto controlador.
     */
    private ProfileController profileController;

    /**
     * M�todo que permite ver o n�mero de publicaci�ns que se mostran na
     * visualizaci�n.
     *
     * @return o n�mero de publicaci�ns que se mostran na visualizaci�n.
     */
    public int getPostsShowed() {
        return postsShowed;
    }

    /**
     * Constructor da clase.
     *
     * @param profileController
     */
    public ProfileView(ProfileController profileController) {
        this.formatter = new SimpleDateFormat("dd-MM-yyy '�s' HH:mm:ss");
        this.profileController = profileController;
    }

    /**
     * Enc�rgase de mostrar toda a informaci�n do perfil, a�nda que de momento
     * s� ser� o nome e estado actual.
     *
     * @param ownProfile serve para indicar se se est� vendo o perfil propio.
     * @param profile o perfil do usuario.
     */
    private void showProfileInfo(boolean ownProfile, Profile profile) {

        System.out.println();
        System.out.println("tacebook - Perfil do usuario: " + profile.getName());
        System.out.println("Estado actual: " + profile.getStatus());

        System.out.println("A t�a biograf�a (10 �ltimas publicaci�ns):");
        if (profile.getPosts().size() >= 1) {
            int postsDisplayed = 0;

            for (Post post : profile.getPosts()) {

                if (postsDisplayed < postsShowed) {

                    if (ownProfile) {
                        System.out.println(postsDisplayed + ".O " + formatter.format(post.getDate()) + " ti escribiches (" + post.getProfileLikes().size() + " me gusta):");
                    } else {
                        System.out.println(postsDisplayed + ".O " + formatter.format(post.getDate()) + " " + profileController.getShownProfile().getName() + " escribiu (" + post.getProfileLikes().size() + " me gusta):");
                    }
                    System.out.println(post.getText());

                    for (Comment comment : post.getComments()) {
                        System.out.println("- " + comment.getText() + " - " + comment.getSourceProfile().getName() + " - " + formatter.format(comment.getDate()));
                    }

                    postsDisplayed++;
                }
            }
        }

        System.out.println("Lista de amigos:");
        if (profile.getFriends().size() >= 1) {
            for (int i = 0; i < profile.getFriends().size(); i++) {
                System.out.println(i + ". " + profile.getFriends().get(i).getName() + " - " + profile.getFriends().get(i).getStatus());
            }
        }

        if (!profile.getFriendshipRequests().isEmpty()) {
            System.out.println("Tes solicitudes de amizade dos seguintes perf�s:");
            for (int i = 0; i < profile.getFriendshipRequests().size(); i++) {
                System.out.println(i + ". " + profile.getFriendshipRequests().get(i).getName() + " quere establecer amizade contigo.");
            }
        }

    }

    /**
     * Rec�be os par�metros necesarios para cambiar o estado do perfil.
     *
     * @param ownProfile serve para indicar se se est� vendo o perfil propio.
     * @param scanner un scanner aberto para pedir datos do teclado.
     * @param profile o perfil actual.
     */
    private void changeStatus(boolean ownProfile, Scanner scanner, Profile profile) {

        if (ownProfile) {
            scanner.nextLine();
            System.out.print("Introduce o teu novo estado: ");
            profileController.updateProfileStatus(scanner.nextLine());
        } else {
            System.out.println("Esta opci�n s� � v�lida na propia biograf�a");
            showProfileMenu(profile);
        }
    }

    /**
     * Chamar� ao m�todo "showProfileInfo" para mostrar a informaci�n do perfil,
     * e a continuaci�n mostrar� as opci�ns de cambiar o estado do perfil (que
     * provocar� a chamada ao m�todo "changeStatus") e pechar a sesi�n (que non
     * far� nada, simplemente sair� do m�todo).
     *
     * @param profile o perfil do usuario.
     */
    public void showProfileMenu(Profile profile) {
        Scanner scanner = new Scanner(System.in);
        showProfileInfo(profileController.getSessionProfile() == profileController.getShownProfile(), profile);
        boolean isOwnProfile = profileController.getSessionProfile() == profileController.getShownProfile();
        System.out.println();

        System.out.println("Escolle unha opci�n:");
        System.out.println("1. Escribir unha nova publicaci�n");
        System.out.println("2. Comentar unha publicaci�n");
        System.out.println("3. Facer me gusta sobre unha publicaci�n");

        if (isOwnProfile) {
            System.out.println("4. Ver a biograf�a dun amigo");
            System.out.println("5. Enviar unha solicitude de amizade");
            System.out.println("6. Aceptar unha solicitude de amizade");
            System.out.println("7. Rexeitar unha solicitude de amizade");
            System.out.println("8. Enviar unha mensaxe privada a un amigo");
            System.out.println("9. Ler unha mensaxe privada");
            System.out.println("10. Eliminar unha mensaxe privada");
            System.out.println("11. Ver publicaci�ns anteriores");
            System.out.println("12. Cambiar o estado");
            System.out.println("13. Pechar a sesi�n");
            System.out.println();
        } else {

            System.out.println("4. Volver a mi�a biograf�a");
            System.out.println("8. Enviar unha mensaxe privada");
            System.out.println("11. Ver publicaci�ns anteriores");
            System.out.println("13. Pechar a sesi�n");
            System.out.println();
        }

        switch (scanner.nextInt()) {
            case 1: //1. Escribir unha nova publicaci�n
                writeNewPost(scanner, profile);
                break;
            case 2: //2. Comentar unha publicaci�n
                commentPost(scanner, profile);
                break;
            case 3: //3. Facer me gusta sobre unha publicaci�n
                addLike(scanner, profile);
                break;
            case 4: //4. Ver a biograf�a dun amigo
                showBiography(isOwnProfile, scanner, profile);
                break;
            case 5: //5. Enviar unha solicitude de amizade
                if (isOwnProfile) {
                    sendFriendshipRequest(isOwnProfile, scanner, profile);
                } else {
                    System.out.println("Esta opci�n s� est� permitida na t�a biograf�a");
                }
                break;
            case 6: //6. Aceptar unha solicitude de amizade
                if (isOwnProfile) {
                    proccessFriendshipRequest(isOwnProfile, scanner, profile, true);
                } else {
                    System.out.println("Esta opci�n s� est� permitida na t�a biograf�a");
                }
                break;
            case 7: //7. Rexeitar unha solicitude de amizade
                if (isOwnProfile) {
                    proccessFriendshipRequest(isOwnProfile, scanner, profile, false);
                } else {
                    System.out.println("Esta opci�n s� est� permitida na t�a biograf�a");
                }
                break;
            case 8: //8. Enviar unha mensaxe privada a un amigo
                sendPrivateMessage(isOwnProfile, scanner, profile);
                break;
            case 9: //9. Ler unha mensaxe privada
                if (isOwnProfile) {
                    readPrivateMessage(isOwnProfile, scanner, profile);
                } else {
                    System.out.println("Esta opci�n s� est� permitida na t�a biograf�a");
                }
                break;
            case 10: //10. Eliminar unha mensaxe privada
                if (isOwnProfile) {
                    deletePrivateMessage(isOwnProfile, scanner, profile);
                } else {
                    System.out.println("Esta opci�n s� est� permitida na t�a biograf�a");
                }
                break;
            case 11: //11. Ver publicaci�ns anteriores
                showOldPosts(scanner, profile);
                break;
            case 12: //12. Cambiar o estado
                if (isOwnProfile) {
                    changeStatus(true, scanner, profile);
                    showProfileMenu(profile);
                } else {
                    System.out.println("Esta opci�n s� est� permitida na t�a biograf�a");
                }
                break;
            case 13: //13. Pechar a sesi�n
                //De momento non facemos nada
                break;
        }
    }

    /**
     * Este m�todo utilizarase cando se pida ao usuario que introduza un n�mero
     * para seleccionar un elemento dunha lista.
     *
     * @param text a lista de opci�ns a mostrar por pantalla.
     * @param maxNumber o n�mero de opci�ns da lista.
     * @param scanner un obxecto da clase Scanner.
     * @return a opci�n que escolleu o usuario.
     */
    private int selectElement(String text, int maxNumber, Scanner scanner) {

        int option;

        do {
            System.out.println(text);
            option = scanner.nextInt();
            scanner.nextLine();
            if (option <= 0 || option >= maxNumber) {
                System.out.println("Debes introducir un n�mero entre 1 e " + maxNumber);
            }
        } while (option <= 0 || option >= maxNumber);

        return option;
    }

    /**
     * Pide o texto para crear unha nova publicaci�n e chama ao controlador para
     * creala.
     *
     * @param scanner un obxecto da clase Scanner.
     * @param profile o perfil que crea a publicaci�n.
     */
    private void writeNewPost(Scanner scanner, Profile profile) {
        System.out.println("Dame o texto da publicaci�n:");
        scanner.nextLine();
        String text = scanner.nextLine();
        profileController.newPost(text, profile);
    }

    /**
     * Pide ao usuario que seleccione unha publicaci�n e que introduza un texto,
     * e chama ao controlador para crear un comentario con ese texto.
     *
     * @param scanner un obxecto da clase Scanner.
     * @param profile o perfil que crea o comentario.
     */
    private void commentPost(Scanner scanner, Profile profile) {
        System.out.println("Selecciona unha publicaci�n:");
        int numberPost = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Introduce o texto do comentario:");
        String commentText = scanner.nextLine();
        profileController.newComment(profile.getPosts().get(numberPost), commentText);
    }

    /**
     * Pide ao usuario que seleccione unha publicaci�n e chama ao controlador
     * para facer like sobre ela.
     *
     * @param scanner un obxecto da clase Scanner.
     * @param profile o perfil que da like sobre a publicaci�n.
     */
    private void addLike(Scanner scanner, Profile profile) {
        System.out.println("Selecciona unha publicaci�n:");
        int numberPost = scanner.nextInt();
        scanner.nextLine();
        profileController.newLike(profile.getPosts().get(numberPost));
    }

    /**
     * Se estamos vendo o propio perfil, pide ao usuario seleccionar unha
     * amizade para establecer ese perfil como perfil a mostrar, e sen�n volve a
     * po�er o perfil da sesi�n como perfil a mostrar.
     *
     * @param ownProfile un boolean que indica se estamos vendo o perfil propio.
     * @param scanner un obxecto da clase Scanner.
     * @param profile o perfil da sesi�n.
     */
    private void showBiography(boolean ownProfile, Scanner scanner, Profile profile) {

        if (ownProfile) {
            System.out.println("Selecciona unha amizade:");
            int numberProfile = scanner.nextInt();
            scanner.nextLine();
            profileController.setShownProfile(profile.getFriends().get(numberProfile));
        } else {
            profileController.setShownProfile(profileController.getSessionProfile());
        }

    }

    /**
     * Pide o nome dun perfil e chama ao controlador para enviarlle unha
     * solicitude de amizade.
     *
     * @param ownProfile un boolean que indica se estamos vendo o perfil propio.
     * @param scanner un obxecto da clase Scanner.
     * @param profile
     */
    private void sendFriendshipRequest(boolean ownProfile, Scanner scanner, Profile profile) {
        System.out.println("Dame o nome dun perfil:");
        scanner.nextLine();
        String nameProfile = scanner.nextLine();
        if (ProfileDB.findByName(nameProfile, 0) != null) {
            if (profile.getFriendshipRequests().contains(ProfileDB.findByName(nameProfile, 0))) {
                System.out.println("Xa tes unha solicitude de amizade de " + nameProfile + "!");
            } else if (ProfileDB.findByName(nameProfile, 0).getFriendshipRequests().contains(profile)) {
                System.out.println("Xa tes unha solicitude de amizade para " + nameProfile + "!");
            }
            profileController.newFriendshipRequest(nameProfile);
        } else {
            System.out.println("Non se atopou un perfil con ese nome!");
            System.out.println();
        }
    }

    /**
     * Pide o n�mero dunha solicitude de amizade e chama ao controlador para
     * aceptala ou rexeitala, en funci�n do que se indique no par�metro
     * "accept".
     *
     * @param ownProfile un boolean que indica se estamos vendo o perfil propio.
     * @param scanner un obxecto da clase Scanner.
     * @param profile o perfil da sesi�n.
     * @param accept un boolean que indica se acepta a solicitude de amizade ou
     * non.
     */
    private void proccessFriendshipRequest(boolean ownProfile, Scanner scanner, Profile profile, boolean accept) {
        if (!profile.getFriendshipRequests().isEmpty()) {
            System.out.println("Dame o n�mero da solicitude de amizade:");
            int numberFriendshipRequest = scanner.nextInt();
            scanner.nextLine();
            if (accept) {
                profileController.acceptFriendshipRequest(profile.getFriendshipRequests().get(numberFriendshipRequest));
            } else {
                profileController.rejectFriendshipRequest(profile.getFriendshipRequests().get(numberFriendshipRequest));
            }
        } else {
            System.out.println("Non hai solicitudes de amizade pendentes!!");
        }

    }

    /**
     * Se estamos vendo o propio perfil, pide ao usuario selecciona un amigo e o
     * texto da mensaxe e chama ao controlador para enviar unha mensaxe. Se
     * estamos vendo o perfil dunha amizade, pide o texto para enviarlle unha
     * mensaxe a ese perfil.
     *
     * @param ownProfile un boolean que indica se estamos vendo o perfil propio.
     * @param scanner un obxecto da clase Scanner.
     * @param profile o perfil da sesi�n.
     */
    private void sendPrivateMessage(boolean ownProfile, Scanner scanner, Profile profile) {

        if (ownProfile) {
            System.out.println("Selecciona un amigo:");
            int numberFriend = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Dame o texto da mensaxe:");
            String message = scanner.nextLine();
            if (profile.getFriends().isEmpty()) {
                System.out.println("Non tes amig@s!!");
            } else {
                profileController.newMessage(profile.getFriends().get(numberFriend), message);
            }
        } else {
            scanner.nextLine();
            System.out.println("Dame o texto da mensaxe:");
            String message = scanner.nextLine();
            profileController.newMessage(profileController.getShownProfile(), message);
        }
    }

    /**
     * Pide ao usuario que seleccione unha mensaxe e a mostra completa, dando as
     * opci�ns de respondela, eliminala ou simplemente volver � biografia
     * marcando a mensaxe como lida, chamando ao controlador para executar as
     * distintas acci�ns.
     *
     * @param ownProfile un boolean que indica se estamos vendo o perfil propio.
     * @param scanner un obxecto da clase Scanner.
     * @param profile o perfil da sesi�n.
     */
    private void readPrivateMessage(boolean ownProfile, Scanner scanner, Profile profile) {
        System.out.println("Selecciona unha mensaxe:");
        int numberMessage = scanner.nextInt();
        scanner.nextLine();
        //Mostra a mensaxe completa
        System.out.println("profile.getMessages().get(postsShowed)");

        System.out.println("�Qu� desexas facer?");
        System.out.println("1 - Responder a mensaxe");
        System.out.println("2 - Eliminar a mensaxe");
        System.out.println("3 - Volver a biograf�a");
        int action = scanner.nextInt();
        scanner.nextLine();

        switch (action) {
            case 1:
                System.out.println("Dame o texto do mensaxe a enviar:");
                String text = scanner.nextLine();
                profileController.replyMessage(profile.getMessages().get(numberMessage), text);
                break;
            case 2:
                profileController.deleteMessage(profile.getMessages().get(numberMessage));
                break;
            case 3:
                profile.getMessages().get(numberMessage).setRead(true);
                break;
        }
    }

    /**
     * Pide ao usuario que seleccione unha mensaxe e chama ao controlador para
     * borrala.
     *
     * @param ownProfile un boolean que indica se estamos vendo o perfil propio.
     * @param scanner un obxecto da clase Scanner.
     * @param profile o perfil da sesi�n.
     */
    private void deletePrivateMessage(boolean ownProfile, Scanner scanner, Profile profile) {
        System.out.println("Selecciona unha mensaxe:");
        int numberMessage = scanner.nextInt();
        scanner.nextLine();
        profileController.deleteMessage(profile.getMessages().get(numberMessage));
    }

    /**
     * Pide o n�mero de publicaci�ns que se queren visualizar e chamar ao
     * controlador para recargar o perfil.
     *
     * @param scanner un obxecto da clase Scanner.
     * @param profile o perfil da sesi�n.
     */
    private void showOldPosts(Scanner scanner, Profile profile) {
        System.out.println("�C�ntas publicaci�ns desexas ver?");
        postsShowed = scanner.nextInt();
        scanner.nextLine();
        profileController.reloadProfile();
    }

    //Os m�todos que se incl�en a partir de aqu�, simplemente mostran mensaxes 
    //por pantalla e ch�manse dende o controlador para informar ao usuario de 
    //circunstancias que poden provocar que unha acci�n non se poida realizar. 
    /**
     * Alerta de que non se atopou o perfil co que se pretend�a establecer
     * amizade.
     */
    public void showProfileNotFoundMessage() {
        System.out.println("Non se atopou un perfil con ese nome!");
    }

    /**
     * Informa de que non se pode facer like sobre unha publicaci�n propia.
     */
    public void showCannotLikeOwnPostMessage() {
        System.out.println("Non se pode dar me gusta a unha publicaci�n propia");
    }

    /**
     * Informa de que non se pode facer like sobre unha publicaci�n sobre a que
     * xa se fixo like.
     */
    public void showAlreadyLikedPostMessage() {
        System.out.println("Non se pode facer like sobre unha publicaci�n sobre a que xa se fixo like");
    }

    /**
     * Informa de que xa tes amizade con ese perfil.
     *
     * @param profileName o nome do perfil que se pretend�a establecer amizade.
     */
    public void showIsAlreadyFriendMessage(String profileName) {
        System.out.println("Xa tes amizade con " + profileName);
    }

    /**
     * Informa de que ese perfil xa ten unha solicitude de amizade contigo.
     *
     * @param profileName o nome do perfil que se pretend�a establecer amizade.
     */
    public void showExistsFrienshipRequestMessage(String profileName) {
        System.out.println(profileName + " xa ten amizade contigo");
    }

    /**
     * Informa de que xa tes unha solicitude de amizade con ese perfil.
     *
     * @param profileName o nome do perfil que se pretend�a establecer amizade.
     */
    public void showDuplicateFrienshipRequestMessage(String profileName) {
        System.out.println("Xa tes unha solicitude de amizade con" + profileName);
    }

}
