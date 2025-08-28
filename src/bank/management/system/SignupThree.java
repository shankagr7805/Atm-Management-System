package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class SignupThree extends JFrame implements ActionListener{
    long random;
    JLabel accDetails,name,CrdNo,pin,sReq,DCNo,Dpass,j1,j2;
    JButton submit,cancel;
    JCheckBox atmCard,ib,mb,email,cb,estate,Ihere;
    JRadioButton SaveAcc,FDAcc,CurAcc,RDAcc;
    String formno;
      SignupThree(String formno){
        setLayout(null);
        this.formno=formno;
      accDetails= new JLabel("Page 3: Account Details");
      accDetails.setFont(new Font("Raleway",Font.BOLD,22));
      accDetails.setBounds(290,50,400,30);
      add(accDetails);
        
      name= new JLabel("Account Type");
      name.setFont(new Font("Raleway",Font.BOLD,20));
      name.setBounds(100,120,300,30);
      add(name);
      
      SaveAcc=new JRadioButton("Savings Account");
      SaveAcc.setBounds(100,160,120,30);
      SaveAcc.setBackground(Color.WHITE);
      add(SaveAcc);
      
      CrdNo=new JLabel("Card Number");
      CrdNo.setFont(new Font("Raleway",Font.BOLD,30));
      CrdNo.setBounds(100,280,200,30);
      add(CrdNo);
      
      j1=new JLabel("XXXX-XXXX-XXXX-5987");
      j1.setBounds(350,280,400,30);
      j1.setFont(new Font("Raleway",Font.BOLD,30));
      add(j1);
      
      j2=new JLabel("XXXX");
      j2.setBounds(350,370,100,30);
      j2.setFont(new Font("Raleway",Font.BOLD,30));
      add(j2);
      
      DCNo=new JLabel("Your 16 Digit Card Number");
      DCNo.setFont(new Font("Raleway",Font.BOLD,13));
      DCNo.setBounds(101,310,300,30);
      add(DCNo);
      
      Dpass=new JLabel("Your four digit password");
      Dpass.setFont(new Font("Raleway",Font.BOLD,13));
      Dpass.setBounds(101,400,300,30);
      add(Dpass);
      
      pin=new JLabel("PIN");
      pin.setBounds(100,370,100,30);
      pin.setFont(new Font("Raleway",Font.BOLD,30));
      add(pin);
      
      sReq =new JLabel("Service Required:");
      sReq.setBounds(100,450,300,30);
      sReq.setFont(new Font("Raleway",Font.BOLD,30));
      add(sReq);
           
      FDAcc=new JRadioButton("Fixed Deposit Amount");
      FDAcc.setBounds(350,160,180,30);
      FDAcc.setBackground(Color.WHITE);
      add(FDAcc);
      
      CurAcc=new JRadioButton("Current Account");
      CurAcc.setBounds(100,200,150,30);
      CurAcc.setBackground(Color.WHITE);
      add(CurAcc);
      
      RDAcc= new JRadioButton("Recurring Deposit Account");
      RDAcc.setBackground(Color.WHITE);
      RDAcc.setBounds(350,200,180,30);
      add(RDAcc);
              
      ButtonGroup account= new ButtonGroup();
      account.add(SaveAcc);
      account.add(FDAcc);
      account.add(CurAcc);
      account.add(RDAcc);
      
      ib=new JCheckBox("Internet Banking");
      ib.setBounds(300,500,150,30);
      ib.setBackground(Color.WHITE);
      ib.setFont(new Font("Raleway",Font.BOLD,13));
      add(ib);
      
     email=new JCheckBox("Email & SMS Alerts");
     email.setBounds(300,550,350,30);
     email.setBackground(Color.WHITE);
     email.setFont(new Font("Raleway",Font.BOLD,13));
     add(email);
     
     cb=new JCheckBox("Mobile Banking");
     cb.setBounds(100,550,150,30);
     cb.setBackground(Color.WHITE);
     cb.setFont(new Font("Raleway",Font.BOLD,13));
     add(cb);
     
     estate=new JCheckBox("Check Book");
     estate.setBounds(100,600,150,30);
     estate.setBackground(Color.WHITE);
     estate.setFont(new Font("Raleway",Font.BOLD,13));
     add(estate);
     
     mb=new JCheckBox("E-statement");
     mb.setBounds(300,600,150,30);
     mb.setBackground(Color.WHITE);
     mb.setFont(new Font("Raleway",Font.BOLD,13));
     add(mb);
     
     atmCard=new JCheckBox("ATM Card");
     atmCard.setBounds(100,500,150,30);
     atmCard.setBackground(Color.WHITE);
     atmCard.setFont(new Font("Raleway",Font.BOLD,13));
     add(atmCard);
     
     Ihere=new JCheckBox("I hereby declares that the above entered details are correct to the best of my knowledge");
     Ihere.setBounds(100,680,750,30);
     Ihere.setBackground(Color.WHITE);
     Ihere.setFont(new Font("Raleway",Font.BOLD,13));
     add(Ihere);
     
     submit=new JButton("Submit");
     submit.setBounds(200,720,80,35);
     submit.setBackground(Color.BLACK);
     submit.setForeground(Color.BLACK);
     add(submit);
     submit.addActionListener(this);
     
     cancel=new JButton("Cancel");
     cancel.setBounds(420,720,80,35);
     cancel.setBackground(Color.BLACK);
     cancel.setForeground(Color.BLACK);
     add(cancel);
     cancel.addActionListener(this);
      
    getContentPane().setBackground(Color.WHITE);
    setSize(850,800);
    setLocation(350,10);
    setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==submit){
            String accountType= null;
            if(SaveAcc.isSelected()){
                accountType="Saving Account";
            }
            else if(FDAcc.isSelected()){
                accountType="Fixed Deposit";
            }
            else if(CurAcc.isSelected()){
                accountType="Current Account";
            }
            else if(RDAcc.isSelected()){
                accountType="Recurring deposit account";
            }
         Random r= new Random();
         String cardNumber= ""+Math.abs((r.nextLong() % 90000000L) + 5673890700000000L);
         String pinNumber=""+Math.abs((r.nextLong() % 9000L)+1000L);
         
         String facility="";
         if(atmCard.isSelected()){
             facility= facility + " ATM Card";
         }else if(ib.isSelected()){
             facility=facility +" Internet Banking";
         }else if(mb.isSelected()){
            facility=facility +" Mobile Banking";
        }else if(email.isSelected()){
            facility=facility +" Email and SMS alerts";
        }else if(cb.isSelected()){
            facility=facility +" Cheque Book";
        }else if(estate.isSelected()){
            facility=facility +" E-Statement";
        }
           try{
               if(accountType.equals("")){
                   JOptionPane.showMessageDialog(null,"Account type is required");
               }else{
                   Conn c=new Conn();
                   String query="insert into signupthree values('"+formno+"','"+accountType+"','"+cardNumber+"','"+pinNumber+"','"+facility+"')";
                   String query2="insert into login values('"+formno+"','"+cardNumber+"','"+pinNumber+"')";
                   c.s.executeUpdate(query);
                   c.s.executeUpdate(query2);
                   
                   JOptionPane.showMessageDialog(null,"Card Number: " +cardNumber+ "\n Pin: " +pinNumber);
               setVisible(false);
               new Deposit(pinNumber).setVisible(false);
               }
                              
           }catch(Exception e){
               System.out.println(e);
           }
        }
        else if(ae.getSource()==cancel){
            setVisible(false);
            new Login().setVisible(true);
        }
    }
    public static void main(String args[]) {
     new SignupThree("");        
    }
}
