import javax.swing.*;
import java.awt.*; 
import java.awt.event.*; 

public class SoundSmootherGUI extends JFrame{
  private SoundSmoother mySmoother; //SoundSmoother object as instance variable


  public SoundSmootherGUI(String fileName) {
    super("Sound Smoother"); // calls constructor of parent class
    setSize(840, 480); //sets size of window 840 by 480
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(null); //no layout

    // Create a button. 
    JButton b = new JButton("Smooth it!");
    b.setBounds(300, 400, 150, 40); 

    //Add an event handler

    b.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e){
        mySmoother.smoothIt(); //button logic
        repaint(); 

      }
    });

    add(b); // Adds the button to the form. 

    mySmoother = new SoundSmoother(fileName, 2, 1, 10, 400);  
  }

  
  public void paint(Graphics g){
    super.paint(g); // Call paint from parent class
    mySmoother.paint(g);
  }

}
