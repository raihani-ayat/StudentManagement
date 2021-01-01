package gestionetudiants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.Vector;



public class MenuPrincipale extends JPanel implements ActionListener{

  static File filFich ;
  static File etudFich ;

  static Vector listEtudiants=new Vector();
  static Vector listFilieres=new Vector();

  static ClassMere F=new ClassMere("Menu Principale",652,460);

  JPanel button_pn = new JPanel();
  JButton quitter = new JButton();
  JButton sauvegarder = new JButton();
  JPanel etud_pn = new JPanel();
  Border border1;
  TitledBorder titledBorder1;
  JPanel fil_pn = new JPanel();
  Border border2;
  TitledBorder titledBorder2;
  JButton modifier = new JButton();
  JPanel stat_pn = new JPanel();
  Border border3;
  TitledBorder titledBorder3;
  JButton stats = new JButton();
  JButton filiere = new JButton();
  JButton matiere = new JButton();
  JPanel Img_pn = new image("images\\acceuil.jpg");
  JLabel Insat_lb = new JLabel();
  JButton nouveau = new JButton();
  JButton notes = new JButton();
  JButton supprimer = new JButton();
  JButton moyennes = new JButton();
  JOptionPane confirm;

  //constructeur
  public MenuPrincipale() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }

    F.Option.addActionListener(this);
    F.save.addActionListener(this);
    F.close.addActionListener(this);
    F.about.addActionListener(this);

    F.save.setEnabled(true);
    F.close.setEnabled(true);
    F.Option.setEnabled(true);
    F.about.setEnabled(true);

    F.copy.setEnabled(false);
    F.cut.setEnabled(false);
    F.paste.setEnabled(false);

    F.getContentPane().add(this,BorderLayout.CENTER);
    F.show();
  }



  //main
  public static void main(String[] args) {

    filFich = new File("c:\\filiere.txt");
    etudFich = new File("c:\\etudiant.txt");

    listEtudiants=Fichier.extraireFile(etudFich);
    listFilieres=Fichier.extraireFileFil(filFich);

    MenuPrincipale menuPrincipale1 = new MenuPrincipale();

  }




  private void jbInit() throws Exception {
    border1 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(134, 134, 134));
    titledBorder1 = new TitledBorder(border1,"Espace Etudiant");
    border2 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(134, 134, 134));
    titledBorder2 = new TitledBorder(border2,"Espace Filiere");
    border3 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(134, 134, 134));
    titledBorder3 = new TitledBorder(border3,"Espace Statistiques");
    this.setLayout(null);
    button_pn.setBackground(Color.gray);
    button_pn.setBorder(BorderFactory.createEtchedBorder());
    button_pn.setDoubleBuffered(true);
    button_pn.setBounds(new Rectangle(298, 312, 335, 54));
    button_pn.setLayout(null);
    quitter.setBounds(new Rectangle(183, 5, 142, 40));
    quitter.setText("Quitter");
    quitter.setIcon(new ImageIcon("images\\sortie.gif"));
    quitter.addActionListener(this); //ecouteur
//    quitter.addActionListener(new MenuPrincipale_quitter_actionAdapter(this));
//    sauvegarder.addActionListener(new MenuPrincipale_sauvegarder_actionAdapter(this));
    sauvegarder.setText("Sauvegarder");
    sauvegarder.setBounds(new Rectangle(24, 5, 142, 40));
    sauvegarder.setIcon(new ImageIcon("images\\sauvegarder.gif"));
    sauvegarder.addActionListener(this); //ecouteur
    etud_pn.setBackground(Color.lightGray);
    etud_pn.setBorder(titledBorder1);
    etud_pn.setBounds(new Rectangle(6, 5, 199, 230));
    etud_pn.setLayout(null);
    fil_pn.setBackground(Color.lightGray);
    fil_pn.setBorder(titledBorder2);
    fil_pn.setBounds(new Rectangle(276, 5, 160, 133));
    fil_pn.setLayout(null);
    modifier.setBounds(new Rectangle(34, 68, 118, 39));
    modifier.setText("Modifier");
    modifier.setIcon(new ImageIcon("images\\modifier.gif"));
    modifier.addActionListener(this);//ecouteur
    stat_pn.setBackground(Color.lightGray);
    stat_pn.setBorder(titledBorder3);
    stat_pn.setBounds(new Rectangle(276, 149, 161, 132));
    stat_pn.setLayout(null);
    stats.setBounds(new Rectangle(15, 26, 134, 44));
    stats.setText("Stat. Gen.");
    stats.setIcon(new ImageIcon("images\\stats.gif"));
    stats.addActionListener(this);//ecouteur
    filiere.setBounds(new Rectangle(17, 26, 132, 40));
    filiere.setText("Filiere");
    filiere.setIcon(new ImageIcon("images\\new.jpg"));
    filiere.addActionListener(this);//ecouteur
    matiere.setBounds(new Rectangle(17, 83, 132, 38));
    matiere.setText("Matiere");
    matiere.setIcon(new ImageIcon("images\\editer.jpg"));
    matiere.addActionListener(this);//ecouteur
    Img_pn.setBackground(Color.darkGray);
    Img_pn.setBounds(new Rectangle(448, 5, 185, 275));
    Insat_lb.setText("");
    Insat_lb.setBounds(new Rectangle(2, 328, 249, 25));
    nouveau.setIcon(new ImageIcon("images\\nouveau.jpg"));
    nouveau.setText("Nouveau");
    nouveau.setBounds(new Rectangle(34, 20, 118, 39));
    nouveau.addActionListener(this);//ecouteur
    notes.setIcon(new ImageIcon("images\\notes.gif"));
    notes.setText("Notes");
    notes.setBounds(new Rectangle(34, 181, 118, 39));
    notes.addActionListener(this);
    supprimer.setIcon(new ImageIcon("images\\supprimer.gif"));
    supprimer.setText("Supp.");
    supprimer.setBounds(new Rectangle(34, 119, 118, 39));
    supprimer.addActionListener(this);//ecouteur
    moyennes.setIcon(new ImageIcon("images\\moyennes.gif"));
    moyennes.setText("Moyennes");
    moyennes.setBounds(new Rectangle(16, 75, 134, 44));
    moyennes.addActionListener(this);//ecouteur
    button_pn.add(sauvegarder, null);
    button_pn.add(quitter, null);
    this.add(etud_pn, null);
    etud_pn.add(modifier, null);
    etud_pn.add(nouveau, null);
    etud_pn.add(notes, null);
    etud_pn.add(supprimer, null);
    fil_pn.add(filiere, null);
    fil_pn.add(matiere, null);
    this.add(Insat_lb, null);
    this.add(button_pn, null);
    this.add(stat_pn, null);
    stat_pn.add(stats, null);
    stat_pn.add(moyennes, null);
    this.add(Img_pn, null);
    this.add(fil_pn, null);
  }

  void quitter_actionPerformed() {
    int result=confirm.showConfirmDialog(null,"Voulez vous sauvegarder avant de quitter??","Gestion Etudiants",JOptionPane.YES_NO_CANCEL_OPTION);
    if(result==0){//reponse oui
      sauvegarder_actionParformed();
      System.exit(0);
    }
    else if(result==1){//reponse non
        System.exit(0);
    }
  }

  void sauvegarder_actionParformed(){
    Fichier.remplirFile(etudFich,listEtudiants);
    Fichier.remplirFileFil(filFich,listFilieres);
  }

  void nouveau_actionPerformed(){
    F.disable();
    new NouveauEtudiant();
  }

  void mod_supp_actionPerformed(){
    F.disable();
    new ModifierEtudiant();
  }

  void notes_actionPerformed(){
    F.disable();
    new SaisieNotes();
  }

  void stats_actionPerformed(){
    F.disable();
    new Statistiques();
  }

  void matiere_actionPerformed(){
    F.disable();
    new AjouterMatiere();
  }

  void filiere_actionPerformed(){
    F.disable();
    new AjouterFiliere();
  }

  void moyennes_actionPerformed(){
    F.disable();
    new Moyennes();
  }

  void apropos_actionPerformed(){
    F.disable();
    new apropos();
  }

  void Option_actionPerformed(){
    F.disable();
    new affichage();
  }

  public void actionPerformed(ActionEvent ae) {
    if(ae.getSource()==quitter || ae.getSource()==F.close){
      quitter_actionPerformed();
    }
    else if(ae.getSource()==sauvegarder || ae.getSource()==F.save){
      sauvegarder_actionParformed();
    }
    else if(ae.getSource()==nouveau){
      nouveau_actionPerformed();
    }
    else if(ae.getSource()==modifier || ae.getSource()==supprimer){
      mod_supp_actionPerformed();
    }
    else if(ae.getSource()==notes){
      notes_actionPerformed();
    }
    else if(ae.getSource()==stats){
      stats_actionPerformed();
    }
    else if(ae.getSource()==matiere){
      matiere_actionPerformed();
    }
    else if(ae.getSource()==moyennes){
      moyennes_actionPerformed();
    }
    else if(ae.getSource()==filiere){
      filiere_actionPerformed();
    }
    else if(ae.getSource()==F.about){
      apropos_actionPerformed();
    }
    else if(ae.getSource()==F.Option){
      Option_actionPerformed();
    }
  }
}