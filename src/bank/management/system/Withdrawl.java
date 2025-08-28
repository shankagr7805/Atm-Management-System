package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Withdrawl extends JFrame implements ActionListener {
    JTextField amount;
    JButton withdrawl, back;
    String pinnumber;

    Withdrawl(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Enter the amount you want to Withdraw");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(180, 300, 400, 20);
        image.add(text);

        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        amount.setBounds(170, 350, 320, 25);
        image.add(amount);

        withdrawl = new JButton("Withdraw");
        withdrawl.setBounds(355, 485, 150, 30);
        image.add(withdrawl);
        withdrawl.addActionListener(this);

        back = new JButton("Back");
        back.setBounds(355, 520, 150, 30);
        image.add(back);
        back.addActionListener(this);

        setSize(900, 900);
        setLocation(300, 0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == withdrawl) {
            String numberStr = amount.getText();
            if (numberStr.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the Amount you want to Withdraw");
                return;
            }

            int withdrawAmount = Integer.parseInt(numberStr);
            try {
                Conn c1 = new Conn();
                
                // Calculate current balance
                int balance = 0;
                ResultSet rs = c1.s.executeQuery("SELECT * FROM bank WHERE pinNumber = '" + pinnumber + "'");
                while (rs.next()) {
                    if (rs.getString("type").equals("Deposit")) {
                        balance += rs.getInt("amount");
                    } else {
                        balance -= rs.getInt("amount");
                    }
                }

                if (balance < withdrawAmount) {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    return;
                }

                // Insert withdrawal with PreparedStatement
                java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(new java.util.Date().getTime());
                String query = "INSERT INTO bank (pinNumber, date, type, amount) VALUES (?, ?, 'Withdraw', ?)";
                PreparedStatement pst = c1.c.prepareStatement(query);
                pst.setString(1, pinnumber);
                pst.setTimestamp(2, sqlTimestamp);
                pst.setInt(3, withdrawAmount);
                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Rs. " + withdrawAmount + " Debited Successfully");
                setVisible(false);
                new Transaction(pinnumber).setVisible(true);

            } catch (Exception e) {
                System.out.println(e);
            }

        } else if (ae.getSource() == back) {
            setVisible(false);
            new Transaction(pinnumber).setVisible(true);
        }
    }

    public static void main(String args[]) {
        new Withdrawl("");
    }
}