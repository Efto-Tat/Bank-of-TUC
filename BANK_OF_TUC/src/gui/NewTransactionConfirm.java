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
import javax.swing.SwingConstants;

public class NewTransactionConfirm extends JPanel implements ActionListener {
    private final JButton btnConfirm, btnCancel;
    
    // Static labels to update them from the previous screen
    private static JLabel amount;
    private static JLabel iban;
    private static JLabel recipient;
    private static JLabel reason;

    public NewTransactionConfirm() {
        setLayout(new BorderLayout(0, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Review Transaction Header
        JLabel titleLabel = new JLabel("Review Transaction");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Center form grid
        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.WEST;

        // Initialize labels
        amount = new JLabel("-");
        iban = new JLabel("-");
        recipient = new JLabel("ΝΙΚΟΣ ΠΑΠΠΑΣ"); // test name TODO: You should make a check with the IBAN to find the name
        reason = new JLabel("-");

        // Add Rows
        addDetail(infoPanel, c, 0, "Payment Amount:", amount);
        addDetail(infoPanel, c, 1, "IBAN of recipient:", iban);
        addDetail(infoPanel, c, 2, "Recipient Name:", recipient);
        addDetail(infoPanel, c, 3, "Reasoning:", reason);

        add(infoPanel, BorderLayout.CENTER);

        // 3. Bottom Buttons (Cancel Left, Confirm Right)
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        btnCancel = new JButton("Cancel");
        btnConfirm = new JButton("Confirm");
        btnConfirm.setFont(new Font("Arial", Font.BOLD, 12));

        southPanel.add(btnCancel, BorderLayout.WEST);
        southPanel.add(btnConfirm, BorderLayout.EAST);

        add(southPanel, BorderLayout.SOUTH);

        // events
        btnCancel.addActionListener(this);
        btnConfirm.addActionListener(this);
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
    
    // TODO: You'll probably use this for inputing data (of the recipient)
    public static void setDetails(String Am, String Ib, String Reas) {
        amount.setText(Am);
        iban.setText(Ib);
        reason.setText(Reas);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            // Return to the input screen to edit,the transaction
            AppMediator.getCardLayout().show(AppMediator.getCards(), "newTransaction");
        } 
        else if (e.getSource() == btnConfirm) {

            /* TODO: You should probably make a balance check here once you make it with data
            Also remove the money from the sender's account*/

            JOptionPane.showMessageDialog(this, "Transaction Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            AppMediator.getCardLayout().show(AppMediator.getCards(), "accountMenu");
        }
    }
}
