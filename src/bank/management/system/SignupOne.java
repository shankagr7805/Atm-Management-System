package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.toedter.calendar.JDateChooser;
import java.sql.*;
import java.util.*;
public class SignupOne extends JFrame implements ActionListener{
    JTextField addText,nameText,fnameText,emailText,cityText,pinText,stateText;
    long random;
    JButton next;
    JRadioButton male,female,other,married,unmarried;
    JDateChooser date;
       SignupOne(){
           
           setLayout(null);
           Random ran = new Random();
          random= Math.abs((ran.nextLong() % 9000L)+1000L);
           
           JLabel form= new JLabel("Application form " + random);
           form.setFont(new Font("Raleway",Font.BOLD,38));
           form.setBounds(220,20,600,40);
           add(form);
           
           JLabel perDetails= new JLabel("Page 1: Personal Details");
           perDetails.setFont(new Font("Raleway",Font.BOLD,22));
           perDetails.setBounds(290,80,400,30);
           add(perDetails);
           
           JLabel name= new JLabel("Name:");
           name.setFont(new Font("Raleway",Font.BOLD,20));
           name.setBounds(100,140,100,30);
           add(name);
           
           nameText=new JTextField();
           nameText.setFont(new Font("Raleway",Font.BOLD,15));
           nameText.setBounds(300,140,300,30);
           add(nameText);
  
           
           JLabel fname= new JLabel("Father's Name:");
           fname.setFont(new Font("Raleway",Font.BOLD,20));
           fname.setBounds(100,190,200,30);
           add(fname);
           
           fnameText=new JTextField();
           fnameText.setFont(new Font("Raleway",Font.BOLD,15));
           fnameText.setBounds(300,190,300,30);
           add(fnameText);
           
           JLabel dob= new JLabel("Date of Birth:");
           dob.setFont(new Font("Raleway",Font.BOLD,20));
           dob.setBounds(100,240,200,30);
           add(dob);
           
           date=new JDateChooser();
           date.setBounds(300,240,300,30);
           date.setForeground(Color.BLACK);
           add(date);
           
           JLabel gender= new JLabel("Gender:");
           gender.setFont(new Font("Raleway",Font.BOLD,20));
           gender.setBounds(100,290,100,30);
           add(gender);
           
           male=new JRadioButton("Male");
           male.setBounds(300,290,60,30);
           male.setBackground(Color.WHITE);
           add(male);
           
           female=new JRadioButton("Female");
           female.setBounds(400,290,80,30);
           female.setBackground(Color.WHITE);
           add(female);
           
           ButtonGroup genderGroup= new ButtonGroup();
           genderGroup.add(male);
           genderGroup.add(female);
           
           JLabel email= new JLabel("Email Address:");
           email.setFont(new Font("Raleway",Font.BOLD,20));
           email.setBounds(100,340,200,30);
           add(email);
           
           emailText=new JTextField();
           emailText.setFont(new Font("Raleway",Font.BOLD,15));
           emailText.setBounds(300,340,300,30);
           add(emailText);
           
           JLabel mStatus= new JLabel("Marital Status:");
           mStatus.setFont(new Font("Raleway",Font.BOLD,20));
           mStatus.setBounds(100,390,200,30);
           add(mStatus);
           
           married=new JRadioButton("Married");
           married.setBounds(300,390,80,30);
           married.setBackground(Color.WHITE);
           add(married);
           
           unmarried=new JRadioButton("Unmarried");
           unmarried.setBounds(420,390,100,30);
           unmarried.setBackground(Color.WHITE);
           add(unmarried);
           
           other=new JRadioButton("Other");
           other.setBounds(540,390,60,30);
           other.setBackground(Color.WHITE);
           add(other);
           
           ButtonGroup marrGroup= new ButtonGroup();
           marrGroup.add(married);
           marrGroup.add(unmarried);
           marrGroup.add(other);
           
           JLabel address= new JLabel("Address:");
           address.setFont(new Font("Raleway",Font.BOLD,20));
           address.setBounds(100,440,100,30);
           add(address);
           
           addText=new JTextField();
           addText.setFont(new Font("Raleway",Font.BOLD,15));
           addText.setBounds(300,440,300,30);
           add(addText);
           
           JLabel city= new JLabel("City:");
           city.setFont(new Font("Raleway",Font.BOLD,20));
           city.setBounds(100,490,100,30);
           add(city);
           
           cityText=new JTextField();
           cityText.setFont(new Font("Raleway",Font.BOLD,15));
           cityText.setBounds(300,490,300,30);
           add(cityText);
           
           JLabel state= new JLabel("State:");
           state.setFont(new Font("Raleway",Font.BOLD,20));
           state.setBounds(100,540,100,30);
           add(state);
           
           stateText=new JTextField();
           stateText.setFont(new Font("Raleway",Font.BOLD,15));
           stateText.setBounds(300,540,300,30);
           add(stateText);
           
           JLabel pincode= new JLabel("Pincode:");
           pincode.setFont(new Font("Raleway",Font.BOLD,20));
           pincode.setBounds(100,590,100,30);
           add(pincode);
           
           pinText=new JTextField();
           pinText.setFont(new Font("Raleway",Font.BOLD,15));
           pinText.setBounds(300,590,300,30);
           add(pinText);
           
           next =new JButton("Next");
           next.setBackground(Color.BLACK);
           next.setForeground(Color.BLACK);
           next.setBounds(500,650,100,30);
           add(next);
           next.addActionListener(this);
           
           getContentPane().setBackground(Color.WHITE);
           setSize(850,800);
           setLocation(350,10);
           setVisible(true);
       }
       
       
    @Override
    public void actionPerformed(ActionEvent ae){
        String formno=" "+random;
        String name=nameText.getText();
        String fname=fnameText.getText();
        String city=cityText.getText();
        String pin=pinText.getText();
        String state=stateText.getText();
        String email=emailText.getText();
        String address=addText.getText();
        
        String gender=null;
        if(male.isSelected()){
            gender="Male";
        }else if(female.isSelected()){
            gender="Female";
        }
        String marry=null;
        if(married.isSelected()){
            marry="Married";
        }else if(unmarried.isSelected()){
            marry="Unmarried";
        }else if(other.isSelected()){
            marry="Other";
        }
        
        try {
            if (name.equals("")) {
                JOptionPane.showMessageDialog(null, "Enter name");
            } else {
                Conn c = new Conn();

                java.util.Date selectedDate = date.getDate();
                java.sql.Date sqlDob = null;
                if (selectedDate != null) {
                    sqlDob = new java.sql.Date(selectedDate.getTime());
                }

                String query = "INSERT INTO signup(formno, name, fname, dob, gender, email, marry, address, city, state, pin) "
                             + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement pstmt = c.c.prepareStatement(query);
                pstmt.setString(1, formno);
                pstmt.setString(2, name);
                pstmt.setString(3, fname);
                pstmt.setDate(4, sqlDob);   
                pstmt.setString(5, gender);
                pstmt.setString(6, email);
                pstmt.setString(7, marry);
                pstmt.setString(8, address);
                pstmt.setString(9, city);
                pstmt.setString(10, state);
                pstmt.setString(11, pin);

                pstmt.executeUpdate();

                setVisible(false);
                new SignupTwo(formno).setVisible(true);
                System.out.println("DOB to insert: " + sqlDob);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
    } 
    public static void main(String args[]) {
       new SignupOne();
    }
}