//USED CARS SALES PROJECT IN JAVA AND JAVA SWING, AWT , AND SQL
//IMPLEMENTATION
// CLASS : UsedCarsSales.java
import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UsedCarsSales {
    // requirements
    private JPanel WelCome;
    private JTextField txtname;
    private JTextField txtprice;
    private JButton addButton;
    private JButton deleteButton;
    private JButton editButton;
    private JTextField txtrunning;
    private JTextField txtage;
    private JTextField txtmodelno;
    private JLabel cname;
    private JLabel price;
    private JLabel running;
    private JLabel age;
    private JLabel modelno;
    private JTextField txtpid;
    private JButton SearchButton;
    private JTable table_1;
    private JLabel pid;
    private JButton buyButton;

// main method
    public static void main(String[] args) {

        ProgressBarDemo demo = new ProgressBarDemo();

        JFrame frame = new JFrame("UsedCarsSales");
        frame.setContentPane(new UsedCarsSales().WelCome);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        // here is main method

    }

    // connection requirements
    Connection con;
    PreparedStatement pst;

    private String dburl = "jdbc:mysql://localhost:3306/database1";
    private String username = "root";
    private String password = "";

    public static boolean f = false;          //  here important
    public String passpid ="";

    // connection method to give connection
    public void Connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");  //com.mysql.jdbc.Driver
            con = DriverManager.getConnection(dburl,username,password);
            if(con!=null)
            {  System.out.println("success");  }

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
    // inside frame the table should be loaded so table_load method
    public  void table_load(){
        try{
            table_1.setModel(new DefaultTableModel(null, new String[]{"PID", "Company Name", "Price", "Running", "Age", "Model No"}));

        }catch(Exception e1){
            e1.printStackTrace();
        }
        try{
            pst = con.prepareStatement("select * from record_table");
            ResultSet rs = pst.executeQuery();
            table_1.setModel(DbUtils.resultSetToTableModel(rs));

        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }// end of table load method

    // from here the constructor
    // inside constructor itself action listener methods are declared
    public UsedCarsSales() {
        Connect();
        table_load();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    String cname, price, running, age, modelno;
                cname = txtname.getText();  // Fetch the value present in textfeild and put in cname
                price = txtprice.getText();
                running = txtrunning.getText();
                age = txtage.getText();
                modelno = txtmodelno.getText();

                if (!Objects.equals(cname, "") && !Objects.equals(price, "") && !Objects.equals(running, "") && !Objects.equals(age, "") && !Objects.equals(modelno, "")) {
                    //here asper database table
                    String q = "insert into record_table(cn,price,running,age,modelno) values(?,?,?,?,?)";
                    try {
                        pst = con.prepareStatement(q);
                        pst.setString(1, cname); // add details in database
                        pst.setString(2, price);
                        pst.setString(3, running);
                        pst.setString(4, age);
                        pst.setString(5, modelno);

                        pst.executeUpdate();
                        table_load();
                        JOptionPane.showMessageDialog(null, "Added Successfully");

                        txtname.setText("");   // after adding set the value in the text feild to null
                        txtprice.setText("");
                        txtrunning.setText("");
                        txtage.setText("");
                        txtmodelno.setText("");
                        txtpid.setText("");
                        txtname.requestFocus();

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Give All details");
                }
            }
        });

        // search button
        SearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String pid = txtpid.getText();  // take value from page and put in pid variable
                    String querry = "select cn,price,running,age,modelno from record_table where pid = ?";
                    pst = con.prepareStatement(querry);
                    pst.setString(1,pid);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true){
                        f=true;
                        passpid=pid;
                        String cname,price,running,age,modelno;
                        cname = rs.getString(1); // if search is successful
                        price = rs.getString(2); //get details from database to local variables
                        running = rs.getString(3);
                        age = rs.getString(4);
                        modelno = rs.getString(4);

                        txtname.setText(cname);  // now put these variable details on text fields
                        txtprice.setText(price);
                        txtrunning.setText(running);
                        txtage.setText(age);
                        txtmodelno.setText(modelno);
                        table_load();

                    }
                    else{
                        txtname.setText("");
                        txtprice.setText("");
                        txtrunning.setText("");
                        txtage.setText("");
                        txtmodelno.setText("");
                        txtpid.setText("");
                        JOptionPane.showMessageDialog(null,"Invalid id");
                        table_load();
                    }
                }catch(SQLException ex){
                    ex.printStackTrace();
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cname, price, running, age, modelno;
                cname = txtname.getText();
                price = txtprice.getText();
                running = txtrunning.getText();
                age = txtage.getText();
                modelno = txtmodelno.getText();

                String pid = txtpid.getText();
                if (!Objects.equals(cname, "") && !Objects.equals(price, "") && !Objects.equals(running, "") && !Objects.equals(age, "") && !Objects.equals(modelno, "")){
                    try {
                        if (cname != null && price != null && running != null && age != null && modelno != null) {
                            String q = "update record_table set cn = ?, price = ?, running = ?, age = ?, modelno = ? where pid=? ";
                            pst = con.prepareStatement(q);
                            pst.setString(1, cname);
                            pst.setString(2, price);
                            pst.setString(3, running);
                            pst.setString(4, age);
                            pst.setString(5, modelno);
                            pst.setString(6, pid);

                            pst.executeUpdate();
                            table_load();
                            JOptionPane.showMessageDialog(null, "Edited Successfully");

                            txtname.setText("");
                            txtprice.setText("");
                            txtrunning.setText("");
                            txtage.setText("");
                            txtmodelno.setText("");
                            txtpid.setText("");
                            txtname.requestFocus();
                        } else {
                            JOptionPane.showMessageDialog(null, "invalid details please provide valid details");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
            }
                else{
                    JOptionPane.showMessageDialog(null, "invalid details please provide valid details");
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String var, q;
                var = txtpid.getText();
                q="delete from record_table where pid = ?";
                try{
                    if(!Objects.equals(var, "")) {
                        pst = con.prepareStatement(q);
                        pst.setString(1, var);

                        pst.executeUpdate();
                        table_load();
                        JOptionPane.showMessageDialog(null, "Car Deleted Successfully ");

                        txtname.setText("");
                        txtprice.setText("");
                        txtrunning.setText("");
                        txtage.setText("");
                        txtmodelno.setText("");
                        txtpid.setText("");
                        txtname.requestFocus();
                    }else{
                        JOptionPane.showMessageDialog(null,"invalid id ");
                    }
                }
                catch(SQLException e1){
                    e1.printStackTrace();
                }
            }
        });
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table_load();
                try {
                    IDandPasswords idandPasswords = new IDandPasswords();// hash map
                    if(f) {
                        table_load();
                        LoginPage loginPage = new LoginPage(idandPasswords.getLoginInfo(),passpid);  // passing the pid
                        txtname.setText("");
                        txtprice.setText("");
                        txtrunning.setText("");
                        txtage.setText("");
                        txtmodelno.setText("");
                        txtpid.setText("");
                        txtname.requestFocus();
                    }
                    else{
                        txtname.setText("");
                        txtprice.setText("");
                        txtrunning.setText("");
                        txtage.setText("");
                        txtmodelno.setText("");
                        txtpid.setText("");
                        JOptionPane.showMessageDialog(null,"Invalid id");
                        table_load();
                    }
                    }catch(Exception ec){
                    ec.printStackTrace();
                }
                }
        });
    }// end of constructor
}// end of class used cars sales
///////////////////////////////////////////////////////////////////////////////////////////////////
