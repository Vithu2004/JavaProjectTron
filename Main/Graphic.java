import graphics.*;

public class Graphic{
    //Taille d'un bloc
    int widthAndHeight;
    //Taille de la fenetre
    int FRAMESIZE = 700;
    
    //Constructeur qui va créer un bloc de la taille de FRAMESIZE et qu'il calculera la taille d'un bloc(variable : widthAndHeight) 
    //Prend en parametre un entier tabsize qui est la taille du tableau
    Graphic(int tabsize){
        Rectangle box = new Rectangle(0,0,FRAMESIZE,FRAMESIZE);
        box.fill();
        widthAndHeight = FRAMESIZE/tabsize;     
    }

    //Fonction qui va changer la fenetre pour rajouter un bloc
    //Prend en parametre un entier et la position dans le tableau
    public void changeFrame(int number, int[] xyPosition){
        int[] xyPositionFrame = changeXyPositionToFramePosition(xyPosition); //Appel de la fonction changeXyPositionToFramePosition qui détermine la position dans la fenetre
        Color color = setRectangleColor(number); //Appel de la fonction setRectangleColor qui détermine la couleur du bloc
        //Création du bloc
        Rectangle rectangle = new Rectangle(xyPositionFrame[1], xyPositionFrame[0], widthAndHeight, widthAndHeight);
        rectangle.setColor(color);
        rectangle.fill();
    }

    //Fonction qui va renvoyer et convertir la position du tableau en position dans la fenetre
    public int[] changeXyPositionToFramePosition(int[] xyPosition){
        int[] xyPositionFrame = new int[]{xyPosition[0] * widthAndHeight, xyPosition[1] * widthAndHeight};
        return xyPositionFrame;
    }
    
    //Fonction qui renvoie la couleur du bloc
    public Color setRectangleColor(int number){
        if(number==1) return Color.BLUE;
        if(number==2) return Color.RED;
        if(number ==3) return Color.GRAY;
        return Color.PINK;
    }
}