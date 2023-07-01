
/////  CLASS : LoginPage.java
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class LoginPage implements ActionListener{
    JFrame frame = new JFrame();
    JButton loginButton = new JButton("Login");
    JButton resetButton = new JButton("Reset");
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JLabel userIDLabel = new JLabel("userID:");
    JLabel userPasswordLabel = new JLabel("password:");
    JLabel messageLabel = new JLabel();
    HashMap<String,String> logininfo = new HashMap<String,String>();

    static String s;
    LoginPage(HashMap<String,String> loginInfoOriginal,String gotpid){
       s=gotpid;
        logininfo = loginInfoOriginal;
        userIDLabel.setBounds(50,100,75,25);        // user id label bounds
        userPasswordLabel.setBounds(50,150,75,25);  // user password label bounds
        messageLabel.setBounds(125,250,250,35);               // bounds for message label
        messageLabel.setFont(new Font(null,Font.ITALIC,25));          // fonts for message label
        userIDField.setBounds(125,100,200,25);              // userid txtfield bounds
        userPasswordField.setBounds(125,150,200,25);        // password text field bounds
        loginButton.setBounds(125,200,100,25);              // login button bounds
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);                            // very important
        resetButton.setBounds(225,200,100,25);              // reset button bounds
        resetButton.setFocusable(false);                                        //very important
        resetButton.addActionListener(this);                                // very important
        frame.add(userIDLabel);
        frame.add(userPasswordLabel);
        frame.add(messageLabel);
        frame.add(userIDField);
        frame.add(userPasswordField);
        frame.add(loginButton);
        frame.add(resetButton);
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==resetButton) { // if pressed reset button
            userIDField.setText("");
            userPasswordField.setText("");
        }
        if(e.getSource()==loginButton) {  // if pressed login button
            String userID = userIDField.getText();
            String password = String.valueOf(userPasswordField.getPassword());
            if(logininfo.containsKey(userID)) {
                if(logininfo.get(userID).equals(password)) {
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("Login successful");
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    frame.dispose();
                   AgreementForm agr = new AgreementForm(userID,s); // here I passed pid in s
                }
                else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Wrong password");
                }
            }
            else {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Username not found");
            }
        }
    }
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
