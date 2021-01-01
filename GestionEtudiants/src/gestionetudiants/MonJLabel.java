package gestionetudiants;

//class qui extend de JLabel pour permettre de changer le font des labels
//de tous les interface a l'aide de "options affichage"

import java.awt.*;
import javax.swing.*;

public class MonJLabel extends JLabel{
  static Font font=new Font("Arial",0,12);
  public MonJLabel(String titre) {
    super(titre);
    setFont(font);
  }
}