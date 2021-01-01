package gestionetudiants;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;


public class apropos extends JComponent implements ActionListener{
  JDialog F=new JDialog();
  JPanel jPanel1 = new JPanel();
  TitledBorder titledBorder1;
  JButton jButton1 = new JButton();
  TitledBorder titledBorder2;
  TitledBorder titledBorder3;
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel6 = new JLabel();
  JPanel jPanel2 = new image("images\\insat.jpg");
  JLabel jLabel8 = new JLabel();
  JLabel jLabel7 = new JLabel();
  JLabel jLabel9 = new JLabel();
  JLabel jLabel10 = new JLabel();
  JLabel jLabel11 = new JLabel();
  JLabel jLabel12 = new JLabel();
  JButton retour = new JButton();
  public apropos() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    titledBorder3 = new TitledBorder("");
    this.setBackground(UIManager.getColor("TextArea.selectionBackground"));
    this.setDoubleBuffered(true);
    this.setOpaque(true);
    this.setLayout(null);
    jPanel1.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
    jPanel1.setEnabled(true);
    jPanel1.setBorder(BorderFactory.createLoweredBevelBorder());
    jPanel1.setBounds(new Rectangle(36, 8, 382, 283));
    jPanel1.setLayout(null);
    jButton1.setBackground(SystemColor.desktop);
    jButton1.setBounds(new Rectangle(13, 10, 157, 42));
    jButton1.setFont(new java.awt.Font("Monospaced", 3, 36));
    jButton1.setForeground(Color.white);
    jButton1.setBorder(BorderFactory.createRaisedBevelBorder());
    jButton1.setOpaque(true);
    jButton1.setHorizontalAlignment(SwingConstants.CENTER);
    jButton1.setHorizontalTextPosition(SwingConstants.CENTER);
    jButton1.setIcon(null);
    jButton1.setText("INSAT");
    jLabel1.setFont(new java.awt.Font("Monospaced", 0, 16));
    jLabel1.setForeground(Color.white);
    jLabel1.setText("PROJET 2006/2007");
    jLabel1.setBounds(new Rectangle(10, 58, 164, 42));
    jLabel2.setFont(new java.awt.Font("Monospaced", 3, 16));
    jLabel2.setForeground(Color.white);
    jLabel2.setText("Gestion Etudiants");
    jLabel2.setBounds(new Rectangle(7, 89, 172, 46));
    jLabel3.setFont(new java.awt.Font("Monospaced", 2, 12));
    jLabel3.setForeground(Color.white);
    jLabel3.setText("Version 1.0 Mai 2007");
    jLabel3.setBounds(new Rectangle(2, 152, 155, 30));
    jLabel4.setFont(new java.awt.Font("Monospaced", 2, 12));
    jLabel4.setForeground(Color.white);
    jLabel4.setText("Licence: Shareware");
    jLabel4.setBounds(new Rectangle(2, 180, 151, 30));
    jLabel5.setFont(new java.awt.Font("Monospaced", 2, 12));
    jLabel5.setForeground(Color.white);
    jLabel5.setText("Programme protégé par copyright");
    jLabel5.setBounds(new Rectangle(1, 205, 218, 31));
    jLabel6.setFont(new java.awt.Font("Monospaced", 2, 12));
    jLabel6.setForeground(Color.white);
    jLabel6.setText("Compatible avec:WIN XP/VISTA");
    jLabel6.setBounds(new Rectangle(2, 249, 215, 27));
    jPanel2.setBounds(new Rectangle(223, 10, 152, 111));
    jLabel8.setFont(new java.awt.Font("Monospaced", 2, 12));
    jLabel8.setForeground(Color.white);
    jLabel8.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel8.setHorizontalTextPosition(SwingConstants.CENTER);
    jLabel8.setText("2007-2010");
    jLabel8.setBounds(new Rectangle(83, 225, 128, 26));
    jLabel7.setFont(new java.awt.Font("Monospaced", 2, 12));
    jLabel7.setForeground(Color.white);
    jLabel7.setText("glinsat.aneantis.com");
    jLabel7.setBounds(new Rectangle(222, 205, 153, 28));
    jLabel9.setFont(new java.awt.Font("Monospaced", 2, 12));
    jLabel9.setForeground(Color.white);
    jLabel9.setText("Pour plus d\'infos,");
    jLabel9.setBounds(new Rectangle(221, 147, 153, 33));
    jLabel10.setFont(new java.awt.Font("Monospaced", 2, 12));
    jLabel10.setForeground(Color.white);
    jLabel10.setText("Visitez:");
    jLabel10.setBounds(new Rectangle(221, 173, 151, 30));
    jLabel11.setFont(new java.awt.Font("Monospaced", 2, 12));
    jLabel11.setForeground(Color.white);
    jLabel11.setText("www.insat.rnu.tn");
    jLabel11.setBounds(new Rectangle(225, 250, 150, 25));
    jLabel12.setFont(new java.awt.Font("Monospaced", 2, 12));
    jLabel12.setForeground(Color.white);
    jLabel12.setText("ou bien");
    jLabel12.setBounds(new Rectangle(224, 228, 145, 21));
    retour.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
    retour.setBounds(new Rectangle(190, 307, 78, 28));
    retour.setForeground(UIManager.getColor("ScrollBar.thumbHighlight"));
    retour.setText("Retour");
    retour.addActionListener(this);
    jPanel1.add(jButton1, null);
    jPanel1.add(jPanel2, null);
    jPanel1.add(jLabel3, null);
    jPanel1.add(jLabel4, null);
    jPanel1.add(jLabel5, null);
    jPanel1.add(jLabel8, null);
    jPanel1.add(jLabel6, null);
    jPanel1.add(jLabel1, null);
    jPanel1.add(jLabel2, null);
    jPanel1.add(jLabel9, null);
    jPanel1.add(jLabel10, null);
    jPanel1.add(jLabel7, null);
    jPanel1.add(jLabel11, null);
    jPanel1.add(jLabel12, null);
    this.add(retour, null);
    this.add(jPanel1, null);

    F.getContentPane().add(this);
    F.setSize(450,400);
    F.setLocation(300,300);
    F.show();
  }

//  public static void main(String[] args){
//    new apropos();
//  }

  public void actionPerformed(ActionEvent ae) {
    if(ae.getSource()==retour){
      MenuPrincipale.F.enable();
      F.dispose();
    }
  }
}

