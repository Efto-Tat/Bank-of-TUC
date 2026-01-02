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

public class AddSecondaryHolderConfirm extends JPanel implements ActionListener {
    private final JButton btnConfirm, btnCancel;
    
    private static JLabel name;
    private static JLabel afm;
    private static JLabel birthdate;

    public AddSecondaryHolderConfirm() {
        setLayout(new BorderLayout(0, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add Secondary Holder Header
        JLabel titleLabel = new JLabel("Add Secondary Holder");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Center form grid
        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.WEST;

        name = new JLabel("NIKOS GEORGIOU"); // Test Data
        afm = new JLabel("-");             // TODO: Obviously you'll have to search for a valid afm to enter the details
        birthdate = new JLabel("1995-05-20");      

        addDetail(infoPanel, c, 0, "Name:", name);
        addDetail(infoPanel, c, 1, "ΑΦΜ:", afm);
        addDetail(infoPanel, c, 2, "Date of Birth:", birthdate);

        add(infoPanel, BorderLayout.CENTER);

        // Buttons on the bottom
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
    
    // Function to set the details from the previous step
    public static void setDetails(String Afm) {
        afm.setText(Afm);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            AppMediator.getCardLayout().show(AppMediator.getCards(), "addHolder");
        } else if (e.getSource() == btnConfirm) {
            // Add the new holder
            String Name = name.getText();
            String Afm = afm.getText();
            String Birthdate = birthdate.getText();
            ManageSecondaryHolders.addHolder(Name, Afm, Birthdate);

            JOptionPane.showMessageDialog(this, "Secondary Holder Added Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            AppMediator.getCardLayout().show(AppMediator.getCards(), "manageSecHolders");
        }
    }
}
