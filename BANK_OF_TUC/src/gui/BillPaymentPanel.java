package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

public class BillPaymentPanel extends JPanel implements ActionListener{

private final JTextField rfField = new JTextField(20); // 20 columns wide
    private final JButton btnSearchResult; 
    private final JButton btnReturn;
    private final JLabel result;

    public BillPaymentPanel() {
        // Similar to Log In Panel we  use a mix of borderlayout and gridbaglayout
        setLayout(new BorderLayout(0, 30)); 
        setBorder(BorderFactory.createEmptyBorder(16,16,16,16));

        // Pay Bill Header
        JLabel titleLabel = new JLabel("Pay Bill");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,10,10,10); 
        c.fill = GridBagConstraints.HORIZONTAL; // Stretch buttons to match width

        // Search Bill by RF code field
        c.gridx = 0; c.gridy = 0; 
        form.add(new JLabel("Search Bill by RF code:"), c);
        c.gridx = 1; c.gridy = 0; 
        form.add(rfField, c);

        // Result label
        result = new JLabel("Result:");
        result.setFont(new Font("Arial", Font.BOLD, 14));
        result.setVisible(false);
        c.gridwidth = 2; 
        c.gridx = 0; c.gridy = 1; form.add(result, c);

        // Bill result button
        btnSearchResult = new JButton("COSMOTE | ΝΙΚΟΣ ΠΑΠΠΑΣ | €37.45  | RF123456");
        btnSearchResult.setPreferredSize(new Dimension(450, 40)); 
        btnSearchResult.setVisible(false);
        c.gridwidth = 2; 
        c.gridx = 0; c.gridy = 2; form.add(btnSearchResult, c);
        // Add the form to the center of the panel
        add(form, BorderLayout.CENTER);

        // Bottom Panel for return button
        JPanel southPanel = new JPanel(new GridBagLayout()); 
        c = new GridBagConstraints();
        c.insets = new Insets(10, 0, 0, 0); 
        btnReturn = new JButton("Return to Account Dashboard");
        southPanel.add(btnReturn, c);
        // Add return to the bottom of the panel
        add(southPanel, BorderLayout.SOUTH);

        // events
        btnReturn.addActionListener(this);
        rfField.addActionListener(this);
        btnSearchResult.addActionListener(this);
    }

    private void performSearch() {
        String inputRF = rfField.getText().trim();
        
        // Testing data, you should connect the actual data here
        String testRF = "RF123456";
        
        if(inputRF.equals(testRF)) {
            result.setVisible(true);
            btnSearchResult.setVisible(true);
        } else {
            result.setVisible(false);
            btnSearchResult.setVisible(false);
            JOptionPane.showMessageDialog(this, "No bill found with RF Code: " + inputRF);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnReturn) {
            // Reset the form entirely when going back
            rfField.setText("");
            result.setVisible(false);
            btnSearchResult.setVisible(false);
            AppMediator.getCardLayout().show(AppMediator.getCards(), "accountMenu");
        } else if (e.getSource() == rfField) {
            performSearch();
        } else if (e.getSource() == btnSearchResult) {
            AppMediator.getCardLayout().show(AppMediator.getCards(), "payBill");
        }
    }
}
