package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ManageBillsPanel extends JPanel implements ActionListener{

    JTextField rfField;
    JButton btnSearchResult; 
    JButton btnIssueBill;
    JButton btnReturn;

    public ManageBillsPanel() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 10, 10, 10); 

        // Manage Bills Header
        JLabel header = new JLabel("Manage Bills");
        header.setFont(new Font("Arial", Font.BOLD, 22));
        header.setHorizontalAlignment(JLabel.CENTER);
        c.gridwidth = 2;
        c.gridx = 0; c.gridy = 0; add(header, c);

        // Search the Bill by RF code field
        c.gridwidth = 1; 
        c.gridy = 1;
        add(new JLabel("Search Bill by RF code:"), c);

        rfField = new JTextField(15);
        c.gridx = 1;
        add(rfField, c);

        c.gridx = 0; 
        c.gridy = 2;
        c.gridwidth = 2; 
        JLabel lblResult = new JLabel("Result:");
        lblResult.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblResult, c);

        btnSearchResult = new JButton("Bill Recipient Name | Payment Amount | RF Code");
        btnSearchResult.setPreferredSize(new Dimension(400, 50)); 
        c.gridy = 3;
        add(btnSearchResult, c);
        
        btnIssueBill = new JButton("Issue Bill");
        c.gridy = 4;
        c.insets = new Insets(50, 10, 10, 10); 
        add(btnIssueBill, c);
        btnReturn = new JButton("Return to Account Dashboard");
        c.gridy = 5;
        c.insets = new Insets(10, 10, 10, 10); 
        add(btnReturn, c);

        // events
        btnReturn.addActionListener(this);
        // btnIssueBill.addActionListener(this); // To be implemented later
        // btnSearchResult.addActionListener(this); // To be implemented later
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnReturn) {
            AppMediator.getCardLayout().show(AppMediator.getCards(), "accountMenu");
        }
    }
}
