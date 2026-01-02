package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class StandingOrderDetails extends JPanel implements ActionListener {
    private final JButton btnEdit, btnDelete, btnPause, btnRestore, btnCancel;
    
    // Static context to know which order we are looking at
    private static DefaultTableModel staticModel;
    private static int staticRowIndex;
    private static String rawStartDate;
    private static String rawFreq;
    private static String currentStatus;
    private static JLabel name, amount, iban, recipientName, nextDate, frequency;
    
    // Formatter for DD/MM/YYYY
    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public StandingOrderDetails() {
        setLayout(new BorderLayout(0, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel with no Header
        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.WEST;

        name = new JLabel("-");
        amount = new JLabel("-");
        iban = new JLabel("-");
        recipientName = new JLabel("NIKOS PAPPAS"); 
        nextDate = new JLabel("-");
        frequency = new JLabel("-");
        addDetail(infoPanel, c, 0, "Standing Order Name:", name);
        addDetail(infoPanel, c, 1, "Payment Amount:", amount);
        addDetail(infoPanel, c, 2, "IBAN/RF Code of Recipient:", iban);
        addDetail(infoPanel, c, 3, "Name of Recipient:", recipientName);
        addDetail(infoPanel, c, 4, "Next Payment Date:", nextDate);
        addDetail(infoPanel, c, 5, "Payment Frequency:", frequency);

        add(infoPanel, BorderLayout.CENTER);

        // All 5 buttons at the bottom
        JPanel southPanel = new JPanel(new BorderLayout(0, 15));
        southPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        JPanel actionGrid = new JPanel(new GridLayout(2, 2, 10, 10));   
        btnEdit = new JButton("Edit Standing Order");
        btnDelete = new JButton("Delete Standing Order");
        btnPause = new JButton("Pause Standing Order");
        btnRestore = new JButton("Restore Standing Order");
        actionGrid.add(btnEdit);
        actionGrid.add(btnDelete);
        actionGrid.add(btnPause);
        actionGrid.add(btnRestore);
        southPanel.add(actionGrid, BorderLayout.CENTER);
        btnCancel = new JButton("Cancel");
        JPanel cancelWrapper = new JPanel(); 
        cancelWrapper.add(btnCancel);
        southPanel.add(cancelWrapper, BorderLayout.SOUTH);

        add(southPanel, BorderLayout.SOUTH);

        // events
        btnEdit.addActionListener(this);
        btnDelete.addActionListener(this);
        btnPause.addActionListener(this);
        btnRestore.addActionListener(this);
        btnCancel.addActionListener(this);
    }
    
    public static void setContext(DefaultTableModel model, int row, String Name, String Amount, String rf, String startDate, String freq, String status) {
        staticModel = model;
        staticRowIndex = row;
        rawStartDate = startDate;
        rawFreq = freq;
        currentStatus = status;
        name.setText(Name);
        amount.setText(Amount);
        iban.setText(rf);
        frequency.setText(freq);
        
        // CALCULATE NEXT PAYMENT
        if ("PAUSED".equals(status)) { //If indefinitely paused then no date for next payment
            nextDate.setText("Indefinitely Paused");
        } else {
            String NextDate = calculateNextPayment(startDate, freq);
            nextDate.setText(NextDate);
        }
    }

    // DATE CALCULATION FUNCTION 
    private static String calculateNextPayment(String startStr, String freqStr) {
        try {
            LocalDate start = LocalDate.parse(startStr, DATE_FMT);
            LocalDate now = LocalDate.now(); // Current date 
            
            // Parse Frequency "0Y, 0M, 0D"
            String[] parts = freqStr.split(","); 
            int years = Integer.parseInt(parts[0].trim().replace("Y", ""));
            int months = Integer.parseInt(parts[1].trim().replace("M", ""));
            int days = Integer.parseInt(parts[2].trim().replace("D", ""));

            LocalDate next = start;
            
            // If the start date is in the past, keep adding frequency until we pass today
            while (!next.isAfter(now)) {
                next = next.plusYears(years).plusMonths(months).plusDays(days);
            }
            
            return next.format(DATE_FMT);

        } catch (Exception e) {
            e.printStackTrace();
            return startStr; // Fallback if parsing fails
        }
    }

    private void addDetail(JPanel p, GridBagConstraints c, int row, String label, JLabel valueLabel) {
        c.gridy = row;
        c.gridx = 0;
        c.weightx = 0.0;
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(lbl, c);
        c.gridx = 1;
        c.weightx = 1.0;
        valueLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        p.add(valueLabel, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            AppMediator.getCardLayout().show(AppMediator.getCards(), "manageStandOrders");
        } else if (e.getSource() == btnDelete) {
            ManageStandingOrders.deleteOrder(staticRowIndex);
            JOptionPane.showMessageDialog(this, "Standing Order Deleted Successfully.");
            AppMediator.getCardLayout().show(AppMediator.getCards(), "manageStandOrders");

        } else if (e.getSource() == btnPause) {
            if ("PAUSED".equals(currentStatus)) {
                JOptionPane.showMessageDialog(this, "Order is already paused indefinitely.");
                return;
            }
            PauseStandingOrder.setContext(staticRowIndex, nextDate.getText());
            AppMediator.getCardLayout().show(AppMediator.getCards(), "pauseStandOrder");
        }else if (e.getSource() == btnRestore) {
            ManageStandingOrders.restoreOrder(staticRowIndex);
            JOptionPane.showMessageDialog(this, "Standing Order Restored.\nNext Payment reset to Today.");
            AppMediator.getCardLayout().show(AppMediator.getCards(), "manageOrders");
        } else if (e.getSource() == btnEdit) {
            EditStandingOrder.setContext(staticRowIndex, name.getText(), amount.getText(), iban.getText(), rawStartDate, rawFreq);
            AppMediator.getCardLayout().show(AppMediator.getCards(), "editStandOrder");
        }
    }
}
