package Interface;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

import Controller.Geometrie;
import Controller.TraceLigne;

@SuppressWarnings("serial")
public class PanneauDessin extends JPanel {
	public int tailleFG = 1;
	private String formeG = "trace_manuelle";
	private Color colFore = Color.black;
	private Color colBack = Color.white;
	private Cursor cu = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	private Image image;
	private int Xmove, Ymove;

	int largeur = 200, hauteur = 300;

	Color colT = new Color(255, 203, 0);
	Color colP = new Color(242, 242, 242);
	Color colM = new Color(62, 142, 220);

	private ArrayList<Geometrie> tableG = new ArrayList<Geometrie>();
	private ArrayList<Geometrie> saveTrace = new ArrayList<Geometrie>();
	Geometrie f;
	TraceLigne traceLigne;

	public PanneauDessin() {
		this.addMouseMotionListener(new MouseMotionListener() {
			public void mouseMoved(MouseEvent souris) {
				if (f instanceof Geometrie) {
					if (f.getTypeForme() == "trace_ligne") {
						Rectangle2D ligne = new Rectangle2D.Double(f.getX(), f.getY(), f.getLastX() - f.getX(),
								f.getLastY() - f.getY());
						if (ligne.contains(souris.getX(), souris.getY())) {
							setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
						} else {
							setCursor(cu);
						}
					} else {
						Rectangle2D rect = new Rectangle2D.Double(f.getX(), f.getY(), f.getWidth(), f.getHeight());
						if (rect.contains(souris.getX(), souris.getY())) {
							setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
						} else {
							setCursor(cu);
						}
					}
				}
			}
			public void mouseDragged(MouseEvent e) {
				if (getCursor().equals(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR))) {
					f.setX(e.getXOnScreen() - Xmove);
					f.setY(e.getYOnScreen() - Ymove);
					f.setLastX(f.getX() + f.getHeight());
					f.setLastY(f.getY() + f.getWidth());

				} else if (formeG == "trace_manuelle") {
					traceLigne.dessiner(e.getX(), e.getY());
				} else if (formeG == "trace_ligne") {
					if (f != null) {
						f.setLastX(e.getX());
						f.setLastY(e.getY());
					}
				} else if (formeG == "GenererMaison") {
					if (f != null) {
						f.setWidth(getLargeur());
						f.setHeight(getHauteur());

					}
				} else {
					if (f != null) {
						f.setWidth(e.getX() - f.getX());
						f.setHeight(e.getY() - f.getY());

					}
				}
				repaint();
			}
		});
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (getCursor().equals(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR))) {
					Xmove = e.getXOnScreen() - (int) f.getX();
					Ymove = e.getYOnScreen() - (int) f.getY();

				} else {
					if (formeG == "trace_manuelle") {
						traceLigne = new TraceLigne(e.getX(), e.getY(), tailleFG, colFore, formeG);
						tableG.add(traceLigne);
					} else if (formeG == "GenererMaisonAuto") {
						f = new Geometrie(e.getX(), e.getY(), getColT(), getColM(), getColM(), getColP(), formeG);
						tableG.add(f);

					} else if (formeG == "GenererMaison") {
						f = new Geometrie(e.getX(), e.getY(), getColT(), getColM(), getColM(), getColP(), formeG);
						tableG.add(f);

					} else {
						f = new Geometrie(e.getX(), e.getY(), tailleFG, colFore, formeG);
						f.setLastX(e.getX());
						f.setLastY(e.getY());
						tableG.add(f);
						setTailleFG(tailleFG);
						getColFore(colFore);
					}
					repaint();
					saveTrace.clear();
				}
			}
		});
	}

    // Pour pouvoir dessiner dans le panneau de dessin
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(getColBack());
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		if (image != null) {
			g.drawImage(image, 0, 0, null);
		}
		for (Geometrie list : tableG) {
			g2.setColor(list.getBordureCol());
			g2.setStroke(new BasicStroke(list.getContourTaille(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			switch (list.typeForme) {
			case "trace_manuelle":
				TraceLigne lg = (TraceLigne) list;
				for (Line2D l : lg.getmL()) {
					g2.draw(l);
				}
				break;
			case "trace_ligne":
				Line2D ligne2d = new Line2D.Double(list.getX(), list.getY(), list.getLastX(), list.getLastY());
				g2.draw(ligne2d);

				list.setLargeurM((int) list.getLastX() - (int) list.getX());
				break;

			case "traceRectangleV":
				Rectangle2D rectangle = new Rectangle2D.Double(list.getX(), list.getY(), list.getWidth(),
						list.getHeight());
				g2.setStroke(new BasicStroke(list.getContourTaille(), BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
				list.setLargeurM((int) list.getWidth());
				list.setHauteurM((int) list.getHeight());
				g2.draw(rectangle);
				break;

			case "traceRectangleP":
				Rectangle2D rectangleP = new Rectangle2D.Double(list.getX(), list.getY(), list.getWidth(),
						list.getHeight());
				list.setLargeurM((int) list.getWidth());
				list.setHauteurM((int) list.getHeight());
				g2.setStroke(new BasicStroke(list.getContourTaille(), BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));

				g2.fill(rectangleP);
				break;

			case "traceCercleV":
				Ellipse2D ellypse = new Ellipse2D.Double(list.getX(), list.getY(), list.getWidth(), list.getHeight());
				g2.draw(ellypse);
				break;

			case "traceCercleP":
				Ellipse2D ellypseP = new Ellipse2D.Double(list.getX(), list.getY(), list.getWidth(), list.getHeight());
				g2.fill(ellypseP);
				break;
			case "traceTriangleV":
				Polygon triangleL = new Polygon(
						new int[] { (int) (list.getWidth() / 2 + list.getX()), (int) (list.getWidth() + list.getX()),
								(int) list.getX() },
						new int[] { (int) list.getY(), (int) (list.getHeight() + list.getY()),
								(int) (list.getHeight() + list.getY()) },
						3);
				g2.draw(triangleL);
				break;
			case "traceTriangleP":
				Polygon triangleLP = new Polygon(
						new int[] { (int) (list.getWidth() / 2 + list.getX()), (int) (list.getWidth() + list.getX()),
								(int) list.getX() },
						new int[] { (int) list.getY(), (int) (list.getHeight() + list.getY()),
								(int) (list.getHeight() + list.getY()) },
						3);
				g2.fill(triangleLP);
				break; 
			case "tracePolygoneV":
				Polygon HexagoneP = new Polygon(
						new int[] { (int) (list.getWidth() / 2 + list.getX()), (int) (list.getWidth() + list.getX()),
								(int) (list.getWidth() + list.getX()), (int) (list.getWidth() / 2 + list.getX()),
								(int) list.getX(), (int) list.getX() },
						new int[] { (int) list.getY(), (int) (list.getHeight() / 4 + list.getY()),
								(int) ((list.getHeight() * 3) / 4 + list.getY()),
								(int) (list.getHeight() + list.getY()),
								(int) ((list.getHeight() * 3) / 4 + list.getY()),
								(int) (list.getHeight() / 4 + list.getY()) },
						6);

				g2.draw(HexagoneP);
				break;
			case "tracePolygoneP":
				Polygon Hexagone = new Polygon(
						new int[] { (int) (list.getWidth() / 2 + list.getX()), (int) (list.getWidth() + list.getX()),
								(int) (list.getWidth() + list.getX()), (int) (list.getWidth() / 2 + list.getX()),
								(int) list.getX(), (int) list.getX() },
						new int[] { (int) list.getY(), (int) (list.getHeight() / 4 + list.getY()),
								(int) ((list.getHeight() * 3) / 4 + list.getY()),
								(int) (list.getHeight() + list.getY()),
								(int) ((list.getHeight() * 3) / 4 + list.getY()),
								(int) (list.getHeight() / 4 + list.getY()) },
						6);

				g2.fill(Hexagone);
				break;
			case "GenererMaison":
//					//Toles
				g2.setColor(list.getColT());
				Polygon p = new Polygon();
				p.addPoint((int) list.getWidth() / 4 + (int) list.getX(),
						(int) (list.getHeight() / 6 + (int) list.getY()));
				p.addPoint(((int) list.getWidth() / 4) * 5 + (int) list.getX(),
						(int) (list.getHeight() / 6 + (int) list.getY()));
				p.addPoint((int) list.getWidth() + (int) list.getWidth() / 12 + (int) list.getX(),
						(int) (list.getHeight()) / 3 + (int) list.getY());
				p.addPoint((int) list.getWidth() / 12 + (int) list.getX(),
						(int) (list.getHeight()) / 3 + (int) list.getY());
				g.fillPolygon(p);

				g2.setColor(new Color(0xAEAEAE));

				g2.draw(new Line2D.Double(((int) list.getWidth() / 4) * 5 + (int) list.getX(),
						(int) (list.getHeight()) / 6 + (int) list.getY(),
						((int) list.getWidth() / 3) * 4 + (int) list.getX(),
						(int) (list.getHeight()) / 5 + (int) list.getY()));
				g2.draw(new Line2D.Double(((int) list.getWidth() / 3) * 4 + (int) list.getX(),
						(int) (list.getHeight()) - (int) (list.getHeight()) / 2 + (int) list.getY(),
						((int) list.getWidth() / 3) * 4 + (int) list.getX(),
						((int) (list.getHeight()) / 5) + (int) list.getY()));
				g2.draw(new Line2D.Double((int) list.getWidth() + (int) list.getWidth() / 12 + (int) list.getX(),
						(int) (list.getHeight()) - (int) (list.getHeight()) / 3 + (int) list.getY(),
						((int) list.getWidth() / 3) * 4 + (int) list.getX(),
						(int) (list.getHeight()) - (int) (list.getHeight()) / 2 + (int) list.getY()));
				g2.draw(new Line2D.Double((int) list.getWidth() + (int) list.getWidth() / 12 + (int) list.getX(),
						(int) (list.getHeight()) / 3 + (int) list.getY(),
						((int) list.getWidth() / 3) * 4 + (int) list.getX(),
						(int) (list.getHeight()) / 5 + (int) list.getY()));

				// Mur pour le largeur
				g2.setColor(list.getColM());

				p = new Polygon();
				p.addPoint((int) list.getWidth() + (int) list.getWidth() / 12 + (int) list.getX(),
						(int) (list.getHeight() / 3) + (int) list.getY());
				p.addPoint(((int) list.getWidth() / 3) * 4 + (int) list.getX(),
						(int) list.getHeight() / 5 + (int) list.getY());

				p.addPoint(((int) list.getWidth() / 3) * 4 + (int) list.getX(),
						(int) list.getHeight() - (int) list.getHeight() / 2 + (int) list.getY());
				p.addPoint((int) list.getWidth() + (int) list.getWidth() / 12 + (int) list.getX(),
						(int) list.getHeight() - (int) list.getHeight() / 3 + (int) list.getY());
				g.fillPolygon(p);

				// Mur devant
				g2.setColor(list.getColM());

				p = new Polygon();
				p.addPoint((int) list.getWidth() / 12 + (int) list.getX(),
						(int) list.getHeight() / 3 + (int) list.getY());
				p.addPoint((int) list.getWidth() + (int) list.getWidth() / 12 + (int) list.getX(),
						(int) list.getHeight() / 3 + (int) list.getY());
				p.addPoint((int) list.getWidth() + (int) list.getWidth() / 12 + (int) list.getX(),
						(int) list.getHeight() - (int) list.getHeight() / 3 + (int) list.getY());
				p.addPoint((int) list.getWidth() / 12 + (int) list.getX(),
						(int) list.getHeight() - (int) list.getHeight() / 3 + (int) list.getY());

				list.setLargeurM(((int) list.getWidth() + (int) list.getWidth() / 12 + (int) list.getX())
						- ((int) list.getWidth() / 12 + (int) list.getX()));
				list.setHauteurM(((int) list.getHeight() / 3) + ((int) list.getHeight() - (int) list.getHeight() / 3));

				g.fillPolygon(p);
				// la Fenetre
				g2.setColor(list.getColP());
				p = new Polygon();
				p.addPoint((int) list.getWidth() / 10 + (int) list.getX(),
						(int) list.getHeight() / 2 - (int) list.getHeight() / 8 + (int) list.getY());
				p.addPoint((int) list.getWidth() / 10 + (int) list.getX(),
						(int) list.getHeight() / 2 + (int) list.getHeight() / 10 + (int) list.getY());
				p.addPoint((int) list.getWidth() / 4 + (int) list.getX(),
						(int) list.getHeight() / 2 + (int) list.getHeight() / 10 + (int) list.getY());
				p.addPoint((int) list.getWidth() / 4 + (int) list.getX(),
						(int) list.getHeight() / 2 - (int) list.getHeight() / 8 + (int) list.getY());
				// la Porte
				g.fillPolygon(p);

				g2.setColor(list.getColP());
				p = new Polygon();
				p.addPoint((int) list.getWidth() - (int) list.getWidth() / 12 + (int) list.getX(),
						(int) list.getHeight() / 2 - (int) list.getHeight() / 8 + (int) list.getY());
				p.addPoint((int) list.getWidth() - (int) list.getWidth() / 12 + (int) list.getX(),
						(int) list.getHeight() / 2 + (int) list.getHeight() / 7 + (int) list.getY());
				p.addPoint((int) list.getWidth() / 2 + (int) list.getX(),
						(int) list.getHeight() / 2 + (int) list.getHeight() / 7 + (int) list.getY());
				p.addPoint((int) list.getWidth() / 2 + (int) list.getX(),
						(int) list.getHeight() / 2 - (int) list.getHeight() / 8 + (int) list.getY());

				g.fillPolygon(p);
				break;

			case "GenererMaisonAuto":
				// Toles
				g2.setColor(list.getColT());
				Polygon p1 = new Polygon();
				p1.addPoint((int) list.getWidth() / 4 + (int) list.getX(),
						(int) (list.getHeight() / 6 + (int) list.getY()));
				p1.addPoint(((int) list.getWidth() / 4) * 5 + (int) list.getX(),
						(int) (list.getHeight() / 6 + (int) list.getY()));
				p1.addPoint((int) list.getWidth() + (int) list.getWidth() / 12 + (int) list.getX(),
						(int) (list.getHeight()) / 3 + (int) list.getY());
				p1.addPoint((int) list.getWidth() / 12 + (int) list.getX(),
						(int) (list.getHeight()) / 3 + (int) list.getY());
				g.fillPolygon(p1);

				g2.setColor(new Color(0xAEAEAE));

				g2.draw(new Line2D.Double(((int) list.getWidth() / 4) * 5 + (int) list.getX(),
						(int) (list.getHeight()) / 6 + (int) list.getY(),
						((int) list.getWidth() / 3) * 4 + (int) list.getX(),
						(int) (list.getHeight()) / 5 + (int) list.getY()));
				g2.draw(new Line2D.Double(((int) list.getWidth() / 3) * 4 + (int) list.getX(),
						(int) (list.getHeight()) - (int) (list.getHeight()) / 2 + (int) list.getY(),
						((int) list.getWidth() / 3) * 4 + (int) list.getX(),
						((int) (list.getHeight()) / 5) + (int) list.getY()));
				g2.draw(new Line2D.Double((int) list.getWidth() + (int) list.getWidth() / 12 + (int) list.getX(),
						(int) (list.getHeight()) - (int) (list.getHeight()) / 3 + (int) list.getY(),
						((int) list.getWidth() / 3) * 4 + (int) list.getX(),
						(int) (list.getHeight()) - (int) (list.getHeight()) / 2 + (int) list.getY()));
				g2.draw(new Line2D.Double((int) list.getWidth() + (int) list.getWidth() / 12 + (int) list.getX(),
						(int) (list.getHeight()) / 3 + (int) list.getY(),
						((int) list.getWidth() / 3) * 4 + (int) list.getX(),
						(int) (list.getHeight()) / 5 + (int) list.getY()));

				// Mur pour le largeur
				g2.setColor(list.getColM());

				p = new Polygon();
				p.addPoint((int) list.getWidth() + (int) list.getWidth() / 12 + (int) list.getX(),
						(int) (list.getHeight() / 3) + (int) list.getY());
				p.addPoint(((int) list.getWidth() / 3) * 4 + (int) list.getX(),
						(int) list.getHeight() / 5 + (int) list.getY());

				p.addPoint(((int) list.getWidth() / 3) * 4 + (int) list.getX(),
						(int) list.getHeight() - (int) list.getHeight() / 2 + (int) list.getY());
				p.addPoint((int) list.getWidth() + (int) list.getWidth() / 12 + (int) list.getX(),
						(int) list.getHeight() - (int) list.getHeight() / 3 + (int) list.getY());
				g.fillPolygon(p);

				// Mur devant
				g2.setColor(list.getColM());

				p = new Polygon();
				p.addPoint((int) list.getWidth() / 12 + (int) list.getX(),
						(int) list.getHeight() / 3 + (int) list.getY());
				p.addPoint((int) list.getWidth() + (int) list.getWidth() / 12 + (int) list.getX(),
						(int) list.getHeight() / 3 + (int) list.getY());
				p.addPoint((int) list.getWidth() + (int) list.getWidth() / 12 + (int) list.getX(),
						(int) list.getHeight() - (int) list.getHeight() / 3 + (int) list.getY());
				p.addPoint((int) list.getWidth() / 12 + (int) list.getX(),
						(int) list.getHeight() - (int) list.getHeight() / 3 + (int) list.getY());

				list.setLargeurM(((int) list.getWidth() + (int) list.getWidth() / 12 + (int) list.getX())
						- ((int) list.getWidth() / 12 + (int) list.getX()));
				list.setHauteurM(((int) list.getHeight() / 3) + ((int) list.getHeight() - (int) list.getHeight() / 3));

				g.fillPolygon(p);
				// la Fenetre
				g2.setColor(list.getColP());
				p = new Polygon();
				p.addPoint((int) list.getWidth() / 10 + (int) list.getX(),
						(int) list.getHeight() / 2 - (int) list.getHeight() / 8 + (int) list.getY());
				p.addPoint((int) list.getWidth() / 10 + (int) list.getX(),
						(int) list.getHeight() / 2 + (int) list.getHeight() / 10 + (int) list.getY());
				p.addPoint((int) list.getWidth() / 4 + (int) list.getX(),
						(int) list.getHeight() / 2 + (int) list.getHeight() / 10 + (int) list.getY());
				p.addPoint((int) list.getWidth() / 4 + (int) list.getX(),
						(int) list.getHeight() / 2 - (int) list.getHeight() / 8 + (int) list.getY());
				// la Porte
				g.fillPolygon(p);

				g2.setColor(list.getColP());
				p = new Polygon();
				p.addPoint((int) list.getWidth() - (int) list.getWidth() / 12 + (int) list.getX(),
						(int) list.getHeight() / 2 - (int) list.getHeight() / 8 + (int) list.getY());
				p.addPoint((int) list.getWidth() - (int) list.getWidth() / 12 + (int) list.getX(),
						(int) list.getHeight() / 2 + (int) list.getHeight() / 7 + (int) list.getY());
				p.addPoint((int) list.getWidth() / 2 + (int) list.getX(),
						(int) list.getHeight() / 2 + (int) list.getHeight() / 7 + (int) list.getY());
				p.addPoint((int) list.getWidth() / 2 + (int) list.getX(),
						(int) list.getHeight() / 2 - (int) list.getHeight() / 8 + (int) list.getY());

				g.fillPolygon(p);
				break;
			default:
				break;

			}
		}
	}

// Methode pour netoyer le panneau de dessin
	public void effacerTous() {
		this.tableG = new ArrayList<Geometrie>();
	}

	// Methode pour pouvoir revenir en arriere
	public void previous() {
		if (!tableG.isEmpty()) {
			Geometrie T = tableG.get(tableG.size() - 1);
			tableG.remove(T);
			saveTrace.add(T);
			repaint();
		}
	}

	// Methode pour avancer vers à la derniere image
	public void next() {
		if (!saveTrace.isEmpty()) {
			Geometrie T = saveTrace.get(saveTrace.size() - 1);
			saveTrace.remove(T);
			tableG.add(T);
			repaint();
		}
	}
	
	// Methode pour enregistrer le dessin dans le panneau
	public void EnregistrerImage() {

		int sauvegarde = JOptionPane.showConfirmDialog(null, "VOULEZ VOUS  SAUVEGARDER CE DESSIN ?", "SAUVEGARDER",
				JOptionPane.YES_NO_OPTION);
		if (sauvegarde == 0) {
			BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
			this.paint(image.getGraphics());
			try {
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				int result = jfc.showSaveDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {

					File fichier = new File(jfc.getSelectedFile() + ".png");
					ImageIO.write(image, "PNG", fichier);
				}
			} catch (IOException ex) {

			}
		}
	}

	// Methode pour ouvrir l'explorateur de selectionner l'image en format PNG
	public void OuvrirImage() throws IOException {

		Graphics2D graph = (Graphics2D) this.getGraphics();
		JFileChooser jfile = new JFileChooser();
		int status = jfile.showOpenDialog(this);
		File file = jfile.getSelectedFile();
		if (status == JFileChooser.APPROVE_OPTION) {
			image = ImageIO.read(file);
			graph.drawImage(image, 0, 0, null);
			effacerTous();
		}
	}

	// Methode pour netoyer le panneau de dessin
	public void nettoyer() {
		if (tableG.isEmpty()) {
			repaint();
		} else {
			if (JOptionPane.showConfirmDialog(this, "Enregistrer ", "",
					JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
				EnregistrerImage();
				effacerTous();
				repaint();
			} else {
				effacerTous();
				repaint();
			}
		}
	}

// Les accesseurs
	public Color getColT() {
		return colT;
	}

	public void setColT(Color colT) {
		this.colT = colT;
	}

	public Color getColP() {
		return colP;
	}

	public void setColP(Color colP) {
		this.colP = colP;
	}

	public Color getColM() {
		return colM;
	}

	public void setColM(Color colM) {
		this.colM = colM;
	}

	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	public void setFormeG(String formeG) {
		this.formeG = formeG;
	}

	public String getFormeG() {
		return formeG;
	}

	public void getColFore(Color colFore) {
		this.colFore = colFore;
	}

	public int getTailleFG() {
		return tailleFG;
	}

	public void setTailleFG(int tailleFG) {
		this.tailleFG = tailleFG;
	}

	public Color getColBack() {
		return colBack;
	}

	public void setColBack(Color colBack) {
		this.colBack = colBack;
	}

	public void setColFore(Color colFore) {
		this.colFore = colFore;
	}

	public Color getColFore() {
		return colFore;
	}

}

