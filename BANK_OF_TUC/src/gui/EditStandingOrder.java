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

public class EditStandingOrder extends JPanel implements ActionListener {
    private static JTextField nameField;
    private static JTextField amountField;
    private static JLabel ibanValue;
    private static JLabel recipientName;
    private static JComboBox<String> cbStartDay, cbStartMonth, cbStartYear;
    private static JComboBox<String> cbFreqDay, cbFreqMonth, cbFreqYear;
    private final JButton btnSave, btnCancel;
    
    // We need to know which row we are editing
    private static int staticRowIndex = -1;

    public EditStandingOrder() {
        setLayout(new BorderLayout(0, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Edit Standing Order Header
        JLabel titleLabel = new JLabel("Edit Standing Order");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Central form grid
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
        form.add(new JLabel("IBAN/RF Code:"), c);
        c.gridx = 1; 
        ibanValue = new JLabel("-"); // Label instead of field because not editable
        ibanValue.setFont(new Font("Arial", Font.BOLD, 14));
        form.add(ibanValue, c);

        c.gridx = 0; c.gridy = 3; 
        form.add(new JLabel("Recipient Name:"), c);
        c.gridx = 1; 
        recipientName = new JLabel("NIKOS PAPPAS"); // Label instead of field because not editable
        recipientName.setFont(new Font("Arial", Font.BOLD, 14));
        form.add(recipientName, c);

        c.gridx = 0; c.gridy = 4; 
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

        c.gridx = 0; c.gridy = 5; 
        form.add(new JLabel("Payment Frequency:"), c);
        JPanel freqPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        cbFreqDay = new JComboBox<>(generateNumbers(0, 31));
        cbFreqMonth = new JComboBox<>(generateNumbers(0, 12));
        cbFreqYear = new JComboBox<>(generateNumbers(0, 5));
        freqPanel.add(new JLabel("Day:")); freqPanel.add(cbFreqDay);
        freqPanel.add(new JLabel("Month:")); freqPanel.add(cbFreqMonth);
        freqPanel.add(new JLabel("Year:")); freqPanel.add(cbFreqYear);
        c.gridx = 1; 
        form.add(freqPanel, c);

        add(form, BorderLayout.CENTER);

        // Buttons at the bottom
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        btnCancel = new JButton("Cancel");
        btnSave = new JButton("Save Changes");
        btnSave.setFont(new Font("Arial", Font.BOLD, 12));
        
        southPanel.add(btnCancel, BorderLayout.WEST);
        southPanel.add(btnSave, BorderLayout.EAST);
        add(southPanel, BorderLayout.SOUTH);

        btnCancel.addActionListener(this);
        btnSave.addActionListener(this);
    }
    
    // Fill everything so we can edit it
    public static void setContext(int rowIndex, String name, String amount, String iban, String startDate, String freq) {
        staticRowIndex = rowIndex;
        
        nameField.setText(name);
        amountField.setText(amount.replace("€", "").trim()); // Remove € for editing
        ibanValue.setText(iban);
        
        // PARSE DATE: e.g "01/05/2026"
        try {
            String[] parts = startDate.split("/");
            cbStartDay.setSelectedItem(parts[0]);
            cbStartMonth.setSelectedItem(parts[1]);
            cbStartYear.setSelectedItem(parts[2]);
        } catch (Exception ignored) { }
        // PARSE FREQ: e.g "0Y, 1M, 0D"
        try {
            String[] parts = freq.split(",");
            // Extract number, parse to int, format to "00" style to match combo box items
            String y = String.format("%02d", Integer.parseInt(parts[0].trim().replace("Y", "")));
            String m = String.format("%02d", Integer.parseInt(parts[1].trim().replace("M", "")));
            String d = String.format("%02d", Integer.parseInt(parts[2].trim().replace("D", "")));
            cbFreqYear.setSelectedItem(y);
            cbFreqMonth.setSelectedItem(m);
            cbFreqDay.setSelectedItem(d);
        } catch (Exception ignored) { }
    }

    // The same one used in BookAppointment
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
            AppMediator.getCardLayout().show(AppMediator.getCards(), "standOrderDetails");
        } 
        else if (e.getSource() == btnSave) {
            String name = nameField.getText();
            String amount = amountField.getText();
            String newStartDate = cbStartDay.getSelectedItem() + "/" + cbStartMonth.getSelectedItem() + "/" + cbStartYear.getSelectedItem();
            String newFreq = cbFreqYear.getSelectedItem() + "Y, " + cbFreqMonth.getSelectedItem() + "M, " + cbFreqDay.getSelectedItem() + "D";

            ManageStandingOrders.updateOrder(staticRowIndex, name, amount, newStartDate, newFreq);
            JOptionPane.showMessageDialog(this, "Changes Saved!");
            AppMediator.getCardLayout().show(AppMediator.getCards(), "manageStandOrders"); 
        }
    }
}
