package gestionetudiants;

/* cette class permet d'afficher les statistique des etudiants des etudiants */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;

public class Statistiques
    extends ClassMere implements ActionListener{

  private JScrollPane sp;
  private JTable table;
  private Vector nomColumn; //les noms de colonnes
  private Vector donnees; //les donnees de la table

  private JButton retour;

  private JPanel button_pn;

  private TableColumn c1,c2,c3,c4,c5;

  private DecimalFormat df = new DecimalFormat("00.00");//nbre de chiffres apres la virgule

  public Statistiques() {
    super("Statistiques", 600, 400);

    sp = new JScrollPane();


    //initialisation des nom des colonnes
    nomColumn = new Vector();
    nomColumn.addElement("Filiere");
    nomColumn.addElement("Nbre Etud.");
    nomColumn.addElement("Moy Max");
    nomColumn.addElement("Moy MIN");
    nomColumn.addElement("Pourcentage Reussite");

    //ajout des donnees
    donnees = new Vector();
    Filiere f;
    for (int i = 0; i < MenuPrincipale.listFilieres.size(); i++) {
      f = (Filiere) MenuPrincipale.listFilieres.elementAt(i);
      donnees.addElement(stat(f.getNom(), f.getNiveau()));
    }

    table = new javax.swing.JTable(donnees, nomColumn); //ajout a la table
    sp.setViewportView(table); //ajout de la scroll pane


    //reglage des dimension des colonnes
    c1 = table.getColumnModel().getColumn(0);
    c1.setPreferredWidth(50);

    c2 = table.getColumnModel().getColumn(1);
    c2.setPreferredWidth(50);

    c3 = table.getColumnModel().getColumn(2);
    c3.setPreferredWidth(50);

    c4 = table.getColumnModel().getColumn(3);
    c4.setPreferredWidth(50);

    c5 = table.getColumnModel().getColumn(4);
    c5.setPreferredWidth(75);


    sp.createVerticalScrollBar();
    getContentPane().add(sp, BorderLayout.CENTER);

    //ajout de la button retour
    retour = new JButton("Retour");
    retour.addActionListener(this);
    button_pn = new JPanel();
    button_pn.add(retour);

    getContentPane().add(button_pn, BorderLayout.SOUTH);

    show();

  }

  //class qui retourne les caracteristiques par filiere et les retourne dans un vecteur
  public Vector stat(String NomFil, int niv) {
    int nbre = 0, reuss = 0; //reuss c le nbre d'etud ayant un moy >10
    double max = 0, min = 21, moy = 0;
    Etudiant e;
    for (int i = 0; i < MenuPrincipale.listEtudiants.size(); i++) {
      e = (Etudiant) MenuPrincipale.listEtudiants.elementAt(i);
      if (e.getFiliere().getNom().equals(NomFil) &&
          e.getFiliere().getNiveau() == niv) { //s'il appartient a cet filiere et a ce niveau
        nbre++;
        moy = e.getFiliere().moyGen();
        if (moy > max) {
          max = moy;
        }
        if (moy < min) {
          min = moy;
        }
        if (moy >= 10) {
          reuss++;
        }
      }
    }

    int pourcent = 0;
    if (reuss != 0) {
      pourcent = reuss * 100 / nbre; ;
    }

    Vector vec = new Vector();
    vec.addElement(NomFil + niv);
    vec.addElement(nbre + "");
    vec.addElement(df.format(max));
    if(min>20) min=0;
    vec.addElement(df.format(min));
    vec.addElement(pourcent + "");

    return vec;
  }

  public void actionPerformed(ActionEvent ae) {
    if(ae.getSource()==retour){
      MenuPrincipale.F.enable();
      dispose();
    }
  }
}
