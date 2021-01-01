/* Cette classe presente la frame qui apparait lorsqu'on veut ajouter
   un noveau etudiant.*/

package gestionetudiants;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;

public class NouveauEtudiant
    extends ClassMere
    implements ItemListener, ActionListener {

  //static Vector listFilieres = new Vector(); // vecteur contenant la liste des filieres
  //static Vector listEtudiants = new Vector(); //vecteur  contenant la liste des etudiants

  MonJLabel ce_lb; //label de la carte d'etudiant
  MonJLabel nom_lb;
  MonJLabel prenom_lb;
  MonJLabel dn_lb;
  MonJLabel sexe_lb;
  MonJLabel filiere_lb;
  MonJLabel situation_lb;

  JTextField ce;
  JTextField nom;
  JTextField prenom;

  JComboBox jour, mois, annee; //les chmaps de date de naissance

  ButtonGroup sexe; //groupe contenant les boton radio de sexe
  JRadioButton masculin;
  JRadioButton feminin;

  JComboBox filiere;
  JComboBox niveau;
  JComboBox situation;

  JButton ajouter, retour;

  JPanel centre_pn, sud_pn; //les differents panel du frame

  JOptionPane confirm;


  //Constructeur
  public NouveauEtudiant() {

    super("Nouveau Etudiant", 350, 350);

    //definir les champs du numero carte etudiant
    ce_lb = new MonJLabel("Numero Carte etudiant:");
    ce = new JTextField();
    ce.setText("" + numAuto());
    ce.disable();

    //definir les champs du nom
    nom_lb = new MonJLabel("Nom:");
    nom = new JTextField();

    //definir les champs du prenom
    prenom_lb = new MonJLabel("Prenom:");
    prenom = new JTextField();

    //definir les champs du date naissance
    dn_lb = new MonJLabel("Date de Naissance:");
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
    sexe = new ButtonGroup();
    masculin = new JRadioButton("Masculin", true);
    feminin = new JRadioButton("Feminin", false);
    sexe.add(masculin);
    sexe.add(feminin);

    //definir les champs du filiere
    filiere_lb = new MonJLabel("Filiere:");


    filiere = new JComboBox();
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

    ajouter = new JButton("Ajouter");
    ajouter.addActionListener(this);
    retour = new JButton("Retour");
    retour.addActionListener(this);

    //** ajout des elements dans les panels

     //ajout des chapms de saisie et labels dans le panel centre
    centre_pn = new JPanel();
    centre_pn.setLayout(new GridLayout(6, 2));
    centre_pn.add(ce_lb);
    centre_pn.add(ce);
    centre_pn.add(nom_lb);
    centre_pn.add(nom);
    centre_pn.add(prenom_lb);
    centre_pn.add(prenom);
    centre_pn.add(dn_lb);

    //ajout des lists jour,mois et annee dans un panel
    JPanel dn_pn = new JPanel();
    dn_pn.add(jour);
    dn_pn.add(mois);
    dn_pn.add(annee);
    //fin ajout des champs date

    centre_pn.add(dn_pn);
    centre_pn.add(sexe_lb);

    //ajout des champs du sexe dans un panel
    JPanel sexe_pn = new JPanel();
    sexe_pn.add(masculin);
    sexe_pn.add(feminin);
    //fin ajout des champs sexe

    centre_pn.add(sexe_pn);

    //ajout du liste filiere est liste niveaux dans un meme panel
    JPanel filiere_pn = new JPanel();
    filiere_pn.add(filiere);
    filiere_pn.add(niveau);

    centre_pn.add(filiere_lb);
    centre_pn.add(filiere_pn);


    //ajout des bottons dans le panel sud
    sud_pn = new JPanel();
    sud_pn.add(ajouter, BorderLayout.PAGE_END);
    sud_pn.add(retour, BorderLayout.PAGE_END);
    //fin ajout dans le panel sud

    //** ajout des panels dans la frame
    getContentPane().add(centre_pn, BorderLayout.CENTER);
    getContentPane().add(sud_pn, BorderLayout.SOUTH);

    setResizable(false);
    show();

  }

  public void itemStateChanged(ItemEvent ie) { // gère le chagement de l'element selectionné de la liste filiere
    if (ie.getItem().equals("MPI") || ie.getItem().equals("CBA")) {
      niveau.setVisible(false);
    }
    else {
      niveau.setVisible(true);
    }
  }

  public void actionPerformed(ActionEvent ae) { //gere les evenements des buttons
    if (ae.getSource() == retour) {
      MenuPrincipale.F.enable();
      dispose();
    }
    else if (ae.getSource() == ajouter) {
      ajouter_actionPerformed();
    }
  }


  //methode qui donne le attribu un numero a chaque etudiant
  public int numAuto() {
    if (MenuPrincipale.listEtudiants.size() == 0) {
      return 1;
    }
    else {
      return ( ( (Etudiant) MenuPrincipale.listEtudiants.elementAt(MenuPrincipale.listEtudiants.size() - 1)).
              getIdEtud() + 1);
    }
  }

  //methode qui traite le clik sur ajouter
  public void ajouter_actionPerformed() {
    Etudiant etud;
    Filiere fil=new Filiere();
    int id = 0, j = 0, m = 0, a = 0,niv=0;
    String n = "", p = "", s = "", f = "";
    Date dn;
    boolean etat = true;

    id = Integer.parseInt(ce.getText());
    n = nom.getText();
    if (n.equals("")) {
      nom_lb.setText("nom : Entrer le nom!!");
      nom_lb.setForeground(Color.red);
      etat = false;
    }else{
      nom_lb.setText("nom :");
      nom_lb.setForeground(Color.black);
    }
    p = prenom.getText();
    if (p.equals("")) {
      prenom_lb.setText("prenom : Entrer le prenom!!");
      prenom_lb.setForeground(Color.red);
      etat = false;
    }else{
      prenom_lb.setText("prenom :");
      prenom_lb.setForeground(Color.black);
    }
    //date
    try {
      j = Integer.parseInt(jour.getSelectedItem().toString());
      m = Integer.parseInt(mois.getSelectedItem().toString());
      a = Integer.parseInt(annee.getSelectedItem().toString());
    }
    catch (NumberFormatException ex) {
      etat=false;
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
      f=filiere.getSelectedItem().toString();
    //niveau
    if(f.equals("MPI") || f.equals("CBA")){
      niv=1;
    }else{
      niv = Integer.parseInt(niveau.getSelectedItem().toString());
    }

    if (etat) { //la saisie est juste
      int result=confirm.showConfirmDialog(null,"Voulez vous vraiment ajouter cet etudiant","Confirmation ajout",JOptionPane.YES_NO_OPTION);
      if(result==0){//reponse oui

        //on recupere la filiere
        fil = recupFiliere(f, niv);
        etud = new Etudiant(id, n, p, dn, s, fil);

        //((Matiere)(etud.getFiliere().getListMatieres().elementAt(2))).setDs(13);
        MenuPrincipale.listEtudiants.addElement(etud);

        ce.setText(numAuto() + "");
      }
      nom.setText("");
      prenom.setText("");
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


}