package gestionetudiants;

import java.util.Vector;

public class Etudiant {

  private int idEtud; //numero de la carte d'etudiant de l'eleve distribué automatiquement
  private String nom, prenom, sexe;
  private Date dn; //date de naissance
  private Filiere filiere; //la class filiere contient nom de filiere et liste des matieres avec notes


  //constructeur de la classe etudiant
  public Etudiant(int ce, String n, String p, Date d, String s, Filiere f) {
    idEtud = ce;
    nom = n;
    prenom = p;
    dn = d;
    sexe = s;
    filiere = f;
  }

  public Date getDn() {
    return dn;
  }

  public Filiere getFiliere() {
    return filiere;
  }

  public int getIdEtud() {
    return idEtud;
  }


  public String getNom() {
    return nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public String getSexe() {
    return sexe;
  }
  public void setDn(Date dn) {
    this.dn = dn;
  }
  public void setFiliere(Filiere filiere) {
    this.filiere = filiere;
  }
  public void setNom(String nom) {
    this.nom = nom;
  }
  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }
  public void setSexe(String sexe) {
    this.sexe = sexe;
  }

}