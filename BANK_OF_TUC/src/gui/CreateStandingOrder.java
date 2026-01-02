package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CreateStandingOrder extends JPanel implements ActionListener {
    private final JTextField nameField;
    private final JTextField amountField;
    private final JTextField ibanField;
    private final JButton btnContinue, btnCancel;

    private final JComboBox<String> cbStartDay, cbStartMonth, cbStartYear;  // First Payment Date Boxes
    private final JComboBox<String> cbFreqDay, cbFreqMonth, cbFreqYear;     // Frequency Boxes (Duration)

    public CreateStandingOrder() {
        setLayout(new BorderLayout(0, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create Standing Order Header
        JLabel titleLabel = new JLabel("Create Standing Order");
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
        form.add(new JLabel("Standing Order Name:"), c);
        c.gridx = 1; 
        nameField = new JTextField(20);
        form.add(nameField, c);

        c.gridx = 0; c.gridy = 1; 
        form.add(new JLabel("Payment Amount:"), c);
        c.gridx = 1; 
        amountField = new JTextField(20);
        form.add(amountField, c);

        c.gridx = 0; c.gridy = 2;
        form.add(new JLabel("IBAN/RF Code of Recipient:"), c);
        c.gridx = 1; 
        ibanField = new JTextField(20);
        form.add(ibanField, c);

        c.gridx = 0; c.gridy = 3;
        form.add(new JLabel("First Payment Date:"), c);
        JPanel startDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        cbStartDay = new JComboBox<>(generateNumbers(1, 31));
        cbStartMonth = new JComboBox<>(generateNumbers(1, 12));
        cbStartYear = new JComboBox<>(generateNumbers(2026, 2030)); 
        startDatePanel.add(new JLabel("Day:")); startDatePanel.add(cbStartDay);
        startDatePanel.add(new JLabel("Month:")); startDatePanel.add(cbStartMonth);
        startDatePanel.add(new JLabel("Year:")); startDatePanel.add(cbStartYear);
        c.gridx = 1; 
        form.add(startDatePanel, c);

        c.gridx = 0; c.gridy = 4; 
        form.add(new JLabel("Payment Frequency:"), c);
        JPanel freqPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        cbFreqDay = new JComboBox<>(generateNumbers(1, 31)); 
        cbFreqMonth = new JComboBox<>(generateNumbers(0, 12)); // Can be 0
        cbFreqYear = new JComboBox<>(generateNumbers(0, 5));   // Can be 0
        freqPanel.add(new JLabel("Day:")); freqPanel.add(cbFreqDay);
        freqPanel.add(new JLabel("Month:")); freqPanel.add(cbFreqMonth);
        freqPanel.add(new JLabel("Year:")); freqPanel.add(cbFreqYear);
        c.gridx = 1; 
        form.add(freqPanel, c);

        add(form, BorderLayout.CENTER);

        // Buttons on the bottom of the panel
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
    
    // Generate numbers function (modified version of the ones in BookAppointment)
    private String[] generateNumbers(int start, int end) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            list.add(String.format("%02d", i));
        }
        return list.toArray(new String[0]);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            AppMediator.getCardLayout().show(AppMediator.getCards(), "manageStandOrders");
        } 
        else if (e.getSource() == btnContinue) {
            String name = nameField.getText();
            String amount = amountField.getText();
            String iban = ibanField.getText();
            if(name.isEmpty() || amount.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in Name and Amount.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String startDate = cbStartDay.getSelectedItem() + "/" + cbStartMonth.getSelectedItem() + "/" + cbStartYear.getSelectedItem();
            String freq = cbFreqYear.getSelectedItem() + "Y, " + cbFreqMonth.getSelectedItem() + "M, " + cbFreqDay.getSelectedItem() + "D";
            CreateStandingOrderConfirm.setDetails(name, amount, iban, startDate, freq);
            // Reset the panel's values
            nameField.setText("");
            amountField.setText("");
            ibanField.setText("");
            cbStartDay.setSelectedIndex(0);
            cbStartMonth.setSelectedIndex(0);
            cbStartYear.setSelectedIndex(0);
            cbFreqDay.setSelectedIndex(0);
            cbFreqMonth.setSelectedIndex(0);
            cbFreqYear.setSelectedIndex(0);
            AppMediator.getCardLayout().show(AppMediator.getCards(), "createStandOrderConfirm");
        }
    }
}
