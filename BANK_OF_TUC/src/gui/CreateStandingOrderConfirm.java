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

public class CreateStandingOrderConfirm extends JPanel implements ActionListener {
    private final JButton btnConfirm, btnCancel;

    private static JLabel name;
    private static JLabel amount;
    private static JLabel iban;
    private static JLabel recipient; 
    private static JLabel startDate;
    private static JLabel frequency;

    public CreateStandingOrderConfirm() {
        setLayout(new BorderLayout(0, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create Standing Order
        JLabel titleLabel = new JLabel("Create Standing Order");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Center form grid
        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.WEST;

        name = new JLabel("-");
        amount = new JLabel("-");
        iban = new JLabel("-");
        recipient = new JLabel("NIKOS PAPPAS"); // test
        startDate = new JLabel("-");
        frequency = new JLabel("-");
        addDetail(infoPanel, c, 0, "Standing Order Name:", name);
        addDetail(infoPanel, c, 1, "Payment Amount:", amount);
        addDetail(infoPanel, c, 2, "IBAN/RF Code:", iban);
        addDetail(infoPanel, c, 3, "Recipient Name:", recipient);
        addDetail(infoPanel, c, 4, "First Payment Date:", startDate);
        addDetail(infoPanel, c, 5, "Payment Frequency:", frequency);

        add(infoPanel, BorderLayout.CENTER);

        // Buttons on the bottom of the panel
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        btnCancel = new JButton("Cancel");
        btnConfirm = new JButton("Confirm");
        btnConfirm.setFont(new Font("Arial", Font.BOLD, 12));

        southPanel.add(btnCancel, BorderLayout.WEST);
        southPanel.add(btnConfirm, BorderLayout.EAST);

        add(southPanel, BorderLayout.SOUTH);

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

    // Function to set the details from the previous step
    public static void setDetails(String Name, String Amount, String Iban, String StartDate, String freq) {
        name.setText(Name);
        amount.setText(Amount);
        iban.setText(Iban);
        startDate.setText(StartDate);
        frequency.setText(freq);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            AppMediator.getCardLayout().show(AppMediator.getCards(), "createStandOrder");
        } else if (e.getSource() == btnConfirm) {
            // Create the row that holds the new order
            ManageStandingOrders.addOrder(name.getText(), amount.getText(), iban.getText(), startDate.getText(), frequency.getText());
            JOptionPane.showMessageDialog(this, "Standing Order Created Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            AppMediator.getCardLayout().show(AppMediator.getCards(), "manageStandOrders");
        }
    }
}
