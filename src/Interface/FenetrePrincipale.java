package Interface;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.util.logging.Handler;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.formdev.flatlaf.FlatIntelliJLaf;

import Controller.Geometrie;

import javax.swing.SpinnerNumberModel;
import java.awt.event.MouseAdapter;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuKeyEvent;

public class FenetrePrincipale {

	private JFrame frame;
	PanneauDessin z_contraintte = new PanneauDessin();
	static JLabel lblHauteur = new JLabel("Hauteur :");
	static JLabel lblLongueur = new JLabel("Longueur :");
	static JLabel lblLargeur = new JLabel("Largeur :");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new FlatIntelliJLaf());

		} catch (Exception e) {
			System.out.println("Echec de chargement de ce theme");
		}
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				FenetrePrincipale window = new FenetrePrincipale();
				window.frame.setVisible(true);
			}
		});

	}

	/**
	 * Create the application.
	 */
	public FenetrePrincipale() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 633, 528);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Geometrie f = new Geometrie();
		JSpinner longueur = new JSpinner();
		longueur.setModel(new SpinnerNumberModel(100, 100, 900, 1));
		JSpinner largeur = new JSpinner();
		largeur.setModel(new SpinnerNumberModel(100, 100, 900, 1));

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);

		JPanel panelTools = new JPanel();
		panelTools.setBackground(new Color(0, 51, 204));


		JButton btnNouveau = new JButton("");
		btnNouveau.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/Image/nouveau.png")));
		btnNouveau.setCursor(new Cursor(Cursor.HAND_CURSOR));

		JButton btnOuvrir = new JButton("");
		btnOuvrir.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/Image/folder.png")));
		btnOuvrir.setCursor(new Cursor(Cursor.HAND_CURSOR));

		JButton btnEnregistrer = new JButton("");
		btnEnregistrer.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/Image/enregistrer-le-fichier.png")));
		btnEnregistrer.setCursor(new Cursor(Cursor.HAND_CURSOR));

		JButton btnAvancer = new JButton("");
		btnAvancer.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/Image/refaire.png")));
		btnAvancer.setCursor(new Cursor(Cursor.HAND_CURSOR));

		JButton btnNewButton_5 = new JButton("Avancer");

		JLabel lblTitre = new JLabel("GRAPHICAL DESIGN TO M1 Images and Interaction");
		lblTitre.setFont(new Font("Berlin sans FB", Font.PLAIN, 18));
		lblTitre.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitre.setForeground(Color.WHITE);

		JButton btnReculer = new JButton("");
		btnReculer.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/Image/annuler.png")));
		btnReculer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				z_contraintte.previous();
			}
		});
		
		GroupLayout gl_panel_2 = new GroupLayout(panelTools);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_2
				.createSequentialGroup().addGap(6)
				.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false).addGroup(gl_panel_2
						.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblTitre, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnNouveau, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE).addGap(12)
						.addComponent(btnOuvrir, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
						.addComponent(btnEnregistrer, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnReculer, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnAvancer, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup().addGap(156).addComponent(btnNewButton_5)))
				.addGap(167)));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_2
				.createSequentialGroup()
				.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblTitre, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(btnOuvrir, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(btnEnregistrer, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(btnAvancer, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 26, Short.MAX_VALUE)
						.addComponent(btnReculer, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 26, Short.MAX_VALUE)
						.addComponent(btnNouveau, Alignment.LEADING, 0, 0, Short.MAX_VALUE))
				.addGap(18).addComponent(btnNewButton_5)));
		panelTools.setLayout(gl_panel_2);

		btnAvancer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				z_contraintte.next();
			}
		});
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				z_contraintte.EnregistrerImage();

			}
		});
		
		btnOuvrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					z_contraintte.OuvrirImage();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNouveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				z_contraintte.nettoyer();

			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, 622, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup().addGap(6)
						.addComponent(panelTools, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(panelTools, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)));

		JPanel z_configuration = new JPanel();
		z_configuration.setBackground(new Color(57, 104, 216));

		JPanel panelBas= new JPanel();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addComponent(z_configuration, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(z_contraintte, GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
								.addComponent(panelBas, GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE))
						.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addComponent(z_contraintte, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panelBas, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
				.addComponent(z_configuration, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE));
		
		
		//Pour les positions et tailles des contraintes
		JLabel lblPositionX = new JLabel("X : ");
		JLabel lblPositionY = new JLabel("Y : ");

		lblLargeur.setBounds(6, 6, 83, 16);
		lblLargeur.setFont(new Font("Berlin sans FB", Font.PLAIN, 14));	
		panelBas.add(lblLargeur);

		lblLongueur.setBounds(93, 6, 104, 16);
		lblLongueur.setFont(new Font("Berlin sans FB", Font.PLAIN, 14));
		panelBas.add(lblLongueur);

		lblHauteur.setBounds(199, 6, 86, 16);
		lblHauteur.setFont(new Font("Berlin sans FB", Font.PLAIN, 14));
		panelBas.add(lblHauteur);

		lblPositionY.setBounds(349, 6, 40, 16);
		lblPositionY.setFont(new Font("Berlin sans FB", Font.PLAIN, 14));
		panelBas.add(lblPositionY);

		lblPositionX.setBounds(297, 6, 40, 16);
		lblPositionY.setFont(new Font("Berlin sans FB", Font.PLAIN, 14));
		panelBas.add(lblPositionX);

	

		JButton btnManuelle = new JButton();
		btnManuelle.setBounds(4, 28, 48, 28);
		btnManuelle.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/Image/inclined-pencil.png")));
		btnManuelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				z_contraintte.setFormeG("trace_manuelle");
				z_contraintte.setTailleFG(3);
				z_contraintte.setColFore(Color.BLACK);
				z_contraintte.repaint();
			}
		});

		JButton btnCercleV = new JButton();
		btnCercleV.setBounds(4, 91, 48, 28);
		btnCercleV.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/Image/dry-clean.png")));
		btnCercleV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				z_contraintte.setFormeG("traceCercleV");
				z_contraintte.getTailleFG();
				z_contraintte.getColFore();
				z_contraintte.repaint();
			}
		});

		JButton btnTriangleV = new JButton();
		btnTriangleV.setBounds(4, 120, 48, 28);
		btnTriangleV.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/Image/triangle.png")));
		btnTriangleV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				z_contraintte.setFormeG("traceTriangleV");
				z_contraintte.getTailleFG();
				z_contraintte.getColFore();
				z_contraintte.repaint();
			}
		});

		JButton btnRectV = new JButton();
		btnRectV.setBounds(4, 60, 48, 28);
		btnRectV.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/Image/rectangle.png")));
		btnRectV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				z_contraintte.setFormeG("traceRectangleV");
				z_contraintte.getTailleFG();
				z_contraintte.getColFore();
				z_contraintte.repaint();
			}
		});

		
		JButton btnPolygoneV = new JButton();
		btnPolygoneV.setBounds(4, 150, 48, 28);
		btnPolygoneV.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/Image/hexagone.png")));
		btnPolygoneV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				z_contraintte.setFormeG("tracePolygoneV");
				z_contraintte.getTailleFG();
				z_contraintte.getColFore();
				z_contraintte.repaint();
			}
		});

		
		//Pour les contraintes matomboka feno fond jiaby
		JButton btnRectP = new JButton();
		btnRectP.setBounds(78, 60, 48, 28);
		btnRectP.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/Image/rectangle2.png")));
		btnRectP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				z_contraintte.setFormeG("traceRectangleP");
				z_contraintte.getTailleFG();
				z_contraintte.getColFore();
				z_contraintte.repaint();
			}
		});

		JButton btnCercleP = new JButton("");
		btnCercleP.setBounds(79, 91, 47, 28);
		btnCercleP.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/Image/cercle.png")));
		btnCercleP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				z_contraintte.setFormeG("traceCercleP");
				z_contraintte.getTailleFG();
				z_contraintte.getColFore();
				z_contraintte.repaint();
			}
		});

		JButton btnTriangleP = new JButton("");
		btnTriangleP.setBounds(78, 120, 48, 28);
		btnTriangleP.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/Image/triangle (2).png")));
		btnTriangleP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				z_contraintte.setFormeG("traceTriangleP");
				z_contraintte.getTailleFG();
				z_contraintte.getColFore();
				z_contraintte.repaint();
			}
		});

		JButton btnPolygoneP = new JButton("");
		btnPolygoneP.setBounds(78, 150, 48, 28);
		btnPolygoneP.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/Image/hexagone (1).png")));
		btnPolygoneP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				z_contraintte.setFormeG("tracePolygoneP");
				z_contraintte.getTailleFG();
				z_contraintte.getColFore();
				z_contraintte.repaint();
			}
		});


		JButton btnLigne = new JButton("");
		btnLigne.setBounds(78, 28, 48, 28);
		btnLigne.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/Image/line.png")));
		btnLigne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				z_contraintte.setFormeG("trace_ligne");
				z_contraintte.getTailleFG();
				z_contraintte.getColFore();
				z_contraintte.repaint();
			}
		});
		
		//Check pour générer la maison atomatique
		JRadioButton rdbtnGenAuto = new JRadioButton("Générer Auto");
		rdbtnGenAuto.setForeground(Color.WHITE);
		rdbtnGenAuto.setBounds(6, 330, 120, 18);
		rdbtnGenAuto.setCursor(new Cursor(Cursor.HAND_CURSOR));
		rdbtnGenAuto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					if (rdbtnGenAuto.isSelected()) {
						z_contraintte.setFormeG("GenererMaisonAuto");
						z_contraintte.repaint();
					} else {
						z_contraintte.setFormeG("GenererMaison");
						z_contraintte.repaint();
					}
			}
		});

		z_contraintte.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				lblPositionX.setText("X : " + e.getX());
				lblPositionY.setText("Y : " + e.getY());
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				lblPositionX.setText("X : " + e.getX());
				lblPositionY.setText("Y : " + e.getY());
				if (f instanceof Geometrie) {
					f.setWidth(e.getX() - f.getX());
					f.setHeight(e.getY() - f.getY());
					if (z_contraintte.getFormeG() == "GenererMaisonAuto") {
						lblHauteur.setText("Hauteur : " + f.getHauteurM());

						lblLongueur.setText("Longueur : " + f.getLargeurM());
					} else {
						lblLargeur.setText("Largeur : " + f.getHauteurM());
						lblLongueur.setText("Longueur : " + f.getLargeurM());
					}
				}
			}
		});
		panelBas.setLayout(null);
					

		//Pour le bouton valider
		JButton btnGenHome = new JButton("Valider");
		btnGenHome.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/Image/correction (1).png")));
		btnGenHome.setBackground(Color.BLACK);
		btnGenHome.setForeground(Color.WHITE);
		btnGenHome.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnGenHome.setBounds(6, 350, 122, 33);
		btnGenHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnGenAuto.isSelected()) {
					z_contraintte.setFormeG("GenererMaisonAuto");
					z_contraintte.repaint();
				} else {
					z_contraintte.setLargeur((int)largeur.getValue());
					z_contraintte.setHauteur((int)longueur.getValue());
					z_contraintte.setFormeG("GenererMaison");
					z_contraintte.repaint();
				}

			}
		});
		
		z_configuration.add(rdbtnGenAuto);
		
		//Pour le foreground du zone de contrainte
		JButton btnForeground = new JButton();
		btnForeground.setBounds(6, 560, 52, 30);
		btnForeground.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/Image/color-palette (1).png")));
		btnForeground.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color newColor = JColorChooser.showDialog(z_contraintte, "Choisir la couleur de la fenetre", Color.white);
				z_contraintte.setColFore(newColor);
				z_contraintte.setOpaque(true);
				z_contraintte.repaint();
				btnForeground.setBackground(newColor);
			}
		});
		btnForeground.setBackground(UIManager.getColor("CheckBox.foreground"));
		
		//Pour le background du zone de contrainte
		JButton btnBackground = new JButton("");
		btnBackground.setBounds(79, 560, 49, 30);
		btnBackground.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/Image/color-palette (1).png")));
		btnBackground.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color newColor = JColorChooser.showDialog(z_contraintte, "Choisissez la couleur de la fenetre", Color.white);
				z_contraintte.setColBack(newColor);
				z_contraintte.getColBack();
				z_contraintte.setOpaque(true);
				z_contraintte.repaint();
				btnBackground.setBackground(newColor);
			}
		});
		btnBackground.setBackground(new Color(255, 255, 255));
		
		
		
		//Pour les tailes des contraintes 
		JSlider sliderTaille = new JSlider();
		sliderTaille.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				z_contraintte.setTailleFG(sliderTaille.getValue());
			}
		});
		sliderTaille.setSnapToTicks(true);
		sliderTaille.setMaximum(20);
		sliderTaille.setMinimum(1);
		sliderTaille.setValue(1);
		sliderTaille.setPaintTicks(true);
		sliderTaille.setPaintLabels(true);
		sliderTaille.setMajorTickSpacing(20);
		sliderTaille.setMinorTickSpacing(1);
		sliderTaille.setBounds(6, 600, 122, 35);
		sliderTaille.setForeground(Color.WHITE);
		sliderTaille.setCursor(new Cursor(Cursor.HAND_CURSOR));

		JLabel formeGeome = new JLabel("Forme du terrain");
		formeGeome.setHorizontalAlignment(SwingConstants.CENTER);
		formeGeome.setBounds(6, 6, 115, 21);
		formeGeome.setFont(new Font("Berlin sans FB", Font.PLAIN, 14));
		formeGeome.setForeground(Color.WHITE);

		JLabel largeu = new JLabel("Largeur");
		largeu.setBounds(4, 202, 51, 16);
		largeu.setHorizontalAlignment(SwingConstants.CENTER);
		largeu.setFont(new Font("Berlin sans FB", Font.PLAIN, 13));
		largeu.setForeground(Color.WHITE);

		JLabel longeu = new JLabel("Longueur");
		longeu.setBounds(77, 202, 54, 16);
		longeu.setFont(new Font("Berlin sans FB", Font.PLAIN, 13));
		longeu.setForeground(Color.WHITE);
		
		
		z_configuration.setLayout(null);
		z_configuration.add(formeGeome);
		z_configuration.add(btnManuelle);
		z_configuration.add(btnLigne);
		z_configuration.add(btnRectV);
		z_configuration.add(btnRectP);
		z_configuration.add(btnCercleV);
		z_configuration.add(btnCercleP);
		z_configuration.add(btnTriangleV);
		z_configuration.add(btnTriangleP);
		z_configuration.add(btnPolygoneV);
		z_configuration.add(btnPolygoneP);
		z_configuration.add(btnGenHome);
		z_configuration.add(sliderTaille);
		z_configuration.add(btnForeground);
		z_configuration.add(largeu);
		z_configuration.add(longeu);
		z_configuration.add(btnBackground);

		largeur.setBounds(5, 215, 60, 21);
		z_configuration.add(largeur);

		longueur.setBounds(67, 215, 60, 21);
		z_configuration.add(longueur);


		JLabel genererMaison = new JLabel("Générer maison");
		genererMaison.setHorizontalAlignment(SwingConstants.CENTER);
		genererMaison.setBounds(6, 183, 110, 16);
		genererMaison.setFont(new Font("Berlin sans FB", Font.PLAIN, 14));
		genererMaison.setForeground(Color.WHITE);
		z_configuration.add(genererMaison);

		JButton toleF = new JButton();
		toleF.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/Image/tole-dacier.png")));
		toleF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color newColor = JColorChooser.showDialog(z_contraintte, "Choisissez la couleur de la fenetre", Color.white);
				z_contraintte.setColT(newColor);
				toleF.setBackground(newColor);
			}
		});
		toleF.setBackground(UIManager.getColor("Objects.YellowDark"));
		toleF.setBounds(6, 257, 46, 30);
		z_configuration.add(toleF);

		JButton porteF = new JButton("");
		porteF.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/Image/opened-door-aperture.png")));
		porteF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color newColor = JColorChooser.showDialog(z_contraintte, "Choisissez la couleur de la fenetre", Color.white);
				z_contraintte.setColP(newColor);
				porteF.setBackground(newColor);
			}
		});
		porteF.setBackground(UIManager.getColor("Button.background"));
		porteF.setBounds(78, 257, 43, 30);
		z_configuration.add(porteF);
		

		JButton btnMur = new JButton("");
		btnMur.setIcon(new ImageIcon(FenetrePrincipale.class.getResource("/Image/mur.png")));
		btnMur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color newColor = JColorChooser.showDialog(z_contraintte, "Choisissez la couleur", Color.white);
				z_contraintte.setColM(newColor);
				btnMur.setBackground(newColor);
			}
		});
		btnMur.setBackground(UIManager.getColor("Actions.Blue"));
		btnMur.setBounds(77, 305, 44, 30);
		z_configuration.add(btnMur);
		
		
		//Pour le label Toles
		JLabel toles = new JLabel("Toles");
		toles.setHorizontalAlignment(SwingConstants.CENTER);
		toles.setBounds(6, 241, 38, 16);
		toles.setFont(new Font("Berlin sans FB", Font.PLAIN, 13));
		toles.setForeground(Color.WHITE);	
		z_configuration.add(toles);
		
		//Pour le label mur
		JLabel mur = new JLabel("Mûrs");
		mur.setHorizontalAlignment(SwingConstants.CENTER);
		mur.setBounds(83, 290, 38, 16);
		mur.setFont(new Font("Berlin sans FB", Font.PLAIN, 13));
		mur.setForeground(Color.WHITE);
		z_configuration.add(mur);

		//Pour le label porte
		JLabel porte = new JLabel("Porte");
		porte.setHorizontalAlignment(SwingConstants.CENTER);
		porte.setBounds(85, 241, 41, 16);
		porte.setFont(new Font("Berlin sans FB", Font.PLAIN, 13));
		porte.setForeground(Color.WHITE);
		z_configuration.add(porte);

		JLabel colorF = new JLabel("FColor");
		colorF.setHorizontalAlignment(SwingConstants.CENTER);
		colorF.setBounds(6, 543, 52, 16);
		colorF.setFont(new Font("Berlin sans FB", Font.PLAIN, 13));
		colorF.setForeground(Color.WHITE);
		z_configuration.add(colorF);

		JLabel colorB = new JLabel("BColor");
		colorB.setHorizontalAlignment(SwingConstants.CENTER);
		colorB.setBounds(78, 543, 52, 16);
		colorB.setFont(new Font("Berlin sans FB", Font.PLAIN, 13));
		colorB.setForeground(Color.WHITE);
		z_configuration.add(colorB);
		
		panel.setLayout(gl_panel);
		frame.getContentPane().setLayout(groupLayout);

		
		/*************************************************************************************
		 * ***********************************************************************************
		 * ************************Code sur les menu*****************************************
		 * ***********************************************************************************
		 * ***********************************************************************************
		 **************************************************************************************/
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Fichier");
		mnNewMenu.setFont(new Font("Berlin sans FB", Font.PLAIN, 13));
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Nouveau");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				z_contraintte.nettoyer();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));


		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Ouvrir");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					z_contraintte.OuvrirImage();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		mnNewMenu.add(mntmNewMenuItem_1);

		mntmNewMenuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Sauvegarder");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				z_contraintte.EnregistrerImage();
			}
		});
		
		mnNewMenu.add(mntmNewMenuItem_2);

		mntmNewMenuItem_2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Fermer");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);
		mntmNewMenuItem_3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK));	
	}
}

