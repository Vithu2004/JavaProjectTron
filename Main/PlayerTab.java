import java.util.Scanner;
import java.util.Random;

public class PlayerTab {
    public int[][] playerTab;
    public int TABSIZE;
    public static Graphic frame;


    //Fonction qui demande la taille du tableau 
    void askTabSize(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Donnez la taille du tableau");
        TABSIZE = scanner.nextInt();
        playerTab = new int[TABSIZE][TABSIZE]; //Initialise le tableau avec ce que l'utilisateur a donné comme taille
        frame = new Graphic(TABSIZE); //Initialise l'instance de la classe Graphic situé le fichier Graphic.java
    }

    //Fonction qui change le tableau en fonction des parametre
    void changePlayerTab(int[] xyPosition, int number){
        playerTab[xyPosition[0]][xyPosition[1]] = number; //Met l'entier number dans la position envoyé dans le tableau
        frame.changeFrame(number, xyPosition); //Appel de la fonction changeFrame situé dans Graphic.java
    }

    //Fonction qui va afficher le tableau dans la console
    void showPlayerTab(){
        for(int i = 0; i< playerTab.length; i++){
            for(int y = 0; y <playerTab[i].length; y++)
            System.out.print(playerTab[i][y] + ", ");
            System.out.println(" ");
        }
        System.out.println("--------------------");
    }
    
    //Fonction qui crée des Obstacles dans le tableau playerTab
    //Prend en parametre le nombre d'obstacles
    void createRandomObstacles(int numObstacles) {
        Random random = new Random();
        for(int i =0; i <numObstacles; i++){ //Tant que i est inférieur au numéro d'obstacles
            int[] xyPosition = new int[]{random.nextInt(TABSIZE-1), random.nextInt(TABSIZE-1)}; //Choisi aléatoirement la position  de l'obstacles
            if (playerTab[xyPosition[0]][xyPosition[1]] == 0) //Vérification qu'il y a rien dans la futur position de l'obstacles
                changePlayerTab(xyPosition, 3); //Appel de la fonction changePlayerTab qui ajoutera l'obstacles dans le tableau
        }
    }

    //Fonction qui va crée une Fraise qui donne le superpouvoir d'ignorer une fois les obstacles et le joueur adversee
    void createFraise() {
        Random random = new Random();
        int[] xyPosition = new int[]{random.nextInt(TABSIZE-1), random.nextInt(TABSIZE-1)}; //Choisi aléatoirement la position  de la fraise
        if (playerTab[xyPosition[0]][xyPosition[1]] == 0) //Vérification qu'il y a rien dans la futur position de la fraise
            changePlayerTab(xyPosition, 4); //Appel de la fonction changePlayerTab qui ajoutera l'obstacles dans le tableau    
    }

    //Fonction qui va vérifier s'il y a une collision contre un mur
    //Prend en parametre la futur position du joueur : playerHeadPosition et son numéro : playerNumber
    void checkWallCollision(int[] playerHeadPosition, int playerNumber) {
        //S'il y a une collision
        if((playerHeadPosition[0] > TABSIZE-1 || playerHeadPosition[0] < 0) || (playerHeadPosition[1] > TABSIZE-1 || playerHeadPosition[1] < 0))
            gameOver(playerNumber); //Appel de la fonction gameOver situé tout en bas 
        else{ 
            if (checkObstaclesCollision(playerHeadPosition[0], playerHeadPosition[1])) //Appel de la fonction checkObstaclesCollision qui renvoie un boolean, si la valeur renvoyé est vrai
                gameOver(playerNumber); //Appel de la fonction gameOver situé tout en bas 
            else{
                checkFraiseCollision(App.redPlayer); //Appel de la fonction checkFraiseCollision pour les deux joueurs
                checkFraiseCollision(App.bluePlayer);
                checkPlayerCollision(App.redPlayer, App.bluePlayer); //Appel de la fonction checkPlayerCollsion
            }    
        }
    }
    
    //Fonction qui va vérifier s'il y a une Collision avec un autre joueur
    //Prend en parametre deux joueurs
    void checkPlayerCollision(Player bluePlayer, Player redPlayer) {
        //Si la futur position du joueur bleu dans le tableau est égale au numéro du joueur rouge
        if(playerTab[bluePlayer.playerHeadPosition[0]][bluePlayer.playerHeadPosition[1]] == redPlayer.PLAYERNUMBER)
            gameOver(bluePlayer.PLAYERNUMBER); //Appel de la fonction gameOver situé tout en bas
         //Si la futur position du joueur rouge dans le tableau est égale au numéro du joueur bleu 
        if(playerTab[redPlayer.playerHeadPosition[0]][redPlayer.playerHeadPosition[1]] == bluePlayer.PLAYERNUMBER)
            gameOver(redPlayer.PLAYERNUMBER); //Appel de la fonction gameOver situé tout en bas 
    }
    
    //Fonction qui renvoie un bolean
    //Prend la position x et y du joueur dans le tableau 
    boolean checkObstaclesCollision(int playerXPosition, int playerYPosition) {
        if (playerTab[playerXPosition][playerYPosition] == 3) //S'il y a une collision
            return true; //renvoie true
        else
            return false;
    }

    //Fonction qui vérifie s'il y a une collision avec une Fraise
    //Prend en parametre un joueur
    void checkFraiseCollision(Player player) {
        //S'il y a une collision avec une Fraise
        if(playerTab[player.playerHeadPosition[0]][player.playerHeadPosition[1]] == 4)
            player.ignoreCollisions = true; //Met la variable ignoreCollisions à true, ce qui permettra au joueur de pouvoir passer par un joueur ou un obstacle une fois
    }

    //Fonction qui annonce la fin du jeu
    //Prend en parametre le numéro du joueur qui a perdu
    void gameOver(int playerNumber){
        System.out.println("Le perdant est joueur n°" + playerNumber);
        App.running = false; //Met la variable running à false pour arreter le programme
    }
    
}