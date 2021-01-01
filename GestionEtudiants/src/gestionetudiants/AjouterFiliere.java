package gestionetudiants;

/*classe permettant d'ajouter une filiere */

import java.util.Vector;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class AjouterFiliere extends ClassMere implements ActionListener{

  Vector VectMat=new Vector();

  private MonJLabel fil_lb=new MonJLabel("Filiere: ");
  private JTextField nomFil=new JTextField(5);

  private JButton ajouter=new JButton("Ajouter");
  private JButton retour=new JButton("Retour");

  private JOptionPane confirm;

  private JPanel centre=new JPanel(),sud=new JPanel();


  public AjouterFiliere(){
    super("Ajout filiere",300,300);

    ajouter.addActionListener(this);
    retour.addActionListener(this);

    centre.add(fil_lb);
    centre.add(nomFil);

    sud.add(ajouter);
    sud.add(retour);

    getContentPane().add(centre,BorderLayout.CENTER);
    getContentPane().add(sud,BorderLayout.SOUTH);

//    setSize(500,500);
    pack();
    show();
  }



  public void actionPerformed(ActionEvent ae) {
    if(ae.getSource()==ajouter){
      ajouter_actionPerformed();
    }
    else if(ae.getSource()==retour){
      MenuPrincipale.F.enable();
      dispose();
    }
  }


  public void ajouter_actionPerformed(){
    String nom=nomFil.getText();
    if(nom.equals("")){
      fil_lb.setForeground(Color.red);
    }
    else{
      fil_lb.setForeground(Color.black);
      if(filExist(nom)){//la filiere existe deja
        confirm.showMessageDialog(null,"La filiere existe deja","Filiere existante",JOptionPane.ERROR_MESSAGE);
      }
      else{//la filiere n'existe pas
        int resultat=confirm.showConfirmDialog(null,"Voulez vous vraiment ajouter cette filiere?","Ajout Filiere",JOptionPane.YES_NO_OPTION);
        if(resultat==0){//reponse oui
          for (int i = 2; i <= 5; i++) { //ajouter la filiere avec les 4 niveaux
            MenuPrincipale.listFilieres.addElement(new Filiere(nom, i,
                new Vector()));
          }
        }
      }
      nomFil.setText("");
    }
  }


  //verifi si la filiere existe deja
  public boolean filExist(String nom){
    Filiere f;
    for(int i=0;i<MenuPrincipale.listFilieres.size();i++){
      f=(Filiere)MenuPrincipale.listFilieres.elementAt(i);
      if(f.getNom().equalsIgnoreCase(nom)){
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args){
    new AjouterFiliere();
  }
}
