package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class AccountMenuPanel extends JPanel implements ActionListener {

    JButton btnReturn, btnBookAppt; // Header Buttons
    JButton btnStandingOrders, btnSecHolders; // 1st Column Buttons
    JButton btnHistory, btnNewTrans, btnBillPay; // 2nd Column Buttons
    JButton btnAccDetails, btnPendingBills; // 3rd Column Buttons

    public AccountMenuPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // I used BorderLayout for the header row
        JPanel topPanel = new JPanel(new BorderLayout());

        // Action buttons for the top panel
        btnReturn = new JButton("<- Return to Account Selection");
        topPanel.add(btnReturn, BorderLayout.WEST);
        btnBookAppt = new JButton("Book an Appointment");
        topPanel.add(btnBookAppt, BorderLayout.EAST);

        // Info of the account in the top panel
        JPanel infoPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        JLabel iban = new JLabel("IBAN: GR100000000000000001 (example 1st acc)", SwingConstants.CENTER);
        iban.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel balance = new JLabel("Account Balance: 1,245.50€ (example 1st acc)", SwingConstants.CENTER);
        balance.setFont(new Font("Arial", Font.BOLD, 16));
        infoPanel.add(iban);
        infoPanel.add(balance);
        // Add a center lining to centerline the info panel so it doesn't stretch too much
        JPanel centerLining = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerLining.add(infoPanel);
        topPanel.add(centerLining, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        // I used GridLayout with 1 row, 3 columns and gaps for the rest of the action buttons
        JPanel centerMenu = new JPanel(new GridLayout(1, 3, 20, 0)); 

        // First column
        JPanel col1 = new JPanel(new GridLayout(0, 1, 10, 10)); 
        btnStandingOrders = new JButton("Manage Standing Orders");
        btnSecHolders = new JButton("Manage Secondary Holders");
        col1.add(btnStandingOrders);
        col1.add(btnSecHolders);
        // Add empty labels to fill space if you don't want buttons to stretch full height
        col1.add(new JLabel("")); 
        
        // Second Column
        JPanel col2 = new JPanel(new GridLayout(0, 1, 10, 10));
        btnHistory = new JButton("View Transaction History");
        btnNewTrans = new JButton("New Transaction");
        btnBillPay = new JButton("Bill Payment");
        col2.add(btnHistory);
        col2.add(btnNewTrans);
        col2.add(btnBillPay);

        // Third and final column
        JPanel col3 = new JPanel(new GridLayout(0, 1, 10, 10));
        btnAccDetails = new JButton("View Account Details");
        btnPendingBills = new JButton("View Pending Bills");
        col3.add(btnAccDetails);
        col3.add(btnPendingBills);
        col3.add(new JLabel(""));

        centerMenu.add(col1);
        centerMenu.add(col2);
        centerMenu.add(col3);

        add(centerMenu, BorderLayout.CENTER);

        // events
        btnReturn.addActionListener(this);
        btnBillPay.addActionListener(this);
        btnPendingBills.addActionListener(this);
        btnHistory.addActionListener(this);
        btnAccDetails.addActionListener(this);
        btnBookAppt.addActionListener(this);
        btnNewTrans.addActionListener(this);
        btnSecHolders.addActionListener(this);
        btnStandingOrders.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnReturn) {
            AppMediator.getCardLayout().show(AppMediator.getCards(), "accounts");
        } else if (e.getSource() == btnBillPay) {
            AppMediator.getCardLayout().show(AppMediator.getCards(), "billPaymentPanel");
        } else if (e.getSource() == btnPendingBills) {
            AppMediator.getCardLayout().show(AppMediator.getCards(), "pendingBillsPanel");
        } else if (e.getSource() == btnHistory) {
            AppMediator.getCardLayout().show(AppMediator.getCards(), "transactionHistoryPanel");
        } else if (e.getSource() == btnAccDetails) {
            AppMediator.getCardLayout().show(AppMediator.getCards(), "accountDetails");
        } else if (e.getSource() == btnBookAppt) {
            AppMediator.getCardLayout().show(AppMediator.getCards(), "bookAppointment");
        } else if (e.getSource() == btnNewTrans) {
            AppMediator.getCardLayout().show(AppMediator.getCards(), "newTransaction");
        } else if (e.getSource() == btnSecHolders) {
            AppMediator.getCardLayout().show(AppMediator.getCards(), "manageSecHolders");
        } else if (e.getSource() == btnStandingOrders) {
            AppMediator.getCardLayout().show(AppMediator.getCards(), "manageStandOrders");
        }
    }
}
