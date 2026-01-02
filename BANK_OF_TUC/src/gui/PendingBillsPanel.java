package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class PendingBillsPanel extends JPanel implements ActionListener{
    // This was modeled after the OG AccountsPanel and it's almost the same
    private final JTable table;
    private final DefaultTableModel model;
    private final JButton btnView, btnClear, btnReturn;

    // Two Bill examples stored
    private final String[][] billDetails = {{   
            "COSMOTE",          // Issuer
            "ΝΙΚΟΣ ΠΑΠΠΑΣ",     // Recipient
            "CO20250501002",    // IBAN
            "€37.45",           // Amount
            "2026-02-01",       // Issue Date
            "RF10203041",       // RF Code
            "N/A",              // Frequency
            "N/A",              // Next Payment
            "N/A",              // Amount Per Bill
            "N/A"},             // Remaining Payments 
        { "VODAFONE GR", "ΝΙΚΟΣ ΠΑΠΠΑΣ", "VF20250415002", "€45.50", "2026-02-10", "RF10203046", "N/A", "N/A", "N/A", "N/A" }};

    public PendingBillsPanel() {
        setLayout(new BorderLayout(8, 8));
        setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        // Pending Bills Header
        JLabel title = new JLabel("Pending Bills");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 18f));
        add(title, BorderLayout.NORTH);

        // Bill data shown
        String[] cols = { "Issuer", "Amount", "RF Code" };
        
        // Data from test examples
        Object[][] data = {
            { "COSMOTE", "€37.45", "RF10203041" },
            { "VODAFONE GR", "€45.50", "RF10203046" }
        };

        model = new DefaultTableModel(data, cols) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(model);
        table.setFillsViewportHeight(true);
        
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Buttons
        JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnView = new JButton("View Details");
        btnClear = new JButton("Clear Selection");
        btnReturn = new JButton("Return to Account Dashboard");
        
        south.add(btnView);
        south.add(btnClear);
        south.add(btnReturn);
        add(south, BorderLayout.SOUTH);

        // events
        btnView.addActionListener(this);
        btnClear.addActionListener(this);
        btnReturn.addActionListener(this);
    }

    private void showBillDetails() {
        int viewRow = table.getSelectedRow();
        if (viewRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a bill to view.", "No selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int modelRow = table.convertRowIndexToModel(viewRow);

        // Retrieve the rest of the details
        String[] details = billDetails[modelRow];

        // Use the details found to open the dialog
        PendingBillDetailsDialog dialog = new PendingBillDetailsDialog(
                SwingUtilities.getWindowAncestor(this), 
                details[0], 
                details[1], 
                details[2], 
                details[3], 
                details[4], 
                details[5], 
                details[6], 
                details[7], 
                details[8], 
                details[9]  
        );
        dialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnReturn) {
            AppMediator.getCardLayout().show(AppMediator.getCards(), "accountMenu");
        } else if (e.getSource() == btnClear) {
            table.clearSelection();
        } else if (e.getSource() == btnView) {
            showBillDetails();
        }
    }
}
