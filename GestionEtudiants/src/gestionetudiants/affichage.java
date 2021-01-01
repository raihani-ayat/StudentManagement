package gestionetudiants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;

public class affichage
    extends JComponent
    implements ActionListener {

  int styl=0;//le style du texte:bold italique
  String nom="Arial";//nom du type d'ecriture
  int size=12;//taille de l'ecriture

  JDialog F=new JDialog(new JDialog());
  JPanel panneau = new JPanel();
  JLabel choix_lb = new JLabel();
  JPanel choix_pn = new JPanel();
  JComboBox styles = new JComboBox();
  JComboBox taille = new JComboBox();
  JButton appliquer = new JButton();
  JButton retour = new JButton();
  JCheckBox gras = new JCheckBox();
  JCheckBox italique = new JCheckBox();
  JComboBox choix = new JComboBox();
  JButton appercu = new JButton();
  JLabel titre = new JLabel();
  JLabel taille_lb = new JLabel();
  JLabel style_lb = new JLabel();
  JPanel apercu_pn = new JPanel();
  JLabel test_lb = new JLabel();

  public affichage() {
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    panneau.setBackground(Color.lightGray);
    panneau.setBounds(new Rectangle(0, 1, 406, 305));
    panneau.setLayout(null);
    choix_lb.setFont(new java.awt.Font("Monospaced", 0, 11));
    choix_lb.setText("Que voulez vous modifier :");
    choix_lb.setBounds(new Rectangle(3, 54, 193, 32));
    choix_pn.setBackground(SystemColor.inactiveCaptionText);
    choix_pn.setBounds(new Rectangle(10, 122, 372, 46));
    choix_pn.setLayout(null);
    styles.setBounds(new Rectangle(76, 14, 113, 19));
    styles.addActionListener(this);
    styles.addItem("Arial");
    styles.addItem("SansSerif");
    styles.addItem("Times New Roman");
    styles.addItem("Snap ITC");
    styles.addItem("Algerian");
    styles.setSelectedIndex(1);
    taille.setBounds(new Rectangle(239, 14, 42, 19));
    taille.addItem("8");
    taille.addItem("9");
    taille.addItem("10");
    taille.addItem("11");
    taille.addItem("12");
    taille.addItem("14");
    taille.addItem("16");
    taille.addItem("20");
    taille.addItem("24");
    taille.setSelectedIndex(4);
    appliquer.setBounds(new Rectangle(90, 266, 108, 22));
    appliquer.setText("Appliquer");
    appliquer.addActionListener(this);//ecouteur
    retour.setText("retour");
    retour.setBounds(new Rectangle(196, 266, 108, 22));
    retour.addActionListener(this);//ecouteur
    gras.setBackground(SystemColor.inactiveCaptionText);
    gras.setText("Gras");
    gras.setBounds(new Rectangle(287, 6, 80, 19));
    italique.setBounds(new Rectangle(287, 23, 80, 19));
    italique.setBackground(SystemColor.inactiveCaptionText);
    italique.setText("Italique");
    choix.setBounds(new Rectangle(132, 85, 125, 24));
    choix.addItem("Titres");
    choix.addItem("Sous Titres");
    appercu.setBounds(new Rectangle(148, 181, 97, 24));
    appercu.setText("Apperçu");
    appercu.addActionListener(this);//ecouteur
    titre.setFont(new Font("DialogInput", 0, 16));
    titre.setForeground(SystemColor.textHighlight);
    titre.setText("Options Affichage");
    titre.setBounds(new Rectangle(105, 11, 184, 38));
    taille_lb.setText("Taille:");
    taille_lb.setBounds(new Rectangle(199, 17, 34, 15));
    style_lb.setText("style:");
    style_lb.setBounds(new Rectangle(24, 18, 34, 15));
    apercu_pn.setBackground(Color.white);
    apercu_pn.setBounds(new Rectangle(17, 208, 349, 48));
    apercu_pn.setLayout(null);
    test_lb.setText("Aperçu");
    test_lb.setBounds(new Rectangle(125, 4, 211, 41));
    test_lb.setFont(new Font(nom,styl,size));
    choix_pn.add(italique, null);
    choix_pn.add(gras, null);
    choix_pn.add(taille, null);
    choix_pn.add(taille_lb, null);
    choix_pn.add(styles, null);
    choix_pn.add(style_lb, null);
    panneau.add(appercu, null);
    panneau.add(choix, null);
    panneau.add(choix_lb, null);
    panneau.add(titre, null);
    panneau.add(appliquer, null);
    panneau.add(retour, null);
//    jPanel2.setVisible(false);
//    jButton3.setVisible(true);
//    jButton4.setVisible(true);

    F.setTitle("Options Affichage");
    F.getContentPane().add(this);
    this.add(panneau, null);
    panneau.add(apercu_pn, null);
    apercu_pn.add(test_lb, null);
    panneau.add(choix_pn, null);
    F.setSize(new Dimension(415, 341));
    F.show();

  }

  public void actionPerformed(ActionEvent ae){
    if(ae.getSource()==appercu){
      setfont();
      test_lb.setFont(new Font(nom,styl,size));
    }else if(ae.getSource()==appliquer){
      setfont();
      if(choix.getSelectedItem().toString().equals("Titres")){//on veu changer le font titres
         ClassMere.titreFont=new Font(nom,styl,size);
      }
      else if(choix.getSelectedItem().toString().equals("Sous Titres")){
        MonJLabel.font=new Font(nom,styl,size);
      }
    }
    else if(ae.getSource()==retour){
      MenuPrincipale.F.enable();
      F.dispose();
    }
  }

  public static void main(String[] args) {
    new affichage();
  }

  //methode qui affecte les valeurs choisi au variables du font
  public void setfont(){
    size=Integer.parseInt(taille.getSelectedItem().toString());
    nom=styles.getSelectedItem().toString();
    if(gras.isSelected() && italique.isSelected()){
      styl=3;
    }else if(italique.isSelected()){
      styl=2;
    }else if(gras.isSelected()){
      styl=1;
    }else{
      styl=0;
    }
  }

}


