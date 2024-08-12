import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class TenantManagement extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField nameField;
    private JTextField contactInfoField;
    private Map<Integer, Tenant> tenants = new HashMap<>();
    private int currentId = 1;
    private final String FILE_NAME = "tenants.ser";

    public TenantManagement() {
        loadTenants();

        setTitle("Tenant Management");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 900, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        tableModel = new DefaultTableModel(
            new Object[][] {},
            new String[] {"ID", "Name", "Contact Info"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setModel(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(table);

        JPanel inputPanel = new JPanel();
        inputPanel.setBorder(BorderFactory.createTitledBorder("Tenant Form"));
        contentPane.add(inputPanel, BorderLayout.NORTH);
        inputPanel.setLayout(new GridLayout(0, 2, 10, 10));

        JLabel lblName = new JLabel("Name:");
        inputPanel.add(lblName);

        nameField = new JTextField();
        inputPanel.add(nameField);
        nameField.setColumns(10);

        JLabel lblContactInfo = new JLabel("Contact Info:");
        inputPanel.add(lblContactInfo);

        contactInfoField = new JTextField();
        inputPanel.add(contactInfoField);
        contactInfoField.setColumns(10);

        JButton btnCreateTenant = new JButton("Create Tenant");
        btnCreateTenant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createTenant();
            }
        });
        inputPanel.add(btnCreateTenant);

        JButton btnEditTenant = new JButton("Edit Tenant");
        btnEditTenant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editTenant();
            }
        });
        inputPanel.add(btnEditTenant);

        JButton btnUpdateTenant = new JButton("Update Tenant");
        btnUpdateTenant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateTenant();
            }
        });
        inputPanel.add(btnUpdateTenant);

        JButton btnDeleteTenant = new JButton("Delete Tenant");
        btnDeleteTenant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteTenant();
            }
        });
        inputPanel.add(btnDeleteTenant);

        populateTable();
    }

    private void createTenant() {
        String name = nameField.getText();
        String contactInfo = contactInfoField.getText();

        Tenant tenant = new Tenant(currentId++, name, contactInfo);
        tenants.put(tenant.getId(), tenant);
        saveTenants();

        Object[] rowData = {
            tenant.getId(),
            tenant.getName(),
            tenant.getContactInfo()
        };
        tableModel.addRow(rowData);

        clearFormFields();
    }

    private void editTenant() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int tenantId = (int) tableModel.getValueAt(selectedRow, 0);
            Tenant tenant = tenants.get(tenantId);

            if (tenant != null) {
                nameField.setText(tenant.getName());
                contactInfoField.setText(tenant.getContactInfo());
            }
        }
    }

    private void updateTenant() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int tenantId = (int) tableModel.getValueAt(selectedRow, 0);

            String name = nameField.getText();
            String contactInfo = contactInfoField.getText();

            Tenant tenant = new Tenant(tenantId, name, contactInfo);
            tenants.put(tenantId, tenant);
            saveTenants();

            tableModel.setValueAt(name, selectedRow, 1);
            tableModel.setValueAt(contactInfo, selectedRow, 2);

            clearFormFields();
        }
    }

    private void deleteTenant() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int tenantId = (int) tableModel.getValueAt(selectedRow, 0);
            tenants.remove(tenantId);
            saveTenants();
            tableModel.removeRow(selectedRow);
        }
    }

    private void populateTable() {
        for (Tenant tenant : tenants.values()) {
            Object[] rowData = {
                tenant.getId(),
                tenant.getName(),
                tenant.getContactInfo()
            };
            tableModel.addRow(rowData);
        }
    }

    private void clearFormFields() {
        nameField.setText("");
        contactInfoField.setText("");
    }

    public Tenant readTenant(int id) {
        return tenants.get(id);
    }

    private void saveTenants() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(tenants);
            oos.writeInt(currentId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTenants() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            tenants = (HashMap<Integer, Tenant>) ois.readObject();
            currentId = ois.readInt();
        } catch (FileNotFoundException e) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TenantManagement frame = new TenantManagement();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}