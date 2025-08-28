package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class FastCash extends JFrame implements ActionListener {
    JButton with100, with500, with1000, with2000, with5000, with10000, back;
    String pinnumber;

    FastCash(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Select Withdrawal Amount");
        text.setBounds(210, 300, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Raleway", Font.BOLD, 16));
        image.add(text);

        with100 = new JButton("Rs 100");
        with100.setBounds(170, 415, 150, 30);
        with100.addActionListener(this);
        image.add(with100);

        with500 = new JButton("Rs 500");
        with500.setBounds(355, 415, 150, 30);
        with500.addActionListener(this);
        image.add(with500);

        with1000 = new JButton("Rs 1000");
        with1000.setBounds(170, 450, 150, 30);
        with1000.addActionListener(this);
        image.add(with1000);

        with2000 = new JButton("Rs 2000");
        with2000.setBounds(355, 450, 150, 30);
        with2000.addActionListener(this);
        image.add(with2000);

        with5000 = new JButton("Rs 5000");
        with5000.setBounds(170, 485, 150, 30);
        with5000.addActionListener(this);
        image.add(with5000);

        with10000 = new JButton("Rs 10000");
        with10000.setBounds(355, 485, 150, 30);
        with10000.addActionListener(this);
        image.add(with10000);

        back = new JButton("Back");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
            new Transaction(pinnumber).setVisible(true);
        } else {
            String amount = ((JButton) ae.getSource()).getText().substring(3); // remove "Rs "
            Conn c = new Conn();

            try {
                // Check balance
                ResultSet rs = c.s.executeQuery("SELECT * FROM bank WHERE pinNumber = '" + pinnumber + "'");
                int balance = 0;
                while (rs.next()) {
                    if (rs.getString("type").equals("Deposit")) {
                        balance += Integer.parseInt(rs.getString("amount"));
                    } else {
                        balance -= Integer.parseInt(rs.getString("amount"));
                    }
                }

                if (balance < Integer.parseInt(amount)) {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    return;
                }

                // Current date-time formatted for MySQL
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate = sdf.format(date);

                // Insert withdrawal record safely
                String query = "INSERT INTO bank(pinNumber, date, type, amount) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = c.c.prepareStatement(query);
                ps.setString(1, pinnumber);
                ps.setString(2, formattedDate);
                ps.setString(3, "Withdrawal");
                ps.setString(4, amount);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(null, "Rs " + amount + " Debited Successfully");

                setVisible(false);
                new Transaction(pinnumber).setVisible(true);

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String args[]) {
        new FastCash(""); // for testing, pass pin number here
    }
}