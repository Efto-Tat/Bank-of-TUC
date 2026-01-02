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

public class ManageStandingOrders extends JPanel implements ActionListener {
    // The layout is very similar to Secondary Holders
    private final JTable table;
    private final DefaultTableModel model;
    private final JButton btnCreate, btnSelect, btnClear, btnReturn;
    private static DefaultTableModel staticModel; // Used for changes in the table
    private static ArrayList<String> startDates; 
    private static ArrayList<String> frequencies;
    private static ArrayList<String> statuses;

    public ManageStandingOrders() {
        setLayout(new BorderLayout(0, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Manage Standing Orders Header
        JLabel titleLabel = new JLabel("Manage Standing Orders");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Center table layout
        JPanel centerPanel = new JPanel(new BorderLayout(0, 10));
        JLabel lblInstruct = new JLabel("Select Standing Order:");
        lblInstruct.setFont(new Font("Arial", Font.BOLD, 14));
        centerPanel.add(lblInstruct, BorderLayout.NORTH);
        // Table columns
        String[] cols = { "Standing Order Name", "Payment Amount", "RF Code" };
        // Test data
        Object[][] data = {
            { "GYM", "45.00€", "RF1122334455" },
            { "RENT", "450.00€", "RF9988776655" }
        };
        model = new DefaultTableModel(data, cols) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        // List initialization
        startDates = new ArrayList<>(Arrays.asList("01/01/2026", "05/04/2026"));
        frequencies = new ArrayList<>(Arrays.asList("0Y, 1M, 31D", "0Y, 2M, 0D"));
        statuses = new ArrayList<>(Arrays.asList("ACTIVE", "ACTIVE"));
        staticModel = model;
        table = new JTable(model);
        table.setFillsViewportHeight(true);
        centerPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        // Bottom buttons and their positioning
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        btnCreate = new JButton("Create Standing Order");
        southPanel.add(btnCreate, BorderLayout.WEST);
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
        btnCreate.addActionListener(this);
        btnSelect.addActionListener(this);
        btnClear.addActionListener(this);
        btnReturn.addActionListener(this);
    }

    // Function to add data from different classes
    public static void addOrder(String name, String amount, String rf, String startDate, String freq) {
        if (staticModel != null) {
            staticModel.addRow(new Object[]{name, amount + "€", rf});
            startDates.add(startDate);
            frequencies.add(freq);
            statuses.add("ACTIVE");
        }
    }

    // Function to delete standing orders
    public static void deleteOrder(int rowIndex) {
        if (staticModel != null && rowIndex >= 0 && rowIndex <= staticModel.getRowCount()) {
            staticModel.removeRow(rowIndex);
            if (rowIndex < startDates.size()) {
                startDates.remove(rowIndex);
                frequencies.remove(rowIndex);
                statuses.remove(rowIndex);
            }
            // TODO: Here there should be code to delete from the data
        }
    }

    // Function to update details of an order
    public static void updateOrder(int row, String name, String amount, String startDate, String freq) {
        if (staticModel != null && row >= 0 && row <= staticModel.getRowCount()) {
            staticModel.setValueAt(name, row, 0);
            staticModel.setValueAt(amount + "€", row, 1);
            while (startDates.size() <= row) startDates.add("01/01/2026"); // Default date for safety
            while (frequencies.size() <= row) frequencies.add("0Y, 1M, 0D"); // Default frequency for safety
            while (statuses.size() <= row) statuses.add("ACTIVE"); // Default status for safety
            startDates.set(row, startDate);
            frequencies.set(row, freq);
            // TODO: It should update the rest of the data too, we must fix this
        }
    }

    // Pause the order indefinitely
    public static void pauseOrderIndefinitely(int row) {
        if (row < statuses.size()) {
            statuses.set(row, "PAUSED");
        }
    }

    // Pause order for a set period of time
    public static void pauseOrderTimed(int row, String newStartDate) {
        if (row < startDates.size()) {
            startDates.set(row, newStartDate); // Update the start date to the future date
            statuses.set(row, "ACTIVE");       // Make sure that the order is active
        }
    }

    // Restore order
    public static void restoreOrder(int row) {
        if (row < statuses.size()) {
            statuses.set(row, "ACTIVE");    // Unpause the time calculation of the order
            startDates.set(row, "04/02/2026"); 
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnReturn) {
            AppMediator.getCardLayout().show(AppMediator.getCards(), "accountMenu");
        } else if (e.getSource() == btnClear) {
            table.clearSelection();
        } else if (e.getSource() == btnSelect) {
            int viewRow = table.getSelectedRow();
            if (viewRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a standing order first.");
                return;
            }
            int row = table.convertRowIndexToModel(viewRow);
            String name = (String) model.getValueAt(row, 0);
            String amount = (String) model.getValueAt(row, 1);
            String rf = (String) model.getValueAt(row, 2);
            String start = "01/01/2026"; 
            String freq = "0Y, 1M, 0D";
            String status = "ACTIVE"; 
            if (row < startDates.size()) {
                start = startDates.get(row);
                freq = frequencies.get(row);
                status = statuses.get(row);
            }
            StandingOrderDetails.setContext(model, row, name, amount, rf, start, freq, status);
            AppMediator.getCardLayout().show(AppMediator.getCards(), "standOrderDetails");
        } 
        else if (e.getSource() == btnCreate) {
            AppMediator.getCardLayout().show(AppMediator.getCards(), "createStandOrder");
        }
    }
}
