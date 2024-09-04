import java.util.Random;
import java.util.Scanner;

public class Player {
    //Numéro qui figurera dans le tableau playerTab
    int PLAYERNUMBER;
    String TYPEOFPLAYER;
    //Tableau d'entier qui sera la position de début dans le tableau et la derniere position du joueur
    int[] playerHeadPosition;
    String playerDirection;
     //La variable ignoreCollision est la variable qui permet de savoir si le joueur a pris une fraise ou non
    boolean ignoreCollisions;
    
    //Constructeur qui initialise les variables de l'instance Player
    //Prend en parametre un entier et un tableau d'entier qui sera la position de début dans le tableau et la derniere position du joueur
    Player(int playerNumber, int[] playerHeadPosition){
        this.PLAYERNUMBER = playerNumber;
        this.playerHeadPosition = new int[]{playerHeadPosition[0], playerHeadPosition[1]};
        this.playerDirection = "O";
        this.ignoreCollisions = false;
        askTypeOfPlayer(); //appel de la fonction askTypeOfPlayer
    }

    //Fonction qui demande le type du joueur : Intelligence Artificielle, Joueur et modifie la variable TYPEOFPLAYER selon ce que le joueur a entré
    void askTypeOfPlayer(){
        Scanner scanner = new Scanner(System.in);
        boolean isTypeOfPlayer = false;
        while(isTypeOfPlayer == false){
            System.out.println(PLAYERNUMBER + " J : joueur ou IA : Intelligence Artificielle ?");
            this.TYPEOFPLAYER = scanner.nextLine();
            if(TYPEOFPLAYER.equals("J") || TYPEOFPLAYER.equals("IA"))
                isTypeOfPlayer = true;
        }
    }

    //Fonction qui change la variable playerDirection
    //Prend une variable en parametre qui permettra de changer la valeur de playerDirection
    void changePlayerDirection(String playerDirection){
        this.playerDirection = playerDirection;
        changePlayerHeadPosition(); //Appel de la fonction changePlayerHeadPosition
    }
    
    //Fonction qui change la variable playerHeadPosition selon la valeur de playerDirection
    void changePlayerHeadPosition(){
        if(playerDirection.equals("Z")) playerHeadPosition[0] = playerHeadPosition[0]-1; //Changement de playerHeadPosition de 1 vers le haut
        if(playerDirection.equals("S")) playerHeadPosition[0] = playerHeadPosition[0]+1; //Changement de playerHeadPosition de 1 vers le bas
        if(playerDirection.equals("Q")) playerHeadPosition[1] = playerHeadPosition[1]-1; //Changement de playerHeadPosition de 1 vers la gauche
        if (playerDirection.equals("D")) playerHeadPosition[1] = playerHeadPosition[1]+1; //Changement de playerHeadPosition de 1 vers la droite
        checkCollision(); //Appel de la fonction checkCollision
    }

    //Fonction qui va lancer la vérification des collision
    //Si la variable ignoreCollision est vrai et qu'il y a une potentielle collision, la variable ignoreCollision sera mis à faux et on créera une nouvelle fraise
    void checkCollision(){
            if(ignoreCollisions && ((playerHeadPosition[0] > App.playerTab.TABSIZE-1 || playerHeadPosition[0] < 0) ||(playerHeadPosition[1] > App.playerTab.TABSIZE-1 || playerHeadPosition[1] < 0) ||(App.playerTab.playerTab[playerHeadPosition[0]][playerHeadPosition[1]] != 0))){
                ignoreCollisions = false;
                App.playerTab.createFraise();
            }
            else
                App.playerTab.checkWallCollision(playerHeadPosition, PLAYERNUMBER); //Appel de la fonction de checkWallCollision situé dans playerTab.java
            if(App.running){ //Si running == true donc si le joeur n'a pas perdu
                App.playerTab.changePlayerTab(playerHeadPosition, PLAYERNUMBER); //Appel de la fonction changePlayerTab dans playerTab.java
                App.playerTab.showPlayerTab(); //Appel de la fonction showPlayerTab dans playerTab.java
            }
        }

    //Partie pour l'intelligence artificielle

    //Fonction qui va renvoyer un tableau modifié par la direction que l'intelligence artificielle aura pris
    //Marche de la meme facon que la fonction changePlayerHeadPosition
    int[] changeIaPosition(String iaDirection){
        int y = playerHeadPosition[0];
        int x = playerHeadPosition[1];
        int[] headPositionCopy = new int[]{y, x}; //copie de la variable playerHeadPosition
        if(iaDirection.equals("Z")) headPositionCopy[0] = headPositionCopy[0]-1;
        if(iaDirection.equals("S")) headPositionCopy[0] = headPositionCopy[0]+1;
        if(iaDirection.equals("Q")) headPositionCopy[1] = headPositionCopy[1]-1;
        if (iaDirection.equals("D")) headPositionCopy[1] = headPositionCopy[1]+1;
        return headPositionCopy; 
    }

    //Fonction qui va renvoyer une direction au hasard
    String iaRandomCommand(){
        Random random = new Random();
        int iaDirectionNum = random.nextInt(4);
        String iaDirection = "Z";
        if(iaDirectionNum == 0) iaDirection = "Z";
        if(iaDirectionNum == 1) iaDirection = "S";
        if(iaDirectionNum == 2) iaDirection = "Q";
        if(iaDirectionNum == 3) iaDirection = "D";
        return iaDirection;
    }

    //Fonction principal de l'intelligence artificielle
    void checkPossibility(){
        int y = playerHeadPosition[0];
        int x = playerHeadPosition[1];
        int [] playerHeadPositionCopy = new int[]{y, x};  //copie de playerHeadPosition
        boolean passPlayer = false;
        int i = 0;
        while(passPlayer ==false && i < 100){
            //Si il y a une potentielle collision
            if((playerHeadPositionCopy[0] > App.playerTab.TABSIZE-1 || playerHeadPositionCopy[0] < 0) ||(playerHeadPositionCopy[1] > App.playerTab.TABSIZE-1 || playerHeadPositionCopy[1] < 0) ||(App.playerTab.playerTab[playerHeadPositionCopy[0]][playerHeadPositionCopy[1]] != 0)){
                playerDirection = iaRandomCommand(); //Appel de la fonction iaRandomCommand, on stocke le résultat dans playerDirection
                playerHeadPositionCopy = changeIaPosition(playerDirection); //Appel de la fonction changeIaPosition pour modifier la fonction playerHeadPositionCopy
                i++; //Augmente la varialbe i de 1
            } else{ //Si il y a pas de potentielle collision
                passPlayer= true; //met passPlayer à false
                changePlayerHeadPosition(); //Appel de la fonction changePlayerHeadPosition
            } if(i == 100) App.playerTab.gameOver(PLAYERNUMBER); //si la variable i est == 100, on considere qu'il y a pas de solution et on appelle la fonction gameOver dans playerTab.java
        }
    }
}

