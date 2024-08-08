package Controller;



import java.awt.Color;
import java.awt.geom.Line2D;
import java.util.LinkedList;

public class TraceLigne extends Geometrie{
    LinkedList<Line2D> l = new LinkedList<>();

    public TraceLigne(double x, double y, int taille, Color c,String typeForme) {
        super(x, y,  taille,  c, typeForme);
        dessiner(x , y);
    }
    
    public void dessiner(double xd, double yd){
        l.add(new Line2D.Double(x, y, xd, yd));
        x = xd;
        y = yd;
    }

    public LinkedList<Line2D> getmL() {
        return l;
    }
}