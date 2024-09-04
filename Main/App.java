public class App {
    static PlayerTab playerTab = new PlayerTab();
    static boolean running = true;
    static Player bluePlayer;
    static Player redPlayer;

    public static void main(String[] args) {
        playerTab.askTabSize();//demande de la taille du tableau
        
        //Paramétrage des joueurs
        int[] bluePlayerHeadPosition = new int[]{0,0};
        bluePlayer = new Player(1, bluePlayerHeadPosition);
        playerTab.changePlayerTab(bluePlayer.playerHeadPosition, bluePlayer.PLAYERNUMBER);
        int[] redPlayerHeadPosition = new int[]{playerTab.TABSIZE-1, playerTab.TABSIZE-1}; 
        redPlayer = new Player(2, redPlayerHeadPosition);
        playerTab.changePlayerTab(redPlayer.playerHeadPosition, redPlayer.PLAYERNUMBER);
        //Création d'obstacles et de fraise
        playerTab.createRandomObstacles(5);
        playerTab.createFraise();

        playerTab.showPlayerTab(); //Affichage du tableau
        //Tant que running == true, le jeu continuera. Boucle permettant de continuer le jeu ou de l'arreter 
        while (running == true) {
            Inputs.checkPlayerType(bluePlayer);
            //Vérification : si running est toujours true, si oui lancé la fonction checkPlayerType pour le joueur rouge
            if (running) Inputs.checkPlayerType(redPlayer);
        }
    }
}