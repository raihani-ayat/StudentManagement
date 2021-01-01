package gestionetudiants;

/*class representant une matiere*/

public class Matiere {
  private String nom;
  private float ds,tp,ex;
  private int coef;//coeficient de la matiere


  public Matiere(String n,float e,float d,float t,int c) {
    nom=n;
    ds=d;
    tp=t;
    ex=e;
    coef=c;
  }
  public float getDs() {
    return ds;
  }
  public float getEx() {
    return ex;
  }
  public String getNom() {
    return nom;
  }
  public float getTp() {
    return tp;
  }
  //calcul du moyenne d'une matiere
  public double moy() {
    if(tp>0) return tp*0.4+0.6*(ex*2+ds)/3; //si la matiere a un tp
    else return (ex*2+ds)/3;
  }
  public int getCoef() {
    return coef;
  }
  public void setDs(float ds) {
    this.ds = ds;
  }
  public void setEx(float ex) {
    this.ex = ex;
  }
  public void setTp(float tp) {
    this.tp = tp;
  }



}