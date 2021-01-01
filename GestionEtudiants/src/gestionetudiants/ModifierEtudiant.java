package gestionetudiants;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;
import java.net.*;

//classe qui contient la frame de modification d'etudiant

public class ModifierEtudiant
    extends ClassMere
    implements ActionListener, ItemListener {

  private int index = -1; //index de l'etudiant recherché dans le vecteur des etudiants.
  //il sera utilisé lors du click sur modifier ou supprimer pour effacer l'etudiant du vecteur

  MonJLabel idRecherche_lb;
  JTextField idRecherche; //zone de saisi du num etud a rechercher

  MonJLabel anciensVal, nouvelsVal; //titre de nouvels valeurs et anciens valeurs
  MonJLabel nom_lb;
  MonJLabel prenom_lb;
  MonJLabel dn_lb;
  MonJLabel sexe_lb;
  MonJLabel filiere_lb;
  MonJLabel situation_lb;

  JTextField nom, nomNv;
  JTextField prenom, prenomNv;
  JTextField dn;
  JTextField ancienSexe; //l'ancien sexe de l'etudiant
  JTextField ancienFiliereNiv; //ancien filiere + niveau

  JComboBox jour, mois, annee; //les chmaps de la nouvelle date de naissance

  ButtonGroup sexe; //groupe contenant les boton radio de sexe
  JRadioButton masculin;
  JRadioButton feminin;

  JComboBox filiere;
  JComboBox niveau;
  JComboBox situation;

  JButton modifier, retour, rechercher, supprimer;

  JOptionPane confirm;

  //les panneux
  JPanel nord_pn, champs_pn, centre_pn, recherche_pn, dn_pn, sexe_pn,
      filiere_pn, sud_pn;

  public ModifierEtudiant() {
    super("Modifier/Supprimer Etudiant", 487, 343);

    idRecherche_lb = new MonJLabel("Num. Etud.:");
    idRecherche = new JTextField(10);
    rechercher = new JButton("Rechercher");

    anciensVal = new MonJLabel("Anciens Valeurs");
    nouvelsVal = new MonJLabel("Nouvels Valeurs");

    //definir les champs du nom
    nom_lb = new MonJLabel("Nom:");
    nom = new JTextField(10);
    nomNv = new JTextField(10);
    nom.disable();

    //definir les champs du prenom
    prenom_lb = new MonJLabel("Prenom:");
    prenom = new JTextField(10);
    prenomNv = new JTextField(10);
    prenom.disable();

    //definir les champs du date naissance
    dn_lb = new MonJLabel("Date de Naissance:");
    dn = new JTextField(10);
    dn.disable();
    jour = new JComboBox();
    mois = new JComboBox();
    annee = new JComboBox();

    //initialisation de la liste des jours
    int i = 0;
    for (i = 1; i <= 31; i++) {
      jour.addItem("" + i);
    }
    jour.setSelectedIndex(0); //definir l'element selectionné par defaut (le 1er element)

//initialisation de la liste des mois
    for (i = 1; i <= 12; i++) {
      mois.addItem("" + i);
    }
    mois.setSelectedIndex(0); //definir l'element selectionné par defaut (le 1er element)

//initialisation de la liste des annees
    for (i = 1970; i <= 2007; i++) {
      annee.addItem("" + i);
    }
    annee.setSelectedIndex(16); //definir l'element selectionné par defaut de l'annee (1986)

    //definir les champs du sexe
    sexe_lb = new MonJLabel("Sexe:");
    ancienSexe = new JTextField(10);
    ancienSexe.disable();
    sexe = new ButtonGroup();
    masculin = new JRadioButton("Masculin", true);
    feminin = new JRadioButton("Feminin", false);
    sexe.add(masculin);
    sexe.add(feminin);

    //definir les champs du filiere et niveau
    filiere_lb = new MonJLabel("Filiere:");

    ancienFiliereNiv = new JTextField(10);
    ancienFiliereNiv.disable();

    filiere = new JComboBox();

    //initialiser la liste des filiers a partir de listVecteurs
    //initialiser la liste des filiers a partir de listVecteurs
    Filiere fi;
    for(i=0;i<MenuPrincipale.listFilieres.size();i++){
      fi=(Filiere)MenuPrincipale.listFilieres.elementAt(i);
      if(fi.getNiveau()==1 || fi.getNiveau()==2) filiere.addItem(fi.getNom());
    }


    filiere.addItemListener(this); //car si la filiere choisi est mpi ou cba il ya un seul niveau sinon il ya 5
    filiere.setSelectedIndex(0); //MPI est selectionné par defaut

    //definir les champs de la liste des niveaux
    niveau = new JComboBox();
//initialisation des elements du niveau
    for (i = 2; i <= 5; i++) {
      niveau.addItem("" + i);
    }
    niveau.setVisible(false); //niveau est au debut non visible car MPI est selectionné par defaut
    niveau.setSelectedIndex(0);

    //definir les buttons
    modifier = new JButton("Modifier");
//    modifier.setIcon(new ImageIcon("modifier.jpg"));
    modifier.addActionListener(this);
    retour = new JButton("Retour");
    retour.addActionListener(this);
    rechercher.addActionListener(this);
    supprimer = new JButton("Supprimer");
    supprimer.addActionListener(this);

    //reglages des panneaux
    //panneaux recherche
    recherche_pn = new JPanel();
    recherche_pn.add(idRecherche_lb);
    recherche_pn.add(idRecherche);
    recherche_pn.add(rechercher);
    recherche_pn.add(retour);

    //panneau dn
    dn_pn = new JPanel();
    dn_pn.add(jour);
    dn_pn.add(mois);
    dn_pn.add(annee);
    //panneau sexe
    sexe_pn = new JPanel();
    sexe_pn.add(masculin);
    sexe_pn.add(feminin);

//panneau filiere
    filiere_pn = new JPanel();
    filiere_pn.add(filiere);
    filiere_pn.add(niveau);
    //panneaux des champs
    champs_pn = new JPanel();
    champs_pn.setLayout(new GridLayout(6, 3));
    champs_pn.add(new Panel()); //vide
    champs_pn.add(anciensVal);
    champs_pn.add(nouvelsVal);
    champs_pn.add(nom_lb);
    champs_pn.add(nom);
    champs_pn.add(nomNv);
    champs_pn.add(prenom_lb);
    champs_pn.add(prenom);
    champs_pn.add(prenomNv);
    champs_pn.add(dn_lb);
    champs_pn.add(dn);
    champs_pn.add(dn_pn);
    champs_pn.add(sexe_lb);
    champs_pn.add(ancienSexe);
    champs_pn.add(sexe_pn);
    champs_pn.add(filiere_lb);
    champs_pn.add(ancienFiliereNiv);
    champs_pn.add(filiere_pn);
    //panneau centre
    centre_pn = new JPanel();
    centre_pn.setLayout(new BorderLayout());
    centre_pn.add("North", recherche_pn);
    centre_pn.add("Center", champs_pn);

    //panneau valid sud
    sud_pn = new JPanel();
    sud_pn.add(modifier);
    sud_pn.add(supprimer);

    //initialiser les panneaux centre et sud comme invisibles
    champs_pn.setVisible(false);
    sud_pn.setVisible(false);

    //ajout dans la frame
    getContentPane().add(centre_pn, BorderLayout.CENTER);
    getContentPane().add(sud_pn, BorderLayout.SOUTH);

    show();

  }

  public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == rechercher) {
      recherche_actionPerformed();
    }
    else if (ae.getSource() == modifier) {
      modifier_actionPerformed();
    }
    else if (ae.getSource() == supprimer) {
      supprimer_actionPerformed();
    }
    else if (ae.getSource() == retour) {
      MenuPrincipale.F.enable();
      dispose();
    }
  }

  public void itemStateChanged(ItemEvent ie) {

    //evenement lors de la selection de la liste filiere
    if (ie.getItem().equals("MPI") || ie.getItem().equals("CBA")) {
      niveau.setVisible(false);
    }
    else {
      niveau.setVisible(true);

    }
  }

  //evenement lors du click sur le button rechercher
  public void recherche_actionPerformed() {
    int id = 0;
    reset();
    try {
      id = Integer.parseInt(idRecherche.getText());
    }
    catch (NumberFormatException ex) {
    }
    if (id <= 0) {
      champs_pn.setVisible(false);
      sud_pn.setVisible(false);

      idRecherche_lb.setText("Num. Etud.: saisir un numero correcte!!");
      idRecherche_lb.setForeground(Color.red);
    }
    else {
      idRecherche_lb.setText("Num. Etud.:");
      idRecherche_lb.setForeground(Color.black);
      index = rechercheEtud(id);
      if (index == -1) {
        champs_pn.setVisible(false);
        sud_pn.setVisible(false);

        confirm.showMessageDialog(null,
                                  "Aucun etudiant trouvé! Verifier le numero",
                                  "Etudiant inexistant",
                                  JOptionPane.WARNING_MESSAGE);
        idRecherche.setText("");

      }
      else {
        Etudiant e = (Etudiant) MenuPrincipale.listEtudiants.elementAt(index);
        nom.setText(e.getNom());
        prenom.setText(e.getPrenom());
        dn.setText(e.getDn().getJour() + "/" + e.getDn().getMois() + "/" +
                   e.getDn().getAnnee());
        ancienSexe.setText(e.getSexe());
        ancienFiliereNiv.setText(e.getFiliere().getNom() +
                                 e.getFiliere().getNiveau());

        champs_pn.setVisible(true);
        sud_pn.setVisible(true);
      }
    }

  }

  //evenement lors du click sur le button supprimer
  public void supprimer_actionPerformed() {
    int resultat = confirm.showConfirmDialog(null,
        "Voulez vous vraiment supprimer cet etudiant", "Attention",
        JOptionPane.YES_NO_OPTION);
    System.out.println(resultat);

    if (index >= 0 && resultat == 0) { // reponse oui
      MenuPrincipale.listEtudiants.removeElementAt(index); //suppression du vecteur
      reset();
      champs_pn.setVisible(false);
      sud_pn.setVisible(false);
    }
  }

  //evenement lors du click sur le button modifier
  public void modifier_actionPerformed() {
    Etudiant etud;
    Filiere fil = new Filiere();
    int ce=0,j = 0, m = 0, a = 0, niv = 0;
    String n = "", p = "", s = "", f = "";
    Date dn;
    boolean etat = true;

    n = nomNv.getText();
    if (n.equals("")) {
      nom_lb.setForeground(Color.red);
      etat = false;
    }
    else {
      nom_lb.setForeground(Color.black);
    }
    p = prenomNv.getText();
    if (p.equals("")) {
      prenom_lb.setForeground(Color.red);
      etat = false;
    }
    else {
      prenom_lb.setForeground(Color.black);
    }
    //date
    try {
      j = Integer.parseInt(jour.getSelectedItem().toString());
      m = Integer.parseInt(mois.getSelectedItem().toString());
      a = Integer.parseInt(annee.getSelectedItem().toString());
    }
    catch (NumberFormatException ex) {
      etat = false;
    }
    dn = new Date(j, m, a);
    //sexe
    if (masculin.isSelected()) {
      s = "masculin";
    }
    else {
      s = "feminin";
    }
    //filiere
    f = filiere.getSelectedItem().toString();
    //niveau
    if (f.equals("MPI") || f.equals("CBA")) {
      niv = 1;
    }
    else {
      niv = Integer.parseInt(niveau.getSelectedItem().toString());
    }

    if (etat) { //la saisie est juste
      //on recupere la filiere

      int resultat = confirm.showConfirmDialog(null,
          "Voulez vous vraiment modifier cet etudiant", "Attention",
          JOptionPane.YES_NO_OPTION);

      if (resultat == 0) { //reponse oui
        fil = recupFiliere(f, niv);
        System.out.println(n + p + j + m + a + s + f + niv + fil.getNom() +
                           ( (Matiere) fil.getListMatieres().elementAt(2)).
                           getNom());
        ce=((Etudiant)MenuPrincipale.listEtudiants.elementAt(index)).getIdEtud();
        etud=new Etudiant(ce,n,p,dn,s,fil);
        MenuPrincipale.listEtudiants.setElementAt(etud,index);
        Etudiant e1=(Etudiant)MenuPrincipale.listEtudiants.elementAt(index);
        champs_pn.setVisible(false);
        sud_pn.setVisible(false);

      }
    }
  }


  //recupere une filiere a partir de son nom et son niveau pour l'affecter a l'etudiant a modifier
  public Filiere recupFiliere(String nomFil,int niv) {
    Vector listMatieres=new Vector();//pour recuperer la liste des matieres
    Filiere fil1=new Filiere();
    for (int i = 0; i < MenuPrincipale.listFilieres.size(); i++) {
      fil1 = ( (Filiere) MenuPrincipale.listFilieres.elementAt(i));

      if (fil1.getNom().equals(nomFil) && fil1.getNiveau()==niv) {
        String nomFilier=fil1.getNom();
        int nivFil=fil1.getNiveau();
        Matiere mat;
        for(int j=0;j<fil1.getListMatieres().size();j++){
          mat=(Matiere)fil1.getListMatieres().elementAt(j);
          listMatieres.addElement(new Matiere(mat.getNom(),mat.getEx(),mat.getDs(),mat.getTp(),mat.getCoef()));
        }

        return new Filiere(nomFilier,nivFil,listMatieres);
      }
    }
    return fil1;
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


  //retablir les champs
  public void reset(){
    nomNv.setText("");
    prenomNv.setText("");
    jour.setSelectedIndex(0);
    mois.setSelectedIndex(0);
    annee.setSelectedIndex(16);
    masculin.setSelected(true);
    feminin.setSelected(false);
    filiere.setSelectedIndex(0);
    niveau.setVisible(false);
    niveau.setSelectedIndex(0);
  }

//  public static void main(String[] args) {
//    new NouveauEtudiant();
//    new ModifierEtudiant();
//  }

}