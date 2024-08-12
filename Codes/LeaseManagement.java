import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class LeaseManagement extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField propertyIdField;
    private JTextField tenantIdField;
    private JTextField startDateField;
    private JTextField endDateField;
    private JTextField rentAmountField;
    private Map<Integer, Lease> leases = new HashMap<>();
    private int currentId = 1;
    private final String FILE_NAME = "leases.ser";

    public LeaseManagement(PropertyManagement propertyManagement, TenantManagement tenantManagement) {
        loadLeases();

        setTitle("Lease Management");
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
            new String[] {"ID", "Property Address", "Tenant Name", "Start Date", "End Date", "Rent Amount"}
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
        inputPanel.setBorder(BorderFactory.createTitledBorder("Lease Form"));
        contentPane.add(inputPanel, BorderLayout.NORTH);
        inputPanel.setLayout(new GridLayout(0, 2, 10, 10));

        JLabel lblPropertyId = new JLabel("Property ID:");
        inputPanel.add(lblPropertyId);

        propertyIdField = new JTextField();
        inputPanel.add(propertyIdField);
        propertyIdField.setColumns(10);

        JLabel lblTenantId = new JLabel("Tenant ID:");
        inputPanel.add(lblTenantId);

        tenantIdField = new JTextField();
        inputPanel.add(tenantIdField);
        tenantIdField.setColumns(10);

        JLabel lblStartDate = new JLabel("Start Date (yyyy-mm-dd):");
        inputPanel.add(lblStartDate);

        startDateField = new JTextField();
        inputPanel.add(startDateField);
        startDateField.setColumns(10);

        JLabel lblEndDate = new JLabel("End Date (yyyy-mm-dd):");
        inputPanel.add(lblEndDate);

        endDateField = new JTextField();
        inputPanel.add(endDateField);
        endDateField.setColumns(10);

        JLabel lblRentAmount = new JLabel("Rent Amount:");
        inputPanel.add(lblRentAmount);

        rentAmountField = new JTextField();
        inputPanel.add(rentAmountField);
        rentAmountField.setColumns(10);

        JButton btnCreateLease = new JButton("Create Lease");
        btnCreateLease.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createLease(propertyManagement, tenantManagement);
            }
        });
        inputPanel.add(btnCreateLease);

        JButton btnEditLease = new JButton("Edit Lease");
        btnEditLease.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editLease();
            }
        });
        inputPanel.add(btnEditLease);

        JButton btnUpdateLease = new JButton("Update Lease");
        btnUpdateLease.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateLease(propertyManagement, tenantManagement);
            }
        });
        inputPanel.add(btnUpdateLease);

        JButton btnDeleteLease = new JButton("Delete Lease");
        btnDeleteLease.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteLease();
            }
        });
        inputPanel.add(btnDeleteLease);

        populateTable(propertyManagement, tenantManagement);
    }

    private void createLease(PropertyManagement propertyManagement, TenantManagement tenantManagement) {
        int propertyId = Integer.parseInt(propertyIdField.getText());
        int tenantId = Integer.parseInt(tenantIdField.getText());
        String startDate = startDateField.getText();
        String endDate = endDateField.getText();
        double rentAmount = Double.parseDouble(rentAmountField.getText());

        Lease lease = new Lease(currentId++, propertyId, tenantId, startDate, endDate, rentAmount);
        leases.put(lease.getId(), lease);
        saveLeases();

        Property property = propertyManagement.readProperty(propertyId);
        Tenant tenant = tenantManagement.readTenant(tenantId);

        if (property != null && tenant != null) {
            Object[] rowData = {
                lease.getId(),
                property.getAddress(),
                tenant.getName(),
                lease.getStartDate(),
                lease.getEndDate(),
                lease.getRentAmount()
            };
            tableModel.addRow(rowData);
        } else {
            if (property == null) {
                System.err.println("Property with ID " + propertyId + " not found.");
            }
            if (tenant == null) {
                System.err.println("Tenant with ID " + tenantId + " not found.");
            }
        }

        clearFormFields();
    }

    private void editLease() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int leaseId = (int) tableModel.getValueAt(selectedRow, 0);
            Lease lease = leases.get(leaseId);

            if (lease != null) {
                propertyIdField.setText(String.valueOf(lease.getPropertyId()));
                tenantIdField.setText(String.valueOf(lease.getTenantId()));
                startDateField.setText(lease.getStartDate());
                endDateField.setText(lease.getEndDate());
                rentAmountField.setText(String.valueOf(lease.getRentAmount()));
            }
        }
    }

    private void updateLease(PropertyManagement propertyManagement, TenantManagement tenantManagement) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int leaseId = (int) tableModel.getValueAt(selectedRow, 0);

            int propertyId = Integer.parseInt(propertyIdField.getText());
            int tenantId = Integer.parseInt(tenantIdField.getText());
            String startDate = startDateField.getText();
            String endDate = endDateField.getText();
            double rentAmount = Double.parseDouble(rentAmountField.getText());

            Lease lease = new Lease(leaseId, propertyId, tenantId, startDate, endDate, rentAmount);
            leases.put(leaseId, lease);
            saveLeases();

            Property property = propertyManagement.readProperty(propertyId);
            Tenant tenant = tenantManagement.readTenant(tenantId);

            if (property != null && tenant != null) {
                tableModel.setValueAt(property.getAddress(), selectedRow, 1);
                tableModel.setValueAt(tenant.getName(), selectedRow, 2);
                tableModel.setValueAt(startDate, selectedRow, 3);
                tableModel.setValueAt(endDate, selectedRow, 4);
                tableModel.setValueAt(rentAmount, selectedRow, 5);
            } else {
                if (property == null) {
                    System.err.println("Property with ID " + propertyId + " not found.");
                }
                if (tenant == null) {
                    System.err.println("Tenant with ID " + tenantId + " not found.");
                }
            }

            clearFormFields();
        }
    }

    private void deleteLease() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int leaseId = (int) tableModel.getValueAt(selectedRow, 0);
            leases.remove(leaseId);
            saveLeases();
            tableModel.removeRow(selectedRow);
        }
    }

    private void populateTable(PropertyManagement propertyManagement, TenantManagement tenantManagement) {
        for (Lease lease : leases.values()) {
            Property property = propertyManagement.readProperty(lease.getPropertyId());
            Tenant tenant = tenantManagement.readTenant(lease.getTenantId());
            if (property != null && tenant != null) {
                Object[] rowData = {
                    lease.getId(),
                    property.getAddress(),
                    tenant.getName(),
                    lease.getStartDate(),
                    lease.getEndDate(),
                    lease.getRentAmount()
                };
                tableModel.addRow(rowData);
            } else {
                if (property == null) {
                    System.err.println("Property with ID " + lease.getPropertyId() + " not found.");
                }
                if (tenant == null) {
                    System.err.println("Tenant with ID " + lease.getTenantId() + " not found.");
                }
            }
        }
    }

    private void clearFormFields() {
        propertyIdField.setText("");
        tenantIdField.setText("");
        startDateField.setText("");
        endDateField.setText("");
        rentAmountField.setText("");
    }

    private void saveLeases() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(leases);
            oos.writeInt(currentId);
            oos.writeInt(currentId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadLeases() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            leases = (HashMap<Integer, Lease>) ois.readObject();
            currentId = ois.readInt();
        } catch (FileNotFoundException e) {
            // File not found, start with empty leases
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PropertyManagement propertyManagement = new PropertyManagement();
                    TenantManagement tenantManagement = new TenantManagement();
                    LeaseManagement frame = new LeaseManagement(propertyManagement, tenantManagement);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}