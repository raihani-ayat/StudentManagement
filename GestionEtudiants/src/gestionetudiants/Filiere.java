package gestionetudiants;

import java.util.Vector;

public class Filiere {
  private String nom;
  private int niveau;
  private Vector listMatieres;

  public Filiere(){

  }


  public Filiere(String n,int niv,Vector m) {
    nom=n;
    niveau = niv;
    listMatieres=m;
  }
  public Vector getListMatieres() {
    return listMatieres;
  }
  public String getNom() {
    return nom;
  }

//moyenne generale
  public double moyGen(){
    Matiere m;
    double moyenne=0;
    int size=listMatieres.size(),coef=0;
    for(int i=0;i<size;i++){
      m=(Matiere)listMatieres.elementAt(i);
      moyenne+=m.moy()*m.getCoef();
      coef+=m.getCoef();
    }
    return moyenne/coef;
  }
  public int getNiveau() {
    return niveau;
  }


}