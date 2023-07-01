
////// CLASS : ProgressBarDemo.java
import java.awt.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProgressBarDemo {
       public JFrame fr = new JFrame();
       public JProgressBar bar = new JProgressBar(0,100);

       public JLabel welcomeLabel = new JLabel("Loading Please Wait"); // this will not be printed
    ProgressBarDemo(){
        welcomeLabel.setBounds(0,0,400,35);
        welcomeLabel.setFont(new Font(null,Font.PLAIN,25));
        welcomeLabel.setText("Loading Please Wait "); //this will be printed

        bar.setValue(0);
        bar.setBounds(50,100,300,40);
        bar.setStringPainted(true);
        bar.setFont(new Font("MV Boli",Font.BOLD,25));
        bar.setForeground(Color.MAGENTA);
        bar.setBackground(Color.black);

        fr.add(bar);
        fr.add(welcomeLabel);  // here added the welcome label
        fr.setSize(420, 420);
        fr.setLayout(null);
        fr.setVisible(true);
        //fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // otherwise it terminates the program on pressing x
        fr.setSize(420,320);
        fr.setLayout(null);

        fill();
        fr.dispose();
    }
    public void fill(){
        int counter = 0;
        while(counter<=100){
            bar.setValue(counter);

            try {
                Thread.sleep(1000);
            }catch(InterruptedException ex){
                ex.printStackTrace();
            }
               counter+=10;
        }
        bar.setString("Done!!");
    }  
}
/////////////////////////////////////////////////////////
