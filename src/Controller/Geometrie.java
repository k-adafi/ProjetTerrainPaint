package Controller;



import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;

// Classe pour construire les differentes formes Geometriques
public class Geometrie {
	
	protected double x,y,lastX,lastY;
	protected double width = 0, height =0;
	protected Color colT;
	protected Color colM;
	protected Color colP;
	Color colMurL = new Color(0,80,20);
	
	protected int contourTaille = 2;
	public String typeForme;
	protected Color bordureCol;
	static protected int largeurM, hauteurM;

//	Les constucteurs
	public Geometrie() {}
    
	public Geometrie(double x2, double y2, int taille, Color c, String typeForme) {
		this.x = x2;
		this.y = y2;
		this.contourTaille = taille;
		this.bordureCol = c;
		this.typeForme = typeForme;
	}

	public Geometrie(double width, double height, Color colT, Color colM, Color colML, Color colP,String typeForme) {
		this.x = width;
		this.y = height;
		this.colT = colT;
		this.colM = colM;
		this.colMurL= colML;
		this.colP = colP;
		this.typeForme = typeForme;
	}

    public static int getLargeurM() {
		return largeurM;
	}

	public static void setLargeurM(int largeurM) {
		Geometrie.largeurM = largeurM;
	}

	public static int getHauteurM() {
		return hauteurM;
	}

	public static void setHauteurM(int hauteurM) {
		Geometrie.hauteurM = hauteurM;
	}
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getLastX() {
		return lastX;
	}

	public void setLastX(double lastX) {
		this.lastX = lastX;
	}

	public double getLastY() {
		return lastY;
	}

	public void setLastY(double lastY) {
		this.lastY = lastY;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public Color getColT() {
		return colT;
	}

	public void setColT(Color colT) {
		this.colT = colT;
	}

	public Color getColM() {
		return colM;
	}

	public void setColM(Color colM) {
		this.colM = colM;
	}

	public Color getColP() {
		return colP;
	}

	public void setColP(Color colP) {
		this.colP = colP;
	}

	public Color getColMurL() {
		return colMurL;
	}

	public void setColMurL(Color colMurL) {
		this.colMurL = colMurL;
	}

	public int getContourTaille() {
		return contourTaille;
	}

	public void setContourTaille(int contourTaille) {
		this.contourTaille = contourTaille;
	}

	public String getTypeForme() {
		return typeForme;
	}

	public void setTypeForme(String typeForme) {
		this.typeForme = typeForme;
	}

	public Color getBordureCol() {
		return bordureCol;
	}

	public void setBordureCol(Color bordureCol) {
		this.bordureCol = bordureCol;
	}


}
