import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Vector;


public class CalculVect {

    boolean polar = false;
    boolean cylindric = false;
    OperationVect operateur = new OperationVect();
    HashSet<Point> ramPt = new HashSet<Point>();
    HashSet<Vecteur> ramVector = new HashSet<Vecteur>();
    Vector<Point> ramPoint = new Vector<Point>(ramPt);
    Vector<Vecteur> ramVecteur = new Vector<Vecteur>(ramVector);
    JButton calculer;
    JButton record;
    JButton erase;
    JButton aPropos;
    JTextArea logField;
    JCheckBox cartesien;
    JCheckBox polaire;
    JCheckBox cylindrique;
    JTextField x, y, z, x1, y1, z1, x2, y2, z2, k;
    JTextField nom;
    JComboBox vectPoint;
    JComboBox vecteursA;
    JComboBox vecteursB;
    JComboBox operation;
    Point ansPoint;
    Vecteur ansVecteur;
    Double ansScalaire;
    Boolean ansBool;
    JFrame cadre = new JFrame("Calculatrice Vectorielle");
    JPanel panneauOuest = new JPanel();
    JPanel panneauEst = new JPanel();
    JPanel panneauSud = new JPanel();
    JPanel panneauCentre = new JPanel();
    JPanel panneauNord = new JPanel();
    Box miniBoite = new Box(BoxLayout.X_AXIS);
    Box miniBoite2 = new Box(BoxLayout.X_AXIS);
    Box miniBoite3 = new Box(BoxLayout.X_AXIS);
    Box miniBoite4 = new Box(BoxLayout.X_AXIS);
    Box boite4 = new Box(BoxLayout.Y_AXIS);
    Box boite3 = new Box(BoxLayout.Y_AXIS);
    Box boite1 = new Box(BoxLayout.X_AXIS);
    Box boite2 = new Box(BoxLayout.X_AXIS);
    Box dilatation = new Box(BoxLayout.Y_AXIS);
    Byte polCyl = 0;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new CalculVect().go();
    }

    @SuppressWarnings("unchecked")
    public void go() {
        ansPoint = new Point();
        ansVecteur = new Vecteur();
        ansVecteur.setName("Liste");
        ansPoint.setName("Liste");
        ramPoint.add(ansPoint);
        ramVecteur.add(ansVecteur);

        cadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panneauOuest.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panneauEst.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panneauSud.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panneauCentre.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panneauNord.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        aPropos = new JButton("À propos");
        aPropos.addActionListener(new EcouteAPropos());
        calculer = new JButton("Calculer");
        calculer.addActionListener(new EcouteCalculer());
        record = new JButton("stocker");
        record.addActionListener(new EcouteStocker());
        erase = new JButton("supprimer");
        erase.addActionListener(new EcouteSupprimer());

        logField = new JTextArea(15, 30);
        logField.setLineWrap(true);
        JScrollPane ascenseur = new JScrollPane(logField);
        ascenseur.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        ascenseur.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        cartesien = new JCheckBox("Rect");
        cartesien.addActionListener(new EcouteCheckBox());
        polaire = new JCheckBox("Pol");
        polaire.addActionListener(new EcouteCheckBox());
        cylindrique = new JCheckBox("Cyl");
        cylindrique.addActionListener(new EcouteCheckBox());
        cartesien.setSelected(true);
        polaire.setSelected(false);
        cylindrique.setSelected(false);

        x = new JTextField("0", 1);
//		x.setSize(5, 1);
        x.setText("0");
        y = new JTextField("0", 1);
        //	y.setSize(5, 1);
        y.setText("0");
        z = new JTextField("0", 1);
        //z.setSize(5, 1);
        z.setText("0");
        nom = new JTextField("", 8);
        nom.setText("O");
        //nom.setSize(8, 1);
        k = new JTextField();
        k.setText("1");

        x1 = new JTextField(4);
        y1 = new JTextField(4);
        z1 = new JTextField(4);
        x2 = new JTextField(4);
        y2 = new JTextField(4);
        z2 = new JTextField(4);

        String[] ListeOperations = {"Sélectionner", "Norme", "Produit Scalaire", "Produit Vectoriel", "Somme", "Différence", "Opposé", "Dilatation", "Perpendiculaires?", "Parallèles?"};
        String[] choixVP = {"Vecteur", "Point"};
        vectPoint = new JComboBox(choixVP);
        vectPoint.setEnabled(false);
        vecteursA = new JComboBox(ramVecteur);
        vecteursA.addActionListener(new SelectVecteurA());
        vecteursA.setSize(90, 10);
        vecteursB = new JComboBox(ramVecteur);
        vecteursB.addActionListener(new SelectVecteurB());
        vecteursB.setSize(90, 10);
        operation = new JComboBox(ListeOperations);
        operation.addActionListener(new OpSelect());

        miniBoite.add(new Label("X"));
        miniBoite.add(new Label("Y"));
        miniBoite.add(new Label("Z"));
        miniBoite2.add(x1);
        miniBoite2.add(y1);
        miniBoite2.add(z1);
        miniBoite3.add(new Label("X"));
        miniBoite3.add(new Label("Y"));
        miniBoite3.add(new Label("Z"));
        miniBoite4.add(x2);
        miniBoite4.add(y2);
        miniBoite4.add(z2);
        boite3.add(miniBoite);
        boite3.add(miniBoite2);
        boite3.add(new Label("Vecteur 1"));
        boite3.add(vecteursA);
        boite4.add(miniBoite3);
        boite4.add(miniBoite4);
        boite4.add(new Label("Vecteur 2"));
        boite4.add(vecteursB);
        dilatation.add(new Label("Facteur"));
        dilatation.add(k);

        panneauSud.add(boite3);
        panneauSud.add(boite4);
        panneauSud.add(operation);
        panneauSud.add(calculer);

        panneauNord.add(new Label("Type Coordonnées :"));
        panneauNord.add(cartesien);
        panneauNord.add(polaire);
        panneauNord.add(cylindrique);
        panneauNord.add(new Label("---"));
        panneauNord.add(aPropos);

        //panneauEst.setLayout(new BoxLayout(panneauEst, BoxLayout.Y_AXIS));	// empilement vertical

        boite1.add(new Label("X"));
        boite1.add(new Label("Y"));
        boite1.add(new Label("Z"));
        boite2.add(x);
        boite2.add(y);
        boite2.add(z);
        boite2.setSize(80, 10);
        panneauOuest.setLayout(new BoxLayout(panneauOuest, BoxLayout.Y_AXIS));    // empilement vertical
        panneauOuest.add(boite1);
        panneauOuest.add(boite2);
        panneauOuest.add(new Label("Nom :"));
        panneauOuest.add(nom);
        panneauOuest.setSize(30, 10);
        panneauOuest.add(new Label("---"));
        panneauOuest.add(record);
        panneauOuest.add(new Label("---"));
        panneauOuest.add(erase);
        panneauOuest.add(new Label("Type :"));
        panneauOuest.add(vectPoint);

        panneauCentre.setLayout(new BoxLayout(panneauCentre, BoxLayout.Y_AXIS));    // empilement vertical
        panneauCentre.add(new Label("Résultats :"));
        panneauCentre.add(ascenseur);

        cadre.setBackground(Color.DARK_GRAY);

        cadre.getContentPane().add(BorderLayout.EAST, panneauEst);
        cadre.getContentPane().add(BorderLayout.WEST, panneauOuest);
        cadre.getContentPane().add(BorderLayout.SOUTH, panneauSud);
        cadre.getContentPane().add(BorderLayout.CENTER, panneauCentre);
        cadre.getContentPane().add(BorderLayout.NORTH, panneauNord);

        cadre.setSize(640, 480);
        cadre.setVisible(true);
    }

    public Vecteur getVecteur(JTextField abs, JTextField ord, JTextField elv) {
        Vecteur u = new Vecteur(0, 0, 0, (byte) 0);
        String un, deux, trois;
        un = abs.getText();
        deux = ord.getText();
        trois = elv.getText();
        try {
            u.setx(Double.valueOf(un), polCyl);
            u.sety(Double.valueOf(deux), polCyl);
            u.setz(Double.valueOf(trois), polCyl);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Problème dans le format de donnée rentrée!\n");
            logField.append("Problème dans le format d'une des données rentrées. \n");
        }
        return u;
    }

    class EcouteAPropos implements ActionListener {
        public void actionPerformed(ActionEvent select) {
            JOptionPane.showMessageDialog(cadre, "Calculatrice vectorielle \n Version 1.0 \n" +
                    "Remarques et commentaires à Macy : macymenkra@gmail.com");
        }

        public void onFailure() {
        }

        public void onSuccess() {
        }
    }

    class SelectVecteurA implements ActionListener {
        public void actionPerformed(ActionEvent select) {
            Vecteur v;
            v = (Vecteur) vecteursA.getSelectedItem();
            x1.setText("" + v.getx(polCyl));
            y1.setText("" + v.gety(polCyl));
            z1.setText("" + v.getz(polCyl));
        }

        public void onFailure() {
        }

        public void onSuccess() {
        }
    }

    class EcouteCheckBox implements ActionListener {
        public void actionPerformed(ActionEvent clic) {
            Object source = clic.getSource();
            if (source == cartesien) {
                polaire.setSelected(false);
                cylindrique.setSelected(false);
                cartesien.setSelected(true);
                polCyl = 0;
            }
            if (source == polaire) {
                cartesien.setSelected(false);
                cylindrique.setSelected(false);
                polaire.setSelected(true);
                polCyl = 1;
            } else if (source == cylindrique) {
                polaire.setSelected(false);
                cartesien.setSelected(false);
                cylindrique.setSelected(true);
                polCyl = 2;
            }
        }

        public void onFailure() {
        }

        public void onSuccess() {
        }
    }

    class OpSelect implements ActionListener {
        public void actionPerformed(ActionEvent select) {
            Integer op = operation.getSelectedIndex();
            switch (op) {
                case 1:    // la norme est sélectionnée
                    x2.setEnabled(false);
                    y2.setEnabled(false);
                    z2.setEnabled(false);
                    vecteursB.setEnabled(false);
                    break;
                case 6:    // l'opposé est sélectionné
                    x2.setEnabled(false);
                    y2.setEnabled(false);
                    z2.setEnabled(false);
                    vecteursB.setEnabled(false);
                    break;
                case 7:    // la dilatation est sélectionnée
                    panneauSud.setVisible(false);
                    panneauSud.remove(boite4);
                    panneauSud.add(dilatation, 1);
                    panneauSud.setVisible(true);
                    break;
                default:
                    break;
            }
        }

        public void onFailure() {
        }

        public void onSuccess() {
        }
    }

    class SelectVecteurB implements ActionListener {
        public void actionPerformed(ActionEvent select) {
            Vecteur v;
            v = (Vecteur) vecteursB.getSelectedItem();
            x2.setText("" + v.getx(polCyl));
            y2.setText("" + v.gety(polCyl));
            z2.setText("" + v.getz(polCyl));
        }

        public void onFailure() {
        }

        public void onSuccess() {
        }
    }

    class EcouteStocker implements ActionListener {
        public void actionPerformed(ActionEvent clic) {
            if (vectPoint.getSelectedItem() == "Vecteur") {
                Vecteur tmp = getVecteur(x, y, z);
                tmp.setName(nom.getText());
                if (ramVector.add(tmp)) {    //	stockage du vecteur dans la ram
                    ramVecteur.add(tmp);
                    logField.append("vec(" + nom.getText() + ") : (" + tmp.getx(polCyl) + ";" + tmp.gety(polCyl) + ";" + tmp.getz(polCyl) + ") \n");
                } else {    // le stockage est un échec car l'elt existe déjà!
                    logField.append("Ce nom a déjà été attribué. Modifier le nom svp. \n");
                    x.setVisible(false);
                    y.setVisible(false);
                    z.setVisible(false);
                    x.setText("" + tmp.getx(polCyl));
                    y.setText("" + tmp.gety(polCyl));
                    z.setText("" + tmp.getz(polCyl)); // on remet le contenu initial des champs
                    x.setVisible(true);
                    y.setVisible(true);
                    z.setVisible(true);
                }
            } else {    //!\\	à modifier !
                Point tmp = new Point(0, 0, 0, polCyl);
                String un, deux, trois;
                un = x.getText();
                deux = y.getText();
                trois = z.getText();
                try {
                    tmp.setx(Double.valueOf(un), polCyl);
                    tmp.sety(Double.valueOf(deux), polCyl);
                    tmp.setz(Double.valueOf(trois), polCyl);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Problème dans le format de la donnée rentrée!\n");
                }
                tmp.setName(nom.getText());
                ramPoint.add(tmp);    //	stockage du point dans la ram
                logField.append("vec(" + nom.getText() + ") : (" + tmp.getx(polCyl) + ";" + tmp.gety(polCyl) + ";" + tmp.getz(polCyl) + ")\n");
            }
            x.setText(null);
            y.setText(null);
            z.setText(null);
            nom.setText(null); // effacer les contenus
        }

        public void onFailure() {
        }

        public void onSuccess() {
        }
    }

    class EcouteCalculer implements ActionListener {
        public void actionPerformed(ActionEvent clic) {
            Vecteur a = getVecteur(x1, y1, z1);
            Vecteur b = getVecteur(x2, y2, z2);
            Integer op = operation.getSelectedIndex();
            switch (op) {
                case 0:
                    traitementResultat();
                    break;
                case 1:
                    ansScalaire = a.norme();
                    traitementResultat(ansScalaire);
                    x2.setEnabled(true);
                    y2.setEnabled(true);
                    z2.setEnabled(true);
                    vecteursB.setEnabled(true);
                    break;
                case 2:
                    ansScalaire = new OperationVect().produitScal(a, b);
                    traitementResultat(ansScalaire);
                    break;
                case 3:
                    ansVecteur = new OperationVect().produitVect(a, b);
                    traitementResultat(ansVecteur);
                    break;
                case 4:
                    ansVecteur = new OperationVect().somme(a, b);
                    traitementResultat(ansVecteur);
                    break;
                case 5:
                    ansVecteur = new OperationVect().soustract(a, b);
                    traitementResultat(ansVecteur);
                    break;
                case 6:
                    ansVecteur = new OperationVect().oppose(a);
                    traitementResultat(ansVecteur);
                    x2.setEnabled(true);
                    y2.setEnabled(true);
                    z2.setEnabled(true);
                    vecteursB.setEnabled(true);
                    break;
                case 7:
                    ansVecteur = new OperationVect().dilatation(Double.valueOf(k.getText()), a);
                    traitementResultat(ansVecteur);
                    panneauSud.setVisible(false);
                    panneauSud.remove(dilatation);
                    panneauSud.add(boite4, 1);
                    panneauSud.setVisible(true);
                    break;
                case 8:
                    ansBool = new OperationVect().arePerpendicular(a, b);
                    traitementResultat(ansBool);
                    break;
                case 9:
                    ansBool = new OperationVect().areParallel(a, b);
                    traitementResultat(ansBool);
                    break;
                default:
                    logField.append("Choisir une opération à effectuer, svp. \n");
                    break;
            }
            operation.setSelectedIndex(0);
        }

        public void traitementResultat() {
            logField.append("> Veuillez sélectionner une opération à effectuer svp. \n");
        }

        public void traitementResultat(Vecteur v) {
            v.setName("Ans");
            logField.append(v + "\n");
            logField.append("> " + v.afficherCoord(polCyl) + "\n");
            if (ramVector.remove(v)) {    // renvoie TRUE si v existe déjà dans le hashSet
                ramVecteur.remove(v);
            }
            ramVector.add(v);
            ramVecteur.add(v);
            x.setText("" + v.getx(polCyl) + "");
            y.setText("" + v.gety(polCyl) + "");
            z.setText("" + v.getz(polCyl) + "");
            nom.setText(v.getName());
        }

        public void traitementResultat(Double result) {
            logField.append("> " + result + "\n");
        }

        public void traitementResultat(Boolean result) {
            if (result) {
                logField.append("> VRAI ! \n");
            } else {
                logField.append("> FAUX ! \n");
            }
        }

        public void onFailure() {
        }

        public void onSuccess() {
        }
    }

    class EcouteSupprimer implements ActionListener {
        public void actionPerformed(ActionEvent clic) {
            Vecteur v = new Vecteur();
            v.setName(nom.getText());
            if (v.getName() == "") {
                logField.append("Veuiller rentrer le nom du vecteur à supprimer svp. \n");
            } else if (ramVector.remove(v))    // renvoie true si v existe dans le hashSet
            {
                ramVecteur.remove(v);
                logField.append("Vecteur " + v.getName() + " supprimé. \n");
            } else {
                logField.append("Le vecteur spécifié n'existe pas dans ma mémoire. \n");
            }
        }

        public void onFailure() {
        }

        public void onSuccess() {
        }
    }
}
