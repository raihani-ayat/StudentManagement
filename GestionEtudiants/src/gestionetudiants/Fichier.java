package gestionetudiants;

/*class contenant les différents traitements d'un fichier */

import java.util.Vector;
import java.io.*;

public class Fichier {

  //method qui remplit un fichier d'etudiants a partir d'un vecteur
  static public void remplirFile(File f, Vector v) {
    Etudiant e;
    Matiere m;
    StringBuffer ligne;
    try {
      BufferedWriter out = new BufferedWriter(new FileWriter(f));
      for (int i = 0; i < v.size(); i++) {
        e = (Etudiant) v.elementAt(i);
        ligne = new StringBuffer(e.getIdEtud() + ";" + e.getNom() + ";" +
                                 e.getPrenom() + ";" + e.getDn().getJour() +
                                 ";" + e.getDn().getMois() + ";" +
                                 e.getDn().getAnnee() + ";" + e.getSexe() + ";" +
                                 e.getFiliere().getNom() + ";" +
                                 e.getFiliere().getNiveau() + ";");

        //ajout de la liste des matieres
        for (int j = 0; j < e.getFiliere().getListMatieres().size(); j++) {
          m = (Matiere) e.getFiliere().getListMatieres().elementAt(j);
          ligne.append(m.getNom() + ";" + m.getCoef() + ";" + m.getEx() + ";" +
                       m.getDs() + ";" +
                       m.getTp() + ";");
        }

        //ecriture de ligne dans le fichier
        out.write(ligne.toString());
        out.newLine();
      }
      out.close();
    }
    catch (IOException ex) {
      //System.out.println(ex.toString());
      erreur();
    }
    //System.out.println("operation terminé avec succés");
  }

  /*Methode qui retourne un etudiant a partir d'une ligne du fichier (chaine de caractere)
   cette methode sera appeler dans la methode extraireFile */
  static public Etudiant extraireEtud(String ligne) {
    int id = 0;
    try {
      id = Integer.parseInt(ligne.substring(0, ligne.indexOf(';')));
      ligne = ligne.substring(ligne.indexOf(';') + 1);
    }
    catch (NumberFormatException ex) {
      erreur();
    }

    String nom = ligne.substring(0, ligne.indexOf(';'));
    ligne = ligne.substring(ligne.indexOf(';') + 1);

    String prenom = ligne.substring(0, ligne.indexOf(';'));
    ligne = ligne.substring(ligne.indexOf(';') + 1);

    //recuperation de la date
    int jour = 0, mois = 0, annee = 0;
    try {
      jour = Integer.parseInt(ligne.substring(0, ligne.indexOf(';')));
      ligne = ligne.substring(ligne.indexOf(';') + 1);
    }
    catch (NumberFormatException ex) {
      erreur();
    }
    try {
      mois = Integer.parseInt(ligne.substring(0, ligne.indexOf(';')));
      ligne = ligne.substring(ligne.indexOf(';') + 1);
    }
    catch (NumberFormatException ex) {
      erreur();
    }
    try {
      annee = Integer.parseInt(ligne.substring(0, ligne.indexOf(';')));
      ligne = ligne.substring(ligne.indexOf(';') + 1);
    }
    catch (NumberFormatException ex) {
      erreur();
    }
    Date dn = new Date(jour, mois, annee);
    //fin recuperation date

    String sexe = ligne.substring(0, ligne.indexOf(';'));
    ligne = ligne.substring(ligne.indexOf(';') + 1);

    //recuperation de la filiere
    Filiere filiere;
    String nomFil = ligne.substring(0, ligne.indexOf(';'));
    ligne = ligne.substring(ligne.indexOf(';') + 1);

    //niveau filiere
    int niv = 0;
    try {
      niv = Integer.parseInt(ligne.substring(0, ligne.indexOf(';')));
      ligne = ligne.substring(ligne.indexOf(';') + 1);
    }
    catch (NumberFormatException ex) {
      erreur();
    }

    Vector listMatieres = new Vector();
    String nomMat;
    float ds = 0, ex = 0, tp = 0;
    int coef = 0;
    //recuperation des matieres
    while (ligne.length() != 0) {
      nomMat = ligne.substring(0, ligne.indexOf(';'));
      ligne = ligne.substring(ligne.indexOf(';') + 1);

      try {
        coef = Integer.parseInt(ligne.substring(0, ligne.indexOf(';')));
        ligne = ligne.substring(ligne.indexOf(';') + 1);
      }
      catch (NumberFormatException exp) {
        erreur();
      }

      try {
        ex = Float.parseFloat(ligne.substring(0, ligne.indexOf(';')));
        ligne = ligne.substring(ligne.indexOf(';') + 1);
      }
      catch (NumberFormatException exp) {
        erreur();
      }

      try {
        ds = Float.parseFloat(ligne.substring(0, ligne.indexOf(';')));
        ligne = ligne.substring(ligne.indexOf(';') + 1);
      }
      catch (NumberFormatException exp) {
        erreur();
      }

      try {
        tp = Float.parseFloat(ligne.substring(0, ligne.indexOf(';')));
        ligne = ligne.substring(ligne.indexOf(';') + 1);
      }
      catch (Exception exp) {
        erreur();
      }
      //ajout dans la liste des matieres
      listMatieres.addElement(new Matiere(nomMat, ex, ds, tp, coef));
    }

    filiere = new Filiere(nomFil, niv, listMatieres);

    return (new Etudiant(id, nom, prenom, dn, sexe, filiere));
  }

//methode de traitement d'un erreur lors d'extraction du fichier
  static public void erreur() {
    System.out.println("ERREUR");
  }

//Methode permettant d'extraire les etudiants a partir d'un fichier et les stocker ds un vecteur
  static public Vector extraireFile(File f) {
    Vector v = new Vector();
    if (f.exists()) {
      try {
        String ch;
        Etudiant e;
        BufferedReader in = new BufferedReader(new FileReader(f));
        do {
          ch = in.readLine();
          if (ch != null) {
            e = extraireEtud(ch);
            v.addElement(e);
          }
        }
        while (ch != null);
      }
      catch (IOException ex) {
        System.out.println(ex.toString());
      }
    }
    return v;
  }

  //extraction d'une filiere a partir d'une ligne (chaine de caractere) cette fonction sera appelé
  //dans la methode exraireFileFil
  static public Filiere extraireFil(String ligne) {
    String nom = ligne.substring(0, ligne.indexOf(';'));
    ligne = ligne.substring(ligne.indexOf(';') + 1);

    //niveau
    int niv=0;
    try {
      niv = Integer.parseInt(ligne.substring(0, ligne.indexOf(';')));
      ligne = ligne.substring(ligne.indexOf(';') + 1);
    }
    catch (NumberFormatException ex) {
      erreur();
    }


    //extraction des matieres
    Vector listMat = new Vector();
    int coef = 0;
    float ex = 0, ds = 0, tp = 0;
    String nomMat = " ";
    while (ligne.length() != 0) {
      nomMat = ligne.substring(0, ligne.indexOf(';'));
      ligne = ligne.substring(ligne.indexOf(';') + 1);

      try {
        coef = Integer.parseInt(ligne.substring(0, ligne.indexOf(';')));
        ligne = ligne.substring(ligne.indexOf(';') + 1);
      }
      catch (NumberFormatException exp) {
        erreur();
      }
      try {
        ex = Float.parseFloat(ligne.substring(0, ligne.indexOf(';')));
        ligne = ligne.substring(ligne.indexOf(';') + 1);
      }
      catch (NumberFormatException exp) {
        erreur();
      }

      try {
        ds = Float.parseFloat(ligne.substring(0, ligne.indexOf(';')));
        ligne = ligne.substring(ligne.indexOf(';') + 1);
      }
      catch (NumberFormatException exp) {
        erreur();
      }
      try {
        tp = Float.parseFloat(ligne.substring(0, ligne.indexOf(';')));
        ligne = ligne.substring(ligne.indexOf(';') + 1);
      }
      catch (Exception exp) {
        erreur();
      }

      listMat.addElement(new Matiere(nomMat, ex, ds, tp, coef));
    }

    return (new Filiere(nom,niv, listMat));
  } //fin extraireFil

  //methode qui extrait les filieres a partir d'un fichiers s'il existe
  //sinon elle initialise la liste de filieres
  static public Vector extraireFileFil(File f) {
    Vector v = new Vector();
    if (f.exists()) {
      try {
        String ch;
        Filiere fil;
        BufferedReader in = new BufferedReader(new FileReader(f));
        do {
          ch = in.readLine();
          if (ch != null) {
            fil = extraireFil(ch);
            v.addElement(fil);
          }
        }
        while (ch != null);
      }
      catch (IOException ex) {
        System.out.println(ex.toString());
      }
    }
    else { //si la le fichier n'existe pas on initialise la liste des filiers
      v.addElement(extraireFil(
          "MPI;1;Math;4;0;0;-1;Info;4;0;0;0;Electronique;4;0;0;0;Francais;1;0;0;-1;Anglais;1;0;0;-1;"));
      v.addElement(extraireFil(
          "CBA;1;Chimie;4;0;0;0;Biologie;0;0;0;4;Francais;1;0;0;-1;Anglais;1;0;0;-1;"));
      //GL
      v.addElement(extraireFil(
          "GL;2;POO;3;0;0;0;CSI;2;0;0;0;GP;3;0;0;-1;Francais;1;0;0;-1;Anglais;1;0;0;-1;"));
      v.addElement(extraireFil(
          "GL;3;POO;3;0;0;0;CSI;2;0;0;0;GP;3;0;0;-1;Francais;1;0;0;-1;Anglais;1;0;0;-1;"));
      v.addElement(extraireFil(
          "GL;4;POO;3;0;0;0;CSI;2;0;0;0;GP;3;0;0;-1;Francais;1;0;0;-1;Anglais;1;0;0;-1;"));
      v.addElement(extraireFil(
          "GL;5;POO;3;0;0;0;CSI;2;0;0;0;GP;3;0;0;-1;Francais;1;0;0;-1;Anglais;1;0;0;-1;"));
      //RT
      v.addElement(extraireFil("RT;2;Reseau;3;0;0;0;CSI;2;0;0;-1;RO;3;0;0;-1;Francais;1;0;0;-1;Anglais;1;0;0;-1;"));
      v.addElement(extraireFil("RT;3;Reseau;3;0;0;0;CSI;2;0;0;-1;RO;3;0;0;-1;Francais;1;0;0;-1;Anglais;1;0;0;-1;"));
      v.addElement(extraireFil("RT;4;Reseau;3;0;0;0;CSI;2;0;0;-1;RO;3;0;0;-1;Francais;1;0;0;-1;Anglais;1;0;0;-1;"));
      v.addElement(extraireFil("RT;5;Reseau;3;0;0;0;CSI;2;0;0;-1;RO;3;0;0;-1;Francais;1;0;0;-1;Anglais;1;0;0;-1;"));
      //IIA
      v.addElement(extraireFil(
          "IIA;2;Automatique;3;0;0;0;Arch.Ord.;2;0;0;0;RO;3;0;0;-1;Francais;1;0;0;-1;Anglais;1;0;0;-1;"));
      v.addElement(extraireFil(
          "IIA;3;Automatique;3;0;0;0;Arch.Ord.;2;0;0;0;RO;3;0;0;-1;Francais;1;0;0;-1;Anglais;1;0;0;-1;"));
      v.addElement(extraireFil(
          "IIA;4;Automatique;3;0;0;0;Arch.Ord.;2;0;0;0;RO;3;0;0;-1;Francais;1;0;0;-1;Anglais;1;0;0;-1;"));
      v.addElement(extraireFil(
          "IIA;5;Automatique;3;0;0;0;Arch.Ord.;2;0;0;0;RO;3;0;0;-1;Francais;1;0;0;-1;Anglais;1;0;0;-1;"));
      //MI
      v.addElement(extraireFil(
          "MI;2;Maintenance;3;0;0;0;Arch.Ord.;2;0;0;0;RO;3;0;0;-1;Francais;1;0;0;-1;Anglais;1;0;0;-1;"));
      v.addElement(extraireFil(
          "MI;3;Maintenance;3;0;0;0;Arch.Ord.;2;0;0;0;RO;3;0;0;-1;Francais;1;0;0;-1;Anglais;1;0;0;-1;"));
      v.addElement(extraireFil(
          "MI;4;Maintenance;3;0;0;0;Arch.Ord.;2;0;0;0;RO;3;0;0;-1;Francais;1;0;0;-1;Anglais;1;0;0;-1;"));
      v.addElement(extraireFil(
          "MI;5;Maintenance;3;0;0;0;Arch.Ord.;2;0;0;0;RO;3;0;0;-1;Francais;1;0;0;-1;Anglais;1;0;0;-1;"));
      //IMI
      v.addElement(extraireFil(
          "IMI;2;Maintenance;3;0;0;0;Arch.Ord.;2;0;0;0;RO;3;0;0;-1;Francais;1;0;0;-1;Anglais;1;0;0;-1;"));
      v.addElement(extraireFil(
          "IMI;3;Maintenance;3;0;0;0;Arch.Ord.;2;0;0;0;RO;3;0;0;-1;Francais;1;0;0;-1;Anglais;1;0;0;-1;"));
      v.addElement(extraireFil(
          "IMI;4;Maintenance;3;0;0;0;Arch.Ord.;2;0;0;0;RO;3;0;0;-1;Francais;1;0;0;-1;Anglais;1;0;0;-1;"));
      v.addElement(extraireFil(
          "IMI;5;Maintenance;3;0;0;0;Arch.Ord.;2;0;0;0;RO;3;0;0;-1;Francais;1;0;0;-1;Anglais;1;0;0;-1;"));
      //CI
      v.addElement(extraireFil(
          "CI;2;Chimie;4;0;0;0;Biologie;4;0;0;0;Francais;1;0;0;-1;Anglais;1;0;0;-1;"));
      v.addElement(extraireFil(
          "CI;3;Chimie;4;0;0;0;Biologie;4;0;0;0;Francais;1;0;0;-1;Anglais;1;0;0;-1;"));
      v.addElement(extraireFil(
          "CI;4;Chimie;4;0;0;0;Biologie;4;0;0;0;Francais;1;0;0;-1;Anglais;1;0;0;-1;"));
      v.addElement(extraireFil(
          "CI;5;Chimie;4;0;0;0;Biologie;4;0;0;0;Francais;1;0;0;-1;Anglais;1;0;0;-1;"));
      //BI
      v.addElement(extraireFil(
          "BI;2;Chimie;4;0;0;0;Biologie;4;0;0;0;Francais;1;0;0;-1;Anglais;1;0;0;-1;"));
      v.addElement(extraireFil(
          "BI;3;Chimie;4;0;0;0;Biologie;4;0;0;0;Francais;1;0;0;-1;Anglais;1;0;0;-1;"));
      v.addElement(extraireFil(
          "BI;4;Chimie;4;0;0;0;Biologie;4;0;0;0;Francais;1;0;0;-1;Anglais;1;0;0;-1;"));
      v.addElement(extraireFil(
          "BI;5;Chimie;4;0;0;0;Biologie;4;0;0;0;Francais;1;0;0;-1;Anglais;1;0;0;-1;"));

    }
    return v;
  } //FIN extraireFileFil

  //methode qui sauvegarde un vecteur de filieres dans un fichier de filieres
  static public void remplirFileFil(File f, Vector v) {
    Filiere fil;
    Matiere m;
    StringBuffer ligne;
    try {
      BufferedWriter out = new BufferedWriter(new FileWriter(f));
      for (int i = 0; i < v.size(); i++) {
        fil = (Filiere) v.elementAt(i);
        ligne = new StringBuffer(fil.getNom() + ";"+fil.getNiveau()+";");

        //ajout de la liste des matieres
        for (int j = 0; j < fil.getListMatieres().size(); j++) {
          m = (Matiere) fil.getListMatieres().elementAt(j);
          ligne.append(m.getNom() + ";" + m.getCoef() + ";" + m.getEx() + ";" +
                       m.getDs() + ";" + m.getTp() + ";");
        }

        //ecriture de ligne dans le fichier
        out.write(ligne.toString());
        out.newLine();
      }
      out.close();
    }
    catch (IOException ex) {
      //System.out.println(ex.toString());
      erreur();
    }
    //System.out.println("operation terminé avec succés");
  }

}