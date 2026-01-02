package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PauseStandingOrder extends JPanel implements ActionListener {

    private static JComboBox<String> cbDay, cbMonth, cbYear;
    private final JButton btnConfirm, btnCancel;
    private static int activeRowIndex;
    private static String currentNextDate; // We need this to add time to it!

    public PauseStandingOrder() {
        setLayout(new BorderLayout(0, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // No Header Layout
        JLabel lblMsg = new JLabel("Pause Time (Select 0 for Indefinite):");
        lblMsg.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblMsg, BorderLayout.NORTH);

        // Center form grid
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        JPanel durPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        cbYear = new JComboBox<>(generateNumbers(0, 1)); // Year (Max 1)
        cbMonth = new JComboBox<>(generateNumbers(0, 12));
        cbDay = new JComboBox<>(generateNumbers(0, 31));
        durPanel.add(new JLabel("Year:")); durPanel.add(cbYear);
        durPanel.add(new JLabel("Month:")); durPanel.add(cbMonth);
        durPanel.add(new JLabel("Day:")); durPanel.add(cbDay);
        form.add(durPanel, c);

        add(form, BorderLayout.CENTER);

        // Buttons at the bottom part
        JPanel southPanel = new JPanel(new BorderLayout());
        btnCancel = new JButton("Cancel");
        btnConfirm = new JButton("Confirm");
        southPanel.add(btnCancel, BorderLayout.WEST);
        southPanel.add(btnConfirm, BorderLayout.EAST);
        add(southPanel, BorderLayout.SOUTH);

        // events
        btnCancel.addActionListener(this);
        btnConfirm.addActionListener(this);
    }

    public static void setContext(int row, String nextDate) {
        activeRowIndex = row;
        currentNextDate = nextDate;
        // Reset boxes to 0
        cbYear.setSelectedIndex(0);
        cbMonth.setSelectedIndex(0);
        cbDay.setSelectedIndex(0);
    }

    private String[] generateNumbers(int start, int end) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            list.add(String.valueOf(i));
        }
        return list.toArray(new String[0]);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            AppMediator.getCardLayout().show(AppMediator.getCards(), "standOrderDetails");
        } 
        else if (e.getSource() == btnConfirm) {
            int y = Integer.parseInt((String) cbYear.getSelectedItem());
            int m = Integer.parseInt((String) cbMonth.getSelectedItem());
            int d = Integer.parseInt((String) cbDay.getSelectedItem());

            if (y == 0 && m == 0 && d == 0) {
                // Indefinite
                ManageStandingOrders.pauseOrderIndefinitely(activeRowIndex);
                JOptionPane.showMessageDialog(this, "Standing Order Paused Indefinitely.");
            } else {
                // Calculate new date = CurrentNextDate + Duration
                try {
                    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    // If current status is already "PAUSED" or invalid, fallback to fixed date
                    LocalDate baseDate;
                    try {
                        baseDate = LocalDate.parse(currentNextDate, fmt);
                    } catch (Exception ex) {
                         baseDate = LocalDate.of(2026, 2, 4); // Fallback
                    }
                    LocalDate newDate = baseDate.plusYears(y).plusMonths(m).plusDays(d);
                    String newDateStr = newDate.format(fmt);

                    ManageStandingOrders.pauseOrderTimed(activeRowIndex, newDateStr);
                    JOptionPane.showMessageDialog(this, "Standing Order Paused Until " + newDateStr);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            AppMediator.getCardLayout().show(AppMediator.getCards(), "manageStandOrders");
        }
    }

}
