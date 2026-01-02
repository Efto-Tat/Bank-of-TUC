package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class ManageSecondaryHolders extends JPanel implements ActionListener{
    private final JTable table;
    private final DefaultTableModel model;
    private final JButton btnAdd, btnSelect, btnClear, btnReturn;
    private static DefaultTableModel staticModel;
    private static ArrayList<String> birthdates;

    public ManageSecondaryHolders() {
        setLayout(new BorderLayout(0, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Manage Secondary Holders Header
        JLabel titleLabel = new JLabel("Manage Secondary Holders");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Center form layout
        JPanel centerPanel = new JPanel(new BorderLayout(0, 10));
        JLabel lblInstruct = new JLabel("Select Secondary Holder:");
        lblInstruct.setFont(new Font("Arial", Font.BOLD, 14));
        centerPanel.add(lblInstruct, BorderLayout.NORTH);

        // Table columns
        String[] cols = { "Name", "ΑΦΜ" };
        // Test data
        Object[][] data = {
            { "ΓΙΩΡΓΟΣ ΧΑΛΚΙΑΔΑΚΗΣ", "067688304" },
            { "ΜΑΡΙΑ ΤΕΡΑΝΟΒΑ","067688305" }
        };
        birthdates = new ArrayList<>(Arrays.asList("1985-04-12", "1990-11-23")); // test for birthdates

        model = new DefaultTableModel(data, cols) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        staticModel = model;
        table = new JTable(model);
        table.setFillsViewportHeight(true);
        
        centerPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        // Bottom buttons and their positioning
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        btnAdd = new JButton("Add Secondary Holder");
        southPanel.add(btnAdd, BorderLayout.WEST);
        JPanel centerButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnSelect = new JButton("Select");
        btnClear = new JButton("Clear Selection");
        centerButtons.add(btnSelect);
        centerButtons.add(btnClear);
        southPanel.add(centerButtons, BorderLayout.CENTER);
        btnReturn = new JButton("Return to Account Dashboard");
        southPanel.add(btnReturn, BorderLayout.EAST);

        add(southPanel, BorderLayout.SOUTH);

        // events
        btnAdd.addActionListener(this);
        btnSelect.addActionListener(this);
        btnClear.addActionListener(this);
        btnReturn.addActionListener(this);
    }

    // Function to add data from different classes
    public static void addHolder(String name, String afm, String birthdate) {
        if (staticModel != null) {
            staticModel.addRow(new Object[]{name, afm});
            birthdates.add(birthdate); // Keep the DOB list in sync!
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnReturn) {
            AppMediator.getCardLayout().show(AppMediator.getCards(), "accountMenu");
        } else if (e.getSource() == btnClear) {
            table.clearSelection();
        } else if (e.getSource() == btnSelect) {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select a holder first.");
                return;
            }

            // Get the information so they can be deleted if needed
            int modelRow = table.convertRowIndexToModel(row);
            String name = (String) model.getValueAt(modelRow, 0);
            String afm = (String) model.getValueAt(modelRow, 1);
            String birthdate = "N/A"; 
            if(modelRow < birthdates.size()) {
                birthdate = birthdates.get(modelRow);
            }
            SecondaryHolderDetails.setContext(model, modelRow, name, afm, birthdate);
            AppMediator.getCardLayout().show(AppMediator.getCards(), "secHolderDetails");
        } else if (e.getSource() == btnAdd) {
            AppMediator.getCardLayout().show(AppMediator.getCards(), "addHolder");
        }
    }
}
