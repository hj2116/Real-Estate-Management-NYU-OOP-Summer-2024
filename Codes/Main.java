import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private PropertyManagement propertyManagement;
    private TenantManagement tenantManagement;
    private LeaseManagement leaseManagement;

    public Main() {
        propertyManagement = new PropertyManagement();
        tenantManagement = new TenantManagement();
        leaseManagement = new LeaseManagement(propertyManagement, tenantManagement);

        setTitle("Property Management System");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1));

        JButton propertyButton = new JButton("Manage Properties");
        JButton tenantButton = new JButton("Manage Tenants");
        JButton leaseButton = new JButton("Manage Leases");

        propertyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                propertyManagement.setVisible(true);
            }
        });

        tenantButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tenantManagement.setVisible(true);
            }
        });

        leaseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                leaseManagement.setVisible(true);
            }
        });

        add(propertyButton);
        add(tenantButton);
        add(leaseButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Main mainFrame = new Main();
                mainFrame.setVisible(true);
            }
        });
    }
}