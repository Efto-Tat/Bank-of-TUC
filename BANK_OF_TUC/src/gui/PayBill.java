package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PayBill extends JPanel implements ActionListener {

    private final JButton btnCancel;
    private final JButton btnPay;

    public PayBill() {
        setLayout(new BorderLayout(0, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Bill information in the center of the panel
        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.WEST;
        
        // Test data, you should put the actual data harvested from the bill here
        addDetail(infoPanel, c, 0, "Payment Amount:", "€37.45");
        addDetail(infoPanel, c, 1, "RF Code:", "RF123456");
        addDetail(infoPanel, c, 2, "Issuer Name:", "Cosmote");
        addDetail(infoPanel, c, 3, "Issue Date:", "2026-02-01");
        addDetail(infoPanel, c, 4, "Recepient Name:", "ΝΙΚΟΣ ΠΑΠΠΑΣ");
        addDetail(infoPanel, c, 5, "Recepient IBAN:", "067688303");
        addDetail(infoPanel, c, 6, "Next Bill Payment:", "N/A");
        addDetail(infoPanel, c, 7, "Amount Per Bill:", "N/A");
        addDetail(infoPanel, c, 8, "Payment Frequency:", "N/A");
        addDetail(infoPanel, c, 9, "Remaining Payments:", "1");

        add(infoPanel, BorderLayout.CENTER);

        // The Buttons at the bottom of the panel
        JPanel buttonPanel = new JPanel(new BorderLayout());
        
        btnCancel = new JButton("Cancel");
        btnPay = new JButton("Pay Bill");
        buttonPanel.add(btnCancel, BorderLayout.WEST); 
        buttonPanel.add(btnPay, BorderLayout.EAST);    
        
        add(buttonPanel, BorderLayout.SOUTH);

        // events
        btnCancel.addActionListener(this);
        btnPay.addActionListener(this);
    }

    // Function to not repeat the information row additions
    private void addDetail(JPanel p, GridBagConstraints c, int row, String label, String value) {
        c.gridy = row;
        c.gridx = 0;
        c.weightx = 0.0;
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(lbl, c);

        c.gridx = 1;
        c.weightx = 1.0;
        JLabel val = new JLabel(value);
        val.setFont(new Font("Arial", Font.PLAIN, 14));
        p.add(val, c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            AppMediator.getCardLayout().show(AppMediator.getCards(), "billPaymentPanel");
        } 
        else if (e.getSource() == btnPay) {
            // Test success feedback, you should probably add here any data changes (not sure tho)
            JOptionPane.showMessageDialog(this, "Payment of 45.50€ to Amazon successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            AppMediator.getCardLayout().show(AppMediator.getCards(), "accountMenu");
        }
    }

}
