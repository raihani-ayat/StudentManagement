package gestionetudiants;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;
import java.text.DecimalFormat;

/*afficher les moyennse des etudiants par filiere*/

public class Moyennes
    extends ClassMere
    implements ItemListener, ActionListener {

  private int niv = 1;
  private String nomFiliere = "Choisir une filiere";

  private JComboBox listFil = new JComboBox();
  private JComboBox listNiv = new JComboBox(); //pour afficher la liste de filieres

  private MonJLabel fil_lb = new MonJLabel("Filiere:"),
      nomMat_lb = new MonJLabel("Filiere");

  private JButton retour = new JButton("Retour");
  private JPanel ret_pn = new JPanel(), centre = new JPanel(),
      fil_pn = new JPanel();

  private JTable table;
  private JScrollPane sp = new JScrollPane();

  private Vector nomColonnnes = new Vector();
  private Vector donnees = new Vector();

  private DecimalFormat df = new DecimalFormat("00.00"); //nbre de chiffres apres la virgule

  public Moyennes() {

    super("Moyennes", 500, 400);

    //initialisation de la liste des filieres
    listFil.addItem("Choisir une filiere");
    Filiere fi;
    for (int i = 0; i < MenuPrincipale.listFilieres.size(); i++) {
      fi = (Filiere) MenuPrincipale.listFilieres.elementAt(i);
      if (fi.getNiveau() == 1 || fi.getNiveau() == 2) {
        listFil.addItem(fi.getNom());
      }
    }
    listFil.addItemListener(this); //car si la filiere choisi est mpi ou cba il ya un seul niveau sinon il ya 5
    listFil.setSelectedIndex(0); //Choisir une filiere est selectionné par defaut

    //initialisation des elements du niveau
    listNiv = new JComboBox();
    for (int i = 2; i <= 5; i++) {
      listNiv.addItem("" + i);
    }
    listNiv.addItemListener(this);
    listNiv.setVisible(false); //niveau est au debut non visible
    listNiv.setSelectedIndex(0);

    //initialisation du veteur nomColonnnes
    nomColonnnes.addElement("Numero");
    nomColonnnes.addElement("Nom");
    nomColonnnes.addElement("Prenom");
    nomColonnnes.addElement("Moyenne");
    nomColonnnes.addElement("Decision");

    //les panneaux
    fil_pn.add(fil_lb);
    fil_pn.add(listFil);
    fil_pn.add(listNiv);
    centre.setLayout(new BorderLayout());
    centre.add("North", fil_pn);
    centre.add("Center", sp);
    retour.addActionListener(this);
    ret_pn.add(retour);

    table = new JTable(donnees, nomColonnnes);
    sp.setViewportView(table);

    getContentPane().add(centre, BorderLayout.CENTER);
    getContentPane().add(ret_pn, BorderLayout.SOUTH);

    show();

  }

//  public static void main(String[] args) {
//    Moyennes moyennes1 = new Moyennes();
//    double a=15.54433;
//    DecimalFormat df = new DecimalFormat("##.00");
//    System.out.println(df.format(a));
//  }

  public void itemStateChanged(ItemEvent ie) {
    if (ie.getSource() == listFil) {
      if (ie.getItem().equals("MPI") || ie.getItem().equals("CBA") ||
          ie.getItem().equals("Choisir une filiere")) {
        listNiv.setVisible(false);
      }
      else {
        listNiv.setVisible(true);
      }
    }

    nomFiliere = listFil.getSelectedItem().toString();
    if (!nomFiliere.equals("Choisir une filiere")) { //une filiere est choisi
      niv = 1;
      if (!nomFiliere.equals("MPI") && !nomFiliere.equals("CBA")) {
        niv = Integer.parseInt(listNiv.getSelectedItem().toString());
      }

      //remplissage du vecteur donnees
      Etudiant e;
      Vector v, d = new Vector();
      donnees.removeAllElements();
      for (int i = 0; i < MenuPrincipale.listEtudiants.size(); i++) {
        e = (Etudiant) MenuPrincipale.listEtudiants.elementAt(i);
        if (e.getFiliere().getNom().equals(nomFiliere) &&
            e.getFiliere().getNiveau() == niv) { //l'etudiant appartient a la filiere choisi
          v = new Vector();
          v.addElement(e.getIdEtud() + "");
          v.addElement(e.getNom());
          v.addElement(e.getPrenom());
          double moy = e.getFiliere().moyGen();
          v.addElement(df.format(moy)); //fixer le nbre de chiffre apres la virgule de moy
          if (moy >= 10) {
            v.addElement("Reussi");
          }
          else {
            v.addElement("Controle");
          }
          d.addElement(v);
        }
      }
      table = new JTable(d, nomColonnnes);
      sp.setViewportView(table);
    }
    else{
      table=new JTable(new Vector(),nomColonnnes);
      sp.setViewportView(table);
    }
  }

  public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == retour) {
      MenuPrincipale.F.enable();
      dispose();
    }
  }

}