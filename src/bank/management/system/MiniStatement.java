package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class MiniStatement extends JFrame {

    MiniStatement(String pinnumber) {
        setTitle("Mini Statement");
        setLayout(null);

        JLabel bank = new JLabel("State Bank Of India");
        bank.setBounds(130, 20, 300, 20);
        bank.setFont(new Font("System", Font.BOLD, 16));
        add(bank);

        JLabel card = new JLabel();
        card.setBounds(20, 60, 350, 20);
        add(card);

        // JTextArea for mini statement
        JTextArea mini = new JTextArea();
        mini.setEditable(false);
        mini.setBackground(Color.WHITE);
        mini.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Monospaced for alignment
        mini.setLineWrap(false);  // don’t wrap, keep columns aligned

        // Scroll pane for transactions
        JScrollPane scrollPane = new JScrollPane(mini);
        scrollPane.setBounds(20, 100, 350, 280);
        add(scrollPane);

        JLabel balance = new JLabel();
        balance.setBounds(20, 400, 350, 20);
        add(balance);

        // Fetch card number
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("SELECT * FROM login WHERE pinNumber = '" + pinnumber + "'");
            while (rs.next()) {
                String cardNumber = rs.getString("cardNumber");
                String masked = cardNumber.substring(0, 4) + "XXXXXXXX" + cardNumber.substring(12);
                card.setText("Card Number: " + masked);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        // Header row for mini statement
        mini.append(String.format("%-20s %-10s %s\n", "Date/Time", "Type", "Amount"));
        mini.append("------------------------------------------------\n");

        // Fetch mini statement and balance
        try {
            Conn conn = new Conn();
            int bal = 0;
            ResultSet rs = conn.s.executeQuery("SELECT * FROM bank WHERE pinNumber='" + pinnumber + "'");
            while (rs.next()) {
                String entry = String.format(
                        "%-20s %-10s %s",
                        rs.getString("date"),
                        rs.getString("type"),
                        rs.getString("amount")
                );
                mini.append(entry + "\n");

                if (rs.getString("type").equals("Deposit")) {
                    bal += Integer.parseInt(rs.getString("amount"));
                } else {
                    bal -= Integer.parseInt(rs.getString("amount"));
                }
            }
            balance.setText("Your current account balance is Rs: " + bal);
        } catch (Exception e) {
            System.out.println(e);
        }

        setSize(400, 500);
        setLocation(100, 100);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    public static void main(String args[]) {
        new MiniStatement(""); // pass pinNumber for testing
    }
}