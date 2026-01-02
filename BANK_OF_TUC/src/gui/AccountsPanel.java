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
import javax.swing.table.DefaultTableModel;

class AccountsPanel extends JPanel implements ActionListener{
    private final JTable table;
    private final DefaultTableModel model;
    JButton enter, clearSel, closePan;
    
    public AccountsPanel() {
        setLayout(new BorderLayout(8,8));
        setBorder(BorderFactory.createEmptyBorder(16,16,16,16));

        JLabel title = new JLabel("My Accounts");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 18f));
        add(title, BorderLayout.NORTH);

        String[] cols = { "IBAN", "Type", "Balance (€)" };
        Object[][] data = {
            { "GR100000000000000001", "Primary", "1,245.50" },
            { "GR100000000000000002", "Primary", "3,010.00" },
            { "GR200000000000000001", "Secondary", "25,730.90" }
        };

        model = new DefaultTableModel(data, cols) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(model);
        table.setFillsViewportHeight(true);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        enter = new JButton("Enter");   // <-- NEW
        clearSel = new JButton("Clear Selection");
        closePan = new JButton("Close");
        south.add(enter);
        south.add(clearSel);
        south.add(closePan);
        add(south, BorderLayout.SOUTH);

        // events
        closePan.addActionListener(this);
        clearSel.addActionListener(this);
        enter.addActionListener(this);
    }

    // We enter the account selected
    private void enterAccount() {
        int viewRow = table.getSelectedRow();
        if (viewRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row first.", "No selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        AppMediator.getCardLayout().show(AppMediator.getCards(), "accountMenu");
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == closePan)
			AppMediator.getCardLayout().show(AppMediator.getCards(),"dashboard");
		else if (e.getSource() == clearSel)
			table.clearSelection();
		else if (e.getSource() == enter)
			enterAccount();
	}
}


