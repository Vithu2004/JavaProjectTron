import java.util.Scanner;

public class Inputs {

    //Fonction qui vérifie qu'ellest le type du joueurs : Intelligence Artificielle ou Joueurs réelle. 
    //Prend en parametre une instance de Player
    public static void checkPlayerType(Player player){
        if(player.TYPEOFPLAYER.equals("IA"))
        player.checkPossibility();
        //Lance la fonction checkPossibilty si c'est une IA
        else
            askPlayerDirection(player);
        //Sinon cette fonction qui est situé dans le même fichier en bas
    }
    
    //Fonction qui demande les direction au joueur. Prend en parametre un joueur
    public static void askPlayerDirection(Player player){
        boolean isPlayerDirection = false;
        String playerDirection = "O";
        Scanner scanner = new Scanner(System.in);
        while(isPlayerDirection == false){ //Tant que le joueur n'a pas entrée les bonnes commandes, la fonction continuera de lui demander les commandes
            System.out.println("Ou voulez-vous aller ? Z : top, S : bottom, Q : left, D : right");
            playerDirection = scanner.nextLine();
            isPlayerDirection = checkPlayerDirection(playerDirection); //appel de la fonction checkPlayerDirection, le boolean renvoyé sera ensuite stocké dans la variable isPlayerDirection
        }
        player.changePlayerDirection(playerDirection); //appel de la fonction d'instance changePlayerDirection situé dans Player.java
    }

    //Fonction qui renvoie un booléan. Prend en parametre un string playerDirection
    //Renvoie false si le joueur n'a pas tapé les bonnes commandes
    public static boolean checkPlayerDirection(String playerDirection){
        if(!playerDirection.equals("Z") && !playerDirection.equals("S") && !playerDirection.equals("Q") && !playerDirection.equals("D")){
            System.out.println("Merci d'utiliser les bonnes touches.");
            return false;
        }
        else return true;
    }
}
