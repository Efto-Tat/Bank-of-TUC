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
import javax.swing.JPanel;
import javax.swing.SwingConstants;

class AccountDetailsPanel extends JPanel implements ActionListener {

    private final JButton btnReturn;
    public AccountDetailsPanel() {
        setLayout(new BorderLayout(0, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Account Details Header
        JLabel titleLabel = new JLabel("Account Details");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Center Info Grid
        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.WEST; 

        // Test information
        addDetail(infoPanel, c, 0, "Primary Holder Name:", "ΝΙΚΟΣ ΠΑΠΠΑΣ");
        addDetail(infoPanel, c, 1, "Account IBAN:", "GR1234567890"); 
        addDetail(infoPanel, c, 2, "Account Balance:", "--€"); 
        addDetail(infoPanel, c, 3, "Date of Birth:", "1976-05-07");
        addDetail(infoPanel, c, 4, "ΑΦΜ:", "067688303");

        add(infoPanel, BorderLayout.CENTER);

        // Return Button in the bottom of the panel
        JPanel southPanel = new JPanel(new GridBagLayout());
        c = new GridBagConstraints();
        c.insets = new Insets(20, 0, 0, 0); 
        
        btnReturn = new JButton("Return to Account Dashboard");
        southPanel.add(btnReturn, c);
        
        add(southPanel, BorderLayout.SOUTH);

        // events
        btnReturn.addActionListener(this);
    }

    // To add each row without copying and pasting
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
        if (e.getSource() == btnReturn) {
            AppMediator.getCardLayout().show(AppMediator.getCards(), "accountMenu");
        }
    }
}

