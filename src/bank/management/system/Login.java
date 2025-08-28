package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    JButton sign,clear,signUp;
    JTextField cardText;
    JPasswordField pinText;
    Login(){
        setTitle("ATM Machine");
        setLayout(null);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2=i1.getImage().getScaledInstance(100, 100,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel label=new JLabel(i3);
        label.setBounds(70,10 ,100 , 100);
        add(label);
        
        JLabel card =new JLabel("Card Number:");
        add(card);
         card.setFont(new Font("Raleway",Font.BOLD,28));
        card.setBounds(100,150 , 250, 30);
        
        cardText=new JTextField();
        add(cardText);
        cardText.setBounds(400,150,250,30);
        
        JLabel pin =new JLabel("PIN:");
        add(pin);
        pin.setFont(new Font("Raleway",Font.BOLD,28));
        pin.setBounds(100,220 , 250, 30);
        
        pinText=new JPasswordField();
        add(pinText);
        pinText.setBounds(400,220,250,30);
        
        JLabel text =new JLabel("Welcome to ATM Machine");
        add(text);
        text.setFont(new Font("Osward",Font.BOLD,30));
        text.setBounds(200,40 , 400, 40);
        
        sign=new JButton("Sign In");
        sign.setBounds(350,300,80,30);
        sign.setBackground(Color.BLACK);
        sign.setForeground(Color.BLACK);
        sign.addActionListener(this);
        add(sign);
        
        clear=new JButton("Clear");
        clear.setBounds(500,300,80,30);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.BLACK);
        clear.addActionListener(this);
        add(clear);
        
        signUp=new JButton("Sign Up");
        signUp.setBounds(350,350,230,30);
        signUp.setBackground(Color.BLACK);
        signUp.setForeground(Color.BLACK);
        signUp.addActionListener(this);
        add(signUp);
        
        getContentPane().setBackground(Color.WHITE);
        
        setSize(800, 480);
        setVisible(true);
        setLocation(350, 200);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == clear){
            cardText.setText("");
            pinText.setText("");
        }
        else if(ae.getSource()==sign){
            Conn conn=new Conn();
            String cardnumber=cardText.getText();
            String pinnumber=pinText.getText();
            String query="select * from login where cardNumber = '"+cardnumber+"' and pinNumber = '"+pinnumber+"'";
            try{
                ResultSet rs =conn.s.executeQuery(query);
                if(rs.next()){
                setVisible(false);
                new Transaction(pinnumber).setVisible(true);       
                }else{
                    JOptionPane.showMessageDialog(null,"Incorrect Card or Pin");
                }
            }catch(Exception e){
                System.out.println(e);
                       
            }
        }
        else if(ae.getSource()==signUp){
                setVisible(false);
            new SignupOne().setVisible(true);
        }
    }
    public static void main(String args[]) {
     new Login();
    }
}
