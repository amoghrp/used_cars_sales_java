
///// CLASS : AgreementForm.java
import net.proteanit.sql.DbUtils;
import java.sql.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AgreementForm implements ActionListener{
        Connection con;
        PreparedStatement pst;
        private String dburl = "jdbc:mysql://localhost:3306/database1";
        private String username = "root";
        private String password = "";
       // public JTable table_1;
        public void Connect(){
                try{
                        Class.forName("com.mysql.cj.jdbc.Driver");  //com.mysql.jdbc.Driver
                        con = DriverManager.getConnection(dburl,username,password);
                        if(con!=null)
                        {  System.out.println("Connection success In Agreement form");  }

                }
                catch(ClassNotFoundException ex){
                        ex.printStackTrace();
                }
                catch(SQLException ex){
                        ex.printStackTrace();
                }
                catch(Exception ex){
                        ex.printStackTrace();
                }
        }

        JFrame frame = new JFrame();
        JLabel welcomeLabel = new JLabel("name of welcome label");  // this will not be printed
        static String s = "";     // this recieves the pid from login form
        JLabel nameLabel = new JLabel("Name");
        JTextField txtname = new JTextField();
        JLabel addressLabel = new JLabel("Address");
        JTextField txtaddress = new JTextField();
        JLabel phoneNoLabel= new JLabel("PhoneNo");
        JTextField txtphone = new JTextField();
        JButton confirmbuyButton = new JButton("Confirm Purchase");
        AgreementForm(String user,String fpid){
                // deletion operation happens in constructor

                System.out.println("inside constructor of Agreement form ");
                Connect();
                s=fpid;
                welcomeLabel.setBounds(215,10,200,30);
                welcomeLabel.setFont(new Font(null,Font.PLAIN,24));
                welcomeLabel.setText("Hello "+user);
                frame.add(welcomeLabel);

                nameLabel.setBounds(10,45,75,25);
                txtname.setBounds(90,45,125,25);
                addressLabel.setBounds(10,70,75,25);
                txtaddress.setBounds(90,70,125,25);
                phoneNoLabel.setBounds(10,95,75,25);
                txtphone.setBounds(90,95,125,25);

                confirmbuyButton.setBounds(30,125,150,25);
                confirmbuyButton.setFocusable(false);
                confirmbuyButton.addActionListener(this);

                frame.add(nameLabel);
                frame.add(txtname);
                frame.add(addressLabel);
                frame.add(txtaddress);
                frame.add(phoneNoLabel);
                frame.add(txtphone);
                frame.add(confirmbuyButton);

                frame.setSize(520, 220);
                frame.setLayout(null);
                frame.setVisible(true);
        }

       //here
       @Override
       public void actionPerformed(ActionEvent e) {

                String qname , qaddress , qphoneno, qspid;
                qspid =s;
                qname=txtname.getText();
                qaddress = txtaddress.getText();
                qphoneno = txtphone.getText();
                if(!Objects.equals(qname, "") && !Objects.equals(qaddress, "") && !Objects.equals(qphoneno, "")){
                        try{
                                System.out.println("inside ........... if ");
                                String querry = "insert into table_2(Name,Phone,Address,spid)values(?,?,?,?)"; // asper database table
                                pst = con.prepareStatement(querry);
                                pst.setString(1,qname); // add details in database
                                pst.setString(2,qphoneno);
                                pst.setString(3,qaddress);
                                pst.setString(4,qspid);
                                pst.executeUpdate();

                                // delete car from database as one of the customer bought that car
                                String q="delete from record_table where pid = ?";
                                pst = con.prepareStatement(q);
                                pst.setString(1,s);

                                pst.executeUpdate();  // for actual deletion

         JOptionPane.showMessageDialog(null,"\nDear "+qname+" you have Successfully Bought the Car . \n" +
        "By this you agree to the terms and conditions of our company. \nWe will be booking this particular car in your name.\n" +
        " We will be contacting you shortly be ready for a ride. :)\n THANK YOU \n");
                                
                                UsedCarsSales.f=false;
                                txtname.setText("");
                                txtaddress.setText("");
                                txtphone.setText("");
                                txtname.requestFocus();
                                frame.dispose();
                        }
                        catch(SQLException ex){
                                ex.printStackTrace();
                        }
                }
                else{
                        JOptionPane.showMessageDialog(null,"Enter all the details");
                }
}
} // End of Code Part.
