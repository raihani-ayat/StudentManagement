package gestionetudiants;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;

public class SaisieNotes
    extends ClassMere
    implements ItemListener, ActionListener {

  private int index = -1; //index de l'etudiant recherché dans le vecteur des etudiants.
  private boolean existeTp = true; //indique pour la matiere en cours de saisi contient un tp ou non

  private MonJLabel id_lb, nomMat_lb, ex_lb, ds_lb, tp_lb, nom_lb, prenom_lb,
      fil_lb;
  private JLabel nom, prenom, fil;

  private JComboBox nomMat;

  private JTextField id, ex, ds, tp;

  private JPanel recherche_pn, notes_pn, mat_pn, infos_pn, saisi_pn, centre_pn;

  private JButton rechercher, ajouter, retour;

  private JOptionPane confirm;

  public SaisieNotes() {
    super("Saisie des notes", 400, 300);

    //champs de recherche
    id_lb = new MonJLabel("Num. Etud. :");
    id = new JTextField(10);
    rechercher = new JButton("Rechercher");
    rechercher.addActionListener(this);
    retour = new JButton("Retour");
    retour.addActionListener(this);
    recherche_pn = new JPanel();
    recherche_pn.add(id_lb);
    recherche_pn.add(id);
    recherche_pn.add(rechercher);
    recherche_pn.add(retour);
    recherche_pn.setBackground(Color.gray);

    //panneau infos
    nom_lb = new MonJLabel("Nom :");
    nom_lb.setForeground(Color.white);
    prenom_lb = new MonJLabel("Prenom :");
    prenom_lb.setForeground(Color.white);
    fil_lb = new MonJLabel("Filiere :");
    fil_lb.setForeground(Color.white);
    nom = new JLabel();
    prenom = new JLabel();
    fil = new JLabel();
    infos_pn = new JPanel();
    infos_pn.add(nom_lb);
    infos_pn.add(nom);
    infos_pn.add(prenom_lb);
    infos_pn.add(prenom);
    infos_pn.add(fil_lb);
    infos_pn.add(fil);

    //champs de matiere
    nomMat_lb = new MonJLabel("Matiere : ");
    nomMat = new JComboBox();
    nomMat.addItemListener(this); //ajouter l'ecouteur
    mat_pn = new JPanel();
    mat_pn.add(nomMat_lb);
    mat_pn.add(nomMat);

    //champs notes
    ex_lb = new MonJLabel("Ex:");
    ex = new JTextField(5);
    ds_lb = new MonJLabel("Ds:");
    ds = new JTextField(5);
    tp_lb = new MonJLabel("TP:");
    tp = new JTextField(5);
    ajouter = new JButton("Ajouter");
    ajouter.addActionListener(this);
    notes_pn = new JPanel();
    notes_pn.add(ex_lb);
    notes_pn.add(ex);
    notes_pn.add(ds_lb);
    notes_pn.add(ds);
    notes_pn.add(tp_lb);
    notes_pn.add(tp);
    notes_pn.add(ajouter);

    //panneau saisi
    saisi_pn = new JPanel();
    saisi_pn.setLayout(new BorderLayout());
    mat_pn.setBackground(Color.gray);
    notes_pn.setBackground(Color.gray);
    saisi_pn.add("North", mat_pn);
    saisi_pn.add("South", notes_pn);

    //panneau centre
    centre_pn = new JPanel();
    centre_pn.setLayout(new BorderLayout());
    centre_pn.add("North", recherche_pn);
    centre_pn.add("Center", infos_pn);
    centre_pn.add("South", saisi_pn);

    //definir les panneaux invisibles
    infos_pn.setVisible(false);
    saisi_pn.setVisible(false);

    getContentPane().add(centre_pn, BorderLayout.CENTER);

    show();

  }

  public void itemStateChanged(ItemEvent ie) {
    nomMat_itemStateChanged();
  }

  public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == rechercher) {
      recherche_actionPerformed();
    }
    else if (ae.getSource() == ajouter) {
      ajouter_actionPerformed();
    }
    else if(ae.getSource()==retour){
      id.setText("");
      id_lb.setText("Num. Etud. :");
      id_lb.setForeground(Color.black);
      MenuPrincipale.F.enable();
      dispose();
    }
  }

  //evenement lors du click sur le button rechercher
  public void recherche_actionPerformed() {
    int num = 0;
    reset();
    try {
      num = Integer.parseInt(id.getText()); //essayer de lire la valeur saisi
    }
    catch (NumberFormatException ex) {
    }

    if (num <= 0) { //si la valeur saisi n'est pa un entier ou c'est un entier negatif
      infos_pn.setVisible(false);
      saisi_pn.setVisible(false);
      id_lb.setText("Num. Etud. : incorrecte");
      id_lb.setForeground(Color.red);
    }
    else { //si la valeur saisi est correcte
      id_lb.setText("Num. Etud. :");
      id_lb.setForeground(Color.black);
      index = rechercheEtud(num); //on recherche l'etudiant ayant le num etud introduit
      if (index == -1) { //si l'etudiant n'est pas trouvé
        infos_pn.setVisible(false);
        saisi_pn.setVisible(false);
        confirm.showMessageDialog(null,
                                  "Aucun etudiant trouvé! Verifier le numero",
                                  "Etudiant inexistant",
                                  JOptionPane.WARNING_MESSAGE);

        id.setText("");

      }
      else { //si l'etudiant est trouvé
        Etudiant e = (Etudiant) MenuPrincipale.listEtudiants.elementAt(index);
        Matiere m;
        nom.setText(e.getNom());
        prenom.setText(e.getPrenom());
        fil.setText(e.getFiliere().getNom() + e.getFiliere().getNiveau());
        nomMat.addItem("Choisir la matiere");
        //initialisation de la liste des matieres
        for (int i = 0; i < e.getFiliere().getListMatieres().size(); i++) {
          m = (Matiere) e.getFiliere().getListMatieres().elementAt(i);
          nomMat.addItem(m.getNom());
        }
        nomMat.setSelectedIndex(0);
        infos_pn.setVisible(true);
//        mat_pn.setVisible(true);
        saisi_pn.setVisible(true);
        notes_pn.setVisible(false);
      }
    }
  }

  //evenement lors du click sur le button ajouter
  public void ajouter_actionPerformed() {
    //verification que la saisi est correcte
    float e = -1, d = -1, t = -1;
    boolean etat = true; //indique si la saisi est juste
    try {
      e = Float.parseFloat(ex.getText());
    }
    catch (NumberFormatException ex) {
      ex_lb.setForeground(Color.red); //********************************************
    }

    try {
      d = Float.parseFloat(ds.getText());
    }
    catch (NumberFormatException ex) {
    }

    if (existeTp) { //si le tp existe
      try {
        t = Float.parseFloat(tp.getText());
      }
      catch (NumberFormatException ex) {
      }
    }

    if (e < 0 || e>20) {
      ex_lb.setForeground(Color.red);
      etat = false;
    }
    else {
      ex_lb.setForeground(Color.black);
    }

    if (d < 0 || d>20) {
      ds_lb.setForeground(Color.red);
      etat = false;
    }
    else {
      ds_lb.setForeground(Color.black);
    }

    if ((t < 0 || t>20)&& existeTp) {
      tp_lb.setForeground(Color.red);
      etat = false;
    }
    else if (existeTp && t >= 0) {
      tp_lb.setForeground(Color.black);
    }
    if (etat) { //si la saisi est juste
      Etudiant et = (Etudiant) MenuPrincipale.listEtudiants.elementAt(index);
      Matiere mat = (Matiere) et.getFiliere().getListMatieres().elementAt(
          nomMat.getSelectedIndex()-1);
      mat.setEx(e);
      mat.setDs(d);
      if (existeTp) {
        mat.setTp(t);
      }
//      }
    }
  }

  //action lorsque l'etat de la liste des matieres change
  public void nomMat_itemStateChanged() {
    ex_lb.setForeground(Color.black);
    ds_lb.setForeground(Color.black);
    tp_lb.setForeground(Color.black);

    String nomMatiere = nomMat.getSelectedItem().toString();
    System.out.println(nomMatiere);//*************************************************
    if (nomMatiere.equals("Choisir la matiere")) { //ocune matiere selectionné
      notes_pn.setVisible(false);
    }
    else { //si une matiere est choisie
      Etudiant et = (Etudiant) MenuPrincipale.listEtudiants.elementAt(index);
        Matiere m = (Matiere) et.getFiliere().getListMatieres().elementAt(
            nomMat.getSelectedIndex()-1);
        notes_pn.setVisible(true);
        if (m.getTp() == -1) { //la matiere ne contient pas un tp
          existeTp = false;
          tp_lb.setVisible(false);
          tp.setVisible(false);
        }
        else { //la matiere contient un tp
          existeTp = true;
          tp_lb.setVisible(true);
          tp.setVisible(true);
        }
        //initialisation des zones de saisi de notes avec les anciens notes
        ex.setText(m.getEx() + "");
        ds.setText(m.getDs() + "");
        tp.setText(m.getTp() + "");
    }
  }

  //recherche d'un etudiant a partir de son num
  public int rechercheEtud(int num) {
    Etudiant e;
    for (int i = 0; i < MenuPrincipale.listEtudiants.size(); i++) {
      e = (Etudiant) MenuPrincipale.listEtudiants.elementAt(i);
      if (e.getIdEtud() == num) {
        return i;
      }
    }
    return -1;
  }


  //initialisation
  public void reset() {
    ex.setText("");
    ds.setText("");
    tp.setText("");
    nomMat.removeAllItems();
  }

}