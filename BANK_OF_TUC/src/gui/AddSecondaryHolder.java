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

public class AddSecondaryHolder extends JPanel implements ActionListener {

    private final JTextField afmField;
    private final JButton btnContinue, btnCancel;

    public AddSecondaryHolder() {
        setLayout(new BorderLayout(0, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add Secondary Holder Header
        JLabel titleLabel = new JLabel("Add Secondary Holder");
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
        form.add(new JLabel("ΑΦΜ of Potential Holder:"), c);
        c.gridx = 1;
        afmField = new JTextField(20);
        form.add(afmField, c);

        add(form, BorderLayout.CENTER);

        // Buttons at the bottom of hte panel
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
            afmField.setText("");
            AppMediator.getCardLayout().show(AppMediator.getCards(), "manageSecHolders");
        } 
        else if (e.getSource() == btnContinue) {
            String afm = afmField.getText().trim();
            if(afm.isEmpty()) {
                 JOptionPane.showMessageDialog(this, "Please enter an ΑΦΜ.", "Error", JOptionPane.WARNING_MESSAGE);
                 return;
            }

            // Pass data to the review panel
            AddSecondaryHolderConfirm.setDetails(afm);
            afmField.setText("");
            AppMediator.getCardLayout().show(AppMediator.getCards(), "confirmHolder");
        }
    }
}
