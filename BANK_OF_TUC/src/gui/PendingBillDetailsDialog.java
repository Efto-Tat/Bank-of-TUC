package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PendingBillDetailsDialog extends JDialog{
    // Modeled after Account Details Dialog
    public PendingBillDetailsDialog(Window owner, String issuer, String recepient, String IBAN, String amount, String date, String rf, String frequency, String nextPayment, String amountPerBill, String remPayments) {
        super(owner, "Bill Details", ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Main Layout
        JPanel content = new JPanel(new BorderLayout(0, 20));
        content.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Center Info Grid
        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);
        c.anchor = GridBagConstraints.WEST;

        // Add details rows
        addDetail(infoPanel, c, 0, "Payment Amount:", amount);
        addDetail(infoPanel, c, 1, "RF Code:", rf);
        addDetail(infoPanel, c, 2, "Issuer Name:", issuer);
        addDetail(infoPanel, c, 3, "Issue Date:", date);
        addDetail(infoPanel, c, 4, "Recepient Name:", recepient);
        addDetail(infoPanel, c, 5, "Recepient IBAN:", IBAN);
        addDetail(infoPanel, c, 6, "Next Bill Payment:", nextPayment);
        addDetail(infoPanel, c, 7, "Amount Per Bill:", amountPerBill);
        addDetail(infoPanel, c, 8, "Payment Frequency:", frequency);
        addDetail(infoPanel, c, 9, "Remaining Payments:", remPayments);

        content.add(infoPanel, BorderLayout.CENTER);

        // Bottom Button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnClose = new JButton("Close");
        buttonPanel.add(btnClose);
        
        content.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(content);
        pack();
        setLocationRelativeTo(owner); 

        btnClose.addActionListener(e -> dispose());
    }

    // To add each row without copying and pasting
    private void addDetail(JPanel p, GridBagConstraints c, int row, String label, String value) {
        c.gridy = row;
        c.gridx = 0;
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(lbl, c);

        c.gridx = 1;
        JLabel val = new JLabel(value);
        val.setFont(new Font("Arial", Font.PLAIN, 14));
        p.add(val, c);
    }
}
