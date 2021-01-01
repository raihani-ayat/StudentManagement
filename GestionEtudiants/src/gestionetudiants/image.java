package gestionetudiants;


//cette class met une image ds un JPanel

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;



public class image extends JPanel{
  Image img=null;
  Image imgFiltre=null;


  public image(String S){
    Toolkit kit=Toolkit.getDefaultToolkit();
    img=kit.getImage(S);
    MediaTracker mt=new MediaTracker(this);
    imgFiltre=createImage(new FilteredImageSource(img.getSource(),new CropImageFilter(0,0,200,100)));
    mt.addImage(imgFiltre,0);
    try{
      mt.waitForID(0);
    }catch(Exception ex){
//      this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
    }


    setVisible(true);
    setSize(400,400);
  }

  public void paint(Graphics g){
    super.paint(g);
    g.drawImage(img,0,0,this);

  }

  public static void main(String[] args){
    image F=new image("insat.jpg");
    JFrame JF=new JFrame("lhkjl");
    JF.getContentPane().add(F);
    JF.pack();
    JF.show();
  }
}
