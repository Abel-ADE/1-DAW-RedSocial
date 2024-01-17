/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook;

import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * Encárgase de mostrar as opcións do menú principal.
 *
 * @author Abel Iglesias Moure
 */
public class ProfileView {

    /**
     * Indica o número de publicacións que se mostran na visualización.
     */
    private int postsShowed = 10;
    /**
     * Para formatear as datas.
     */
    private final SimpleDateFormat formatter;

    /**
     * Mantén a referencia ao obxecto controlador.
     */
    private final ProfileController profileController;

    /**
     * Método que permite ver o número de publicacións que se mostran na
     * visualización.
     *
     * @return o número de publicacións que se mostran na visualización.
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
        this.formatter = new SimpleDateFormat("dd-MM-yyy 'ás' HH:mm:ss");
        this.profileController = profileController;
    }

    /**
     * Encárgase de mostrar toda a información do perfil, aínda que de momento
     * só será o nome e estado actual.
     *
     * @param ownProfile serve para indicar se se está vendo o perfil propio.
     * @param profile o perfil do usuario.
     */
    private void showProfileInfo(boolean ownProfile, Profile profile) {

        System.out.println();
        System.out.println("tacebook - Perfil do usuario: " + profile.getName());
        System.out.println("Estado actual: " + profile.getStatus());

        System.out.println("A túa biografía (" + postsShowed + " últimas publicacións):");

        //Mostro as publicacións
        for (int i = 0; i < profile.getPosts().size() && i < postsShowed && !profile.getPosts().isEmpty(); i++) {

            Post post = profile.getPosts().get(i);

            System.out.print("    ");
            System.out.print(i);
            System.out.print(".O ");
            System.out.print(formatter.format(post.getDate()));

            if (post.getAuthor().getName().equals(profileController.getSessionProfile().getName())) {
                System.out.print(" ti escribiches (");
            } else {
                System.out.print(" ");
                System.out.print(post.getAuthor().getName());
                System.out.print(" escribiu (");
            }

            System.out.print(post.getProfileLikes().size());
            System.out.println(" me gusta):");

            System.out.println("      " + post.getText());

            //Mostro os comentarios de cada publicación
            for (Comment comment : post.getComments()) {
                System.out.print("        - ");
                System.out.print(comment.getText());
                System.out.print(" - ");
                System.out.print(comment.getSourceProfile().getName());
                System.out.print(" - ");
                System.out.println(formatter.format(comment.getDate()));
            }
        }

        //Mostro as listas de amigos
        System.out.println("Lista de amigos:");

        for (int i = 0; i < profile.getFriends().size() && !profile.getFriends().isEmpty(); i++) {
            //Obteño o perfil da lista de amigos
            Profile prof = profile.getFriends().get(i);

            //Mostro os amigos
            System.out.print("    ");
            System.out.print(i);
            System.out.print(". ");
            System.out.print(prof.getName());
            System.out.print(" - ");
            System.out.println(prof.getStatus());
        }

        //Mostro as solicitudes de amizade
        if (!profile.getFriendshipRequests().isEmpty()) {
            System.out.println("Tes solicitudes de amizade dos seguintes perfís:");

            for (int i = 0; i < profile.getFriendshipRequests().size(); i++) {
                Profile prof = profile.getFriendshipRequests().get(i);

                System.out.print("    ");
                System.out.print(i);
                System.out.print(". ");
                System.out.print(prof.getName());
                System.out.println(" quere establecer amizade contigo.");
            }
        }

        //Mostro as mensaxes
        if (!profile.getMessages().isEmpty()) {

            int notRead = 0;

            for (Message message : profile.getMessages()) {
                if (!message.isRead()) {
                    notRead++;
                }
            }

            System.out.println(notRead > 0 ? "Tes " + notRead + " mensaxes sen ler!!" : "Mensaxes privados:");
            
            for (int i = 0; i < profile.getMessages().size(); i++) {

                Message mess = profile.getMessages().get(i);

                System.out.print(mess.isRead() ? "    " : "    *");
                System.out.print(i);
                System.out.print(". De ");
                System.out.print(mess.getDestProfile().getName());
                System.out.print("(");
                System.out.print(formatter.format(mess.getDate()));
                System.out.print(") ");
                System.out.print(mess.getText().substring(0, 10));
                System.out.println("...");
            }
        }

    }

    /**
     * Recíbe os parámetros necesarios para cambiar o estado do perfil.
     *
     * @param ownProfile serve para indicar se se está vendo o perfil propio.
     * @param scanner un scanner aberto para pedir datos do teclado.
     * @param profile o perfil actual.
     */
    private void changeStatus(boolean ownProfile, Scanner scanner, Profile profile) {
        if (ownProfile) {
            scanner.nextLine();
            System.out.print("Introduce o teu novo estado: ");
            profileController.updateProfileStatus(scanner.nextLine());
        } else {
            System.out.println("Esta opción só está permitida na túa biografía");
        }
    }

    /**
     * Chamará ao método "showProfileInfo" para mostrar a información do perfil,
     * e a continuación mostrará as opcións de cambiar o estado do perfil (que
     * provocará a chamada ao método "changeStatus") e pechar a sesión (que non
     * fará nada, simplemente sairá do método).
     *
     * @param profile o perfil do usuario.
     */
    public void showProfileMenu(Profile profile) {
        Scanner scanner = new Scanner(System.in);
        boolean isOwnProfile = profileController.getSessionProfile() == profileController.getShownProfile();
        int option;

        showProfileInfo(isOwnProfile, profile);

        System.out.println();
        System.out.println("Escolle unha opción:");
        System.out.println("1. Escribir unha nova publicación");
        System.out.println("2. Comentar unha publicación");
        System.out.println("3. Facer me gusta sobre unha publicación");

        if (isOwnProfile) {
            
            System.out.println("4. Ver a biografía dun amigo");
            System.out.println("5. Enviar unha solicitude de amizade");
            System.out.println("6. Aceptar unha solicitude de amizade");
            System.out.println("7. Rexeitar unha solicitude de amizade");
            System.out.println("8. Enviar unha mensaxe privada a un amigo");
            System.out.println("9. Ler unha mensaxe privada");
            System.out.println("10. Eliminar unha mensaxe privada");
            System.out.println("11. Ver publicacións anteriores");
            System.out.println("12. Cambiar o estado");
        } else {
            
            System.out.println("4. Volver a miña biografía");
            System.out.println("8. Enviar unha mensaxe privada");
            System.out.println("11. Ver publicacións anteriores");
        }

        System.out.println("13. Pechar a sesión");

        do {
            option = scanner.nextInt();
            scanner.nextLine();
            if (option <= 0 || option > 13) {
                System.out.println("Debes introducir un número entre 0 e 13");
            }
        } while (option <= 0 || option > 13);

        switch (option) {
            case 1: //1. Escribir unha nova publicación
                writeNewPost(scanner, profile);
                break;
            case 2: //2. Comentar unha publicación
                commentPost(scanner, profile);
                break;
            case 3: //3. Facer me gusta sobre unha publicación
                addLike(scanner, profile);
                break;
            case 4: //4. Ver a biografía dun amigo
                showBiography(isOwnProfile, scanner, profile);
                break;
            case 5: //5. Enviar unha solicitude de amizade
                sendFriendshipRequest(isOwnProfile, scanner, profile);
                break;
            case 6: //6. Aceptar unha solicitude de amizade
                proccessFriendshipRequest(isOwnProfile, scanner, profile, true);
                break;
            case 7: //7. Rexeitar unha solicitude de amizade
                proccessFriendshipRequest(isOwnProfile, scanner, profile, false);
                break;
            case 8: //8. Enviar unha mensaxe privada a un amigo
                sendPrivateMessage(isOwnProfile, scanner, profile);
                break;
            case 9: //9. Ler unha mensaxe privada
                readPrivateMessage(isOwnProfile, scanner, profile);
                break;
            case 10: //10. Eliminar unha mensaxe privada
                deletePrivateMessage(isOwnProfile, scanner, profile);
                break;
            case 11: //11. Ver publicacións anteriores
                showOldPosts(scanner, profile);
                break;
            case 12: //12. Cambiar o estado            
                changeStatus(true, scanner, profile);
                break;
            case 13: //13. Pechar a sesión
                //De momento non facemos nada
                break;
        }

        if (option != 13) {
            showProfileMenu(profile);
        }
    }

    /**
     * Este método utilizarase cando se pida ao usuario que introduza un número
     * para seleccionar un elemento dunha lista.
     *
     * @param text a lista de opcións a mostrar por pantalla.
     * @param maxNumber o número de opcións da lista.
     * @param scanner un obxecto da clase Scanner.
     * @return a opción que escolleu o usuario.
     */
    private int selectElement(String text, int maxNumber, Scanner scanner) {

        int option;

        do {
            System.out.println(text);
            option = scanner.nextInt();
            scanner.nextLine();
            if (option < 0 || option >= maxNumber) {
                System.out.println("Debes introducir un número entre 0 e " + (maxNumber - 1));
            }
        } while (option < 0 || option >= maxNumber);

        return option;
    }

    /**
     * Pide o texto para crear unha nova publicación e chama ao controlador para
     * creala.
     *
     * @param scanner un obxecto da clase Scanner.
     * @param profile o perfil que crea a publicación.
     */
    private void writeNewPost(Scanner scanner, Profile profile) {
        System.out.println("Dame o texto da publicación:");
        String text = scanner.nextLine();
        profileController.newPost(text, profile);
    }

    /**
     * Pide ao usuario que seleccione unha publicación e que introduza un texto,
     * e chama ao controlador para crear un comentario con ese texto.
     *
     * @param scanner un obxecto da clase Scanner.
     * @param profile o perfil que crea o comentario.
     */
    private void commentPost(Scanner scanner, Profile profile) {

        int numberPost = selectElement("Selecciona unha publicación:", profile.getPosts().size(), scanner);
        System.out.println("Introduce o texto do comentario:");
        String commentText = scanner.nextLine();
        profileController.newComment(profile.getPosts().get(numberPost), commentText);
    }

    /**
     * Pide ao usuario que seleccione unha publicación e chama ao controlador
     * para facer like sobre ela.
     *
     * @param scanner un obxecto da clase Scanner.
     * @param profile o perfil que da like sobre a publicación.
     */
    private void addLike(Scanner scanner, Profile profile) {
        int numberPost = selectElement("Selecciona unha publicación:", profile.getPosts().size(), scanner);
        profileController.newLike(profile.getPosts().get(numberPost));
    }

    /**
     * Se estamos vendo o propio perfil, pide ao usuario seleccionar unha
     * amizade para establecer ese perfil como perfil a mostrar, e senón volve a
     * poñer o perfil da sesión como perfil a mostrar.
     *
     * @param ownProfile un boolean que indica se estamos vendo o perfil propio.
     * @param scanner un obxecto da clase Scanner.
     * @param profile o perfil da sesión.
     */
    private void showBiography(boolean ownProfile, Scanner scanner, Profile profile) {

        if (ownProfile) {
            int numberProfile = selectElement("Selecciona unha amizade:", profile.getFriends().size(), scanner);
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
        if (ownProfile) {

            System.out.println("Dame o nome dun perfil:");
            scanner.nextLine();
            String nameProfile = scanner.nextLine();

            if (ProfileDB.findByName(nameProfile, 0) != null) {

                if (profile.getFriendshipRequests().contains(ProfileDB.findByName(nameProfile, 0))) {
                    System.out.println("Xa tes unha solicitude de amizade de " + nameProfile + "!");
                } else if (ProfileDB.findByName(nameProfile, 0).getFriendshipRequests().contains(profile)) {
                    System.out.println("Xa tes unha solicitude de amizade para " + nameProfile + "!");
                } else if (profile.getFriends().contains(ProfileDB.findByName(nameProfile, 0))) {
                    System.out.println("Xa tes amizade con " + nameProfile + "!");
                } else if (profile.getName().equals(ProfileDB.findByName(nameProfile, 0).getName())) {
                    System.out.println("Non se pode establecer amizade contigo mesmo!");
                } else {
                    profileController.newFriendshipRequest(nameProfile);
                }
            } else {
                System.out.println("Non se atopou un perfil con ese nome!");
            }
        } else {
            System.out.println("Esta opción só está permitida na túa biografía");
        }
    }

    /**
     * Pide o número dunha solicitude de amizade e chama ao controlador para
     * aceptala ou rexeitala, en función do que se indique no parámetro
     * "accept".
     *
     * @param ownProfile un boolean que indica se estamos vendo o perfil propio.
     * @param scanner un obxecto da clase Scanner.
     * @param profile o perfil da sesión.
     * @param accept un boolean que indica se acepta a solicitude de amizade ou
     * non.
     */
    private void proccessFriendshipRequest(boolean ownProfile, Scanner scanner, Profile profile, boolean accept) {
        if (ownProfile) {

            if (!profile.getFriendshipRequests().isEmpty()) {
                int numberFriendshipRequest = selectElement("Dame o número da solicitude de amizade:", profile.getFriendshipRequests().size(), scanner);
                if (accept) {
                    profileController.acceptFriendshipRequest(profile.getFriendshipRequests().get(numberFriendshipRequest));
                } else {
                    profileController.rejectFriendshipRequest(profile.getFriendshipRequests().get(numberFriendshipRequest));
                }
            } else {
                System.out.println("Non hai solicitudes de amizade pendentes!!");
            }

        } else {
            System.out.println("Esta opción só está permitida na túa biografía");
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
     * @param profile o perfil da sesión.
     */
    private void sendPrivateMessage(boolean ownProfile, Scanner scanner, Profile profile) {

        if (ownProfile) {
            int numberFriend = selectElement("Selecciona un amigo:", profile.getFriends().size(), scanner);
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
     * opcións de respondela, eliminala ou simplemente volver á biografia
     * marcando a mensaxe como lida, chamando ao controlador para executar as
     * distintas accións.
     *
     * @param ownProfile un boolean que indica se estamos vendo o perfil propio.
     * @param scanner un obxecto da clase Scanner.
     * @param profile o perfil da sesión.
     */
    private void readPrivateMessage(boolean ownProfile, Scanner scanner, Profile profile) {
        if (ownProfile) {

            if (!profile.getMessages().isEmpty()) {

                //Pide seleccionar a mensaxe para ler
                int numberMessage = selectElement("Selecciona unha mensaxe:", profile.getMessages().size(), scanner);

                //Mostra a mensaxe completa
                System.out.println("Mensaxe privada");
                System.out.println("De: " + profile.getMessages().get(numberMessage).getSourceProfile().getName());
                System.out.println("Data: " + formatter.format(profile.getMessages().get(numberMessage).getDate()));
                System.out.println("Texto:");
                System.out.println(profile.getMessages().get(numberMessage).getText());
                System.out.println("");

                //Marca a mensaxe como leida
                profile.getMessages().get(numberMessage).setRead(true);

                //Pide unha opción a escoller
                System.out.println("Escolle unha opción:");
                System.out.println("1 - Responder a mensaxe");
                System.out.println("2 - Eliminar a mensaxe");
                System.out.println("3 - Volver a biografía");
                int action = scanner.nextInt();
                scanner.nextLine();

                //Ejecuta a acción escollida polo usuario
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

            } else {
                System.out.println("Non tes mensaxes!!");
            }
        } else {
            System.out.println("Esta opción só está permitida na túa biografía");
        }
    }

    /**
     * Pide ao usuario que seleccione unha mensaxe e chama ao controlador para
     * borrala.
     *
     * @param ownProfile un boolean que indica se estamos vendo o perfil propio.
     * @param scanner un obxecto da clase Scanner.
     * @param profile o perfil da sesión.
     */
    private void deletePrivateMessage(boolean ownProfile, Scanner scanner, Profile profile) {
        if (ownProfile) {
            if (!profile.getMessages().isEmpty()) {
                int numberMessage = selectElement("Selecciona unha mensaxe:", profile.getMessages().size(), scanner);
                profileController.deleteMessage(profile.getMessages().get(numberMessage));
            } else {
                System.out.println("Non tes mensaxes!!");
            }
        } else {
            System.out.println("Esta opción só está permitida na túa biografía");
        }
    }

    /**
     * Pide o número de publicacións que se queren visualizar e chamar ao
     * controlador para recargar o perfil.
     *
     * @param scanner un obxecto da clase Scanner.
     * @param profile o perfil da sesión.
     */
    private void showOldPosts(Scanner scanner, Profile profile) {
        postsShowed = selectElement("¿Cántas publicacións desexas ver?", profile.getPosts().size(), scanner);
        profileController.reloadProfile();
    }

    //Os métodos que se inclúen a partir de aquí, simplemente mostran mensaxes 
    //por pantalla e chámanse dende o controlador para informar ao usuario de 
    //circunstancias que poden provocar que unha acción non se poida realizar. 
    /**
     * Alerta de que non se atopou o perfil co que se pretendía establecer
     * amizade.
     */
    public void showProfileNotFoundMessage() {
        System.out.println("Non se atopou un perfil con ese nome!");
    }

    /**
     * Informa de que non se pode facer like sobre unha publicación propia.
     */
    public void showCannotLikeOwnPostMessage() {
        System.out.println("Non se pode dar me gusta a unha publicación propia");
    }

    /**
     * Informa de que non se pode facer like sobre unha publicación sobre a que
     * xa se fixo like.
     */
    public void showAlreadyLikedPostMessage() {
        System.out.println("Non se pode facer like sobre unha publicación sobre a que xa se fixo like");
    }

    /**
     * Informa de que xa tes amizade con ese perfil.
     *
     * @param profileName o nome do perfil que se pretendía establecer amizade.
     */
    public void showIsAlreadyFriendMessage(String profileName) {
        System.out.println("Xa tes amizade con " + profileName);
    }

    /**
     * Informa de que ese perfil xa ten unha solicitude de amizade contigo.
     *
     * @param profileName o nome do perfil que se pretendía establecer amizade.
     */
    public void showExistsFrienshipRequestMessage(String profileName) {
        System.out.println(profileName + " xa ten amizade contigo");
    }

    /**
     * Informa de que xa tes unha solicitude de amizade con ese perfil.
     *
     * @param profileName o nome do perfil que se pretendía establecer amizade.
     */
    public void showDuplicateFrienshipRequestMessage(String profileName) {
        System.out.println("Xa tes unha solicitude de amizade con" + profileName);
    }

}
