package gestionetudiants;

/*Cette classe contient les elements communs pour les frames*/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.text.DefaultEditorKit;
import java.awt.event.ActionEvent;

public class ClassMere
    extends JFrame {
  static Font titreFont=new Font("Algerian", 1, 20);//font du titre peu etre modifier a partir de options affichage
  JLabel titre; //titre du frame
  JPanel titre_pn;
  JMenuBar m = new JMenuBar(); //la barre du menu
  JMenu file = new JMenu("Fichier"); //le menu fichier
  JMenu Edit = new JMenu("Edition"); //le menu edition
  JMenu View = new JMenu("Affichage"); //le menu affichage
  JMenu help = new JMenu("Aide"); //le menu aide
//  JMenu fenetre=new JMenu("Fenetre");

//  JMenuItem open = new JMenuItem("Ouvrir"); //l'element ouvrir
  JMenuItem save = new JMenuItem("Enregistrer"); //l'element Enregistrer
//  JMenuItem save_as = new JMenuItem("Enregistrer sous"); //l'element Enregistrer sous
  JMenuItem close = new JMenuItem("Quitter"); //l'element Quitter
  JMenuItem copy; // = new JMenuItem("copier"); //l'element copier
  JMenuItem cut; // = new JMenuItem("couper"); //l'element coper
  JMenuItem paste; // = new JMenuItem("coller"); //l'element coller
  JMenuItem about = new JMenuItem("A propos"); //l'element A propos
  JMenuItem Option = new JMenuItem("Options d'affichage"); //l'element Options d'affichage

  public ClassMere(String Title, int L, int H) {

    titre = new JLabel(Title);
    titre.setFont(titreFont); //definir le font du titre
    titre.setForeground(Color.DARK_GRAY);

    titre_pn = new JPanel();
    titre_pn.add(titre);
    getContentPane().add(titre_pn, BorderLayout.NORTH);

    setTitle(Title); //Definir le titre du frame
    setSize(L, H); //definir la taille du frame
    setLocation(245, 180);

    //initialisation des actions copier couper coller
    Action alt = new DefaultEditorKit.CopyAction();
    Action actionCopier = new DefaultEditorKit.CopyAction();
    Action actionColler = new DefaultEditorKit.PasteAction();
    Action actionCouper = new DefaultEditorKit.CutAction();
    KeyStroke k = KeyStroke.getKeyStroke(KeyEvent.CTRL_MASK, KeyEvent.VK_C);
    KeyStroke k1 = KeyStroke.getKeyStroke(KeyEvent.CTRL_MASK, KeyEvent.VK_V);
    KeyStroke k2 = KeyStroke.getKeyStroke(KeyEvent.CTRL_MASK, KeyEvent.VK_X);
    KeyStroke k3 = KeyStroke.getKeyStroke(KeyEvent.ALT_MASK, KeyEvent.VK_F);
    JTextPane textPane = new JTextPane();

    copy = new JMenuItem(actionCopier);
    copy.setText("copier");
    cut = new JMenuItem(actionCouper);
    cut.setText("couper");
    paste = new JMenuItem(actionColler);
    paste.setText("coller");

    save.setEnabled(false);
    close.setEnabled(false);
    Option.setEnabled(false);
    about.setEnabled(false);

//    file.add(open);
//    file.addSeparator();
    file.add(save);
//    file.add(save_as);
    file.addSeparator();
    file.add(close);

    //ajouter les elments du menu Edit
    Edit.add(copy);
    Edit.add(cut);
    Edit.add(paste);

    //ajouter les elments du menu view
    View.add(Option);

    //ajouter les elments du menu help
    help.add(about);

    //ajouter les menus au barre
    m.add(file);
    m.add(Edit);
    m.add(View);
    m.add(help);
    //Ajouter la barre au frame
    setJMenuBar(m);
//
    //fermeture de la frame principale
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent evt) {
//        System.exit(0);
        MenuPrincipale.F.enable();
      }
    });

    setResizable(false);
    show();
  }

}