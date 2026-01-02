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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class NewTransaction extends JPanel implements ActionListener{
    private final JTextField amountField;
    private final JTextField ibanField;
    private final JTextField reasonField;
    private final JButton btnContinue, btnCancel;

    public NewTransaction() {
        setLayout(new BorderLayout(0, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Transfer Money Header
        JLabel titleLabel = new JLabel("Transfer Money");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Center form grid
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0; c.gridy = 0;
        form.add(new JLabel("Payment Amount:"), c);
        c.gridx = 1;
        amountField = new JTextField(10); 
        form.add(amountField, c);

        c.gridx = 0; c.gridy = 1;
        form.add(new JLabel("IBAN of recipient:"), c);
        c.gridx = 1;
        ibanField = new JTextField(20); 
        form.add(ibanField, c);

        c.gridx = 0; c.gridy = 2;
        form.add(new JLabel("Reasoning (Optional):"), c);
        c.gridx = 1;
        reasonField = new JTextField(20);
        form.add(reasonField, c);

        add(form, BorderLayout.CENTER);

        // Buttons at the bottom of the panel
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); 

        btnCancel = new JButton("Cancel");
        btnContinue = new JButton("Continue");
        btnContinue.setFont(new Font("Arial", Font.BOLD, 12)); 

        southPanel.add(btnCancel, BorderLayout.WEST);
        southPanel.add(btnContinue, BorderLayout.EAST);

        add(southPanel, BorderLayout.SOUTH);

        // events
        btnCancel.addActionListener(this);
        btnContinue.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            // Clear fields on cancel 
            amountField.setText("");
            ibanField.setText("");
            reasonField.setText("");
            AppMediator.getCardLayout().show(AppMediator.getCards(), "accountMenu");
        } 
        else if (e.getSource() == btnContinue) {
            String amount = amountField.getText();
            String iban = ibanField.getText();
            String reason = reasonField.getText();
            
            if(amount.isEmpty() || iban.isEmpty()) {
                 JOptionPane.showMessageDialog(this, "Please fill in Amount and IBAN.", "Error", JOptionPane.WARNING_MESSAGE);
                 return;
            }

            // Send Data to transaction confirm panel
            NewTransactionConfirm.setDetails(amount, iban, reason);
            amountField.setText("");
            ibanField.setText("");
            reasonField.setText("");
            AppMediator.getCardLayout().show(AppMediator.getCards(), "newTransactionConfirm");
        }
    }
}
