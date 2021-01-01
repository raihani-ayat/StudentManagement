package gestionetudiants;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;

public class AjouterMatiere
    extends ClassMere
    implements ActionListener, ItemListener {

  private int niv = 1;
  private String nomFiliere = "Choisir une filiere";

  private JComboBox listFil, listNiv; //pour afficher la liste de filieres

  private MonJLabel fil_lb, nomMat_lb, coef_lb;
  private JTextField nomMat, coef;
  private List listMat; //pour afficher la liste de matiere pour la fil selectionné

  private JButton retour, ajouter, supprimer;

  private JPanel centre_pn, sud_pn, fil_pn, supp_pn, ajout_pn;

  private JRadioButton tpExiste; //case a cocher indique si la matiere contient un tp

  private JOptionPane confirm;

  public AjouterMatiere() {
    super("Ajouter/Supprimer Matiere", 400, 300);

    fil_lb = new MonJLabel("Filiere :");
    listFil = new JComboBox();
    //initialiser la liste des filiers a partir de listVecteurs
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

    //definir les champs de la liste des niveaux
    listNiv = new JComboBox();
    //initialisation des elements du niveau
    for (int i = 2; i <= 5; i++) {
      listNiv.addItem("" + i);
    }
    listNiv.addItemListener(this);
    listNiv.setVisible(false); //niveau est au debut non visible
    listNiv.setSelectedIndex(0);

    //ajout du liste filiere est liste niveaux dans panneau filiere
    fil_pn = new JPanel();
    fil_pn.setBackground(Color.gray);
    fil_pn.add(fil_lb);
    fil_pn.add(listFil);
    fil_pn.add(listNiv);

    // panneau supprission
    listMat = new List();
    listMat.setSize(10, 10);
    supprimer = new JButton("Supprimer");
    supprimer.addActionListener(this);
    // supprimer.enable(false);
    supp_pn = new JPanel();
    supp_pn.add(listMat);
    supp_pn.add(supprimer);

    //panneau ajout
    nomMat_lb = new MonJLabel("Nom matiere :");
    nomMat = new JTextField(10);
    coef_lb = new MonJLabel("Coef. :");
    coef = new JTextField(2);
    ajouter = new JButton("Ajouter");
    ajouter.addActionListener(this);
    ajouter.disable();
    tpExiste = new JRadioButton("La matiere contient un TP");
    tpExiste.setSelected(false);

    JPanel info_mat = new JPanel();
    info_mat.setBackground(Color.gray);
    info_mat.add(nomMat_lb);
    info_mat.add(nomMat);
    info_mat.add(coef_lb);
    info_mat.add(coef);
    info_mat.add(ajouter);
    JPanel tp_pn = new JPanel();
    tp_pn.setBackground(Color.gray);
    tp_pn.add(tpExiste);
    tpExiste.setBackground(Color.gray);
    ajout_pn = new JPanel(new BorderLayout());
    ajout_pn.add("North", info_mat);
    ajout_pn.add("South", tp_pn);

    //panneau centre
    centre_pn = new JPanel(new BorderLayout());
    centre_pn.add("North", fil_pn);
    centre_pn.add("West", supp_pn);
    centre_pn.add("South", ajout_pn);

    //panneau sud
    retour = new JButton("Retour");
    retour.addActionListener(this);
    sud_pn = new JPanel();
    sud_pn.add(retour);

    getContentPane().add(centre_pn, BorderLayout.CENTER);
    getContentPane().add(sud_pn, BorderLayout.SOUTH);

    show();

  }

  public void itemStateChanged(ItemEvent ie) {
    //si la filiere change on affiche les niveaux correspondant a cet filiere
    if (ie.getSource() == listFil) {
      if (ie.getItem().equals("MPI") || ie.getItem().equals("CBA") ||
          ie.getItem().equals("Choisir une filiere")) {
        listNiv.setVisible(false);
      }
      else {
        listNiv.setVisible(true);
      }
    }

    //a chaque changement des filieres on affiche la liste de matieres correspondants
    nomFiliere = listFil.getSelectedItem().toString();
    listMat.removeAll();
    if (!nomFiliere.equals("Choisir une filiere")) { //une filiere est choisi

      niv = 1;
      if (!nomFiliere.equals("MPI") && !nomFiliere.equals("CBA")) {
        niv = Integer.parseInt(listNiv.getSelectedItem().toString());
      }
      Filiere f = recupFil(nomFiliere, niv);
      //ajout de la liste des matieres correspondant dans listMat
      Matiere m;
      for (int i = 0; i < f.getListMatieres().size(); i++) {
        m = (Matiere) f.getListMatieres().elementAt(i);
        listMat.addItem(m.getNom());
      }
    }
  }

  public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == ajouter) {
      ajouter_actionPerformed();
    }
    else if (ae.getSource() == supprimer) {
      supprimer_actionPerformed();
    }
    else if (ae.getSource() == retour) {
      MenuPrincipale.F.enable();
      dispose();
    }
  }

  //retourne la filiere recherché
  public Filiere recupFil(String nom, int n) {
    Filiere f = new Filiere();
    for (int i = 0; i < MenuPrincipale.listFilieres.size(); i++) {
      f = (Filiere) MenuPrincipale.listFilieres.elementAt(i);
      if (f.getNom().equals(nom) && f.getNiveau() == n) {
        return f;
      }
    }
    return f;
  }

  //evenement suite au click sur ajouter
  public void ajouter_actionPerformed() {
    int c = -1, t = -1;
    String nom = "";
    boolean etat = true; //indique si la saisi est juste
    try {
      c = Integer.parseInt(coef.getText());
    }
    catch (NumberFormatException ex) {
    }

    if (c < 1) {
      coef_lb.setForeground(Color.red);
      etat = false;
    }
    else {
      coef_lb.setForeground(Color.black);
    }
    nom = nomMat.getText();
    if (nom.equals("")) {
      nomMat_lb.setForeground(Color.red);
      etat = false;
    }
    else {
      nomMat_lb.setForeground(Color.black);
    }
    if (nomFiliere.equals("Choisir une filiere")) {
      confirm.showMessageDialog(null, "Aucune filiere n'est selectionnée",
                                "ERREUR",
                                JOptionPane.ERROR_MESSAGE);
    }
    else {
      if (etat) { //la saisi est juste
        int resultat = confirm.showConfirmDialog(null,
            "Voulez vous vraiment ajouer cette matiere", "Ajout Matiere",
            JOptionPane.YES_NO_OPTION);
        if (resultat == 0) { // reponse oui
          if (tpExiste.isSelected()) { //matiere contient un tp
            t = 0;
          }
          Filiere fil = recupFil(nomFiliere, niv);
          Matiere m = new Matiere(nom, 0, 0, t, c);
          fil.getListMatieres().addElement(m); //on ajouter la matiere a la filiere
          listMat.addItem(nom);
          //on ajoute la matiere au etudiant existant a cet filiere
          Etudiant e;
          for (int i = 0; i < MenuPrincipale.listEtudiants.size(); i++) {
            e = (Etudiant) MenuPrincipale.listEtudiants.elementAt(i);
            if (e.getFiliere().getNom().equals(nomFiliere) &&
                e.getFiliere().getNiveau() == niv) {
              e.getFiliere().getListMatieres().addElement(m); //on lui ajoute la matiere
            }
          } //fin ajout aux etudiants
        } //fin ajout
        nomMat.setText("");
        coef.setText("");
      }
    }
  }

  public void supprimer_actionPerformed() {
    System.out.println(nomFiliere);
    if (nomFiliere.equals("Choisir une filiere")) { //pa de matiere selectionné
      confirm.showMessageDialog(null, "Aucune filiere n'est selectionnée",
                                "ERREUR",
                                JOptionPane.ERROR_MESSAGE);
    }
    else {
      int selec = listMat.getSelectedIndex(); //index de la matiere selectionné
      if (selec == -1) { //aucun element n'est selectionné
        confirm.showMessageDialog(null, "Aucune matiere n'est selectionnée",
                                  "Selectionner une matiere",
                                  JOptionPane.ERROR_MESSAGE);
      }
      else {
        int resultat = confirm.showConfirmDialog(null,
            "Voulez vous vraiment supprimer cette matiere",
            "Supprission Matiere",
            JOptionPane.YES_NO_OPTION);
        if (resultat == 0) { //reponse oui
          listMat.remove(selec); //effacer la matiere de la liste
          Filiere fil = recupFil(nomFiliere, niv);
          fil.getListMatieres().removeElementAt(selec); //retirer de la filiere
          //retirer la matiere des etudiants
          Etudiant e;
          for (int i = 0; i < MenuPrincipale.listEtudiants.size(); i++) {
            e = (Etudiant) MenuPrincipale.listEtudiants.elementAt(i);
            if (e.getFiliere().getNom().equals(nomFiliere) &&
                e.getFiliere().getNiveau() == niv) {
              e.getFiliere().getListMatieres().removeElementAt(selec);
            }
          }
        }
      }
    }
    {

    }
  }

}