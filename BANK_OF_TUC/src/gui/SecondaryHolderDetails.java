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
import javax.swing.table.DefaultTableModel;

public class SecondaryHolderDetails extends JPanel implements ActionListener {

    private final JButton btnRemove, btnReturn;
    
    private static DefaultTableModel activeModel;
    private static int activeRowIndex;
    private static JLabel name;
    private static JLabel afm;
    private static JLabel birthdate;

    public SecondaryHolderDetails() {
        setLayout(new BorderLayout(0, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Secondary Holder Info Header
        JLabel titleLabel = new JLabel("Secondary Holder Information");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Center form grid
        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.WEST;

        name = new JLabel("-");
        afm = new JLabel("-");
        birthdate = new JLabel("-");
        addDetail(infoPanel, c, 0, "Name:", name);
        addDetail(infoPanel, c, 1, "ΑΦΜ:", afm);
        addDetail(infoPanel, c, 2, "Date of Birth:", birthdate);

        add(infoPanel, BorderLayout.CENTER);

        // Buttons at the bottom
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        btnRemove = new JButton("Remove Secondary Holder");
        southPanel.add(btnRemove, BorderLayout.WEST);
        btnReturn = new JButton("Return");
        southPanel.add(btnReturn, BorderLayout.EAST);

        add(southPanel, BorderLayout.SOUTH);

        // events
        btnRemove.addActionListener(this);
        btnReturn.addActionListener(this);
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

    // Static method to receive the holder's data AND the table model reference
    public static void setContext(DefaultTableModel model, int rowIndex, String Name, String Afm, String Birthdate) {
        activeModel = model;
        activeRowIndex = rowIndex;
        name.setText(Name);
        afm.setText(Afm);
        birthdate.setText(Birthdate);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnReturn) {
            AppMediator.getCardLayout().show(AppMediator.getCards(), "manageHolders");
        } 
        else if (e.getSource() == btnRemove) {
                // Remove the row from the previous table
                if (activeModel != null && activeRowIndex >= 0) {
                    activeModel.removeRow(activeRowIndex);
                }
                JOptionPane.showMessageDialog(this, "Secondary Holder removed successfully.");
                AppMediator.getCardLayout().show(AppMediator.getCards(), "manageSecHolders");
        }
    }
    
}
