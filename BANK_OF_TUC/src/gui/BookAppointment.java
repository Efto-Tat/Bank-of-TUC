package gui;

import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BookAppointment extends JPanel implements ActionListener{
    private final JComboBox<String> cbDay, cbMonth, cbYear, cbTime;
    private final JButton btnConfirm, btnCancel;

    public BookAppointment() {
        setLayout(new BorderLayout(0, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Book an Appointment Header
        JLabel titleLabel = new JLabel("Book an Appointment with a Representative");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Center panel grid
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.WEST;

        c.gridx = 0; c.gridy = 0;
        form.add(new JLabel("Select a Date:"), c);

        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        
        String[] days = generateNumbers(1, 31);
        String[] months = generateNumbers(1, 12);
        String[] years = { "2026", "2027", "2028" };
        cbDay = new JComboBox<>(days);
        cbMonth = new JComboBox<>(months);
        cbYear = new JComboBox<>(years);

        datePanel.add(new JLabel("Day:"));
        datePanel.add(cbDay);
        datePanel.add(new JLabel("Month:"));
        datePanel.add(cbMonth);
        datePanel.add(new JLabel("Year:"));
        datePanel.add(cbYear);

        c.gridx = 1;
        form.add(datePanel, c);
        c.gridx = 0; c.gridy = 1;
        form.add(new JLabel("Select a Time:"), c);
        String[] times = generateTimes(9, 22);
        cbTime = new JComboBox<>(times);
        c.gridx = 1;
        form.add(cbTime, c);

        add(form, BorderLayout.CENTER);

        // Buttons at the bottom
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // Top spacing
        
        btnCancel = new JButton("Cancel");
        btnConfirm = new JButton("Confirm");

        southPanel.add(btnCancel, BorderLayout.WEST);
        southPanel.add(btnConfirm, BorderLayout.EAST);
        add(southPanel, BorderLayout.SOUTH);

        // events
        btnConfirm.addActionListener(this);
        btnCancel.addActionListener(this);
    }
    
    // Generate "01" to "31" (used for both months and days)
    private String[] generateNumbers(int start, int end) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            list.add(String.format("%02d", i)); 
        }
        return list.toArray(new String[0]);
    }

    // Generate "09:00" to "22:00"
    private String[] generateTimes(int startHour, int endHour) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = startHour; i <= endHour; i++) {
            list.add(String.format("%02d:00", i));
        }
        return list.toArray(new String[0]);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            AppMediator.getCardLayout().show(AppMediator.getCards(), "accountMenu");
        } else if (e.getSource() == btnConfirm) {
            String date = cbDay.getSelectedItem() + "/" + cbMonth.getSelectedItem() + "/" + cbYear.getSelectedItem();
            String time = (String) cbTime.getSelectedItem();
            JOptionPane.showMessageDialog(this, "Appointment Booked Successfully!\nDate: " + date + "\nTime: " + time, "Success", JOptionPane.INFORMATION_MESSAGE);
            
            AppMediator.getCardLayout().show(AppMediator.getCards(), "accountMenu");
        }
    }
}
